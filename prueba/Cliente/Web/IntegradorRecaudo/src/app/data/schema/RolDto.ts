import { doubleclickbidmanager_v1 } from "googleapis";
import { ModuloDto } from "./moduloDto";

export class RolDto {
  id?: number;
  rol?: string; 
  permisos?: string;
  activo?: boolean;
  modulos?: ModuloDto[];
  submodulos?: number[];

  constructor(init?: Partial<RolDto>) {

    if (init) {
      Object.assign(this, init);
    }
  }
}
