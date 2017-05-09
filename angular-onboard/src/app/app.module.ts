import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { UsersListComponent } from './users-list/users-list.component';

import { TaqtileApiService } from './taqtile-api.service';
import { UserInfoService } from './user-info.service';
import { routing } from './app.routes';
import { UserDetailsComponent } from './user-details/user-details.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { LoginGuardService } from './login-guard.service';
import { LoginPipe } from './login.pipe';
import { HomeComponent } from './home/home.component';
import { MessagesService } from './messages.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    UsersListComponent,
    UserDetailsComponent,
    CreateUserComponent,
    EditUserComponent,
    LoginPipe,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    TaqtileApiService,
    UserInfoService,
    LoginGuardService,
    MessagesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
