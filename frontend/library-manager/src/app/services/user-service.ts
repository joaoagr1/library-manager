import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { User } from '../models/users.model';
import { NotificationService } from './notification-service';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private apiUrl = '/api/library/users'; // Use o caminho do proxy

  constructor(private http: HttpClient, private notificationService: NotificationService) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  createUser(user: User): Observable<User> {
    console.log(user);
    return this.http.post<User>(this.apiUrl, user);
  }

  updateUser(user: any): Observable<any> {
    // console.log("user clicadoooooooo");
    // console.log(user);

    console.log("entrou no serive????");

    return this.http.put(`${this.apiUrl}/${user.id}`, user);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {

      errorMessage = `Error: ${error.error.message}`;
    } else {
      
       error
      errorMessage = `Error: ${error.error.error}`;
    }
    this.notificationService.showError(errorMessage);
    return throwError(errorMessage);
  }

}
