import { Component, OnInit, Inject } from '@angular/core';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';


@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  isAuthenticated: boolean = false;
  userFullName: string = '';
  storage: Storage = sessionStorage;

  constructor(
    private oktaAuthService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth
  ) { }

  ngOnInit(): void {

    // Subscribe to auth state changes
    this.oktaAuthService.authState$.subscribe(
      (result: any) => {
        this.isAuthenticated = result.isAuthenticated! 
        this.getUserDetails();
      }
    );
  }
  getUserDetails() {
    if(this.isAuthenticated) {
      // Fetch the logged in user datails (user's claims)

      // User full name is exposed as a property name
      this.oktaAuth.getUser().then(
        (res) => {
          this.userFullName = res.name as string;

          // Retrieve the user's email from authentication response
          const theEmail = res.email;
          // Store the email in browser
          this.storage.setItem('userEmail', JSON.stringify(theEmail));
        }
      );
    }
  }

  logout() {
    // Terminates the session with Okta and removes current tokens
    this.oktaAuth.signOut();
  }

}
