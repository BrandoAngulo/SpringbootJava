import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { GenericDto } from 'src/app/data/schema/GenericDto';
import { environment } from 'src/environments/environment';
import { HttpService } from './http.service';
import { LocalStorageService } from './localStorage.service';

@Injectable({
  providedIn: 'root',
})
export class ModulosService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'modulos/', router, localStorage);
  }

  getModulos(): Observable<GenericDto> { 
    return super.get('consultar-todos', 'Error consultando los modulos.');
  }
}
