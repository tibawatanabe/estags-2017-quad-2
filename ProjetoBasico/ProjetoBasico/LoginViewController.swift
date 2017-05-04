//
//  LoginViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit
import Alamofire

class LoginViewController: UIViewController {

    @IBOutlet weak var usernameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    @IBAction func loginPressed(_ sender: AnyObject) -> Void {
        
        let username = usernameField.text
        let password = passwordField.text
        
        // Alamofire.request("API") // enviando o login senha
        Alamofire.request("https://httpbin.org/get").responseJSON { response in
            print(response.request)  // original URL request
            print(response.response) // HTTP URL response
            print(response.data)     // server data
            print(response.result)   // result of response serialization
            
            if let JSON = response.result.value {
                print("JSON: \(JSON)")
            }
        }
        
        if(username == "" || password == ""){
            displayMessage(msg: "Please fill in all required fields")
        }
        else if (username == "pypasquao" && password == "1234"){
            print("Ok!")
        }
        else {
            displayMessage(msg: "Username and password do not match")
        }
    }
    func displayMessage(msg: String){
        let myAlert = UIAlertController(title: "Alert", message: msg, preferredStyle: UIAlertControllerStyle.alert)
        
        let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil)
        
        myAlert.addAction(okAction)
        
        self.present(myAlert, animated: true, completion: nil)
    }

}
