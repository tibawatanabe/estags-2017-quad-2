import {Router, Request, Response, NextFunction} from 'express';
import * as _ from 'lodash';
import * as fs from 'fs';

var jwt = require('jsonwebtoken');
var mongoose = require('mongoose');

const User = require ('../User');

mongoose.connect('mongodb://localhost:27017')

class ErrorNotFound extends Error{}
class ErrorAlreadyExist extends Error{}
class ErrorWrongPassword extends Error{}
class ErrorPermissionDenied extends Error{}

export class UserRouter {
  router: Router
  /**
   * Initialize the UserRouter
   */
  constructor() {
    this.router = Router();
    this.init();
  }

  /**
   * GET all Users.
   */
  public getAll(req: Request, res: Response, next: NextFunction) {

    User.find()
        .then(users => _.map(users, user => {
          return {id: user._id,
                  name: user.name}
        }))
        .then(datas => res.json(datas))
        .catch(err => res.json({message: 'Error during find users.'}))
  }

  /**
   * GET details of one User by id
   */
  public getOne(req: Request, res: Response, next: NextFunction) {
    let token = req.headers['token'];
    let decoded = jwt.decode(token, 'secret');

    User.findById(req.params.id)
        .then(user => {
          if (req.params.id !== decoded.id) {
            throw new ErrorPermissionDenied();
          }
          return user;
        })
        .then(user => res.json({name: user.name,
                        email: user.email,
                        createdAt: user.createdAt,
                        updatedAt: user.updatedAt}))
        .catch(err => {
          if (err instanceof ErrorPermissionDenied) {
            res.json({message: 'You can`t see others details.'});
          }
          else {
            res.json({message: 'User not found.'})
          }
        });
  }

  /**
   * CREATE one User
   */
  public createOne(req: Request, res: Response, next: NextFunction) {

    let currentDate = new Date();
    let newUser = new User({name: req.body.name,
                            email: req.body.email,
                            password: req.body.password,
                            createdAt: currentDate,
                            updatedAt: currentDate});

    User.findOne({name: req.body.name})
        .then(user => {
          if (user) {
            throw new ErrorAlreadyExist();
          }
          else {
            return user;
          }
        })
        .then(user => newUser.save())
        .then(user => res.json({message: 'Sucess',
                        name: newUser.name,
                        email: newUser.email,
                        createdAt: currentDate,
                        updatedAt: currentDate}))
        .catch(err => {
          if (err instanceof ErrorAlreadyExist) {
            res.json({message: 'User already exists.'});
          }
          else {
            res.json({message: 'Error during creating user.'});
          }
        }); 
  }

  /**
   * DELETE one User
   */
  public deleteOne (req: Request, res: Response, next: NextFunction) {

    let token = req.headers['token'];
    let decoded = jwt.decode(token, 'secret');

    User.remove({_id: decoded.id})
        .then(res.json({message: 'Sucessfully deleted'}))
        .catch(err => res.json({message: 'Error during delete'}));
  }

  /**
   * UPDATE one User (change password)
   */
  public updateOne (req: Request, res: Response, next: NextFunction) {
    let token = req.headers['token'];
    let oldPassword = req.body.oldPassword;
    let newPassword = req.body.newPassword;
    let decoded = jwt.decode(token, 'secret');

    User.findById(decoded.id)     
        .then(user => {
          if (user.password !== oldPassword) {
            throw new ErrorWrongPassword();
          }
          else {
            return user;
          }        
        })
        .then(user => {
          user.password = newPassword;
          user.updatedAt = new Date();
          return user;
        })
        .then(user => user.save())
        .then(user => res.json({message: 'Password changed sucessfully.'}))
        .catch(err => {
          if (err instanceof ErrorWrongPassword) {
            res.json({message: 'Old password doesn`t matches.'})
          }
          else {
            res.json({message: 'Error to save.'})
          }
        })
  }

  /**
   * Authenticate one User
   */
  public authenticateOne (req: Request, res: Response, next: NextFunction) {

    User.findOne({name: req.body.name})
        .then(user => {
          if (!user) {
            throw new ErrorNotFound();
          }
          return user;
        })
        .then(user => {
          if(user.password != req.body.password) {
            throw new ErrorWrongPassword();
          }
          return user;
        })
        .then(user => {
          let token = jwt.sign({name: user.name,
                                id: user._id,
                                email: user.email,
                                createdAt: user.createdAt}, 'secret')
          res.json({message: 'Auhentication suceed.',
                    name: user.name,
                    email: user.email,
                    token: token})
        })
        .catch(err => {
          if(err instanceof ErrorNotFound) {
            res.json({message: 'Authentication failed. User not found.'});
          }
          else if (err instanceof ErrorWrongPassword) {
            res.json({message: 'Authentication failed. Wrong password.'});
          }
          else {
            res.json({message: 'Error to authenticate.'})
          }
        })
  }

  /**
   * Verify if Token is correct
   */
  public verifyToken (req: Request, res: Response, next: NextFunction) {  
    let token = req.headers['token'];

    if (token) {
      jwt.verify(token, 'secret', (err) => {
        if (err) {
          return res.json({message: 'Failed to authenticate token.'});
        }
        else {
          next(); 
        }
      });
    }
    else {
      res.status(403).json({
        message: 'No token provided.'
      });
    }
  }

  /**
   * Decode and verify Token
   */
  public decodeToken (req: Request, res: Response, next: NextFunction) {
    let token = req.headers['token'];
    let decoded = jwt.decode(token, 'secret');
    User.findById(decoded.id, (err, user) => {
      if (user) {
        next();
      }
      else {
        res.status(403).json({message: 'Authentication failed. Wrong token.'});
      }
    });
  }

  /**
   * Take each handler, and attach to one of the Express.Router's
   * endpoints.
   */
  init() {
    this.router.get('/', this.verifyToken, this.decodeToken, this.getAll);
    this.router.get('/:id', this.verifyToken, this.decodeToken, this.getOne);
    this.router.post('/', this.createOne);
    this.router.post('/authenticate', this.authenticateOne);
    this.router.delete('/', this.verifyToken, this.decodeToken, this.deleteOne);
    this.router.put('/', this.verifyToken, this.decodeToken, this.updateOne);
  }

}

// Create the UserRouter, and export its configured Express.Router
const userRoutes = new UserRouter();
userRoutes.init();

export default userRoutes.router;
