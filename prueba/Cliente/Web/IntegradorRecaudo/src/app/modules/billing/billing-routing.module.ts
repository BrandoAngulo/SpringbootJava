import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BillingComponent } from './billing/billing.component';

const routes: Routes = [
  {
    path: '',
    component: BillingComponent,
  },
  { path: '**', redirectTo: 'integradorRecaudo', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BillingRoutingModule { }
