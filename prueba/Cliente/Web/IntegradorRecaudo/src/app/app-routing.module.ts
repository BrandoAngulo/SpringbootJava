import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { AccesoDenegadoComponent } from './shared/components_custom/acceso_denegado/acceso-denegado.component';
import { AuthGuard } from './core/guard/auth.guard';
import { noAutenticacion } from './core/guard/no-auth.guard';

const AppRoutes: Routes = [
  {
    path: '',
    redirectTo: 'autenticacion',
    pathMatch: 'full',
  },
  {
    path: 'autenticacion',
    loadChildren: () =>
      import('./modules/autenticacion/autenticacion.module').then(
        (m) => m.AutenticacionModule
      ),
    canActivate: [noAutenticacion],
    title: 'Autenticacion',
  },
  {
    path: 'accesodenegado',
    component: AccesoDenegadoComponent,
    title: 'Acceso Denegado',
  },
  {
    path: 'integradorRecaudo',
    loadChildren: () =>
      import('./modules/home/home.module').then((m) => m.HomeModule),
    canMatch: [AuthGuard],
    title: 'Home',
  },
  { path: '**', redirectTo: 'accesodenegado', pathMatch: 'full' },
];

@NgModule({
  imports: [
    RouterModule.forRoot(AppRoutes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
