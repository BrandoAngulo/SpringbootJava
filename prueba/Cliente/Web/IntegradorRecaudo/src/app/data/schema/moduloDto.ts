import { SubModuloDto } from './submoduloDto';

export class ModuloDto {
  id: number;
  ruta?: String;
  icono?: String;
  titulo: String;
  submodulos: SubModuloDto[];

  constructor(init?: Partial<ModuloDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
