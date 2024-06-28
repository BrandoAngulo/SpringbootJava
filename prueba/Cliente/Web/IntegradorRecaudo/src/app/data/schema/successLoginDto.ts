import { ModuloDto } from './moduloDto';
import { RolDto } from './RolDto';

export class SuccessLoginDto {
  id?: number;
  nombres?: string;
  apellidos?: string;
  email?: string;
  documento?: string;
  roles?: string[];
  token?: string;
  modulos?: ModuloDto[];
  nombreUsuario?: number;

  constructor(init?: Partial<SuccessLoginDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
