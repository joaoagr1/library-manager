import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  constructor(private snackBar: MatSnackBar) {}

  showError(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 10000,
      panelClass: ['error-snackbar'],
      verticalPosition: 'top' // Ajusta a posição vertical para o topo

    });
  }
}