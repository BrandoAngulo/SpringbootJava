import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProcessesComponent } from './processes/processes.component';
import { ServersComponent } from './servers/servers.component';
import { TimeZoneComponent } from './time-zone/time-zone.component';
import { VersionsComponent } from './versions/versions.component';
import { RolesComponent } from './roles/roles.component';

const routes: Routes = [
  {
    path: 'timezone',
    component: TimeZoneComponent,
  },
  {
    path: 'versions',
    component: VersionsComponent,
  },
  {
    path: 'processes',
    component: ProcessesComponent,
  },
  {
    path: 'servers',
    component: ServersComponent,
  },
  {
    path: 'roles',
    component: RolesComponent,
  },
  { path: '**', redirectTo: 'integradorRecaudo', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SettingsRoutingModule {}
