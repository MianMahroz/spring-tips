import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'COOKIE AND SESSION EXAMPLE';

  constructor(private authService:AuthService){
  }

  ngOnInit() {
      var req= {
        username:"Alex",
        password:"abcdef"
      }
        this.authService.signin(req).subscribe(res=>{

      });

  }

  getUserDetails(){

    this.authService.fetchActiveUserDetails().subscribe(res=>{

    });
  }

}
