import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { UsersListComponent } from './users-list/users-list.component';
import { UserDetailsComponent } from './user-details/user-details.component'

const routes: Routes = [
    { path:'login', component: LoginComponent },
    { path:'users-list', component: UsersListComponent },
    { path: 'user-details/:id', component: UserDetailsComponent }
]

export const routing = RouterModule.forRoot(routes);