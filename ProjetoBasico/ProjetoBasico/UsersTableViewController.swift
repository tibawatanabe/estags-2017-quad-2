//
//  UsersTableViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit
import Alamofire

class UsersTableViewController: UITableViewController {
    
    var users = [User]() {
        didSet {
            tableView.reloadData()
        }
    }
    
    var totalUsers: Int = 0
    
    private func setupPage() {
        self.navigationItem.hidesBackButton = true
    }
    
    private func setupHeaders() -> HTTPHeaders{
        let token = UserDefaults.standard.value(forKey: "user_auth_token")!
        let headers: HTTPHeaders = [
            "Authorization": token as! String
        ]
        return headers
    }
    
    private func setupParameters() -> Parameters{
        let pagination = Pagination(page : 1, window: 30)
        
        guard let pagString = pagination.toJSONString() else{
            return ["pagination" : ""]
        }
        return ["pagination" : pagString]
    }
    
    private func displayMessage(msg: String){
        let myAlert = UIAlertController(title: "Alert", message: msg, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil)
        myAlert.addAction(okAction)
        self.present(myAlert, animated: true, completion: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupPage()
        let headers = setupHeaders()
        let parameters = setupParameters()
        
        Alamofire.request("https://tq-template-node.herokuapp.com/users",method: .get, parameters: parameters,                          encoding: URLEncoding(destination: .queryString), headers: headers).responseJSON { response in
            
            guard let responseData = response.data else {
                self.displayMessage(msg: "Request couldn't be handled!")
                return
            }
            guard let responseString = String(data: responseData, encoding: String.Encoding.utf8) else {
                self.displayMessage(msg: "Invalid response.data")
                return
            }
            
            switch response.result {
                case .success(_):
                    if let listResponse = ListResponse(JSONString: responseString){
                        self.users = listResponse.data
                        self.totalUsers = listResponse.total
                    }
                case let .failure(error):
                    self.displayMessage(msg: "Unable to retrieve list! \(error)")
            }
        }
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.users.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "UserCell", for: indexPath)
        cell.textLabel?.text = users[indexPath.row].name
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if segue.identifier == "ShowUser" {
            
            let userVC = segue.destination as? DetalUserViewController
            guard let cell = sender as? UITableViewCell,
                let indexPath = tableView.indexPath(for: cell) else{
                    return
            }
            userVC?.user = users[indexPath.row]
        
        }
        else if segue.identifier == "CreateUser" {
            
            let createVC = segue.destination as? CreateUserViewController
            createVC?.numberOfUsers = self.totalUsers
            
            
            
        }
        
    }
}
