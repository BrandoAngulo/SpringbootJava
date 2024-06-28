import { GenericDto } from './GenericDto';

export class ChangePasswordDto extends GenericDto {
  id_usuario?: number;
  contrasena_antigua?: string;
  contrasena_nueva?: string;

  constructor(init?: Partial<ChangePasswordDto>) {
    super();
    if (init) {
      Object.assign(this, init);
    }
  }
}
