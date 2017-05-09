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
        type <- map["type"]
        createdAt <- map["createdAt"]
        updatedAt <- map["updatedAt"]
    }
    
    func setUser(user: String){self.user = user}
    func setPassword(password: String){self.password = password}
    func setID(id: Int){self.id = id}
    func setName(name: String){self.name = name}
    func setType(type: String){self.type = type}
    func setCreatedAt(createdAt: String){self.createdAt = createdAt}
    func setUpdatedAt(updatedAt: String){self.updatedAt = updatedAt}
    
    func getUser() -> String{return self.user}
    func getPassword() -> String{return self.password}
    func getID() -> Int{return self.id}
    func getName() -> String{return self.name}
    func getType() -> String{return self.type}
    func getCreatedAt() -> String{return self.createdAt}
    func getUpdatedAt() -> String{return self.updatedAt}
    
}
