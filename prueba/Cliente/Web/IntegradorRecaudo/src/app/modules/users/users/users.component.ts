import { Component, inject, OnInit, ViewChild } from '@angular/core';
import {
  AbstractControl,
  UntypedFormBuilder,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import { MatSelect } from '@angular/material/select';

import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { UsuarioDto } from 'src/app/data/schema/usuarioDto';
import { SpinerComponent } from 'src/app/shared/components_custom/spinner/spiner.component';
import { Constant } from 'src/app/shared/utils/constant';
import { ValidatorsCustom } from 'src/app/shared/utils/validators';
import { RolesService } from '../../../core/services/roles.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {
  private usuarioService = inject(UsuarioService);
  private rolesService = inject(RolesService);
  private formBuilder = inject(UntypedFormBuilder);
  private alertsService = inject(AlertsService);
  private spinnerService = inject(NgxSpinnerService);

  public spinner = new SpinerComponent(this.spinnerService);

  //DataTable
  public tableData: Array<UsuarioDto> = new Array<UsuarioDto>();
  public estado: boolean = true;
  public edit: boolean = true;

  public columnHeader = {
    id: 'ID',
    documento: 'Documento',
    nombres: 'Nombre',
    apellidos: 'Apellido',
    correo: 'Email',
    acciones: 'Acciones',
  };
  //DataTable

  //Formulario
  public form: UntypedFormGroup;
  public rolesUsuario: String[];
  public usuarioDto: UsuarioDto = new UsuarioDto();
  public usuarioId: number = null;
  public modoEdicion: boolean = false;
  @ViewChild('selectTipoUsuarioFocus') selectTipoUsuarioFocus: MatSelect;
  public passVisible: boolean = true;

  //cadenas para errores
  public documentoError: string = '';
  public nombresError: string = '';
  public apellidosError: string = '';
  public emailError: string = '';
  public usuarioError: string = '';
  public passwordError: string = '';
  public passwordSeguridadError: boolean = false;
  public tipoUsuarioError: string = '';

  constructor() {
    this.buildForm();
  }

  ngOnInit(): void {
    this.getRoles();
    this.getAll();
  }

  buildForm(): void {
    this.form = this.formBuilder.group({
      documento: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS_NUMEROS),
        ],
      ],
      nombres: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_2),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS),
        ],
      ],
      apellidos: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_2),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS),
        ],
      ],
      correo: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_100),
          Validators.pattern(Constant.PATTERN_CORREO),
        ],
      ],
      nombre_usuario: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS_NUMEROS),
        ],
      ],
      contrasena: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_CONTRASENA),
          Validators.maxLength(Constant.CAMPO_MAXIMO_CONTRASENA),
          Validators.pattern(Constant.PATTERN_CONTRASENA),
          ValidatorsCustom.validarSiHayEspacios,
        ],
      ],
      roles: [[''], [Validators.required]],
    });
  }

  //getters
  get documento(): AbstractControl {
    return this.form.get('documento');
  }

  get nombres(): AbstractControl {
    return this.form.get('nombres');
  }

  get apellidos(): AbstractControl {
    return this.form.get('apellidos');
  }

  get correo(): AbstractControl {
    return this.form.get('correo');
  }

  get nombreUsuario(): AbstractControl {
    return this.form.get('nombre_usuario');
  }

  get contrasena(): AbstractControl {
    return this.form.get('contrasena');
  }

  get roles(): AbstractControl {
    return this.form.get('roles');
  }

  //validar campos is-invalid
  validarDocumentoCampo(): boolean {
    return this.documento.errors && this.documento.touched;
  }

  validarNombresCampo(): boolean {
    return this.nombres.errors && this.nombres.touched;
  }

  validarApellidosCampo(): boolean {
    return this.apellidos.errors && this.apellidos.touched;
  }

  validarEmailCampo(): boolean {
    return this.correo.errors && this.correo.touched;
  }

  validarusuarioCampo(): boolean {
    return this.nombreUsuario.errors && this.nombreUsuario.touched;
  }

  validarContrasenaCampo(): boolean {
    return this.contrasena.errors && this.contrasena.touched;
  }

  validarTipoUsuarioCampo(): boolean {
    return this.roles.errors && this.roles.touched;
  }
  //validar campos is-invalid

  //validar campos is-valid
  validarDocumentoCampoValido(): boolean {
    return this.documento.valid;
  }

  validarNombresCampoValido(): boolean {
    return this.nombres.valid;
  }

  validarApellidosCampoValido(): boolean {
    return this.apellidos.valid;
  }

  validarEmailCampoValido(): boolean {
    return this.correo.valid;
  }

  validarusuarioCampoValido(): boolean {
    return this.nombreUsuario.valid;
  }

  validarContrasenaCampoValido(): boolean {
    return this.contrasena.valid;
  }

  validarTipoUsuarioCampoValido(): boolean {
    return this.roles.valid;
  }
  //validar campos is-valid

  //validar que error mostrar de acuerdo al campo
  validarDocumento(): boolean {
    let status = false;
    if (this.documento.dirty) {
      if (this.documento.hasError('required')) {
        this.documentoError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.documento.hasError('minlength')) {
        this.documentoError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.documento.hasError('maxlength')) {
        this.documentoError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.documento.hasError('pattern')) {
        this.documentoError = Constant.ERROR_CAMPO_SOLO_NUMEROS_LETRAS;
        status = true;
      }
    }

    return status;
  }

  validarNombres(): boolean {
    let status = false;
    if (this.nombres.dirty) {
      if (this.nombres.hasError('required')) {
        this.nombresError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.nombres.hasError('minlength')) {
        this.nombresError = Constant.ERROR_CAMPO_MINIMO_2;
        status = true;
      } else if (this.nombres.hasError('maxlength')) {
        this.nombresError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.nombres.hasError('pattern')) {
        this.nombresError = Constant.ERROR_CAMPO_SOLO_LETRAS;
        status = true;
      }
    }

    return status;
  }

  validarApellidos(): boolean {
    let status = false;
    if (this.apellidos.dirty) {
      if (this.apellidos.hasError('required')) {
        this.apellidosError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.apellidos.hasError('minlength')) {
        this.apellidosError = Constant.ERROR_CAMPO_MINIMO_2;
        status = true;
      } else if (this.apellidos.hasError('maxlength')) {
        this.apellidosError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.apellidos.hasError('pattern')) {
        this.apellidosError = Constant.ERROR_CAMPO_SOLO_LETRAS;
        status = true;
      }
    }

    return status;
  }

  validarEmail(): boolean {
    let status = false;
    if (this.correo.dirty) {
      if (this.correo.hasError('required')) {
        this.emailError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.correo.hasError('minlength')) {
        this.emailError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.correo.hasError('maxlength')) {
        this.emailError = Constant.ERROR_CAMPO_MAXIMO_100;
        status = true;
      } else if (this.correo.hasError('pattern')) {
        this.emailError = Constant.ERROR_CAMPO_EMAIL_INVALIDO;
        status = true;
      }

      return status;
    }
  }

  validarusuario(): boolean {
    let status = false;
    if (this.nombreUsuario.dirty) {
      if (this.nombreUsuario.hasError('required')) {
        this.usuarioError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.nombreUsuario.hasError('minlength')) {
        this.usuarioError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.nombreUsuario.hasError('maxlength')) {
        this.usuarioError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.nombreUsuario.hasError('pattern')) {
        this.usuarioError = Constant.ERROR_CAMPO_SOLO_NUMEROS_LETRAS;
        status = true;
      }

      return status;
    }
  }

  validarPassword(): boolean {
    let status = false;
    this.passwordSeguridadError = false;
    if (this.contrasena.dirty) {
      if (this.contrasena.hasError('required')) {
        this.passwordError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.contrasena.hasError('minlength')) {
        this.passwordError = Constant.ERROR_CAMPO_MINIMO_CONTRASENA;
        status = true;
      } else if (this.contrasena.hasError('maxlength')) {
        this.passwordError = Constant.ERROR_CAMPO_MAXIMO_CONTRASENA;
        status = true;
      } else if (
        this.contrasena.hasError('pattern') ||
        this.contrasena.hasError('hayEspacios')
      ) {
        this.passwordSeguridadError = true;
      }
    }

    return status;
  }

  validarTipoUsuario(): boolean {
    let status = false;
    if (this.roles.dirty) {
      if (this.roles.hasError('required')) {
        this.tipoUsuarioError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }

    return status;
  }
  //validar que error mostrar de acuerdo al campo

  limpiarFormulario(): void {
    this.form.reset();
    this.modoEdicion = false;
    this.usuarioId = null;
    this.roles.setValue('');
    this.nombreUsuario.enable();
    this.contrasena.enable();
  }

  passVisibleToogle(): void {
    if (!this.modoEdicion) {
      this.passVisible = !this.passVisible;
    }
  }

  getRoles(): void {
    this.spinner.spinnerShow();
    this.rolesService
      .getRolesNombre()
      .pipe(
        finalize(() => {
          this.spinner.spinnerHide();
        })
      )
      .subscribe(
        (res: any) => {
          this.rolesUsuario = res;
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  getAll(): void {
    this.spinner.spinnerShow();

    this.usuarioService
      .getAll()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.tableData = res as UsuarioDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  registrar(): void {
    if (this.form.valid) {
      this.usuarioDto = {
        nombre_usuario: this.nombreUsuario.value,
        contrasena: this.contrasena.value,
        nombres: this.nombres.value,
        apellidos: this.apellidos.value,
        correo: this.correo.value,
        documento: this.documento.value,
        roles: this.roles.value,
      };

      this.spinner.spinnerShow();

      this.usuarioService
        .create(this.usuarioDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data) => {
            this.limpiarFormulario();
            this.getAll();
            this.alertsService.toast(
              'success',
              'Usuario registrado existosamente.'
            );
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

  actualizarUsuario(): void {
    if (this.form.valid) {
      this.usuarioDto = {
        id: this.usuarioId,
        nombre_usuario: this.nombreUsuario.value,
        contrasena: this.contrasena.value,
        nombres: this.nombres.value,
        apellidos: this.apellidos.value,
        correo: this.correo.value,
        documento: this.documento.value,
        roles: this.roles.value,
      };

      this.usuarioService
        .update(this.usuarioDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data: UsuarioDto) => {
            this.getAll();
            this.alertsService.toast(
              'success',
              'Usuario actualizado existosamente.'
            );

            this.limpiarFormulario();
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
    }
  }

  actualizarEstado(id: number): void {
    this.alertsService.fireConfirm(
      'warning',
      'Esta seguro de cambiar el estado?',
      '',
      () => {
        this.spinner.spinnerShow();
        this.usuarioService
          .updateEstado(id)
          .pipe(finalize(() => this.spinner.spinnerHide()))
          .subscribe(
            (data) => {
              this.getAll();
              this.alertsService.toast('success', 'Cambio de estado exito.');

              this.limpiarFormulario();
            },
            (err) => {
              this.alertsService.fireError(err);
            }
          );
      }
    );
  }

  cargarUsuario(usuarioEdit: UsuarioDto): void {
    this.form.patchValue(usuarioEdit);
    this.usuarioId = usuarioEdit.id;

    this.nombreUsuario.disable();
    this.contrasena.setValue('**********');
    this.contrasena.disable();

    this.roles.setValue(usuarioEdit.roles);

    this.modoEdicion = true;
    this.selectTipoUsuarioFocus.focus();
  }
}
