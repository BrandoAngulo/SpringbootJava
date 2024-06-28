import { createReducer, on } from '@ngrx/store';
import { initialState } from './autenticacion.state';
import * as AutenticacionActions from './autenticacion.actions';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';

export const autenticacionReducer = createReducer(
  initialState,
  on(AutenticacionActions.adicionarSesion, (state, { usuarioSesion }) => ({
    ...state,
    sesion: usuarioSesion,
  })),
  on(AutenticacionActions.eliminarSesion, (state) => ({
    ...state,
    sesion: {} as SuccessLoginDto,
  }))
);
