import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Loan } from '../models/loans.model';
import { LoansService } from '../services/loan-service';
import { MatDialog } from '@angular/material/dialog';
import { EditLoanDialogComponent } from './edit-loan-dialog/edit-loan-dialog.component';
import { NotificationService } from '../services/notification-service';


@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.css']
})
export class LoansComponent implements OnInit {
  displayedColumns: string[] = ['user', 'book', 'loanDate', 'returnDate', 'status', 'actions'];
  dataSource = new MatTableDataSource<Loan>();
  newLoan: Loan = {
    userId: '',
    bookId: '',
    returnDate: '',
    loanStatus:'ACTIVE',
    loanDate: ''
  };

  isEditing: boolean = false; 


  constructor(private loanService: LoansService,
     public dialog: MatDialog,
     private notificationService: NotificationService
    ) {}


  ngOnInit(): void {
    this.loadLoans();
  }

  loadLoans(): void {
    this.loanService.getAllLoans().subscribe(loans => {
      console.log(loans);
      this.dataSource.data = loans;
    },
    error => {
      this.notificationService.showError(error.error.error);
    }
  );
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
    },
    error => {
      this.notificationService.showError(error.error.error);
    }
  );

  }

  onEdit(loan: Loan): void {
    console.log(loan);
    const dialogRef = this.dialog.open(EditLoanDialogComponent, {
      width: '400px',
      data: { loan }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result);
        this.loanService.updateLoan(result).subscribe(
          () => {
            this.loadLoans();
          },
          error => {
            this.notificationService.showError(error.error.error);
          }
        );
      }
    });
  
  }

  onDelete(id: string): void {
    this.loanService.deleteLoan(id).subscribe(() => {
      this.loadLoans();
    });
  }
}