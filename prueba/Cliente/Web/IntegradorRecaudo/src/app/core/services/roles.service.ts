import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { GenericDto } from 'src/app/data/schema/GenericDto';
import { environment } from 'src/environments/environment';
import { HttpService } from './http.service';
import { LocalStorageService } from './localStorage.service';
import { RolDto } from 'src/app/data/schema/RolDto';

@Injectable({
  providedIn: 'root',
})
export class RolesService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'roles/', router, localStorage);
  }

  getRoles(): Observable<GenericDto> { 
    return super.get('consultar-roles-modulos-asignados', 'Error consultando los roles.');
  }

  getRolesNombre(): Observable<GenericDto> {
    return super.get('consultar-nombre-roles', 'Error consultando los tipos de roles.');
  }

  create(rol: RolDto) {
    return super.post('registrar', 'Error al crear rol.', rol);
  }

  updateEstado(id: number) {
    return super.put(`cambiar-estado/${id}`, 'Error al inactivar el rol.');
  }
  
  update(rol: RolDto) {
    return super.put('actualizar', 'Error al actualizar rol.', rol);
  }
} 
