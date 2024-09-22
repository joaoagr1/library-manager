import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan } from '../models/loans.model';


@Injectable({
  providedIn: 'root'
})
export class LoansService {
  private apiUrl = 'api/library/loans';

  constructor(private http: HttpClient) {}

  createLoan(loanRequestDTO: Loan): Observable<Loan> {
    console.log(loanRequestDTO);
    return this.http.post<Loan>(this.apiUrl, loanRequestDTO);
  }

  getAllLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(this.apiUrl);
  }

  getLoanById(id: string): Observable<Loan> {
    return this.http.get<Loan>(`${this.apiUrl}/${id}`);
  }

  updateLoan(id: string, loanRequestDTO: Loan): Observable<Loan> {
    return this.http.put<Loan>(`${this.apiUrl}/${id}`, loanRequestDTO);
  }

  deleteLoan(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}