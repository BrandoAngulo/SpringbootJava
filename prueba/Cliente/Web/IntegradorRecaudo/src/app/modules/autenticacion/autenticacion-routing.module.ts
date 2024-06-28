import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SessionActiveGuard } from 'src/app/core/guard/session-active.guard';
import { LoginComponent } from './login/login.component';

const AutenticacionModule: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [SessionActiveGuard],
  },
  { path: '**', redirectTo: 'login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(AutenticacionModule)],
  exports: [RouterModule],
})
export class AutenticacionRoutingModule {}
