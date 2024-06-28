import { inject } from '@angular/core';
import { CanMatchFn, Router } from '@angular/router';
import { LoginService } from 'src/app/modules/autenticacion/login/login.service';

export const noAutenticacion: CanMatchFn = () => {
  const token = inject(LoginService).verificarToken();
  const router = inject(Router);
  if (!!token) {
    router.navigate(['/integradorRecaudo']);
    return false;
  }
  return true;
};
