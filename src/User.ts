import * as mongoose from "mongoose";


interface IUser{
    name:string;
    email:string;
    password:string;
    createdAt:Date;
    updatedAt:Date;
}

interface IUserModel extends IUser, mongoose.Document{};
var userSchema = new mongoose.Schema({
    name:String,
    email:String,
    password:String,
    createdAt:Date,
    updatedAt:Date,
});

var User = mongoose.model<IUserModel>("User", userSchema);

export = User;

