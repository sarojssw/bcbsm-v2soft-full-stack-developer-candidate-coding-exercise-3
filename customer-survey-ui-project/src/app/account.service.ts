import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  userObj:any = {
    username: ''
  };

  constructor(private http: HttpClient) { }

  onLogin(obj: any) : Observable<any>{
    return this.http.post('http://localhost:8081/api/v1/auth/signin', obj);
  }

  onSignUp(obj: any) : Observable<any>{
    return this.http.post('http://localhost:8081/api/v1/auth/signup', obj);
  }

  getFeedback() : Observable<any>{
    debugger
    alert(localStorage.getItem('userrole'));
    /* const userRole = localStorage.getItem('userrole');

     if(userRole == 'Admin'){
       return this.http.get('http://localhost:8081/api/v1/admin/feedback/view');
     } else {
       let bodyData = {
         "username" : localStorage.getItem('username')
       };
       return this.http.post('http://localhost:8081/api/v1/user/feedback/view', bodyData);
     }*/
    let bodyData = {
      "username" : localStorage.getItem('username')
    };
    return this.http.post('http://localhost:8081/api/v1/user/feedback/view', bodyData);

  }
}
