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
      //
  }

  getUserDetails(){

    this.authService.fetchActiveUserDetails().subscribe(res=>{

    });
  }

  signin(){

    var req= {
        username:"Alex",
        password:"abcdef"
      }
        this.authService.signin(req).subscribe(res=>{

        });

  }

  signout(){

        this.authService.signout({}).subscribe(res=>{

        });

  }
  

}
