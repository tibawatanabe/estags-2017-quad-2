import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { UsersListComponent } from './users-list/users-list.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { EditUserComponent } from "app/edit-user/edit-user.component";
import { LoginGuardService } from './login-guard.service';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
    // { path:'login/:error', component: LoginComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path:'login', component: LoginComponent },
    { path: 'home', component: HomeComponent,  canActivate: [LoginGuardService] },
    { path: 'users-list/:page', component: UsersListComponent, canActivate: [LoginGuardService] },
    { path: 'user-details/:id', component: UserDetailsComponent, canActivate: [LoginGuardService] },
    { path: 'create-user', component: CreateUserComponent, canActivate: [LoginGuardService] },
    { path: 'edit-user/:id', component: EditUserComponent, canActivate: [LoginGuardService] }
]

export const routing = RouterModule.forRoot(routes);