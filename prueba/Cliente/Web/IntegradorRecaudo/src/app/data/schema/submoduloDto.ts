export class SubModuloDto {
  id: number;
  ruta?: String;
  icono?: String;
  titulo: String;
  
  constructor(init?: Partial<SubModuloDto>) {
      if (init) {
        Object.assign(this, init);
      }
    }
}
