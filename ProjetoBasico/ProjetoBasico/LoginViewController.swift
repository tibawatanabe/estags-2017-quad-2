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
        
        usernameField.text = "admin@taqtile.com"
        passwordField.text = "1111"
    }
    @IBAction func loginPressed(_ sender: AnyObject) -> Void {
        
        let user = usernameField.text
        let password = passwordField.text
        
        // https://tq-template-node.herokuapp.com
        
        if(user == "" || password == ""){
            displayMessage(msg: "Please fill in all required fields")
        }
        
        let userAtual = User(user: user!, password: password!)
        
        let par = userAtual.toRequestParams()
        
        Alamofire.request("https://tq-template-node.herokuapp.com/authenticate", method: .post, parameters: par, encoding: JSONEncoding.default).responseJSON { response in
            
            switch response.result {
            case let .success(JSON):
                if let data = (JSON as! NSDictionary).value(forKey: "data"){
                    let userResponse = UserResponse(JSONString: String(data: response.data!, encoding: String.Encoding.utf8)!)
                    self.displayMessage(msg: "Login ok!")
                    //                  let token = (data as! NSDictionary).value(forKey: "token")!
                    
                    if let token = userResponse?.token{
                        UserDefaults.standard.set(token, forKey: "user_auth_token")
                    }
                }
                else if let errors = (JSON as! NSDictionary).value(forKey: "errors"){
                    let errorResponse = ErrorResponse(JSONString: String(data: response.data!, encoding: String.Encoding.utf8)!)
                    self.displayMessage(msg: (errorResponse?.errors.first?.message)!)
                }

            case let .failure(error):
                self.displayMessage(msg: "Please check your connection")
            }
        }

    }
    func displayMessage(msg: String){
        let myAlert = UIAlertController(title: "Alert", message: msg, preferredStyle: UIAlertControllerStyle.alert)
        
        let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil)
        
        myAlert.addAction(okAction)
        
        self.present(myAlert, animated: true, completion: nil)
    }

}
