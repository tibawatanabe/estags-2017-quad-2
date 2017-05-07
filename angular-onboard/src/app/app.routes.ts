import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { UsersListComponent } from './users-list/users-list.component';

const routes: Routes = [
    { path:'login', component: LoginComponent },
    { path:'users-list', component: UsersListComponent }
]

export const routing = RouterModule.forRoot(routes);