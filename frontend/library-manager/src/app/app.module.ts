import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './users/users.component';
import { LoansComponent } from './loans/loans.component';
import { RouterModule } from '@angular/router';  // Importando RouterModule
import { UsersService } from './services/users-service';  // Importando o serviço
import { HttpClientModule } from '@angular/common/http';  // Importar HttpClientModule para usar HttpClient

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoansComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule   // Importando HttpClientModule
  ],
  providers: [UsersService],  // Adicionando UsersService aos providers
  bootstrap: [AppComponent]
})
export class AppModule { }
