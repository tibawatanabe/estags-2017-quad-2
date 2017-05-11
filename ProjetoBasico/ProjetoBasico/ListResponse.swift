//
//  ListResponse.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/8/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import ObjectMapper

class ListResponse: Mappable {
    
    var data: [User] = []
    var page: Int = 0
    var window: Int = 0
    var total: Int = 0
    var totalPages: Int = 0
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        data <- map["data"]
        page <- map["pagination.page"]
        window <- map["pagination.window"]
        total <- map["pagination.total"]
        totalPages <- map["pagination.totalPages"]
    }
    
}
