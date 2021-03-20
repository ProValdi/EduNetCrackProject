import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { jqxGridModule } from 'jqwidgets-ng/jqxgrid';

import { AppComponent } from './components/app/app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { UserPageComponent } from './components/user-page/user-page.component';
import { LearnTabComponent } from './components/learn-tab/learn-tab.component';
import { FormsModule } from "@angular/forms";
import { TagTreeComponent } from './components/tag-tree/tag-tree.component';
import { jqxTreeModule } from "jqwidgets-ng/jqxtree";
import { jqxTreeGridModule } from "jqwidgets-ng/jqxtreegrid";
import { jqxMenuModule } from 'jqwidgets-ng/jqxmenu';
import { TeachTabComponent } from './components/teach-tab/teach-tab.component';
import { OutgoingRequestsComponent } from './components/outgoing-requests/outgoing-requests.component';
import { IncomingRequestsComponent } from './components/incoming-requests/incoming-requests.component';
import { SignInTabComponent } from './components/sign-in-tab/sign-in-tab.component';

@NgModule({
  declarations: [
    AppComponent,
    UserPageComponent,
    LearnTabComponent,
    TagTreeComponent,
    TeachTabComponent,
    OutgoingRequestsComponent,
    IncomingRequestsComponent,
    SignInTabComponent,
  ],
  imports: [
    BrowserModule,
    jqxGridModule,
    jqxTreeGridModule,
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


