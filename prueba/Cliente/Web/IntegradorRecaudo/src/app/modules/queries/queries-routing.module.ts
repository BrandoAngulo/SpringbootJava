import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DatabaseQueriesComponent } from './database-queries/database-queries.component';
import { ExecuteQueriesComponent } from './execute-queries/execute-queries.component';
import { MassiveQueriesComponent } from './massive-queries/massive-queries.component';

const routes: Routes = [
  {
    path: 'database',
    component: DatabaseQueriesComponent,
  },
  {
    path: 'massive',
    component: MassiveQueriesComponent,
  },
  {
    path: 'execute',
    component: ExecuteQueriesComponent,
  },
  { path: '**', redirectTo: 'integradorRecaudo', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class QueriesRoutingModule {}
