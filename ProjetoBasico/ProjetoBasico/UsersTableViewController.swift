//
//  UsersTableViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit

class UsersTableViewController: UITableViewController {
    var users: [User]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.hidesBackButton = true
        
        let user1 = User()
        let user2 = User()
        let user3 = User()
        
        user1.name = "Paula"
        user1.age = "19"
        user1.username = "pypasquao"
        
        user2.name = "Leonardo"
        user2.age = "19"
        user2.username = "borges"
        
        user3.name = "Bruno"
        user3.age = "19"
        user3.username = "nishimoto"
        
        
        users = [user1, user2, user3]
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let u = users{
            return u.count
        }
        return 0
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "UserCell", for: indexPath)
        
        let user = users?[indexPath.row]
        
        if let u = user{
            cell.textLabel?.text = u.name
        }
        
        
        
        
        return cell
    }

}
