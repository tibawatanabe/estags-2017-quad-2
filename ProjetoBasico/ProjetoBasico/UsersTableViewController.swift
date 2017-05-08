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
    
    var users = [User]()
    var page_count: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.hidesBackButton = true
        
        // Recupera token de autenticacao
        let token = UserDefaults.standard.value(forKey: "user_auth_token")!
        // Coloca o token na header
        let headers: HTTPHeaders = [
            "Authorization": token as! String
        ]
        // Cria objeto pagination com os parametros desejados
        let pagination = Pagination(page : 1, window: 7)
        // Converte objeto em uma String JSON
        let pagString = pagination.toJSONString(prettyPrint: false)
        // Seta parametros para query
        let par: Parameters = [
            "pagination" : pagString!
        ]
        
        Alamofire.request("https://tq-template-node.herokuapp.com/users",
                          method: .get,
                          parameters: par,
                          encoding: URLEncoding(destination: .queryString),
                          headers: headers).responseJSON { response in
            switch response.result {
                case let .success(JSON):
                    print(JSON)
                    if let listResponse = ListResponse(JSONString: String(data: response.data!, encoding: String.Encoding.utf8)!){
                        self.page_count = listResponse.data.count
                        for i in 0..<self.page_count{
                            let user = listResponse.data[i]
                            self.users.append(user)
                            debugPrint(self.users)
                        }
                        self.tableView.reloadData()
                    }
                case let .failure(error):
                    print(error)
            }
        }
        
        
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (self.page_count)
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "UserCell", for: indexPath)
        
        cell.textLabel?.text = users[indexPath.row].name
        
//        if let u = user{
//            cell.textLabel?.text = u.name
//        }
        
        
        
        
        return cell
    }

}
