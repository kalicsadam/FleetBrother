import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/data/dto/user.dto';
import { UserCreationRequestBody } from 'src/app/data/requestbody/user-creation.dto';
import { MessageDialogService } from 'src/app/shared/services/message-dialog.service';
import { UserManagementService } from 'src/app/shared/services/user-management.service';

@Component({
  selector: 'app-user-creation-page',
  templateUrl: './user-creation-page.component.html',
  styleUrls: ['./user-creation-page.component.scss']
})
export class UserCreationPageComponent implements OnInit {
  constructor(
    private userService : UserManagementService,
    private dialogService : MessageDialogService
    ){

  }

  ngOnInit(): void {
    this.fetchData()
  }

  users : User[] = []

  fetchData(){
    this.userService.getUsers().subscribe(users=> {
      this.users = users
    })
  }

  onCreate(user : UserCreationRequestBody){
    this.userService.createUser(user).subscribe(result => {
      if(result){
        this.dialogService.openSuccessDialog("Operation successful.", "User has been created.")
      } else {
        this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
      }
      this.fetchData();
    })
  }

  onDelete(user : User){
    const ref = this.dialogService.openChooseDialog("Delete user", `Are you sure you want to delete user: ${user.username}${user.isAdmin ? " (Admin)" : ""}?`);
    ref.afterClosed().subscribe(decision => {
      if(decision){
        this.userService.deleteUser(user.id).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "User has been deleted.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }
}
