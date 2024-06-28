import { GenericDto } from './GenericDto';
import { RolDto } from './RolDto';

export class UsuarioDto extends GenericDto {
  id?:number;
  nombre_usuario?: string;
  contrasena?: string;
  estado?: string;
  nombres?: string;
  apellidos?: string;
  correo?: string;
  documento?: string;
  id_tipo_documento?: number;
  roles?: String[];

  constructor(init?: Partial<UsuarioDto>) {
    super();
    if (init) {
      Object.assign(this, init);
    }
  }
}
