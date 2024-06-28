import { SuccessLoginDto } from "src/app/data/schema/successLoginDto";

export interface AutenticacionState {
    sesion: SuccessLoginDto;
  }
  
  export const initialState: AutenticacionState = {
    sesion: {} as SuccessLoginDto,
  };
  