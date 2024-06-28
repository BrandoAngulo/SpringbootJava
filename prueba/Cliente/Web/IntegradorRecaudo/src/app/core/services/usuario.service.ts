import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ChangePasswordDto } from 'src/app/data/schema/changePasswordDto';
import { tipoUsuariosDto } from 'src/app/data/schema/tipoUsuariosDto';
import { UsuarioDto } from 'src/app/data/schema/usuarioDto';
import { environment } from 'src/environments/environment';
import { HttpService } from './http.service';
import { LocalStorageService } from './localStorage.service';
import { GenericDto } from 'src/app/data/schema/GenericDto';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'usuario/', router, localStorage);
  }

  getAll(): Observable<GenericDto> {
    return super.get('consultar-usuarios', 'Error consultando los usuarios.');
  }

  getById(id: number): Observable<GenericDto> {
    return super.get(`consultar-usuario/${id}`, 'Error consultando el usuario.');
  }


  create(usuario: UsuarioDto) {
    return super.post('registrar', 'Error al crear usuarios.', usuario);
  }

  update(usuario: UsuarioDto) {
    return super.put('actualizar', 'Error al actualizar usuarios.', usuario);
  }

  updateEstado(id: number) {
    return super.put(`cambiar-estado/${id}`, 'Error al actualizar usuario.');
  }

  changePassword(changePassword: ChangePasswordDto) {
    return super.put(
      'cambiar-contrasena',
      'Error al actualizar la constraseña.',
      changePassword
    );
  }
}
