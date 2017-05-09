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
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    private func checkAllFilled() -> Bool{
        return (nameField.text != "" && emailField.text != "" && passwordField.text != "" && confirmPasswordField.text != "" && typeField.text != "")
    }
    
    private func validateEmail(candidate: String) -> Bool {
        let emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        return NSPredicate(format: "SELF MATCHES %@", emailRegex).evaluate(with: candidate)
    }

    private func checkFields() -> Bool{
        let emailIsValid = validateEmail(candidate: emailField.text!)
        guard emailIsValid else{
            displayMessage(msg: "Invalid email")
            return false
        }
        guard passwordField.text == confirmPasswordField.text else{
            displayMessage(msg: "Passwords do not match")
            return false
        }
        return true
    }
    
    private func getCurrentDate() -> String{
        let now = Date()
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        formatter.timeZone = TimeZone(identifier: "pt_BR")
        return formatter.string(from: now)
        
    }
    
    private func NewUser() -> User {
        let newUser = User(user: emailField.text!, password: passwordField.text!)
        
        newUser.setName(name: nameField.text!)
        newUser.setType(type: typeField.text!)
        newUser.setID(id: numberOfUsers + 1)
        newUser.setCreatedAt(createdAt: getCurrentDate())
        newUser.setCreatedAt(createdAt: getCurrentDate())
        
        return newUser
    }
    
    private func setupHeaders() -> HTTPHeaders{
        let token = UserDefaults.standard.value(forKey: "user_auth_token")!
        let headers: HTTPHeaders = [
            "Authorization": token as! String
        ]
        return headers
    }
    
    private func setupParameters(newUser: User) -> Parameters{
        let par: Parameters = [
            "name": newUser.getName(),
            "email": newUser.getUser(),
            "password": newUser.getPassword(),
            "type": newUser.getType()
        ]
        return par
    }
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
        
        let newUser = NewUser()
        
        makePostRequest(newUser: newUser)
        
    }
    
    private func makePostRequest(newUser: User){
        let headers = setupHeaders()
        let parameters = setupParameters(newUser: newUser)
        
        Alamofire.request("https://tq-template-node.herokuapp.com/user",method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: headers).responseJSON { response in
            
            switch response.result {
                case .success:
                    self.displayMessage(msg: "New user successfully created!")
                case .failure:
                    self.displayMessage(msg: "Request couldn't be handled!")
            }
            
        }
    
    }
    

    private func displayMessage(msg: String){
        let myAlert = UIAlertController(title: "Alert", message: msg, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil)
        myAlert.addAction(okAction)
        self.present(myAlert, animated: true, completion: nil)
    }
    
   
}
