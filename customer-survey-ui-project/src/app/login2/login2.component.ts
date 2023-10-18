import { Component } from '@angular/core';
import {AccountService} from "../account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login2',
  templateUrl: './login2.component.html',
  styleUrls: ['./login2.component.scss'],
})
export class Login2Component {

  signupCustomerName: string = "";
  signupUserName: string = "";
  signupPassword: string = "";
  loginUserName: string = "";
  loginPassword: string = "";

  signUpUsers: any[] = [];
  signUpObj:any = {
    name: '',
    username: '',
    password: ''
  };

  loginObj:any = {
    username: '',
    password: ''
  };




  constructor(private accService : AccountService, private router : Router) {
  }

  ngOnInit(): void{
    const localData = localStorage.getItem('signUpUsers');
    if(localData!=null){
      this.signUpUsers = JSON.parse(localData);
    }
  }

  onSingUp(): void{
    /*this.signUpUsers.push(this.signUpObj);
    localStorage.setItem('signUpUsers', JSON.stringify(this.signUpUsers))
    this.signUpObj = {
      signUpCustomerName: '',
      signUpUserName: '',
      password: ''
    };*/
    this.accService.onSignUp(this.signUpObj).subscribe((resp: any) => {
      debugger
      console.log('resp', resp);
      alert(resp.message);

    })
    localStorage.clear();
  }

  onLogin(): void{
    debugger
   /* const isUserExist = this.signUpUsers.find(m => m.signUpUserName == this.loginObj.loginUserName && m.password == this.loginObj.password)
    if(isUserExist != undefined){
      alert('User login successfully');
    } else {
      alert('Wrong credentials');
    }*/

    this.accService.onLogin(this.loginObj).subscribe((resp: any) => {
      debugger
      console.log('resp', resp);
      localStorage.setItem('token',resp.token);
      localStorage.setItem('username',this.loginObj.username);
      if(this.loginObj.username=='Admin'){
        localStorage.setItem('userrole','Admin');
      } else {
        localStorage.setItem('userrole','User');
      }
      this.router.navigateByUrl('/dashboard');
    })
  }

}
