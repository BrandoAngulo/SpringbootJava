import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { GenericDto } from 'src/app/data/schema/GenericDto';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { LocalStorageService } from './localStorage.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ValidacionProcesosService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'procesos/', router, localStorage);
  }

  consultarProcesos(): Observable<GenericDto> {
    return super.get('consultar-todos-procesos', 'Error consultando procesos.');
  }
}
