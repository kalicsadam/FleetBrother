import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/data/dto/user.dto';
import { UserCreationRequestBody } from 'src/app/data/requestbody/user-creation.dto';

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor() { }

  placeholderUsers : User[] = [
    {
      id: 1,
      username: "test",
      isAdmin: true
    },
    {
      id: 2,
      username: "test2",
      isAdmin: false
    }
  ]

  getUsers() {
    return new BehaviorSubject(this.placeholderUsers)
  }

  createUser(user : UserCreationRequestBody) {
    this.placeholderUsers.push({
      id: Math.floor(Math.random() * 999),
      username: user.username,
      isAdmin: user.isAdmin
    })
    return new BehaviorSubject(true)
  }

  deleteUser(userId : number) {
    this.placeholderUsers = this.placeholderUsers.filter(user => user.id != userId)
    return new BehaviorSubject(true)
  }
}
