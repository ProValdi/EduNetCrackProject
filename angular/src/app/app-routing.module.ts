import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './components/user-list/user-list.component';
import {UserPageComponent} from "./components/user-page/user-page.component";
import {LearnTabComponent} from "./components/learn-tab/learn-tab.component";
import {TagTreeComponent}  from './components/tag-tree/tag-tree.component';
import {TeachTabComponent}  from './components/teach-tab/teach-tab.component';
import {OutgoingRequestsComponent} from './components/outgoing-requests/outgoing-requests.component';
import {SignInTabComponent} from './components/sign-in-tab/sign-in-tab.component';


const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'profile', component: UserPageComponent },
  { path: 'learn', component: LearnTabComponent },
  { path: 'tags', component: TagTreeComponent },
  { path: 'teach', component: TeachTabComponent },
  { path: 'outgoing-requests', component: OutgoingRequestsComponent },
  { path: 'login', component: SignInTabComponent },
  { path: 'registration', component: SignInTabComponent },
  { path: '', redirectTo: '/learn', pathMatch: 'full' },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
