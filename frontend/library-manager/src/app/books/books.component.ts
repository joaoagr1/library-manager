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
  newBook: Book = {
    title: '',
    author: '',
    isbn: '',
    publicationDate: '',
    category: ''
  };
  displayedColumns: string[] = ['id', 'title', 'author', 'isbn', 'publicationDate', 'category', 'actions'];

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

  createBook(): void {
    this.booksService.createBook(this.newBook).subscribe(
      (newBook: Book) => {
        this.books.push(newBook);
        this.resetNewBook();
      },
      (error) => {
        console.error('Error creating book', error);
      }
    );
  }

  deleteBook(id?: string): void {
    if (!id) {
      console.error('Book ID is undefined');
      return;
    }
    this.booksService.deleteBook(id).subscribe(
      () => {
        this.books = this.books.filter(b => b.id !== id);
      },
      (error) => {
        console.error('Error deleting book', error);
      }
    );
  }

  private resetNewBook(): void {
    this.newBook = {
      title: '',
      author: '',
      isbn: '',
      publicationDate: '',
      category: ''
    };
  }
}