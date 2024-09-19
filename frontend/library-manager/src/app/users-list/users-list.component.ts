import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users.service';
import { Users } from '../users.model';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users: Users[] = [];

  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.usersService.getAllUsers().subscribe(data => {
      this.users = data;
    });
  }

  deleteUser(id: string): void {
    this.usersService.deleteUser(id).subscribe(() => {
      this.loadUsers(); // Reload the list after deletion
    });
  }
}
