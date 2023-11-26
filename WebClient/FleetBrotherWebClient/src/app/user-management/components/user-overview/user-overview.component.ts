import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from 'src/app/data/dto/user.dto';

@Component({
  selector: 'app-user-overview',
  templateUrl: './user-overview.component.html',
  styleUrls: ['./user-overview.component.scss']
})
export class UserOverviewComponent {
  @Input() user : User | undefined

  @Output() onDelete: EventEmitter<User> = new EventEmitter()
}
