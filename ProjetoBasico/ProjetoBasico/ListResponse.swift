//
//  ListResponse.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/8/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import ObjectMapper

class ListResponse: Mappable{
    
    var data: [User] = []
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        data <- map["data"]
    }
    
}
