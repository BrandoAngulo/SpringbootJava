import {
  Component,
  ElementRef,
  inject,
  OnInit,
  ViewChild,
} from '@angular/core';
import {
  UntypedFormGroup,
  Validators,
  UntypedFormBuilder,
} from '@angular/forms';

import { LoginService } from './login.service';

import { LoginRequestDto } from 'src/app/data/schema/loginRequestDto';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { SpinerComponent } from 'src/app/shared/components_custom/spinner/spiner.component';
import { Router } from '@angular/router';
import { SuccessLoginDto } from 'src/app/data/schema/successLoginDto';
import { Constant } from 'src/app/shared/utils/constant';
import { finalize } from 'rxjs/operators';
import { NgxSpinnerService } from 'ngx-spinner';
import { AutenticacionStoreService } from 'src/app/core/store/autenticacion/autenticacion-store.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public form: UntypedFormGroup;
  public loginRequestDto: LoginRequestDto = new LoginRequestDto();
  public passVisible: boolean = true;

  public spinner = new SpinerComponent(this.spinnerService);
  @ViewChild('inputPass') inputPass: ElementRef;

  //cadenas para errores
  public userError: string = '';
  public passError: string = '';

  private readonly autenticacionStore = inject(AutenticacionStoreService);

  constructor(
    private formBuilder: UntypedFormBuilder,
    private alertsService: AlertsService,
    private loginService: LoginService,
    private router: Router,
    private spinnerService: NgxSpinnerService
  ) {
    this.buildForm();
    this.spinner.spinnerHide();
  }

  ngOnInit(): void {
    this.spinner.spinnerHide();
  }

  buildForm(): void {
    this.form = this.formBuilder.group({
      correo: [
        '',
        [
          Validators.required,
          Validators.email,
          Validators.minLength(Constant.CAMPO_MINIMO_4),
          Validators.maxLength(Constant.CAMPO_MAXIMO_50),
        ],
      ],
      pass: [
        '',
        [
          Validators.required,
          Validators.minLength(Constant.CAMPO_MINIMO_CONTRASENA),
          Validators.maxLength(Constant.CAMPO_MAXIMO_CONTRASENA),
        ],
      ],
    });
  }

  get correo() {
    return this.form.get('correo');
  }

  get pass() {
    return this.form.get('pass');
  }

  validarUserCampo(): boolean {
    return this.correo.errors && this.correo.touched;
  }

  validarPassCampo(): boolean {
    return this.pass.errors && this.pass.touched;
  }

  validarUserCampoValido(): boolean {
    return this.correo.valid;
  }

  validarPassCampoValido(): boolean {
    return this.pass.valid;
  }

  validarUser(): boolean {
    let status = false;
    if (this.correo.dirty) {
      if (this.correo.hasError('required')) {
        this.userError = 'Campo requerido';
        status = true;
      } else if (this.correo.hasError('minlength')) {
        this.userError = 'Este campo es de mínimo 4';
        status = true;
      } else if (this.correo.hasError('maxlength')) {
        this.userError = 'Este campo es de máximo 50';
        status = true;
      }
    }

    return status;
  }

  validarPass(): boolean {
    let status = false;
    if (this.pass.dirty) {
      if (this.pass.hasError('required')) {
        this.passError = 'Campo requerido';
        status = true;
      } else if (this.pass.hasError('minlength')) {
        this.passError = 'Este campo es de mínimo 8';
        status = true;
      } else if (this.pass.hasError('maxlength')) {
        this.passError = 'Este campo es de máximo 30';
        status = true;
      }
    }

    return status;
  }

  passVisibleToogle(): void {
    this.passVisible = !this.passVisible;
  }

  limpiarFormulario(): void {
    this.form.reset();
  }

  login(): void {
    if (this.form.valid) {
      this.loginRequestDto = {
        correo: this.correo?.value,
        password: this.pass?.value,
      };

      this.spinner.spinnerShow();

      this.loginService
        .auth(this.loginRequestDto)
        .pipe(finalize(() => this.spinner.spinnerHide()))
        .subscribe({
          next: (sesionLoginDto: SuccessLoginDto) => {
            this.autenticacionStore.adicionarSesion(sesionLoginDto);
            this.limpiarFormulario();
            this.router.navigate(['/integradorRecaudo']);
          },
          error: (err) => {
            this.alertsService.fireError(err);
          },
          complete: () => {},
        });
    } else {
      this.form.markAllAsTouched();
      this.alertsService.toast('error', Constant.ERROR_FORM_INCOMPLETO);
    }
  }
}
