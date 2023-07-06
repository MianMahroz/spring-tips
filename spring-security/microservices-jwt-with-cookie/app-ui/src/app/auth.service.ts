
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, EMPTY, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),withCredentials: true
  };
  
  @Injectable({
    providedIn: 'root'
  })
  export class AuthService {

    constructor(private http: HttpClient) { }

    signin(req:any): Observable<any> {
        return this.http.post<any>(environment.serverPath+"/auth/signin",req, httpOptions)
        .pipe(
          catchError( err => {
            return throwError(err);
               
          })
        );
      }
      signout(req:any): Observable<any> {
        return this.http.post<any>(environment.serverPath+"/auth/signout",req, httpOptions)
        .pipe(
          catchError( err => {
            return throwError(err);
               
          })
        );
      }
      
      fetchActiveUserDetails(): Observable<any> {
        return this.http.get<any>(environment.serverPath+"/auth/currentUser", httpOptions)
        .pipe(
          catchError( err => {
            return throwError(err);
               
          })
        );
      }
    
  }