import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BillingRoutingModule } from './billing-routing.module';

import { MaterialModule } from 'src/app/shared/utils/material/material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { BillingComponent } from './billing/billing.component';

@NgModule({
  declarations: [
    BillingComponent
  ],
  imports: [CommonModule, BillingRoutingModule, MaterialModule, SharedModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
})
export class BillingModule {}
