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

class User : Mappable {
    var email: String = ""
    var password: String = ""
    var id: Int = 0
    var name: String = ""
    var type: String = ""
    var createdAt: String = ""
    var updatedAt: String = ""
    
    required init?(map: Map) {}
    
    init(user: String, password: String) {
        self.email = user
        self.password = password
        self.createdAt = getCurrentDate()
        self.updatedAt = getCurrentDate()
    }
    
    func toRequestParams() -> Parameters {
        let parameters: Parameters = [
                    "user": self.email,
                    "password" : self.password
        ]
        return parameters
    }
    
    func mapping(map: Map) {
        email <- map["email"]
        password <- map["password"]
        id <- map["id"]
        name <- map["name"]
        type <- map["type"]
        createdAt <- map["createdAt"]
        updatedAt <- map["updatedAt"]
    }
    
    private func getCurrentDate() -> String {
        let now = Date()
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        formatter.timeZone = TimeZone(abbreviation: "BRT")
        return formatter.string(from: now)
        
    }
    
    func setup(name: String, type: String, id: Int) {
        self.name = name
        self.type = type
        self.id = id
    }
    
    func update(name: String, email: String) {
        self.name = name
        self.email = email
        self.updatedAt = getCurrentDate()
    }
    
}
