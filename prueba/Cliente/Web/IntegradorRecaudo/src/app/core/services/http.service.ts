import { MessageExceptionDto } from './../../data/schema/MessageExceptionDto';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { AlertsService } from './alerts.service';
import { Router } from '@angular/router';
import { LocalStorageService } from './localStorage.service';
import { GenericDto } from 'src/app/data/schema/GenericDto';

@Injectable()
export class HttpService<T> {
  constructor(
    protected httpClient: HttpClient,
    protected url: string,
    protected router: Router,
    protected localStorage: LocalStorageService
  ) {}

  protected messageException: MessageExceptionDto = new MessageExceptionDto();

  alert: AlertsService = new AlertsService();

  protected changeUrl(newUrl: string) {
    this.url = newUrl;
  }

  // GET
  protected get(
    endpoint: string,
    errorMsg: string,
    config?: {}
  ): Observable<T> {
    return this.httpClient.get<T[]>(`${this.url}${endpoint}`, config).pipe(
      map((response: any) => {
        if (response.status == 500) {
          throw response.payload;
        }
        if (response.status == 200) {
          return response.payload;
        }
      }),
      catchError((error) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status == 401) {
            this.alert.toast(
              'error',
              'Su sesión ha expirado. Inicie sesión nuevamente.'
            );
            this.localStorage.removeItem('sesion');
            this.router.navigate(['/autenticacion/login']);
          } else {
            this.messageException.message = errorMsg;
            return throwError(this.messageException);
          }
        } else {
          return throwError(error);
        }
      })
    );
  }

  // POST
  protected post<N>(
    endpoint: string,
    errorMsg: string,
    datos?: N
  ): Observable<T> {
    return this.httpClient.post<T>(`${this.url}${endpoint}`, datos).pipe(
      map((response: GenericDto) => {
        if (response.status == 500) {
          throw response.payload;
        }
        if (response.status == 200) {
          return response.payload;
        }
      }),
      catchError((error) => {
        if (error instanceof HttpErrorResponse) {
          const errorGeneral: GenericDto = error.error;
          if (error.status == 401) {
            this.alert.toast(
              'error',
              'Su sesión ha expirado. Inicie sesión nuevamente.'
            );
            this.localStorage.removeItem('sesion');
            this.router.navigate(['/autenticacion/login']);
          } else {
            this.messageException.message = errorGeneral.payload.mensaje;
            this.messageException.code = errorGeneral.status;
            this.messageException.recomen = errorGeneral.payload.recomendacion;
            return throwError(this.messageException);
          }
        } else {
          return throwError(error);
        }
      })
    );
  }

  // PUT
  protected put<N>(
    endpoint: string,
    errorMsg: string,
    datos?: N
  ): Observable<T> {
    return this.httpClient.put<T>(`${this.url}${endpoint}`, datos).pipe(
      map((response: GenericDto) => {
        if (response.status == 500) {
          throw response.payload;
        }
        if (response.status == 200) {
          return response.payload;
        }
      }),
      catchError((error) => {
        if (error instanceof HttpErrorResponse) {
          const errorGeneral: GenericDto = error.error;
          if (error.status == 401) {
            this.alert.toast(
              'error',
              'Su sesión ha expirado. Inicie sesión nuevamente.'
            );
            this.localStorage.removeItem('sesion');
            this.router.navigate(['/autenticacion/login']);
          } else {
            this.messageException.message = errorGeneral.payload.mensaje;
            this.messageException.code = errorGeneral.status;
            this.messageException.recomen = errorGeneral.payload.recomendacion;
            return throwError(this.messageException);
          }
        } else {
          return throwError(error);
        }
      })
    );
  }

  protected obtenerArchivos(
    endpoint: string,
    errorMsg: string,
    config?: {}
  ): Observable<Blob | void> {
    return this.httpClient.get<Blob>(`${this.url}${endpoint}`, config).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status == 401) {
            this.alert.toast(
              'error',
              'Su sesión ha expirado. Inicie sesión nuevamente.'
            );
            this.localStorage.removeItem('sesion');
            this.router.navigate(['/autenticacion/login']);
          } else {
            this.messageException.message = errorMsg;
            return throwError(this.messageException);
          }
        } else {
          return throwError(error);
        }
      })
    );
  }
}
