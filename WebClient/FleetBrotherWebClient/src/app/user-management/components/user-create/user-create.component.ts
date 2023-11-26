import { Component, EventEmitter, Output } from '@angular/core';
import { UserCreationRequestBody } from 'src/app/data/requestbody/user-creation.dto';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.scss']
})
export class UserCreateComponent {
  inputUsername : string = ""
  inputPassword : string = ""
  inputIsAdmin : boolean = false

  @Output() onCreate : EventEmitter<UserCreationRequestBody> = new EventEmitter()

  OnSubmit(){
    if(this.inputUsername != "" && this.inputPassword != ""){
      this.onCreate.emit({
        username: this.inputUsername,
        password: this.inputPassword,
        isAdmin: this.inputIsAdmin
      })

      this.inputUsername = ""
      this.inputPassword = ""
      this.inputIsAdmin = false
    }
  }
}
