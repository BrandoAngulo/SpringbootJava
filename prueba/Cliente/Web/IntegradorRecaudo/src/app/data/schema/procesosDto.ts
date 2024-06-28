import { GenericDto } from './GenericDto';

export class ProcesosDto extends GenericDto {
  id?: number;
  hora_fecha_actual?: string;
  cliente?: string;
  procesos?: string;

  constructor(init?: Partial<ProcesosDto>) {
    super();
    if (init) {
      Object.assign(this, init);
    }
  }
}
