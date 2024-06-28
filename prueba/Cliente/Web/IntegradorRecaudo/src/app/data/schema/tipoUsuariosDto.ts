export class tipoUsuariosDto {
  id?: number;
  tipo_usuario?: string;
  estado?: string;

  constructor(init?: Partial<tipoUsuariosDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
