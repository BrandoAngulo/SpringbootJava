import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpService } from 'src/app/core/services/http.service';
import { LocalStorageService } from 'src/app/core/services/localStorage.service';
import { ClienteDto as GenericDto } from 'src/app/data/schema/clienteDto';
import { TimeZoneDto } from 'src/app/data/schema/timeZoneDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CustomersService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'cliente/', router, localStorage);
  }

  getAll(): Observable<GenericDto> {
    return super.get('consultar-todos', 'Error consultando los clientes.');
  }


  updateEstado(id: number) {
    return super.put(`cambiar-estado/${id}`, 'Error al actualizar estado estado del cliente.');
  }

  eliminar(id: number) {
    return super.post(`eliminar/${id}`, 'Error al eliminar el cliente.');
  }

  registrar(usuario: GenericDto) {
    return super.post('registrar', 'Error al crear cliente.', usuario);
  }
  update(usuario: GenericDto) {
    return super.put('actualizar', 'Error al crear cliente.', usuario);
  }
}
