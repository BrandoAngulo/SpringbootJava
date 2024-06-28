import { OnInit, inject, Component } from '@angular/core';

import {
  FormGroup,
  Validators,
  FormBuilder,
  AbstractControl,
} from '@angular/forms';

import { finalize } from 'rxjs/operators';
import { NgxSpinnerService } from 'ngx-spinner';
import { Constant } from 'src/app/shared/utils/constant';
import { VersionesDto } from 'src/app/data/schema/versionesDto';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { VersionsService } from 'src/app/core/services/versions.service';
import { SpinerComponent } from 'src/app/shared/components_custom/spinner/spiner.component';

@Component({
  selector: 'app-versions',
  templateUrl: './versions.component.html',
  styleUrls: ['./versions.component.css'],
})
export class VersionsComponent implements OnInit {
  private formBuilder = inject(FormBuilder);
  private spinnerService = inject(NgxSpinnerService);
  private versionService = inject(VersionsService);
  private alertsService = inject(AlertsService);

  spinner = new SpinerComponent(this.spinnerService);

  //Formulario
  public formularioVersiones: FormGroup;
  public versionDto: VersionesDto = new VersionesDto();
  public versionId: number = null;
  public modoEdicion: boolean = false;
  public manejaEstados: boolean = true;

  // Errores
  public nombreVersionError: string = '';
  public javaVersionError: string = '';
  public phpVersionError: string = '';
  public movilVersionError: string = '';

  //Tabla de versiones
  public tablaVersiones: Array<VersionesDto> = new Array<VersionesDto>();

  public permiteEditar: boolean = true;
  public encabezados = {
    fecha: 'Fecha',
    nombre_version: 'Nombre de versión',
    java_version: 'Versión Java',
    php_version: 'Versión PHP',
    movil_version: 'Versión Movil',
    acciones: 'Acciones',
  };

  constructor() {
    this.buildForm();
  }

  ngOnInit(): void {
    this.consultarVersiones();
    this.limpiarFormulario();
  }

  buildForm(): void {
    this.formularioVersiones = this.formBuilder.group({
      nombre_version: ['', [Validators.required]],
      java_version: ['', [Validators.required]],
      php_version: ['', [Validators.required]],
      movil_version: ['', [Validators.required]],
    });
  }

  //getters
  get nombreVersion(): AbstractControl {
    return this.formularioVersiones.get('nombre_version');
  }

  get javaVersion(): AbstractControl {
    return this.formularioVersiones.get('java_version');
  }

  get phpVersion(): AbstractControl {
    return this.formularioVersiones.get('php_version');
  }

  get movilVersion(): AbstractControl {
    return this.formularioVersiones.get('movil_version');
  }

  //Validar campos
  validarNombreVersion(): boolean {
    let status = false;
    if (this.nombreVersion.dirty) {
      if (this.nombreVersion.hasError('required')) {
        this.nombreVersionError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }

    return status;
  }

  validarJavaVersion(): boolean {
    let status = false;
    if (this.javaVersion.dirty) {
      if (this.javaVersion.hasError('required')) {
        this.javaVersionError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }

    return status;
  }

  validarPhpVersion(): boolean {
    let status = false;
    if (this.phpVersion.dirty) {
      if (this.phpVersion.hasError('required')) {
        this.phpVersionError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }

    return status;
  }

  validarMovilVersion(): boolean {
    let status = false;
    if (this.movilVersion.dirty) {
      if (this.movilVersion.hasError('required')) {
        this.movilVersionError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }

    return status;
  }

  consultarVersiones(): void {
    this.spinner.spinnerShow();

    this.versionService
      .getAll()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.tablaVersiones = res as VersionesDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  limpiarFormulario(): void {
    this.formularioVersiones.reset();
    this.modoEdicion = false;
    this.versionDto = null;
  }

  registrarVersion(): void {
    if (this.formularioVersiones.valid) {
      this.versionDto = {
        nombre_version: this.nombreVersion.value,
        java_version: this.javaVersion.value,
        php_version: this.phpVersion.value,
        movil_version: this.movilVersion.value,
      };

      this.spinner.spinnerShow();

      this.versionService
        .create(this.versionDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data) => {
            this.limpiarFormulario();
            this.consultarVersiones();
            this.alertsService.toast(
              'success',
              'Versión registrada existosamente.'
            );
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
    } else {
      this.formularioVersiones.markAllAsTouched();
      this.alertsService.toast('error', Constant.ERROR_FORM_INCOMPLETO);
    }
  }

  actualizarVersion() {
    if (this.formularioVersiones.valid) {
      this.versionDto = {
        id: this.versionId,
        nombre_version: this.nombreVersion.value,
        java_version: this.javaVersion.value,
        php_version: this.phpVersion.value,
        movil_version: this.movilVersion.value,
      };
      this.spinner.spinnerShow();

      this.versionService
        .update(this.versionDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data: VersionesDto) => {
            this.consultarVersiones();
            this.alertsService.toast(
              'success',
              'Versión actualizada existosamente.'
            );
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
      this.limpiarFormulario();
    } else {
      this.formularioVersiones.markAllAsTouched();
      this.alertsService.toast('error', Constant.ERROR_FORM_INCOMPLETO);
    }
  }

  cambiarEstado(id: number) {
    this.alertsService.fireConfirm(
      'warning',
      'Esta seguro de cambiar el estado?',
      '',
      () => {
        this.spinner.spinnerShow();
        this.versionService
          .cambiarEstado(id)
          .pipe(finalize(() => this.spinner.spinnerHide()))
          .subscribe(
            (data) => {
              this.consultarVersiones();
              this.alertsService.toast('success', 'Cambio de estado exitoso.');

              this.limpiarFormulario();
            },
            (err) => {
              this.alertsService.fireError(err);
            }
          );
      }
    );
  }

  cargarVersion(versionCargada: VersionesDto) {
    this.formularioVersiones.patchValue(versionCargada);
    this.versionId = versionCargada.id;
    this.modoEdicion = true;
  }
}
