import { Component, EventEmitter, Output } from '@angular/core';
import { UserCreationRequestBody } from 'src/app/data/requestbody/user-creation.dto';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.scss']
})
export class UserCreateComponent {
  inputEmail : string = ""
  inputPassword : string = ""
  inputIsAdmin : boolean = false

  @Output() onCreate : EventEmitter<UserCreationRequestBody> = new EventEmitter()

  OnSubmit(){
    if(this.inputEmail != "" && this.inputPassword != ""){
      this.onCreate.emit({
        email: this.inputEmail,
        password: this.inputPassword,
        isAdmin: this.inputIsAdmin
      })

      this.inputEmail = ""
      this.inputPassword = ""
      this.inputIsAdmin = false
    }
  }
}
