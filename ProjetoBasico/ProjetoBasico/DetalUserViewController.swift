//
//  DetalUserViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/8/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit

class DetalUserViewController: UIViewController {

    @IBOutlet weak var UserName: UITextField!
    @IBOutlet weak var UserID: UITextField!
    @IBOutlet weak var UserEmail: UITextField!
    @IBOutlet weak var UserCreatedAt: UITextField!
    @IBOutlet weak var UserUpdatedAt: UITextField!
    
    var user: User?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        if let u = user{
            UserName.text = u.getName()
            UserID.text = String(u.getID())
            UserEmail.text = u.getEmail()
            UserCreatedAt.text = u.getCreatedAt()
            UserUpdatedAt.text = u.getUpdatedAt()
        }
    
    }

}
