import { Component, OnInit } from '@angular/core';
import { BooksService } from '../services/books-service';
import { Book } from '../models/books.model';
import { MatTableDataSource } from '@angular/material/table';
import { NotificationService } from '../services/notification-service';


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
    category: 'FICTION'
  };
  displayedColumns: string[] = ['id', 'title', 'author', 'isbn', 'publicationDate', 'category', 'actions'];
  dataSource = new MatTableDataSource<Book>();
  categories: string[] = [
    'FICTION', 'NON_FICTION', 'SCIENCE', 'HISTORY', 'FANTASY', 'MYSTERY', 'ROMANCE', 'THRILLER', 'BIOGRAPHY', 'SELF_HELP', 
    'HEALTH', 'TRAVEL', 'COOKING', 'ART', 'TECHNOLOGY', 'BUSINESS', 'POLITICS', 'RELIGION', 'CHILDREN', 'YOUNG_ADULT', 
    'CLASSICS', 'POETRY', 'GRAPHIC_NOVELS', 'HOBBIES', 'SCIENCE_FICTION', 'COMPUTERS', 'BIBLES'
  ];

  constructor(
    private booksService: BooksService,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.listBooks();
  }

  listBooks(): void {
    this.booksService.listBooks().subscribe(
      (data: Book[]) => {
        this.books = data;
        this.dataSource.data = data;
      },
      (error) => {
        this.notificationService.showError(error.error.error);
      }
    );
  }

  createBook(): void {
    this.booksService.createBook(this.newBook).subscribe(


      
      (newBook: Book) => {
        this.books.push(newBook);
        this.dataSource.data = [...this.books]; 
        this.resetNewBook();
      },
      (error) => {
        console.error(this.newBook);
        this.notificationService.showError(error.error.error);
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
        this.dataSource.data = [...this.books]; 
      },
      (error) => {
        this.notificationService.showError(error.error.error);
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