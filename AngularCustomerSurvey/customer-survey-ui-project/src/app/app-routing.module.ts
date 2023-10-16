import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {HomeComponent} from "./home/home.component";
import {SubmitfeedbackComponent} from "./submitfeedback/submitfeedback.component";
import {ViewfeedbackComponent} from "./viewfeedback/viewfeedback.component";

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },

  {
    path: 'home',
    component: HomeComponent,

  },
  {

    path: 'signup',
    component: SignupComponent,
  },{

    path: 'submit',
    component: SubmitfeedbackComponent,
  },{

    path: 'view',
    component: ViewfeedbackComponent,
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
