import { Injectable, inject } from '@angular/core';
import { Router, CanMatchFn } from '@angular/router';
import { LoginService } from 'src/app/modules/autenticacion/login/login.service';
import { VerificarExpiracionService } from '../services/verificar-expiracion.service';

export const AuthGuard: CanMatchFn = () => {
  const token = inject(LoginService).verificarToken();
  const verificarExpiracionService = inject(VerificarExpiracionService);
  const router = inject(Router);
  if (token) {
    if (verificarExpiracionService.isAuthenticated(token)) {
      return true;
    } else {
      sesionExpirada(router);
      return false;
    }
  }
  sesionExpirada(router);
  return false;
};

function sesionExpirada(router: Router) {
  localStorage.removeItem('autenticacion');
  localStorage.clear();
  router.navigate(['/autenticacion/login']);

  /*const modal = new Modal(this.modalService);
    modal.modal(true, 'ModalConfirmacionComponent', true, {
      confirmacion: {
        titulo: 'Información',
        mensaje: 'Su sesión ha expirado. Inicie sesión nuevamente.',
      },
      redireccion: {
        activo: false,
        ruta: '',
      },
    });*/
}
