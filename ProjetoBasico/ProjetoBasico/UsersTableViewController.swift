//
//  UsersTableViewController.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import UIKit
import Alamofire

class UserCell: UITableViewCell{
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var idField: UITextField!
    @IBOutlet weak var createdAtField: UITextField!
    @IBOutlet weak var updatedAtField: UITextField!
}

class UsersTableViewController: UITableViewController {

    var users = [User]() {
        didSet {
            tableView.reloadData()
        }
    }
    
    var totalUsers: Int = 0
    var windowSize: Int = 0
    
    // MARK: Lifecycle
    override func viewWillAppear(_ animated: Bool) {
        setupPage()
        self.windowSize = 8
        makeGetRequest()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    // MARK: tableView
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.users.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "UserCell", for: indexPath) as! UserCell
        
        cell.nameField?.text = users[indexPath.row].name
        cell.idField?.text = String(users[indexPath.row].id)
        cell.createdAtField?.text = users[indexPath.row].createdAt
        cell.updatedAtField?.text = users[indexPath.row].updatedAt
        
        cell.layer.borderWidth = 1.0
        cell.layer.borderColor = UIColor(red:0.58, green:0.47, blue:0.40, alpha:1.0).cgColor
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        let lastElement = users.count - 1
        if (indexPath.row == lastElement && self.windowSize < self.totalUsers) {
            self.windowSize += 5
            makeGetRequest()
        }
    }
    
    // MARK: segue
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ShowUser" {
            guard let cell      = sender as? UITableViewCell,
                let indexPath = tableView.indexPath(for: cell)
                else { return }
            
            let userVC = segue.destination as? DetailUserViewController
            userVC?.user = users[indexPath.row]
        }
        else if segue.identifier == "CreateUser" {
            let createVC = segue.destination as? CreateUserViewController
            createVC?.numberOfUsers = self.totalUsers
        }
    }
    
    // MARK: others
    private func setupPage() {
        self.navigationItem.hidesBackButton = true
    }
    
    // MARK: request
    private func makeGetRequest() {
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
                if let listResponse = ListResponse(JSONString: responseString) {
                    self.users = listResponse.data
                    self.totalUsers = listResponse.total
                }
            case let .failure(error):
                self.displayMessage(msg: "Unable to retrieve list! \(error)")
            }
        }
    }
    
    private func setupHeaders() -> HTTPHeaders{
        let token = UserDefaults.standard.value(forKey: "user_auth_token")!
        let headers: HTTPHeaders = [
            "Authorization": token as! String
        ]
        return headers
    }
    
    private func setupParameters() -> Parameters{
        let pagination = Pagination(page: 1, window: self.windowSize)
        
        guard let pagString = pagination.toJSONString() else {
            return ["pagination" : ""]
        }
        return ["pagination" : pagString]
    }
    
}
