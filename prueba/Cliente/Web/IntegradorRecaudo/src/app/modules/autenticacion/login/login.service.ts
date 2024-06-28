import { inject, Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

import { HttpService } from 'src/app/core/services/http.service';
import { LocalStorageService } from 'src/app/core/services/localStorage.service';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { LoginRequestDto } from 'src/app/data/schema/loginRequestDto';
import { environment } from 'src/environments/environment';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';
import { Router } from '@angular/router';
import { AutenticacionStoreService } from 'src/app/core/store/autenticacion/autenticacion-store.service';

@Injectable({
  providedIn: 'root',
})
export class LoginService extends HttpService<SuccessLoginDto> {
  successLoginDto: SuccessLoginDto;

  private store = inject(AutenticacionStoreService);
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'auth/', router, localStorage);
  }

  auth(loginRequestDto: LoginRequestDto): Observable<SuccessLoginDto> {
    this.localStorage.removeItem('sesion');
    return super.post('login', 'Error al iniciar sesion', loginRequestDto);
  }

  verificarToken(): string {
    let token = this.store.obtenerToken$();
    return token;
  }

  logout() {
    this.store.eliminarSesion$();
    this.localStorage.clear();
    this.router.navigate(['/autenticacion/login']);
  }
}
