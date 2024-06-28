import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ServidoresDto } from 'src/app/data/schema/servidoresDto';
import { environment } from 'src/environments/environment';
import { GenericDto } from 'src/app/data/schema/GenericDto';
import { HttpService } from 'src/app/core/services/http.service';
import { LocalStorageService } from 'src/app/core/services/localStorage.service';

@Injectable({
  providedIn: 'root',
})
export class LogsService extends HttpService<any> {
  constructor(
    public httpClient: HttpClient,
    public localStorage: LocalStorageService,
    public router: Router
  ) {
    super(httpClient, environment.BaseUrl + 'logs/', router, localStorage);
  }

  obtenerLogs(): Observable<String[]> {
    return super.get('consultar-todos', 'Error consultando los logs.');
  }

  descargarLog(nombreArchivo: string): Observable<Blob | void> {
    return super.obtenerArchivos(
      'descargar-archivo',
      'Error descargando archivo.',
      {
        params: { nombreArchivo },
        responseType: 'blob', 
      }
    );
  }
}
