import { Store } from '@ngrx/store';
import { inject, Injectable } from '@angular/core';
import { AutenticacionState } from './autenticacion.state';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';
import * as fromAutenticacionActions from './autenticacion.actions';
import * as fromAutenticacionSelectors from './autenticacion.selector';
import { Observable } from 'rxjs';
import { ModuloDto } from 'src/app/data/schema/moduloDto';

@Injectable({
  providedIn: 'root',
})
export class AutenticacionStoreService {
  private store = inject(Store<AutenticacionState>);

  constructor() {}

  // dispatch
  public adicionarSesion(nuevaSesion: SuccessLoginDto) {
    this.store.dispatch(
      fromAutenticacionActions.adicionarSesion({
        usuarioSesion: nuevaSesion,
      })
    );
  }
  public eliminarSesion$() {
    this.store.dispatch(fromAutenticacionActions.eliminarSesion());
  }

  // Selectors

  public obtenerToken$(): string {
    let token: string = '';
    this.store
      .select(fromAutenticacionSelectors.selectTokenSesion)
      .subscribe((data: string) => {
        token = data;
      });
    return token;
  }

  public obtenerSesion$(): Observable<SuccessLoginDto> {
    return this.store.select(fromAutenticacionSelectors.selectSesion);
  }

  public obtenerId$(): Observable<number> {
    return this.store.select(fromAutenticacionSelectors.selectId);
  }
  public obtenerModulos$(): Observable<ModuloDto[]> {
    return this.store.select(fromAutenticacionSelectors.selectModulos);
  }
}
