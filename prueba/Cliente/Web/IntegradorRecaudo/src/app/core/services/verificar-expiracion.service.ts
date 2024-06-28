import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class VerificarExpiracionService {
  constructor(private jwtHelper: JwtHelperService) {}

  isAuthenticated(token: string): boolean {
    try {
      if (token) {
        return !this.jwtHelper.isTokenExpired(token);
      } else {
        return false;
      }
    } catch (error) {
      return false;
    }
  }
}
