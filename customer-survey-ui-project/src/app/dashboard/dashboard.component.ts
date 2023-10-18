import { Component } from '@angular/core';
import {AccountService} from "../account.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  constructor(private accService : AccountService) {
  }

  ngOnInit(): void{
    this.loadFeedback();
  }

  loadFeedback(){
    debugger
    this.accService.getFeedback().subscribe((resp: any) => {
      debugger
    })
  }

}


