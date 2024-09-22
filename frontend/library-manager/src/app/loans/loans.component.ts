import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Loan } from '../models/loans.model';
import { LoansService } from '../services/loan-service';

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.css']
})
export class LoansComponent implements OnInit {
  displayedColumns: string[] = ['user', 'book', 'loanDate', 'returnDate', 'status'];
  dataSource = new MatTableDataSource<Loan>();
  newLoan: Loan = {
    userId: '',
    bookId: '',
    returnDate: '',
    loanStatus:'ACTIVE',
  };

  isEditing: boolean = false; 


  constructor(private loanService: LoansService) {}

  ngOnInit(): void {
    this.loadLoans();
  }

  loadLoans(): void {
    this.loanService.getAllLoans().subscribe(loans => {
      this.dataSource.data = loans;
    });
  }

  onSubmit(): void {
    this.loanService.createLoan(this.newLoan).subscribe(() => {
      this.loadLoans();
      this.newLoan = {
        userId: '',
        bookId: '',
        loanDate: '',
        returnDate: ''
      };
    });
  }
}