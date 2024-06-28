import {
  CUSTOM_ELEMENTS_SCHEMA,
  NgModule,
  NO_ERRORS_SCHEMA,
} from '@angular/core';
import { CommonModule } from '@angular/common';

import { QueriesRoutingModule } from './queries-routing.module';
import { MaterialModule } from 'src/app/shared/utils/material/material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { DatabaseQueriesComponent } from './database-queries/database-queries.component';
import { MassiveQueriesComponent } from './massive-queries/massive-queries.component';
import { ExecuteQueriesComponent } from './execute-queries/execute-queries.component';

@NgModule({
  declarations: [DatabaseQueriesComponent, MassiveQueriesComponent, ExecuteQueriesComponent],
  imports: [CommonModule, QueriesRoutingModule, MaterialModule, SharedModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
})
export class QueriesModule {}
