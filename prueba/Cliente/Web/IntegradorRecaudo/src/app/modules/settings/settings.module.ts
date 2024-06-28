import {
  CUSTOM_ELEMENTS_SCHEMA,
  NgModule,
  NO_ERRORS_SCHEMA,
} from '@angular/core';
import { CommonModule } from '@angular/common';

import { SettingsRoutingModule } from './settings-routing.module';
import { MaterialModule } from 'src/app/shared/utils/material/material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { TimeZoneComponent } from './time-zone/time-zone.component';
import { VersionsComponent } from './versions/versions.component';
import { ProcessesComponent } from './processes/processes.component';
import { ServersComponent } from './servers/servers.component';
import { RolesComponent } from './roles/roles.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [TimeZoneComponent, VersionsComponent, ProcessesComponent, ServersComponent, RolesComponent],
  imports: [CommonModule, SettingsRoutingModule, MaterialModule, SharedModule, ReactiveFormsModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
})
export class SettingsModule {}
