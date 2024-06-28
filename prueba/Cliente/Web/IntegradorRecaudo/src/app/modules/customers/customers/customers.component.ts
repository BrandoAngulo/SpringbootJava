import {
  Component,
  ElementRef,
  inject,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { ClienteDto } from 'src/app/data/schema/clienteDto';
import { ServidoresDto } from 'src/app/data/schema/servidoresDto';
import { VersionesDto } from 'src/app/data/schema/versionesDto';
import { TimeZoneDto } from 'src/app/data/schema/timeZoneDto';
import { SpinerComponent } from 'src/app/shared/components_custom/spinner/spiner.component';
import { CustomersService } from './customers.service';
import { ServersService } from 'src/app/core/services/servers.service';
import { VersionsService } from 'src/app/core/services/versions.service';
import { Constant } from 'src/app/shared/utils/constant';
import { ValidatorsCustom } from 'src/app/shared/utils/validators';
import { TimeZoneService } from 'src/app/core/services/timezone.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css'],
})
export class CustomersComponent implements OnInit {
  private formBuilder = inject(FormBuilder);
  private customersService = inject(CustomersService);
  private versionsService = inject(VersionsService);
  private serversService = inject(ServersService);
  private timeZoneService = inject(TimeZoneService);
  private alertsService = inject(AlertsService);
  private spinnerService = inject(NgxSpinnerService);
  private renderer = inject(Renderer2);
  private element = inject(ElementRef);

  spinner = new SpinerComponent(this.spinnerService);

  //Formulario
  public formularioClientes: FormGroup;
  public clienteDto: ClienteDto = new ClienteDto();
  public modoEdicion: boolean = false;
  public idClienteEdit: number;
  public timeZones: Array<TimeZoneDto> = new Array<TimeZoneDto>();
  public versiones: Array<VersionesDto> = new Array<VersionesDto>();
  public servidores: Array<ServidoresDto> = new Array<ServidoresDto>();
  public servidorSeleccionado: ServidoresDto;
  public versionSeleccionada: ServidoresDto;
  public zonaHorariaSeleccionada: ServidoresDto;
  public maxDate = new Date();
  //Formulario

  //cadenas para errores
  public nombreAdminError: string = '';
  public celularError: string = '';
  public emailError: string = '';
  public documentoError: string = '';
  public documentoFacturacionError: string = '';
  public tipoFacturaError: string = '';
  public tipoMonedaError: string = '';
  public fechaCreacionError: string = '';
  public servidorError: string = '';
  public tipoClienteError: string = '';
  public nombreJavaError: string = '';
  public nombreWebError: string = '';
  public puertoWebError: string = '';
  public puertoMovilError: string = '';
  public versionError: string = '';
  public timeZoneError: string = '';

  //DataTable
  @ViewChild(MatPaginator) paginator: MatPaginator;
  public tableData: Array<ClienteDto> = new Array<ClienteDto>();
  public estado: boolean = true;
  public edit: boolean = true;
  public eliminar: boolean = true;

  public columnHeader = {
    id: 'ID',
    fecha_creacion: 'Fecha creación',
    servidor: 'Servidor',
    nombre_java: 'Cliente',
    nombre_web: 'Web Cliente',
    puerto_web: 'Puerto Web',
    puerto_movil: 'Puerto Web',
    version: 'Versión',
    zona_horaria: 'Zona Horaria',
    activo: 'Estado',
    acciones: 'Acciones',
  };
  //DataTable

  constructor() {
    this.buildForm();
  }

  ngOnInit(): void {
    this.getAll();
    this.getTimezones();
    this.getVersiones();
    this.getSevidores();
    this.limpiarFormulario();
  }

