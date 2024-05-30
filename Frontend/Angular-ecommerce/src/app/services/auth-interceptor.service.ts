import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';
import { lastValueFrom } from 'rxjs';
import { from } from 'rxjs';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.handleAccess(request, next));
  }

  private async handleAccess(request: any, next: HttpHandler): Promise<HttpEvent<any>> {
    
    // Only add an access token for secured endpoints
    const securedEndpoints = ['http://localhost:8081/api/orders'];
    if(securedEndpoints.some(url => request.urlWithParams.includes(url))){
      // Get access token
      const accessToken = this.oktaAuth.getAccessToken();
      // Clone request and add new header with access token
      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + accessToken
        }
      });
    }
    return await lastValueFrom(next.handle(request));
  }
}
