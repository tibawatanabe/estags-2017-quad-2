//
//  CreateUserViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/9/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit
import Alamofire

class CreateUserViewController: UIViewController {
    
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var emailField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var confirmPasswordField: UITextField!
    @IBOutlet weak var typeField: UITextField!
    
    var numberOfUsers: Int = 0
    
    // MARK: Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    // MARK: ACTION
    @IBAction func donePressed(_ sender: AnyObject) {
        guard checkAllFilled() else {
            displayMessage(msg: "Please fill in all required fields")
            return
        }
        guard checkFields() else {
            return
        }
        
        let newUser = createUser()
        makePostRequest(newUser: newUser)
    }
    
    // MARK: other
    private func checkAllFilled() -> Bool {
        return (
                nameField.text            != "" &&
                emailField.text           != "" &&
                passwordField.text        != "" &&
                confirmPasswordField.text != "" &&
                typeField.text            != ""
        )
    }
    
    private func checkFields() -> Bool {
        let emailIsValid = validateEmail(candidate: emailField.text!)
        guard emailIsValid else {
            displayMessage(msg: "Invalid email")
            return false
        }
        guard passwordField.text == confirmPasswordField.text else{
            displayMessage(msg: "Passwords do not match")
            return false
        }
        return true
    }
    
    private func validateEmail(candidate: String) -> Bool {
        let emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        return NSPredicate(format: "SELF MATCHES %@", emailRegex).evaluate(with: candidate)
    }
    
    private func createUser() -> User {
        let newUser = User(user: emailField.text!, password: passwordField.text!)
        newUser.setup(name: nameField.text!, type: typeField.text!, id: numberOfUsers + 1)
        
        return newUser
    }
    
    // MARK: request
    private func makePostRequest(newUser: User) {
        let headers = setupHeaders()
        let parameters = setupParameters(newUser: newUser)
        
        Alamofire.request("https://tq-template-node.herokuapp.com/user",
                          method: .post,
                          parameters: parameters,
                          encoding: JSONEncoding.default,
                          headers: headers
            ).responseJSON { response in
                
                switch response.result {
                case .success:
                    self.displayMessage(msg: "New user successfully created!")
                case .failure:
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
    
    private func setupParameters(newUser: User) -> Parameters {
        let par: Parameters = [
            "name": newUser.name,
            "email": newUser.email,
            "password": newUser.password,
            "type": newUser.type
        ]
        return par
    }
    
}
