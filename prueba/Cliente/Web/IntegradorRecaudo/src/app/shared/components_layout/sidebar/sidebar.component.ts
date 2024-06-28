import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { AutenticacionStoreService } from 'src/app/core/store/autenticacion/autenticacion-store.service';
import { LoginRequestDto } from 'src/app/data/schema/loginRequestDto';
import { ModuloDto } from 'src/app/data/schema/moduloDto';
import { UsuarioDto } from 'src/app/data/schema/usuarioDto';
import { LoginService } from 'src/app/modules/autenticacion/login/login.service';
import { SpinerComponent } from '../../components_custom/spinner/spiner.component';

declare const $: any;

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  private loginService = inject(LoginService);
  private autenticacionStoreService = inject(AutenticacionStoreService);
  private modulosUsuario$: Observable<ModuloDto[]>;
  private usuario$: Observable<UsuarioDto>;
  private spinnerService = inject(NgxSpinnerService);
  private usuarioService = inject(UsuarioService);

  public menuItems: ModuloDto[] = [];
  public loginRequestDto: LoginRequestDto = new LoginRequestDto();
  public spinner = new SpinerComponent(this.spinnerService);

  //User
  public usuario: UsuarioDto;
  public nombreMostrar: string = '';

  constructor(private alertsService: AlertsService) {
    this.modulosUsuario$ = this.autenticacionStoreService.obtenerModulos$();
    this.usuario$ = this.autenticacionStoreService.obtenerSesion$();
  }

  ngOnInit() {
    this.consultarUsuario();
    this.consultarModulos();
  }

  consultarModulos() {
    this.modulosUsuario$.subscribe((modulos: ModuloDto[]) => {
      this.menuItems = modulos;
    });
  }

  isMobileMenu() {
    if ($(window).width() > 991) {
      return false;
    }
    return true;
  }

  closeSession() {
    this.spinner.spinnerShow();
    this.loginService.logout();
  }

  confirmCloseSesion() {
    this.alertsService.fireConfirm(
      'error',
      'Esta seguro de cerrar sesión?',
      'Al cerrarla, tendrá que autenticarse de nuevo para realizar alguna operación!',
      () => {
        this.closeSession();
      }
    );
  }

  consultarUsuario(): void {
    this.spinner.spinnerShow();

    this.usuario$.subscribe((usuario: UsuarioDto) => {
      this.usuario = usuario;
      this.nombreMostrar = `${this.usuario.nombres} ${this.usuario.apellidos}`;
      this.spinner.spinnerHide();
    });
  }
}
