import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ServidoresDto } from 'src/app/data/schema/servidoresDto';
import { environment } from 'src/environments/environment';
import { HttpService } from './http.service';
import { LocalStorageService } from './localStorage.service';
import { GenericDto } from 'src/app/data/schema/GenericDto';

@Injectable({
  providedIn: 'root',
})
export class ServersService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'servidores/', router, localStorage);
  }

  getAll(): Observable<GenericDto> {
    return super.get('consultar-todos', 'Error consultando los servidores.');
  }
}
