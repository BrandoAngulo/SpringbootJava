export class Constant {
  static VERSION = 'v0.3.0 - by Play Technologies &reg';
  static MESES = [
    'enero',
    'febrero',
    'marzo',
    'abril',
    'mayo',
    'junio',
    'julio',
    'agosto',
    'septiembre',
    'octubre',
    'noviembre',
    'diciembre',
  ];

  static PATTERN_LETRAS = /^[A-Za-zñÑÁÉÍÓÚÜáéíóúü\s]+$/;
  static PATTERN_NUMEROS = /^\d+$/;
  static PATTERN_LETRAS_NUMEROS = /^[A-Za-z0-9]+$/;
  static PATTERN_CORREO =
    /^[a-z0-9+_-]+(?:\.[a-z0-9+_-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
  static PATTERN_CONTRASENA =
    /^(?=\D*\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}$/;

  //Cantidad minima Validators
  static CAMPO_MINIMO_2 = 2;
  static CAMPO_MINIMO_4 = 4;
  static CAMPO_MAXIMO_6 = 6;
  static CAMPO_MAXIMO_20 = 20;
  static CAMPO_MAXIMO_30 = 30;
  static CAMPO_MAXIMO_50 = 50;
  static CAMPO_MAXIMO_100 = 100;
  static CAMPO_MINIMO_CONTRASENA = 8;
  static CAMPO_MAXIMO_CONTRASENA = 30;

  //  Form field errors
  static ERROR_FORM_INCOMPLETO =
  'Todos los campos deben estar correctamente diligenciados.';
  static ERROR_CAMPO_REQUERIDO = 'Campo requerido';
  static ERROR_CAMPO_SOLO_LETRAS = 'Solo se permiten letras';
  static ERROR_CAMPO_SOLO_NUMEROS = 'Solo se permiten números';
  static ERROR_CAMPO_SOLO_NUMEROS_LETRAS = 'Solo se permiten números y letras';
  static ERROR_CAMPO_MINIMO_2 = 'Este campo es de mínimo 2 caracteres';
  static ERROR_CAMPO_MINIMO_4 = 'Este campo es de mínimo 4 caracteres';
  static ERROR_CAMPO_MAXIMO_6 = 'Este campo es de máximo 6 caracteres';
  static ERROR_CAMPO_MAXIMO_20 = 'Este campo es de máximo 20 caracteres';
  static ERROR_CAMPO_MAXIMO_30 = 'Este campo es de máximo 30 caracteres';
  static ERROR_CAMPO_MAXIMO_50 = 'Este campo es de máximo 50 caracteres';
  static ERROR_CAMPO_MAXIMO_100 = 'Este campo es de máximo 100 caracteres';
  static ERROR_CAMPO_EMAIL_INVALIDO = 'El email es inválido';
  static ERROR_CAMPO_FECHA_MAYOR = 'La fecha es mayor';

  //Contraseña Error
  static ERROR_CAMPO_MINIMO_CONTRASENA = 'Este campo es de mínimo 8 caracteres';
  static ERROR_CAMPO_MAXIMO_CONTRASENA = 'Este campo es de máximo 30 caracteres';
  static ERROR_CAMPO_CONTRASENA_INALIDA = 'La contraseña es inválida.';
  static ERROR_CAMPO_CONTRASENA_NO_CONCIDEN = 'Las contraseñas no conciden.';
}
