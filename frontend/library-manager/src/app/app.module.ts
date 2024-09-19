import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import { LoansComponent } from './loans/loans.component';
import { BooksComponent } from './books/books.component';
import { UserListComponent } from './users/user-list/user-list.component';
import { UserFormComponent } from './users/user-form/user-form.component';
import { BookListComponent } from './books/book-list/book-list.component';
import { BookFormComponent } from './books/book-form/book-form.component';
import { LoanListComponent } from './loans/loan-list/loan-list.component';
import { LoanFormComponent } from './loans/loan-form/loan-form.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    LoansComponent,
    BooksComponent,
    UserListComponent,
    UserFormComponent,
    BookListComponent,
    BookFormComponent,
    LoanListComponent,
    LoanFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
