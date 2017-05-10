//
//  LoginViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit
import Alamofire
import ObjectMapper

class LoginViewController: UIViewController {

    @IBOutlet weak var usernameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    private func currentUser() -> User {
        let user = usernameField.text
        let password = passwordField.text
        let userAtual = User(user: user!, password: password!)
        return userAtual
    }
    
    private func makePostRequest(currentUser: User) {
        let par = currentUser.toRequestParams()
        
        Alamofire.request("https://tq-template-node.herokuapp.com/authenticate",
                          method: .post,
                          parameters: par,
                          encoding:JSONEncoding.default
            ).responseJSON { response in
                
                switch response.result {
                case let .success(JSON):
                    guard let JSON = JSON as? NSDictionary, let responseData = response.data else {
                        self.displayMessage(msg: "Request couldn't be handled!")
                        return
                    }
                    guard let responseString = String(data: responseData, encoding: String.Encoding.utf8) else {
                        self.displayMessage(msg: "Invalid response.data")
                        return
                    }
                    
                    let hasAnyData = JSON.value(forKey: "data") != nil
                    let hasAnyError = JSON.value(forKey: "errors") != nil
                    if hasAnyData {
                        let userResponse = UserResponse(JSONString: responseString)
                        if let token = userResponse?.token {
                            UserDefaults.standard.set(token, forKey: "user_auth_token")
                        }
                        self.navigateToUserList()
                    } else if hasAnyError {
                        guard let errorMessage = ErrorResponse(JSONString: responseString)?.errors.first?.message else {
                            return
                        }
                        self.displayMessage(msg: errorMessage)
                    }
                case let .failure(error):
                    self.displayMessage(msg: "Please check your connection \(error)")
                }
        }
    
    }
    
    @IBAction func loginPressed(_ sender: AnyObject) -> Void {
        let currentUser = self.currentUser()
        
        let allFieldsAreFilled = currentUser.email != "" && currentUser.password != ""
        guard allFieldsAreFilled else {
            displayMessage(msg: "Please fill in all required fields")
            return
        }
        
        makePostRequest(currentUser: currentUser)
    }
    
    private func navigateToUserList() {
        performSegue(withIdentifier: "UsersTableViewController", sender: self)
    }
    
    private func displayMessage(msg: String) {
        let myAlert = UIAlertController(title: "Alert", message: msg, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil)
        myAlert.addAction(okAction)
        self.present(myAlert, animated: true, completion: nil)
    }

}
