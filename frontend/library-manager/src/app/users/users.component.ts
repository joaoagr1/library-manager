import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from '../models/users.model';
import { UsersService } from '../services/users-service';

@Component({
  selector: 'app-user',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'email', 'registrationDate', 'phone', 'actions'];
  dataSource = new MatTableDataSource<User>();
  newUser: User = { id: 0, name: '', email: '', phone: '' }; // Não precisa de registrationDate

  constructor(private usersService: UsersService) { }

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
      this.newUser = { id: 0, name: '', email: '', phone: '' }; // Reseta o formulário
    });
  }
}