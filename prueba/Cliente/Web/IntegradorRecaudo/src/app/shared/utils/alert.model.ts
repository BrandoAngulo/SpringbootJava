export class Alert {
  type: AlertType;
  message: string;
  recomen: string;

  constructor(init?: Partial<Alert>) {
    Object.assign(this, init);
  }
}

export enum AlertType {
    Success,
    Error,
    Info,
    Warning
}