  buildForm(): void {
    this.formularioClientes = this.formBuilder.group({
      nombre_admin: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          // Validators.pattern(Constant.PATTERN_LETRAS_NUMEROS),
        ],
      ],
      celular: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_2),
          Validators.maxLength(Constant.CAMPO_MAXIMO_20),
          Validators.pattern(Constant.PATTERN_NUMEROS),
        ],
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_100),
          Validators.pattern(Constant.PATTERN_CORREO),
        ],
      ],
      documento: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS_NUMEROS),
        ],
      ],
      documento_facturacion: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS_NUMEROS),
        ],
      ],
      tipo_factura: ['', [Validators.required]],
      tipo_moneda: ['', [Validators.required]],
      fecha_creacion: [
        '',
        [
          Validators.required,
          ValidatorsCustom.validarFechaMayorMax(this.maxDate),
        ],
      ],
      tipo_cliente: ['', [Validators.required]],
      nombre_java: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_2),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS_NUMEROS),
        ],
      ],
      puerto_web: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_6),
          Validators.pattern(Constant.PATTERN_NUMEROS),
        ],
      ],
      puerto_movil: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_6),
          Validators.pattern(Constant.PATTERN_NUMEROS),
        ],
      ],
      nombre_web: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
          Validators.pattern(Constant.PATTERN_LETRAS_NUMEROS),
        ],
      ],
      id_servidor: ['', [Validators.required]],
      id_zona_horaria: ['', [Validators.required]],
      id_version: ['', [Validators.required]],
    });
  }

  //getters
  get id(): AbstractControl {
    return this.formularioClientes.get('id');
  }

  get nombre_admin(): AbstractControl {
    return this.formularioClientes.get('nombre_admin');
  }

  get celular(): AbstractControl {
    return this.formularioClientes.get('celular');
  }

  get email(): AbstractControl {
    return this.formularioClientes.get('email');
  }

  get documento(): AbstractControl {
    return this.formularioClientes.get('documento');
  }

  get documento_facturacion(): AbstractControl {
    return this.formularioClientes.get('documento_facturacion');
  }

  get tipo_factura(): AbstractControl {
    return this.formularioClientes.get('tipo_factura');
  }

  get tipo_moneda(): AbstractControl {
    return this.formularioClientes.get('tipo_moneda');
  }

  get fecha_creacion(): AbstractControl {
    return this.formularioClientes.get('fecha_creacion');
  }

  get tipo_cliente(): AbstractControl {
    return this.formularioClientes.get('tipo_cliente');
  }

  get nombre_java(): AbstractControl {
    return this.formularioClientes.get('nombre_java');
  }

  get puerto_web(): AbstractControl {
    return this.formularioClientes.get('puerto_web');
  }

  get puerto_movil(): AbstractControl {
    return this.formularioClientes.get('puerto_movil');
  }

  get nombre_web(): AbstractControl {
    return this.formularioClientes.get('nombre_web');
  }

  get id_servidor(): AbstractControl {
    return this.formularioClientes.get('id_servidor');
  }

  get id_zona_horaria(): AbstractControl {
    return this.formularioClientes.get('id_zona_horaria');
  }

  get id_version(): AbstractControl {
    return this.formularioClientes.get('id_version');
  }

  //getters

  //validar que error mostrar de acuerdo al campo
  validarNombreAdmin(): boolean {
    let status = false;
    if (this.nombre_admin.dirty) {
      if (this.nombre_admin.hasError('required')) {
        this.nombreAdminError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.nombre_admin.hasError('minlength')) {
        this.nombreAdminError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.nombre_admin.hasError('maxlength')) {
        this.nombreAdminError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.nombre_admin.hasError('pattern')) {
        this.nombreAdminError = Constant.ERROR_CAMPO_SOLO_NUMEROS_LETRAS;
        status = true;
      }
    }

    return status;
  }

  validarCelular(): boolean {
    let status = false;
    if (this.celular.dirty) {
      if (this.celular.hasError('required')) {
        this.celularError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.celular.hasError('minlength')) {
        this.celularError = Constant.ERROR_CAMPO_MINIMO_2;
        status = true;
      } else if (this.celular.hasError('maxlength')) {
        this.celularError = Constant.ERROR_CAMPO_MAXIMO_20;
        status = true;
      } else if (this.celular.hasError('pattern')) {
        this.celularError = Constant.ERROR_CAMPO_SOLO_NUMEROS;
        status = true;
      }
    }

    return status;
  }

  validarEmail(): boolean {
    let status = false;
    if (this.email.dirty) {
      if (this.email.hasError('required')) {
        this.emailError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.email.hasError('minlength')) {
        this.emailError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.email.hasError('maxlength')) {
        this.emailError = Constant.ERROR_CAMPO_MAXIMO_100;
        status = true;
      } else if (this.email.hasError('pattern')) {
        this.emailError = Constant.ERROR_CAMPO_EMAIL_INVALIDO;
        status = true;
      }
    }

    return status;
  }

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

  validarDocumentoFacturacion(): boolean {
    let status = false;
    if (this.documento_facturacion.dirty) {
      if (this.documento_facturacion.hasError('required')) {
        this.documentoFacturacionError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.documento_facturacion.hasError('minlength')) {
        this.documentoFacturacionError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.documento_facturacion.hasError('maxlength')) {
        this.documentoFacturacionError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.documento_facturacion.hasError('pattern')) {
        this.documentoFacturacionError =
          Constant.ERROR_CAMPO_SOLO_NUMEROS_LETRAS;
        status = true;
      }
    }

    return status;
  }

  validarTipoFactura(): boolean {
    let status = false;
    if (this.tipo_factura.dirty) {
      if (this.tipo_factura.hasError('required')) {
        this.tipoFacturaError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }
    return status;
  }

  validarTipoMoneda(): boolean {
    let status = false;
    if (this.tipo_moneda.dirty) {
      if (this.tipo_moneda.hasError('required')) {
        this.tipoMonedaError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }
    return status;
  }

  validarFechaCreacion(): boolean {
    let status = false;
    if (this.fecha_creacion.dirty) {
      if (this.fecha_creacion.hasError('required')) {
        this.fechaCreacionError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.fecha_creacion.hasError('fechaMayorMax')) {
        this.fechaCreacionError = Constant.ERROR_CAMPO_FECHA_MAYOR;
        status = true;
      }
    }

    return status;
  }

  validarServidor(): boolean {
    let status = false;
    if (this.id_servidor.dirty) {
      if (this.id_servidor.hasError('required')) {
        this.servidorError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }
    return status;
  }

  validarTipoCliente(): boolean {
    let status = false;
    if (this.tipo_cliente.dirty) {
      if (this.tipo_cliente.hasError('required')) {
        this.tipoClienteError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }
    return status;
  }

  validarNombreJava(): boolean {
    let status = false;
    if (this.nombre_java.dirty) {
      if (this.nombre_java.hasError('required')) {
        this.nombreJavaError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.nombre_java.hasError('minlength')) {
        this.nombreJavaError = Constant.ERROR_CAMPO_MINIMO_2;
        status = true;
      } else if (this.nombre_java.hasError('maxlength')) {
        this.nombreJavaError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.nombre_java.hasError('pattern')) {
        this.nombreJavaError = Constant.ERROR_CAMPO_SOLO_NUMEROS_LETRAS;
        status = true;
      }
    }

    return status;
  }

  validarNombreWeb(): boolean {
    let status = false;
    if (this.nombre_web.dirty) {
      if (this.nombre_web.hasError('required')) {
        this.nombreWebError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.nombre_web.hasError('minlength')) {
        this.nombreWebError = Constant.ERROR_CAMPO_MINIMO_2;
        status = true;
      } else if (this.nombre_web.hasError('maxlength')) {
        this.nombreWebError = Constant.ERROR_CAMPO_MAXIMO_50;
        status = true;
      } else if (this.nombre_web.hasError('pattern')) {
        this.nombreWebError = Constant.ERROR_CAMPO_SOLO_NUMEROS_LETRAS;
        status = true;
      }
    }

    return status;
  }

  validarPuertoWeb(): boolean {
    let status = false;
    if (this.puerto_web.dirty) {
      if (this.puerto_web.hasError('required')) {
        this.puertoWebError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.puerto_web.hasError('minlength')) {
        this.puertoWebError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.puerto_web.hasError('maxlength')) {
        this.puertoWebError = Constant.ERROR_CAMPO_MAXIMO_6;
        status = true;
      } else if (this.nombre_admin.hasError('pattern')) {
        this.puertoWebError = Constant.ERROR_CAMPO_SOLO_NUMEROS;
        status = true;
      }
    }

    return status;
  }

  validarPuertomovil(): boolean {
    let status = false;
    if (this.puerto_movil.dirty) {
      if (this.puerto_movil.hasError('required')) {
        this.puertoMovilError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      } else if (this.puerto_movil.hasError('minlength')) {
        this.puertoMovilError = Constant.ERROR_CAMPO_MINIMO_4;
        status = true;
      } else if (this.puerto_movil.hasError('maxlength')) {
        this.puertoMovilError = Constant.ERROR_CAMPO_MAXIMO_6;
        status = true;
      } else if (this.puerto_movil.hasError('pattern')) {
        this.puertoMovilError = Constant.ERROR_CAMPO_SOLO_NUMEROS;
        status = true;
      }
    }

    return status;
  }

  validarVersion(): boolean {
    let status = false;
    if (this.id_version.dirty) {
      if (this.id_version.hasError('required')) {
        this.versionError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }
    return status;
  }

  validarTimezone(): boolean {
    let status = false;
    if (this.id_zona_horaria.dirty) {
      if (this.id_zona_horaria.hasError('required')) {
        this.timeZoneError = Constant.ERROR_CAMPO_REQUERIDO;
        status = true;
      }
    }
    return status;
  }

  //validar que error mostrar de acuerdo al campo

  getTimezones(): void {
    this.spinner.spinnerShow();
    this.timeZoneService
      .getAll()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.timeZones = res as TimeZoneDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  getVersiones(): void {
    this.spinner.spinnerShow();
    this.versionsService
      .getAll()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.versiones = res as VersionesDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  getSevidores(): void {
    this.spinner.spinnerShow();
    this.serversService
      .getAll()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.servidores = res as ServidoresDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  getAll(): void {
    this.spinner.spinnerShow();
    this.customersService
      .getAll()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.tableData = res as ClienteDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }

  cargarCliente(clienteEdit: ClienteDto): void {
    let arrayServers = this.servidores.find(
      (servidor) => servidor.nombre === clienteEdit.servidor
    );

    let arrayVersiones = this.versiones.find(
      (version) => version.nombre_version === clienteEdit.version
    );
    let arrayTimeZone = this.timeZones.find(
      (zona_horaria) => zona_horaria.descripcion === clienteEdit.zona_horaria
    );

    this.limpiarFormulario();

    this.idClienteEdit = clienteEdit.id;
    this.renderer.selectRootElement('#nombre_admin').focus();

    this.formularioClientes.patchValue(clienteEdit);

    this.formularioClientes.patchValue({
      id_servidor: arrayServers,
      id_version: arrayVersiones,
      id_zona_horaria: arrayTimeZone,
      tipo_cliente: clienteEdit.tipo_cliente,
    });

    this.modoEdicion = true;
  }

  actualizarEstado(clienteCambiarEstado: number): void {
    this.alertsService.fireConfirm(
      'warning',
      `Está seguro de cambiar el estado del cliente ?`,
      '',
      () => {
        this.spinner.spinnerShow();
        this.customersService
          .updateEstado(clienteCambiarEstado)
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

  eliminarCliente(clienteEliminar: ClienteDto): void {
    this.alertsService.fireConfirm(
      'error',
      `Está seguro de eliminar al cliente <span class="text-danger">${clienteEliminar.nombre_java.toUpperCase()}</span> ?`,
      '',
      () => {
        this.spinner.spinnerShow();
        this.customersService
          .eliminar(clienteEliminar.id)
          .pipe(finalize(() => this.spinner.spinnerHide()))
          .subscribe(
            (data) => {
              this.getAll();
              this.alertsService.toast(
                'success',
                'El cliente ha sido eliminado.'
              );

              this.limpiarFormulario();
            },
            (err) => {
              this.alertsService.fireError(err);
            }
          );
      }
    );
  }

  limpiarFormulario(): void {
    this.formularioClientes.reset();
    this.modoEdicion = false;
    this.clienteDto = null;
  }

  registrar(): void {
    if (this.formularioClientes.valid) {
      this.servidorSeleccionado = this.id_servidor.value;
      this.versionSeleccionada = this.id_version.value;
      this.zonaHorariaSeleccionada = this.id_zona_horaria.value;

      this.clienteDto = {
        nombre_admin: this.nombre_admin.value,
        celular: this.celular.value,
        email: this.email.value,
        documento: this.documento.value,
        documento_facturacion: this.documento_facturacion.value,
        tipo_factura: this.tipo_factura.value,
        tipo_moneda: this.tipo_moneda.value,
        fecha_creacion: this.fecha_creacion.value,
        id_servidor: this.servidorSeleccionado.id,
        id_zona_horaria: this.zonaHorariaSeleccionada.id,
        id_version: this.versionSeleccionada.id,
        tipo_cliente: this.tipo_cliente.value,
        nombre_java: this.nombre_java.value,
        puerto_web: this.puerto_web.value,
        puerto_movil: this.puerto_movil.value,
        nombre_web: this.nombre_web.value,
      };

      this.spinner.spinnerShow();

      this.customersService
        .registrar(this.clienteDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data) => {
            this.limpiarFormulario();
            this.getAll();
            this.alertsService.toast(
              'success',
              'Cliente registrado existosamente.'
            );
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
    } else {
      ValidatorsCustom.validateAllFormFields(
        this.formularioClientes,
        this.element
      );
      this.alertsService.toast('error', Constant.ERROR_FORM_INCOMPLETO);
    }
  }

  actualizarCliente(): void {
    if (this.formularioClientes.valid) {
      this.servidorSeleccionado = this.id_servidor.value;
      this.versionSeleccionada = this.id_version.value;
      this.zonaHorariaSeleccionada = this.id_zona_horaria.value;

      this.clienteDto = {
        id: this.idClienteEdit,
        nombre_admin: this.nombre_admin.value,
        celular: this.celular.value,
        email: this.email.value,
        documento: this.documento.value,
        documento_facturacion: this.documento_facturacion.value,
        tipo_factura: this.tipo_factura.value,
        tipo_moneda: this.tipo_moneda.value,
        fecha_creacion: this.fecha_creacion.value,
        id_servidor: this.servidorSeleccionado.id,
        id_zona_horaria: this.zonaHorariaSeleccionada.id,
        id_version: this.versionSeleccionada.id,
        tipo_cliente: this.tipo_cliente.value,
        nombre_java: this.nombre_java.value,
        puerto_web: this.puerto_web.value,
        puerto_movil: this.puerto_movil.value,
        nombre_web: this.nombre_web.value,
      };

      this.spinner.spinnerShow();

      this.customersService
        .update(this.clienteDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe(
          (data) => {
            this.limpiarFormulario();
            this.getAll();
            this.alertsService.toast(
              'success',
              'Cliente registrado existosamente.'
            );
          },
          (err) => {
            this.alertsService.fireError(err);
          }
        );
    } else {
      ValidatorsCustom.validateAllFormFields(
        this.formularioClientes,
        this.element
      );
      this.alertsService.toast('error', Constant.ERROR_FORM_INCOMPLETO);
    }
  }
}
