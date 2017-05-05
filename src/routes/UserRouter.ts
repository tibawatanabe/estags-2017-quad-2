import {Router, Request, Response, NextFunction} from 'express';
const Users : Array<any> = require('../users.json');

var jwt = require('jsonwebtoken');


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
    let allUser: string = "";
    for (let i = 0; i < Users.length; i++) {
        allUser += Users[i].name + "\n";
    }
     res.send(allUser);
  }

  /**
   * GET details of one User by id
   */
  public getOne(req: Request, res: Response, next: NextFunction) {
    let query = parseInt(req.params.id);
    let user = Users.find(user => user.id === query);
    if (user) {
        res.status(200)
        .json({name: user.name, 
              email: user.email});
    }
    else {
        res.status(404)
        .send({
           message: 'No user found with the given id.',
           status: res.status
        });
    }
  }

  /**
   * CREATE one User
   */
  public createOne(req: Request, res: Response, next: NextFunction) {
    let fs = require('fs');
    let userName : string = req.body.name;
    let userEmail : string = req.body.email;
    let userPassword : string = req.body.password;
    let user = Users.find (user => user.name === userName);
    if (!user) {
      res.status(200)
      Users.push({id: Users.length + 1,
          name: userName,
          email: userEmail,
          password: userPassword}
          );
      fs.writeFile('src/users.json', JSON.stringify(Users, null, ' '), (err) => { 
          if (err) throw err;
      });
      res.json({
        message: 'Sucess',
        name: userName,
        email: userEmail
      });
    }
    else {
      res.send({
        message: 'User already exists'
      });
    }
    
  }

  /**
   * Take each handler, and attach to one of the Express.Router's
   * endpoints.
   */
  init() {
    this.router.get('/', this.getAll);
    this.router.get('/:id', this.getOne);
    this.router.post('/', this.createOne);
  }

}

// Create the UserRouter, and export its configured Express.Router
const userRoutes = new UserRouter();
userRoutes.init();

export default userRoutes.router;