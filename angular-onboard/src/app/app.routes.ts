import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { UsersListComponent } from './users-list/users-list.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { CreateUserComponent } from './create-user/create-user.component';

const routes: Routes = [
    { path:'login', component: LoginComponent },
    { path:'users-list', component: UsersListComponent },
    { path: 'user-details/:id', component: UserDetailsComponent },
    { path: 'create-user', component: CreateUserComponent }
]

export const routing = RouterModule.forRoot(routes);