import { ActionReducer } from '@ngrx/store';
import { environment } from '../../../environments/environment';
import * as CryptoJS from 'crypto-js';

export function localStorageMetaReducer(
  reducer: ActionReducer<any>
): ActionReducer<any> {
  return function (state, action) {
    if (action.type === '@ngrx/store/init') {
      const estadoAlmacenado = localStorage.getItem('autenticacion');

      if (estadoAlmacenado) {
        const estadoDesencriptado = desencriptarLlave(estadoAlmacenado!);
        return estadoDesencriptado;
      }
    }
    const nuevoEstado = reducer(state, action);
    let llaveEncriptada = encriptarLlave(nuevoEstado);
    localStorage.setItem('autenticacion', llaveEncriptada);
    return nuevoEstado;
  };
}

function encriptarLlave(data: string): string {
  const cryptoKey = environment.cryptoKey;
  let dataEncriptada = CryptoJS.AES.encrypt(JSON.stringify(data), cryptoKey);
  data = dataEncriptada.toString();
  return data;
}

function desencriptarLlave(data: string) {
  const cryptoKey = environment.cryptoKey;
  let bytes = CryptoJS.AES.decrypt(data, cryptoKey);
  let dataDesencriptada = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
  return dataDesencriptada;
}
