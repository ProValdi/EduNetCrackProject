import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppComponent } from './components/app/app.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { UserPageComponent } from './components/user-page/user-page.component';
import { LearnTabComponent } from './components/learn-tab/learn-tab.component';
import { FormsModule } from "@angular/forms";
import { TagTreeComponent } from './components/tag-tree/tag-tree.component';
import { jqxTreeModule } from "jqwidgets-ng/jqxtree";
import { jqxMenuModule } from 'jqwidgets-ng/jqxmenu';
import { TeachTabComponent } from './components/teach-tab/teach-tab.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserPageComponent,
    LearnTabComponent,
    TagTreeComponent,
    TeachTabComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    jqxTreeModule,
    jqxMenuModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


