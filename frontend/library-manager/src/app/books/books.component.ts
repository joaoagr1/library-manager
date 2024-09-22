import { Component, OnInit } from '@angular/core';
import { BooksService } from '../services/books-service';
import { Book } from '../models/books.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { EditBookDialogComponent } from './edit-book-dialog/edit-book-dialog.component';
import { NotificationService } from '../services/notification-service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  books: Book[] = [];
  categories: string[] = ['FICTION', 'NON_FICTION', 'SCIENCE', 'HISTORY', 'BIOGRAPHY'];
  newBook: Book = {
    title: '',
    author: '',
    isbn: '',
    publicationDate: '',
    category: 'FICTION'
  };
  displayedColumns: string[] = ['id', 'title', 'author', 'isbn', 'publicationDate', 'category', 'actions'];
  dataSource = new MatTableDataSource<Book>();
  isEditing: boolean = false; 

  constructor(private booksService: BooksService, 
    public dialog: MatDialog,
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
        this.dataSource.data = this.books.slice(); 
        this.resetNewBook();
      },
      (error) => {
        console.log(error);
        this.notificationService.showError(error.error.error);
      }
    );
  }

  deleteBook(id: string): void {
   
    this.booksService.deleteBook(id).subscribe(
      () => {
        this.books = this.books.filter(b => b.id !== id);
        this.dataSource.data = this.books.slice(); 
      },
      (error) => {
        this.notificationService.showError(error.error.error.error);
      }
    );
  }

  editBook(book: Book): void {
    const dialogRef = this.dialog.open(EditBookDialogComponent, {
      width: '400px',
      data: { ...book }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.booksService.updateBook(result).subscribe(
          (updatedBook: Book) => {
            const index = this.books.findIndex(b => b.id === updatedBook.id);
            if (index !== -1) {
              this.books[index] = updatedBook;
              this.dataSource.data = this.books.slice();
            }
          },
          (error) => {
            this.notificationService.showError(error.error.error);
          }
        );
      }
    });
  }

  private resetNewBook(): void {
    this.newBook = {
      title: '',
      author: '',
      isbn: '',
      publicationDate: '',
      category: 'Fiction' 
    };
  }
}