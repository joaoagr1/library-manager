import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Loan } from '../../models/loans.model';

@Component({
  selector: 'app-edit-loan-dialog',
  templateUrl: './edit-loan-dialog.component.html',
  styleUrls: ['./edit-loan-dialog.component.css']
})
export class EditLoanDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<EditLoanDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { loan: Loan }
  ) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    this.dialogRef.close(this.data.loan);
  }
}