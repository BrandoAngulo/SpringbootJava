import {
  CUSTOM_ELEMENTS_SCHEMA,
  NgModule,
  NO_ERRORS_SCHEMA,
} from '@angular/core';
import { CommonModule } from '@angular/common';

import { MonitoringRoutingModule } from './monitoring-routing.module';
import { MaterialModule } from 'src/app/shared/utils/material/material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { LogsComponent } from './logs/logs.component';
import { ProcessValidationComponent } from './process-validation/process-validation.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [LogsComponent, ProcessValidationComponent],
  imports: [
    CommonModule,
    MonitoringRoutingModule,
    MaterialModule,
    SharedModule,
    ReactiveFormsModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
})
export class MonitoringModule {}
