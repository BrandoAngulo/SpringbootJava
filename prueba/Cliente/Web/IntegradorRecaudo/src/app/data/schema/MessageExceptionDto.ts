export class MessageExceptionDto {
  code?: number;
  message?: string;
  recomen?: string;

  constructor(init?: Partial<MessageExceptionDto>) {
    if (init) {
      Object.assign(this, init);
    }
  }
}
