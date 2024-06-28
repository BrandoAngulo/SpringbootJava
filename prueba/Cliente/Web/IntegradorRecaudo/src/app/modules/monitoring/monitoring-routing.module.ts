import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogsComponent } from './logs/logs.component';
import { ProcessValidationComponent } from './process-validation/process-validation.component';

const routes: Routes = [
  {
    path: 'logs',
    component: LogsComponent,
  },
  {
    path: 'process_validations',
    component: ProcessValidationComponent,
  },
  { path: '**', redirectTo: 'integradorRecaudo', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MonitoringRoutingModule {}
