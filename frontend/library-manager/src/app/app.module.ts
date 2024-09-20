import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './users/users.component';
import { LoansComponent } from './loans/loans.component';
import { RouterModule } from '@angular/router';
import { UsersService } from './services/users-service';
import { HttpClientModule } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar'; // Importando MatToolbarModule

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoansComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    MatToolbarModule // Adicionando MatToolbarModule aos imports
  ],
  providers: [
    UsersService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }