export class VersionesDto {
  id?: number;
  fecha?: string;
  nombre_version?: string;
  java_version?: string;
  php_version?: string;
  movil_version?: string;
  activo?: boolean;

  constructor(init?: Partial<VersionesDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
