import { Component, OnInit, inject } from '@angular/core';
import { AbstractControl } from '@angular/forms';

import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertsService } from 'src/app/core/services/alerts.service';
import { ValidacionProcesosService } from 'src/app/core/services/validacion-procesos.service';
import { ProcesosDto } from 'src/app/data/schema/procesosDto';
import { SpinerComponent } from 'src/app/shared/components_custom/spinner/spiner.component';

@Component({
  selector: 'app-process-validation',
  templateUrl: './process-validation.component.html',
  styleUrls: ['./process-validation.component.css'],
})
export class ProcessValidationComponent implements OnInit {
  private alertsService = inject(AlertsService);
  private spinnerService = inject(NgxSpinnerService);
  private validacionProcesosService = inject(ValidacionProcesosService);

  public spinner = new SpinerComponent(this.spinnerService);

  //Tabla procesos
  public tablaProcesos: Array<ProcesosDto> = new Array<ProcesosDto>();

  public encabezados = {
    hora_fecha_actual: 'Hora y fecha actual',
    cliente: 'Cliente',
    procesos: 'Procesos',
  };

  constructor() {
    this.cargarDatos();
  }

  ngOnInit(): void {}

  cargarDatos(): void {
    this.spinner.spinnerShow();

    this.validacionProcesosService
      .consultarProcesos()
      .pipe(finalize(() => this.spinner.spinnerHide()))
      .subscribe(
        (res: any) => {
          this.tablaProcesos = res as ProcesosDto[];
        },
        (err) => {
          this.alertsService.fireError(err);
        }
      );
  }
}
