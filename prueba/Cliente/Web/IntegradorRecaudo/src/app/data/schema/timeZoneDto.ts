export class TimeZoneDto {
  id?: number;
  utc?: string;
  zona_horaria?: string;
  pais?: string;
  ciudad?: string;
  orden?: number;
  estado?: string;
  descripcion?: string;

  constructor(init?: Partial<TimeZoneDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
