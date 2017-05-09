//
//  Pagination.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/8/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper

class Pagination: Mappable{
    
    var page: Int = 0
    var window: Int = 0
    
    init(page: Int, window: Int) {
        self.page = page
        self.window = window
    }
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        page <- map["page"]
        window <- map["window"]
    }
    
}
