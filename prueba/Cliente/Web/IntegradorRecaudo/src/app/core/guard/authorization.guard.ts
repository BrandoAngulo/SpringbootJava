import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthorizationService } from '../services/authorization.service';

@Injectable({
  providedIn: 'root',
})
export class AuthorizationGuard {
  constructor(
    private authorizationService: AuthorizationService,
    private router: Router
  ) {}

  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    const routeConfig = next.routeConfig.path;
    const url = state.url;
    const isAuthorized = this.authorizationService.isAuthorized(
      routeConfig,
      url
    );

    if (!isAuthorized) {
      // if not authorized, show access denied message
      this.router.navigate(['accesodenegado']);
    }

    return isAuthorized;
  }
}
