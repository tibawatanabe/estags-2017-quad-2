//
//  DetailUserViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/10/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit

class DetailUserViewController: UIViewController {

    @IBOutlet weak var UserName: UITextField!
    @IBOutlet weak var UserID: UITextField!
    @IBOutlet weak var UserEmail: UITextField!
    @IBOutlet weak var UserCreatedAt: UITextField!
    @IBOutlet weak var UserUpdatedAt: UITextField!
    
    var user: User?
    
    override func viewWillAppear(_ animated: Bool) {
        if let u = user {
            UserName.text = u.name
            UserID.text = String(u.id)
            UserEmail.text = u.email
            UserCreatedAt.text = u.createdAt
            UserUpdatedAt.text = u.updatedAt
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "EditUser" {
            let editVC = segue.destination as? EditUserViewController
            editVC?.user = user
        }
    }

}
