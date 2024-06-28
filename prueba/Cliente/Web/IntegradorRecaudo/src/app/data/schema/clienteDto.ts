export class ClienteDto {
  id?: number;
  nombre_admin?: string;
  celular?: string;
  email?: string;
  documento?: string;
  documento_facturacion?: string;
  tipo_factura?: string;
  tipo_moneda?: string;
  fecha_creacion?: Date;
  tipo_cliente?: number;
  nombre_java?: string;
  puerto_web?: number;
  puerto_movil?: number;
  nombre_web?: string;
  id_servidor?: number;
  servidor?: string;
  id_zona_horaria?: number;
  zona_horaria?: string;
  id_version?: number;
  version?: string;
  estado?: string;

  constructor(init?: Partial<ClienteDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
