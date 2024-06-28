import { CUSTOM_ELEMENTS_SCHEMA, NgModule,  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { AutenticacionRoutingModule } from './autenticacion-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/utils/material/material.module';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [
    LoginComponent,
  ],
  imports: [
    CommonModule,
    AutenticacionRoutingModule,    
    ReactiveFormsModule,
    MaterialModule,
    SharedModule
  ],
  exports: [
  ], 
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AutenticacionModule { }
