import { Component, OnInit } from '@angular/core';
import { BooksService } from '../services/books-service';
import { Book } from '../models/books.model';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  books: Book[] = [];
  recommendedBooks: Book[] = [];

  constructor(private booksService: BooksService) {}

  ngOnInit(): void {
    this.listBooks();
  }

  listBooks(): void {
    this.booksService.listBooks().subscribe(
      (data: Book[]) => {
        this.books = data;
      },
      (error) => {
        console.error('Error fetching books', error);
      }
    );
  }

  createBook(book: Book): void {
    this.booksService.createBook(book).subscribe(
      (newBook: Book) => {
        this.books.push(newBook);
      },
      (error) => {
        console.error('Error creating book', error);
      }
    );
  }

  updateBook(id: string, book: Book): void {
    this.booksService.updateBook(id, book).subscribe(
      (updatedBook: Book) => {
        const index = this.books.findIndex(b => b.id === id);
        if (index !== -1) {
          this.books[index] = updatedBook;
        }
      },
      (error) => {
        console.error('Error updating book', error);
      }
    );
  }

  deleteBook(id: string): void {
    this.booksService.deleteBook(id).subscribe(
      () => {
        this.books = this.books.filter(b => b.id !== id);
      },
      (error) => {
        console.error('Error deleting book', error);
      }
    );
  }

  getRecommendedBooks(userId: string): void {
    this.booksService.getRecommendedBooks(userId).subscribe(
      (data: Book[]) => {
        this.recommendedBooks = data;
      },
      (error) => {
        console.error('Error fetching recommended books', error);
      }
    );
  }
}