import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { HomeComponent } from './home/home.component';
import { SubmitfeedbackComponent } from './submitfeedback/submitfeedback.component';
import { ViewfeedbackComponent } from './viewfeedback/viewfeedback.component';
import { Login2Component } from './login2/login2.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {CustomInterceptor} from "./custom.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SubmitfeedbackComponent,
    ViewfeedbackComponent,
    Login2Component,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: CustomInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
