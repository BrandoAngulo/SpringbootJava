import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AccesoDenegadoComponent } from './shared/components_custom/acceso_denegado/acceso-denegado.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { AuthorizationService } from './core/services/authorization.service';

import { TokenInterceptorsService } from './core/interceptors/token-interceptors.service';

import { StoreModule } from '@ngrx/store';
import { autenticacionReducer } from './core/store/autenticacion/autenticacion.reducer';
import { environment } from 'src/environments/environment';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { localStorageMetaReducer } from './core/store/local-storage.reducer';

export function tokenGetter() {
  return '';
}

@NgModule({
  declarations: [AppComponent, AccesoDenegadoComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    StoreModule.forRoot(
      { auth: autenticacionReducer },
      {
        metaReducers: [localStorageMetaReducer],
      }
    ),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: !isDevMode() }),
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
      },
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorsService,
      multi: true,
    },
    JwtHelperService,
    AuthorizationService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
