import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/users.model';
import { UsersService } from '../services/users-service';
import { EditUserModalComponent } from './edit-modal-component/edit-modal-component.component';

@Component({
  selector: 'app-user',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'email', 'phone', 'actions'];
  dataSource = new MatTableDataSource<User>();
  newUser: User = { name: '', email: '', phone: '' }; 
  editUserForm: FormGroup;

  constructor(
    private usersService: UsersService,
    private dialog: MatDialog,
    private fb: FormBuilder
  ) {
    this.editUserForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(15)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.usersService.getUsers().subscribe((users: User[]) => {
      this.dataSource.data = users;
    });
  }

  deleteUser(id: number): void {
    this.usersService.deleteUser(id).subscribe(() => {
      this.loadUsers(); 
    });
  }

  createUser(): void {
    this.usersService.createUser(this.newUser).subscribe((user: User) => {
      this.loadUsers(); 
      this.newUser = { name: '', email: '', phone: '' }; 
    });
  }

  openEditModal(user: any): void {
    this.editUserForm.patchValue({
      email: user.email,
      phone: user.phone,
      password: ''
    });
    const dialogRef = this.dialog.open(EditUserModalComponent, {
      data: { form: this.editUserForm }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.onSubmit(user.id);
      }
    });
  }

  onSubmit(userId: number): void {
    if (this.editUserForm.valid) {
      this.usersService.updateUser(this.editUserForm.value).subscribe(response => {
        this.loadUsers();
      });
    }
  }
}