//
//  ErrorResponse.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/5/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import ObjectMapper

class ErrorResponse: Mappable{ 
    
    var errors: [ErrorResponseData] = []
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        errors <- map["errors"]
    }

    
    class ErrorResponseData: Mappable {
    
        var name: String = ""
        var defaultMessage: String = ""
        var message: String = ""
        
        required init?(map: Map) {}
        
        func mapping(map: Map) {
            name <- map["name"]
            defaultMessage <- map["defaultMessage"]
            message <- map["message"]
        }
    }

}
