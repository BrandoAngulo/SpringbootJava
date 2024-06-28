import { createAction, props } from '@ngrx/store';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';

// Accion para agregar la sesion del usuario al store
export const adicionarSesion = createAction(
  '[Autenticacion] Adicionar Sesion',
  props<{ usuarioSesion: SuccessLoginDto }>()
);


// Accion para agregar la sesion del usuario al store
export const eliminarSesion = createAction(
    '[Autenticacion] Eliminar Sesion',
  );
  
