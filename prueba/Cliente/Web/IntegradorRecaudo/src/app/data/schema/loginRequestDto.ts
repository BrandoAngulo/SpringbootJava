import { GenericDto } from './GenericDto';

export class LoginRequestDto extends GenericDto {
  correo?: string;
  password?: string;

  constructor(init?: Partial<LoginRequestDto>) {
    super();
    if (init) {
      Object.assign(this, init);
    }
  }
}
