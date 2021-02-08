import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './components/app/app.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { UserPageComponent } from './components/user-page/user-page.component';
import { LearnTabComponent } from './components/learn-tab/learn-tab.component';


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserPageComponent,
    LearnTabComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
