import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  name: string = "";
  loginname: string = "";
  password: string = "";

  constructor(private router: Router, private  http: HttpClient) {
  }

  save(){
    let bodyData = {
      "name" : this.name,
      "username" : this.loginname,
      "password" : this.password
    };

    this.http.post("http://localhost:8081/api/v1/auth/signup",bodyData,{responseType: 'text'}).subscribe(resultData =>
    {

      alert(resultData);
      this.router.navigateByUrl('/');

    });
  }
}
