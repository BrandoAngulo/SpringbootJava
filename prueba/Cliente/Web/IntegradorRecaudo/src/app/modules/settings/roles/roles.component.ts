import { Component, OnInit, ViewChild, inject } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { RolDto } from 'src/app/data/schema/RolDto';
import { SpinerComponent } from 'src/app/shared/components_custom/spinner/spiner.component';
import { Constant } from 'src/app/shared/utils/constant';
import { finalize } from 'rxjs/operators';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { RolesService } from 'src/app/core/services/roles.service';
import { ModulosService } from 'src/app/core/services/modulos.service';
import { MatSelect } from '@angular/material/select';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.scss'],
})
export class RolesComponent implements OnInit {
  private rolesService = inject(RolesService);
  private modulosService = inject(ModulosService);
  private formBuilder = inject(FormBuilder);
  private alertsService = inject(AlertsService);
  private spinnerService = inject(NgxSpinnerService);

  public spinner = new SpinerComponent(this.spinnerService);

  //DataTable
  public tablaRoles: Array<RolDto> = new Array<RolDto>();
  public estado: boolean = true;
  public editar: boolean = true;

  public encabezadosRoles = {
    id: 'ID',
    rol: 'Nombre rol',
    acciones: 'Acciones',
  };

  //Formulario
  public formularioRoles: FormGroup;
  public rolDto: RolDto = new RolDto();
  public modoEdicion: boolean = false;
  public submodulo: String[];
  public rolId: number = null;
  @ViewChild('selectRolFocus') selectRolFocus: MatSelect;

  //Cadenas de error
  public nombreRolError: string = '';
  public modulosError: string = '';

  constructor() {
    this.buildForm();
  }

  ngOnInit(): void {
    this.obtenerRoles();
    this.obtenerModulos();
    this.limpiarFormulario();
  }

  buildForm(): void {
    this.formularioRoles = this.formBuilder.group({
      nombre_rol: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_30),
        ],
      ],
      submodulos: ['', [Validators.required]],
    });
  }

  //getters
  get nombreRol(): AbstractControl {
    return this.formularioRoles.get('nombre_rol');
  }

  get submodulos(): AbstractControl {
    return this.formularioRoles.get('submodulos');
  }

  //Validar errores de acuerdo a los campos
  validarNombreRol(): boolean {
    let status = false;
    if (this.nombreRol.dirty) {
      if (this.nombreRol.hasError('required')) {
        this.nombreRolError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.nombreRol.hasError('minlength')) {
        this.nombreRolError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.nombreRol.hasError('maxlength')) {
        this.nombreRolError = Constant.ERROR_CAMPO_MAXIMO_30;
        status = true;
      }
    }
    return status;
  }

  validarModulo(): boolean {
    let status = false;
    if (this.submodulos.dirty) {
      if (this.submodulos.hasError('required')) {
        this.modulosError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }
    return status;
  }

  limpiarFormulario(): void {
    this.formularioRoles.reset();
    this.modoEdicion = false;
  }

  obtenerRoles(): void {
    this.spinner.spinnerShow();

    this.rolesService
      .getRoles()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.tablaRoles = res as RolDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  obtenerModulos(): void {
    this.spinner.spinnerShow();

    this.modulosService
      .getModulos()
      .pipe(
        finalize(() => {
          this.spinner.spinnerHide();
        })
      )
      .subscribe(
        (res: any) => {
          this.submodulo = res;
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  registrarRol(): void {
    if (this.formularioRoles.valid) {
      this.rolDto = {
        rol: this.nombreRol.value,
        permisos: null,
        submodulos: this.submodulos.value,
      };

      this.spinner.spinnerShow();

      this.rolesService
        .create(this.rolDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data) => {
            this.limpiarFormulario();
            this.obtenerRoles();
            this.alertsService.toast(
              'success',
              'Rol registrado existosamente.'
            );
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
    } else {
      this.formularioRoles.markAllAsTouched();
      this.alertsService.toast('error', Constant.ERROR_FORM_INCOMPLETO);
    }
  }

  actualizarEstado(id: number): void {
    this.alertsService.fireConfirm(
      'warning',
      'Esta seguro de cambiar el estado?',
      '',
      () => {
        this.spinner.spinnerShow();
        this.rolesService
          .updateEstado(id)
          .pipe(finalize(() => this.spinner.spinnerHide()))
          .subscribe(
            (data) => {
              this.obtenerRoles();
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

  actualizarRol(): void {
    if (this.formularioRoles.valid) {
      this.rolDto = {
        id: this.rolId,
        rol: this.nombreRol.value,
        submodulos: this.submodulos.value,
      };

      this.rolesService
        .update(this.rolDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data) => {
            this.obtenerRoles();
            this.alertsService.toast(
              'success',
              'Rol actualizado existosamente.'
            );

            this.limpiarFormulario();
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
    }
  }

  cargarRol(rolEdit: RolDto): void {
    this.formularioRoles.patchValue(rolEdit);
    this.rolId = rolEdit.id;
    let submodulosArray = rolEdit.modulos.flatMap((obj) => obj.submodulos);

    let arrayIndices = submodulosArray.reduce((arrIndex, element) => {
      arrIndex.push(element.id.toString());
      return arrIndex;
    }, []);

    this.nombreRol.setValue(rolEdit.rol);
    this.submodulos.setValue(arrayIndices);

    this.modoEdicion = true;
    this.selectRolFocus.focus();
  }
}
