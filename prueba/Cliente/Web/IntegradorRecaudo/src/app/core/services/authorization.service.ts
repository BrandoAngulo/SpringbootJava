import { Injectable, inject } from '@angular/core';
import { AutenticacionStoreService } from '../store/autenticacion/autenticacion-store.service';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';
import { Observable } from 'rxjs';
import { ModuloDto } from 'src/app/data/schema/moduloDto';

@Injectable({
  providedIn: 'root',
})
export class AuthorizationService {
  private autenticacionStoreService = inject(AutenticacionStoreService);
  private modulosUsuario$: Observable<ModuloDto[]>;
  public sesion: SuccessLoginDto;
  public modulos: ModuloDto[];

  constructor() {
    this.modulosUsuario$ = this.autenticacionStoreService.obtenerModulos$();
  }

  isAuthorized(routeConfig: String, url: String): boolean {
    this.modulosUsuario$.subscribe((data: ModuloDto[]) => {
      this.modulos = data;
    });

    if (url == '/integradorRecaudo') {
      return true;
    }

    if (url == '/integradorRecaudo/users/change_password') {
      return true;
    }

    url = url.slice(19);

    let submodulos = this.modulos.flatMap((obj) => obj.submodulos);
    let tieneSubmodulos = this.modulos.some(
      (element) => element.ruta == routeConfig
    );

    let submoduloPermitido = submodulos.some(
      (submodulo) => submodulo.ruta == url
    );

    return tieneSubmodulos ? true : submoduloPermitido;
  }
}
