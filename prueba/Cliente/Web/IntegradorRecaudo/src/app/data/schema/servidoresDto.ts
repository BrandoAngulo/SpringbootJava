export class ServidoresDto {
  id?: number;
  nombre?: string;

  constructor(init?: Partial<ServidoresDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
