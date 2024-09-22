import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'edit-modal-component',
  templateUrl: './edit-modal-component.component.html',
  styleUrls: ['./edit-modal-component.component.css']

})
export class EditUserModalComponent {
  constructor(
    public dialogRef: MatDialogRef<EditUserModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { form: FormGroup }
  ) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    this.dialogRef.close(this.data.form.value);
  }
}