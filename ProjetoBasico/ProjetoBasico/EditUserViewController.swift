//
//  EditUserViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/9/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit
import Alamofire

class EditUserViewController: UIViewController {

    @IBOutlet weak var newNameField: UITextField!
    @IBOutlet weak var newEmailField: UITextField!
    
    var user: User?
    
    // MARK: Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        setupPage()
    }
    
    // MARK: ACTIONS
    @IBAction func donePressed(_ sender: AnyObject) {
        let allFieldsAreFilled = checkAllFilled()
        guard allFieldsAreFilled else {
            displayMessage(msg: "Please fill in all required fields")
            return
        }
        
        let allFieldsAreValid = checkFields()
        guard allFieldsAreValid else {
            return
        }
        
        if let u = self.user {
            u.update(name: newNameField.text!, email: newEmailField.text!)
            makePostRequest(updatedUser: u)
        }
    }
    
    // MARK: other
    private func setupPage() {
        newNameField.text  = user?.name
        newEmailField.text = user?.email
    }
    
    
    private func checkAllFilled() -> Bool {
        return (
            newNameField.text  != "" &&
            newEmailField.text != ""
        )
    }
    
    private func checkFields() -> Bool {
        let emailIsValid = validateEmail(candidate: newEmailField.text!)
        guard emailIsValid else{
            displayMessage(msg: "Invalid email")
            return false
        }
        return true
    }
    
    private func validateEmail(candidate: String) -> Bool {
        let emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        return NSPredicate(format: "SELF MATCHES %@", emailRegex).evaluate(with: candidate)
    }
    
    // MARK: request
    private func makePostRequest(updatedUser: User) {
        let headers = setupHeaders()
        let parameters = setupParameters(updatedUser: updatedUser)
        let url = "https://tq-template-node.herokuapp.com/user/" + String(updatedUser.id)
        
        Alamofire.request(url,
                          method: .put,
                          parameters: parameters,
                          encoding: JSONEncoding.default,
                          headers: headers
            ).responseJSON { response in
            
            switch response.result {
            case let .success(JSON):
                print(JSON)
                self.displayMessage(msg: "User successfully updated!")
            case let .failure(error):
                print(error)
                self.displayMessage(msg: "Request couldn't be handled!")
            }
        }
    }
    
    private func setupHeaders() -> HTTPHeaders {
        let token = UserDefaults.standard.value(forKey: "user_auth_token")!
        let headers: HTTPHeaders = [
            "Authorization": token as! String
        ]
        return headers
    }
    
    private func setupParameters(updatedUser: User) -> Parameters {
        let par: Parameters = [
            "name":  updatedUser.name,
            "email": updatedUser.email
        ]
        return par
    }
}
