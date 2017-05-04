import {Router, Request, Response, NextFunction} from 'express';
const Users = require('../users');

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
    for (let i = 0; i < 5; i++) {
        allUser += Users[i].name + "\n";
    }
     res.send(allUser);
  }


  /**
   * Take each handler, and attach to one of the Express.Router's
   * endpoints.
   */
  init() {
    this.router.get('/', this.getAll);
  }

}

// Create the UserRouter, and export its configured Express.Router
const userRoutes = new UserRouter();
userRoutes.init();

export default userRoutes.router;