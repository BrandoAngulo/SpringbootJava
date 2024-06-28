import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { VersionesDto as GenericDto } from 'src/app/data/schema/versionesDto';
import { environment } from 'src/environments/environment';
import { HttpService } from './http.service';
import { LocalStorageService } from './localStorage.service';

@Injectable({
  providedIn: 'root',
})
export class TimeZoneService extends HttpService<GenericDto> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(
      httpClient,
      environment.BaseUrl + 'zona-horaria/',
      router,
      localStorage
    );
  }

  getAll(): Observable<GenericDto> {
    return super.get('consultar-todos', 'Error consultando las versiones.');
  }
}
