import { createFeatureSelector, createSelector } from '@ngrx/store';
import { AutenticacionState } from './autenticacion.state';

export const autenticacionKey = 'auth';
export const selectAutenticacion =
  createFeatureSelector<AutenticacionState>(autenticacionKey);

export const selectTokenSesion = createSelector(
  selectAutenticacion,
  (state) => state.sesion.token
);

export const selectSesion = createSelector(
  selectAutenticacion,
  (state) => state.sesion
);

export const selectId = createSelector(
  selectAutenticacion,
  (state) => state.sesion.id
);

export const selectModulos = createSelector(
  selectAutenticacion,
  (state) => state.sesion.modulos
);
