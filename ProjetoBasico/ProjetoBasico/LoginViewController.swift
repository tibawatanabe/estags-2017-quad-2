//
//  LoginViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit

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
        
        if (username == "Paula" && password == "1234"){
            print("Ok!")
        }
        else {
            print("Fail")
        }
    }

}
