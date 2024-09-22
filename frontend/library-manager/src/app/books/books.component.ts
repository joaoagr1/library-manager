import { Component, OnInit } from '@angular/core';
import { BooksService } from '../services/books-service';
import { Book } from '../models/books.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { EditBookDialogComponent } from './edit-book-dialog/edit-book-dialog.component';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  books: Book[] = [];
  categories: string[] = ['Fiction', 'Non-Fiction', 'Science', 'History', 'Biography'];
  newBook: Book = {
    title: '',
    author: '',
    isbn: '',
    publicationDate: '',
    category: 'Fiction'
  };
  displayedColumns: string[] = ['id', 'title', 'author', 'isbn', 'publicationDate', 'category', 'actions'];
  dataSource = new MatTableDataSource<Book>();
  isEditing: boolean = false; // Adiciona a propriedade isEditing

  constructor(private booksService: BooksService, public dialog: MatDialog) {}

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
        console.error('Error fetching books', error);
      }
    );
  }

  createBook(): void {
    this.booksService.createBook(this.newBook).subscribe(
      (newBook: Book) => {
        this.books.push(newBook);
        this.dataSource.data = this.books.slice(); // Atualiza a dataSource com uma nova referência
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
        this.dataSource.data = this.books.slice(); 
      },
      (error) => {
        console.error('Error deleting book', error);
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
            console.error('Error updating book', error);
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
      category: 'Fiction' // Valor padrão para a categoria
    };
  }
}