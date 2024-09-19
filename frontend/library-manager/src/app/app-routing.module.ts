import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { UserComponent } from '../app/users/users.component';
import { BooksComponent } from '../app/books/books.component';
import { LoansComponent } from '../app/loans/loans.component';

const routes: Routes = [
  { path: 'users', component: UserComponent },
  { path: 'books', component: BooksComponent },
  { path: 'loans', component: LoansComponent },
  { path: '', redirectTo: '/users', pathMatch: 'full' },  // Rota padrão
  { path: '**', redirectTo: '/users' }  // Redireciona qualquer rota inválida
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
