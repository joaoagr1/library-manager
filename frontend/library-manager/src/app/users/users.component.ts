import { Component, OnInit } from '@angular/core';
import { UsersService } from '../services/users-service';

@Component({
  selector: 'app-user',
  templateUrl: './users.component.html', // Certifique-se de que o caminho está correto
  styleUrls: ['./users.component.css'] // Certifique-se de que o caminho está correto
})
export class UserComponent implements OnInit {

  users: any[] = [];

  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    this.usersService.getUsers().subscribe(data => {
      console.log("teste"+data);
      this.users = data;
    });
  }
}