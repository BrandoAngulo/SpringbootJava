import {
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

// import Services
import { VerificarExpiracionService } from '../services/verificar-expiracion.service';
import { LocalStorageService } from '../services/localStorage.service';
import { AlertsService } from '../services/alerts.service';
import { LoginService } from 'src/app/modules/autenticacion/login/login.service';

@Injectable({
  providedIn: 'root',
})
export class TokenInterceptorsService implements HttpInterceptor {
  constructor(
    private localStorage: LocalStorageService,
    public verificarExpiracionService: VerificarExpiracionService,
    private router: Router,
    private alertsService: AlertsService
  ) {}

  verificarRutas(url: string): boolean {
    let result: boolean;
    environment.blacklist.forEach((item) => {
      if (url.includes(item)) {
        result = true;
        return result;
      }
    });
    return result;
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (this.verificarRutas(req.url)) {
      return next.handle(req);
    } else {
      const token = inject(LoginService).verificarToken();
      if (token) {
        if (this.verificarExpiracionService.isAuthenticated(token)) {
          const headers = new HttpHeaders({
            Authorization: 'Bearer ' + token,
          });
          const reqClone = req.clone({
            headers,
          });
          return next.handle(reqClone);
        }
        this.sesionExpirada();
        return;
      }
      return;
    }
  }

  sesionExpirada(): void {
    this.localStorage.removeItem('sesion');
    this.router.navigate(['/login']);

    this.alertsService.toast('error', 'Su sesión ha expirado');
  }
}
