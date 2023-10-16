import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginname: string = "";
  password: string = "";

  constructor(private router: Router, private  http: HttpClient) {
  }

  login() {
    console.log(this.loginname);
    console.log(this.password);

    let bodyData = {
      username: this.loginname,
      password: this.password,
    };

    this.http.post("http://localhost:8081/api/v1/auth/signin", bodyData).subscribe(  (resultData: any) => {
      console.log(resultData);

      alert(resultData.message);

      if (resultData.message == "Email not exits")
      {

        alert("Email not exits");


      }
      else if(resultData.message == "Login Success")

      {
        this.router.navigateByUrl('/home');
      }
      else
      {
        alert("Incorrect UserName and Password not match");
      }
    });

  }








}
