import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Book } from '../models/books.model';
import { NotificationService } from './notification-service';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private apiUrl = '/api/library/books'; // Use o caminho do proxy

  constructor(private http: HttpClient, private notificationService: NotificationService) {}

  createBook(book: Book): Observable<Book> {
    console.log(book.author + " " + book.title + " " + book.category + " " + book.isbn + " " + book.publicationDate);
    return this.http.post<Book>(this.apiUrl, book);
  }

  getBookById(id: string): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`);
  }

  updateBook(id: string, book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${id}`, book);
  }

  deleteBook(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  listBooks(page: number = 0, size: number = 10): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  getRecommendedBooks(userId: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}/category/${userId}`);
  }

  addBookByTitle(title: string): Observable<Book> {
    return this.http.post<Book>(`${this.apiUrl}/google-api?title=${title}`, {});
  }

  getBookInformation(title: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/google-api?title=${title}`);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error: ${error.error.error}`;
    }
    this.notificationService.showError(errorMessage);
    return throwError(errorMessage);
  }
}