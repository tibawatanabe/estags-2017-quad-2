import {Router, Request, Response, NextFunction} from 'express';
const Users : Array<any> = require('../users');

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
        .send("name: " + user.name + "\n" + 
              "email: " + user.email + "\n" + 
              "password: " + user.password + "\n");
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
   * Take each handler, and attach to one of the Express.Router's
   * endpoints.
   */
  init() {
    this.router.get('/', this.getAll);
    this.router.get('/:id', this.getOne);
  }

}

// Create the UserRouter, and export its configured Express.Router
const userRoutes = new UserRouter();
userRoutes.init();

export default userRoutes.router;