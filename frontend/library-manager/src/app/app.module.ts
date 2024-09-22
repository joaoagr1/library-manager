import { EditUserModalComponent } from './users/edit-modal-component/edit-modal-component.component';
import { NotificationService } from './services/notification-service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './users/users.component';
import { BooksComponent } from './books/books.component';
import { LoansComponent } from './loans/loans.component';
import { HttpClientModule } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar'; 
import { MatTableModule } from '@angular/material/table'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { EditBookDialogComponent } from './books/edit-book-dialog/edit-book-dialog.component';
import { MatSelectModule } from '@angular/material/select';
import { EditLoanDialogComponent } from './loans/edit-loan-dialog/edit-loan-dialog.component'; 

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    BooksComponent,
    LoansComponent,
    EditUserModalComponent,
    HomeComponent,
    EditBookDialogComponent,
    EditLoanDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatToolbarModule,
    MatTableModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    MatSelectModule
  ],
  providers: [
    NotificationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }