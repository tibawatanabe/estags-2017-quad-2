//
//  User.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/4/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper

class User : Mappable{
    var user: String = ""
    var password: String = ""
    var id: Int = 0
    var name: String = ""
    var email: String = ""
    var type: String = ""
    var createdAt: String = ""
    var updatedAt: String = ""
    
     required init?(map: Map) {}
    
    init(user: String, password: String) {
        self.user = user
        self.password = password
    }
    
    func toRequestParams() -> Parameters{
        let parameters: Parameters = [
                    "user": self.user,
                    "password" : self.password
        ]
        return parameters
    }
    
    func mapping(map: Map) {
        user <- map["user"]
        password <- map["password"]
        id <- map["id"]
        name <- map["name"]
        email <- map["email"]
        type <- map["type"]
        createdAt <- map["createdAt"]
        updatedAt <- map["updatedAt"]
    }
    
}
