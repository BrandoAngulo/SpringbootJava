import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';
import { LocalStorageService } from '../services/localStorage.service';
import { VerificarExpiracionService } from '../services/verificar-expiracion.service';

@Injectable({
  providedIn: 'root',
})
export class SessionActiveGuard {
  constructor(
    private localStorage: LocalStorageService,
    private verificarExpiracionService: VerificarExpiracionService,
    private router: Router
  ) {}

  canActivate(): boolean {
    const sesion: SuccessLoginDto = this.localStorage.getItem('sesion');
    if (sesion) {
      if (sesion.token) {
        if (this.verificarExpiracionService.isAuthenticated(sesion.token)) {
          this.router.navigate(['/integradorRecaudo']);

          return true;
        } else {
          this.localStorage.removeItem('sesion');

          return true;
        }
      } else {
        this.localStorage.removeItem('sesion');

        return true;
      }
    }
    this.localStorage.removeItem('sesion');
    return true;
  }
}
