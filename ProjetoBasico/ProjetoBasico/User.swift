//
//  User.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import Alamofire

class User {
    var user: String = ""
    var password: String = ""
    
    func toRequestParams() -> Parameters{
        let parameters: Parameters = [
                    "user": self.user,
                    "password" : self.password
        ]
        return parameters
    }

    func setUser(user: String){
        self.user = user
    }
    func setPassword(password: String){
        self.password = password
    }
    func getUser() -> String{
        return self.user
    }
    func getPassword() -> String{
        return self.password
    }
    
}
