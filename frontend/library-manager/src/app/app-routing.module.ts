import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserComponent } from './users/users.component';
import { BooksComponent } from './books/books.component';
import { LoansComponent } from './loans/loans.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: 'users', component: UserComponent },
  { path: 'books', component: BooksComponent },
  { path: 'loans', component: LoansComponent },
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }