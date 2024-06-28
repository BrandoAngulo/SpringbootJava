import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorizationGuard } from 'src/app/core/guard/authorization.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home.component';
import { NotFoundComponent } from './not-found/not-found.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivateChild: [AuthorizationGuard],
    children: [
     
      {
        path: '',
        component: NotFoundComponent,
      },
      {
        path: 'dashboard',
        component: DashboardComponent,
        data: {
          allowedRoles: ['ADMINISTRADOR', 'ANALISTA'],
        },
      },
      {
        path: 'settings',
        loadChildren: () =>
          import('../settings/settings.module').then((m) => m.SettingsModule),
        data: {
          allowedRoles: ['ADMINISTRADOR'],
        },
        title: 'Configuraciones'
      },
      {
        path: 'users',
        loadChildren: () =>
          import('../users/users.module').then((m) => m.UsersModule),
        data: {
          allowedRoles: ['ADMINISTRADOR'],
        },
        title: 'Usuarios'
      },
      {
        path: 'customers',
        loadChildren: () =>
          import('../customers/customers.module').then(
            (m) => m.CustomersModule
          ),
        data: {
          allowedRoles: ['ADMINISTRADOR', 'SOPORTE'],
        },
        title: 'Clientes'
      },
      {
        path: 'queries',
        loadChildren: () =>
          import('../queries/queries.module').then((m) => m.QueriesModule),
        data: {
          allowedRoles: ['ADMINISTRADOR', 'SOPORTE', 'ANALISTA'],
        },
      },
      {
        path: 'monitoring',
        loadChildren: () =>
          import('../monitoring/monitoring.module').then(
            (m) => m.MonitoringModule
          ),
        data: {
          allowedRoles: ['ADMINISTRADOR', 'SOPORTE', 'ANALISTA'],
        },
      },
      {
        path: 'billing',
        loadChildren: () =>
          import('../billing/billing.module').then((m) => m.BillingModule),
        data: {
          allowedRoles: ['ADMINISTRADOR', 'FINANCIERO', 'ANALISTA'],
        },
      },
      {
        path: 'reports',
        loadChildren: () =>
          import('../reports/reports.module').then((m) => m.ReportsModule),
        data: {
          allowedRoles: ['ADMINISTRADOR', 'ANALISTA',],
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
