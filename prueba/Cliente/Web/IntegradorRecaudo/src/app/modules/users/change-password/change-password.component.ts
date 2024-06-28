import { Component, inject, OnInit, ViewChild } from '@angular/core';
import {
  AbstractControl,
  UntypedFormBuilder,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { LocalStorageService } from 'src/app/core/services/localStorage.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { AutenticacionStoreService } from 'src/app/core/store/autenticacion/autenticacion-store.service';
import { ChangePasswordDto } from 'src/app/data/schema/changePasswordDto';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';
import { SpinerComponent } from 'src/app/shared/components_custom/spinner/spiner.component';
import { Constant } from 'src/app/shared/utils/constant';
import { ValidatorsCustom } from 'src/app/shared/utils/validators';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  @ViewChild(SpinerComponent) spinner: SpinerComponent;

  //Formulario
  form: UntypedFormGroup;
  passVisible: boolean = true;
  passVisibleNew: boolean = true;
  passVisibleConfrim: boolean = true;
  changePasswordDto: ChangePasswordDto = new ChangePasswordDto();

  //cadenas para errores
  passwordError: string = '';
  passwordNewError: string = '';
  passwordConfirmError: string = '';
  passwordSeguridadError: boolean = false;
  idUsuario: number;
  private idUsuario$: Observable<number>;
  private autenticacionStoreService = inject(AutenticacionStoreService);

  constructor(
    private formBuilder: UntypedFormBuilder,
    private usuarioService: UsuarioService,
    private alertsService: AlertsService
  ) {
    this.idUsuario$ = this.autenticacionStoreService.obtenerId$();
  }

  ngOnInit(): void {
    this.buildForm();

    this.idUsuario$.subscribe((id: number) => {
      this.idUsuario = id;
    });
  }

  buildForm(): void {
    this.form = this.formBuilder.group(
      {
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(Constant.CAMPO_MINIMO_CONTRASENA),
            Validators.maxLength(Constant.CAMPO_MAXIMO_CONTRASENA),
            ValidatorsCustom.validarSiHayEspacios,
          ],
        ],
        passwordNew: [
          '',
          [
            Validators.required,
            Validators.minLength(Constant.CAMPO_MINIMO_CONTRASENA),
            Validators.maxLength(Constant.CAMPO_MAXIMO_CONTRASENA),
            Validators.pattern(Constant.PATTERN_CONTRASENA),
            ValidatorsCustom.validarSiHayEspacios,
          ],
        ],
        passwordConfirm: [
          '',
          [
            Validators.required,
            Validators.minLength(Constant.CAMPO_MINIMO_CONTRASENA),
            Validators.maxLength(Constant.CAMPO_MAXIMO_CONTRASENA),
            Validators.pattern(Constant.PATTERN_CONTRASENA),
          ],
        ],
      },
      {
        validators: ValidatorsCustom.validarQueSeanIguales,
      }
    );
  }

  //getters
  get password(): AbstractControl {
    return this.form.get('password');
  }

  get passwordNew(): AbstractControl {
    return this.form.get('passwordNew');
  }

  get passwordConfirm(): AbstractControl {
    return this.form.get('passwordConfirm');
  }
  //getters

  //validar campos is-invalid
  validarPasswordCampo(): boolean {
    return this.password.errors && this.password.touched;
  }

  validarPasswordNewCampo(): boolean {
    return this.passwordNew.errors && this.passwordNew.touched;
  }

  validarPasswordConfirmCampo(): boolean {
    return this.passwordConfirm.errors && this.passwordConfirm.touched;
  }
  //validar campos is-invalid

  //validar campos is-valid
  validarPasswordCampoValido(): boolean {
    return this.password.valid;
  }

  validarPasswordNewCampoValido(): boolean {
    return this.passwordNew.valid;
  }

  validarPasswordConfirmCampoValido(): boolean {
    return this.passwordConfirm.valid;
  }
  //validar campos is-valid

  //validar que error mostrar de acuerdo al campo
  validarPassword(): boolean {
    let status = false;
    if (this.password.dirty) {
      if (this.password.hasError('required')) {
        this.passwordError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.password.hasError('minlength')) {
        this.passwordError = Constant.ERROR_CAMPO_MINIMO_CONTRASENA;
        status = true;
      } else if (this.password.hasError('maxlength')) {
        this.passwordError = Constant.ERROR_CAMPO_MAXIMO_CONTRASENA;
        status = true;
      } else if (this.password.hasError('hayEspacios')) {
        status = false;
      }
    }

    return status;
  }

  validarPasswordNew(): boolean {
    let status = false;
    this.passwordSeguridadError = false;
    if (this.passwordNew.dirty) {
      if (this.passwordNew.hasError('required')) {
        this.passwordNewError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.passwordNew.hasError('minlength')) {
        this.passwordNewError = Constant.ERROR_CAMPO_MINIMO_CONTRASENA;
        status = true;
      } else if (this.passwordNew.hasError('maxlength')) {
        this.passwordNewError = Constant.ERROR_CAMPO_MAXIMO_CONTRASENA;
        status = true;
      } else if (
        this.passwordNew.hasError('pattern') ||
        this.passwordNew.hasError('hayEspacios')
      ) {
        this.passwordSeguridadError = true;
        status = false;
      }
    }

    return status;
  }

  validarPasswordConfirm(): boolean {
    let status = false;
    if (this.passwordConfirm.dirty) {
      if (this.passwordConfirm.hasError('required')) {
        this.passwordConfirmError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.passwordConfirm.hasError('minlength')) {
        this.passwordConfirmError = Constant.ERROR_CAMPO_MINIMO_CONTRASENA;
        status = true;
      } else if (this.passwordConfirm.hasError('maxlength')) {
        this.passwordConfirmError = Constant.ERROR_CAMPO_MAXIMO_CONTRASENA;
        status = true;
      } else if (this.form.errors) {
        if (this.form.errors.noSonIguales && this.passwordNew.dirty) {
          this.passwordConfirmError =
            Constant.ERROR_CAMPO_CONTRASENA_NO_CONCIDEN;
          status = true;
        }
      }
    }

    return status;
  }
  //validar que error mostrar de acuerdo al campo
  passVisibleToogle(campo_number: number): void {
    switch (campo_number) {
      case 1:
        this.passVisible = !this.passVisible;
        break;
      case 2:
        this.passVisibleNew = !this.passVisibleNew;
        break;
      case 3:
        this.passVisibleConfrim = !this.passVisibleConfrim;
        break;
    }
  }

  actualizar(): void {
    if (this.form.valid) {
      this.changePasswordDto = {
        id_usuario: this.idUsuario,
        contrasena_antigua: this.password.value,
        contrasena_nueva: this.passwordNew.value,
      };

      this.spinner.spinnerShow();
      this.usuarioService
        .changePassword(this.changePasswordDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data) => {
            this.alertsService.toast(
              'success',
              'Contraseña actualizada existosamente.'
            );

            this.limpiarFormulario();
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
    } else {
      this.form.markAllAsTouched();
      this.alertsService.toast('error', Constant.ERROR_FORM_INCOMPLETO);
    }
  }

  limpiarFormulario(): void {
    this.form.reset();
    this.passVisible = true;
    this.passVisibleNew = true;
    this.passVisibleConfrim = true;
  }
}
