import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoanListComponent } from './loan-list/loan-list.component';
import { LoanFormComponent } from './loan-form/loan-form.component';



@NgModule({
  declarations: [
    LoanListComponent,
    LoanFormComponent
  ],
  imports: [
    CommonModule
  ]
})
export class LoansModule { }
