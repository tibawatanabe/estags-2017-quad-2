//
//  UserResponse.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/5/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import ObjectMapper

class UserResponse: Mappable {
    var token: String = ""
    var id: Int = 0
    var name: String = ""
    var email: String = ""
    var type: String = ""
    var createdAt: String = ""
    var updatedAt: String = ""
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        token <- map["data.token"]
        id <- map["data.user.id"]
        name <- map["data.user.name"]
        email <- map["data.user.email"]
        type <- map["data.user.type"]
        createdAt <- map["data.user.createdAt"]
        updatedAt <- map["data.user.updatedAt"]
    }

}
