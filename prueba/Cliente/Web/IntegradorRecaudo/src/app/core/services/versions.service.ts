import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {
  VersionesDto as GenericDto,
  VersionesDto,
} from 'src/app/data/schema/versionesDto';
import { environment } from 'src/environments/environment';
import { HttpService } from './http.service';
import { LocalStorageService } from './localStorage.service';

@Injectable({
  providedIn: 'root',
})
export class VersionsService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'versiones/', router, localStorage);
  }

  getAll(): Observable<GenericDto> {
    return super.get('consultar-todos', 'Error consultando las versiones.');
  }

  create(version: VersionesDto) {
    return super.post('registar-versiones', 'Error al crear versión', version);
  }

  update(version: VersionesDto) {
    return super.put(
      `actualizar-versiones`,
      'Error al actualizar versión',
      version
    );
  }

  cambiarEstado(id: number) {
    return super.put(`cambiar-estado/${id}`, 'Error al actualizar versión');
  }
}
