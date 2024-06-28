import {
  CUSTOM_ELEMENTS_SCHEMA,
  NgModule,
  NO_ERRORS_SCHEMA,
} from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpinerComponent } from './components_custom/spinner/spiner.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { SidebarComponent } from './components_layout/sidebar/sidebar.component';
import { NavbarComponent } from './components_layout/navbar/navbar.component';
import { FooterComponent } from './components_layout/footer/footer.component';
import { RouterModule } from '@angular/router';
import { DataTableComponent } from './components_custom/data-table/data-table.component';
import { MaterialModule } from './utils/material/material.module';

@NgModule({
  declarations: [
    SpinerComponent,
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    DataTableComponent,
  ],
  imports: [CommonModule, NgxSpinnerModule, RouterModule, MaterialModule],
  exports: [
    SpinerComponent,
    NgxSpinnerModule,
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    DataTableComponent,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
})
export class SharedModule {}
