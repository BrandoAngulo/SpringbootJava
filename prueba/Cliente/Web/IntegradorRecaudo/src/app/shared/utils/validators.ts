import { ElementRef } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
} from '@angular/forms';

export class ValidatorsCustom {
  static validarSiHayEspacios: ValidatorFn = (
    control: FormControl
  ): ValidationErrors | null => {
    const campo = control.value;
    if (campo) {
      return campo.includes(' ') ? { hayEspacios: true } : null;
    }
  };

  static validarQueSeanIguales: ValidatorFn = (
    control: FormGroup
  ): ValidationErrors | null => {
    const contrasena = control.get('passwordNew');
    const confirmarPassword = control.get('passwordConfirm');

    return contrasena.value == confirmarPassword.value
      ? null
      : { noSonIguales: true };
  };

  static validarFechaMayorMax(fechaValida: Date): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const fecha = new Date(control.value);
      if (fecha) {
        return fechaValida > fecha ? null : { fechaMayorMax: true };
      }
    };
  }

  static validateAllFormFields(formGroup: FormGroup, element: ElementRef) {
    formGroup.markAllAsTouched();

    Object.keys(formGroup.controls).forEach((field) => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsDirty();
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control, element);
      }
    });

    for (const field of Object.keys(formGroup.controls)) {
      const control = formGroup.get(field);
      if (control.invalid) {
        const invalidControl = element.nativeElement.querySelector(
          '[formControlName="' + field + '"]'
        );
        invalidControl.focus();
        break;
      }
    }
  }
}
