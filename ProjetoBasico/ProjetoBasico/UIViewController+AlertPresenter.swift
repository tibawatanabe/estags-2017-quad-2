//
//  UIViewController+AlertPresenter.swift
//  ProjetoBasico
//
//  Created by Taqtile on 5/10/17.
//  Copyright © 2017 Taqtile. All rights reserved.
//

import Foundation
import UIKit

extension UIViewController {
    func displayMessage(msg: String) {
        let myAlert = UIAlertController(title: "Alert", message: msg, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil)
        myAlert.addAction(okAction)
        self.present(myAlert, animated: true, completion: nil)
    }
}
