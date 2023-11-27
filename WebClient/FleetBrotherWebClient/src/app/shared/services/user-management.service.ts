import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { User } from 'src/app/data/dto/user.dto';
import { UserCreationRequestBody } from 'src/app/data/requestbody/user-creation.dto';

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor(private http : HttpClient) { }

  getUsers() {
    return this.http.get<User[]>("/api/user")
  }

  createUser(user : UserCreationRequestBody) {
    return this.http.put("/api/user", user, { observe: 'response' }).pipe(map(response => response.ok))
  }

  deleteUser(userId : number) {
    return this.http.delete("/api/user/" + userId, { observe: 'response' }).pipe(map(response => response.ok))
  }
}
