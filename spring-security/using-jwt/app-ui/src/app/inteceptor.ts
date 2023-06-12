
import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
  HttpResponse, HttpUserEvent, HttpErrorResponse} from '@angular/common/http';
import { Observable, EMPTY, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import {CookieService} from 'ngx-cookie-service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
    let authReq = req;
    // let JSESSIONID = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbGV4IiwiaWF0IjoxNjg1ODkzNjE5LCJleHAiOjE2ODU4OTU0MTl9.YEA1q26xKG15pCw87XkW2q9hfulllA4gdpuP9Y_F8z4";

    // if (JSESSIONID != null) {
    //   //console.log('session Token:' + this.token.getToken());
    //   authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + JSESSIONID)});
    // }
    return next.handle(authReq).pipe(
      tap(
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
              this.router.navigate(['/']);
            }
          }
        }
      )
    );
  }

}
