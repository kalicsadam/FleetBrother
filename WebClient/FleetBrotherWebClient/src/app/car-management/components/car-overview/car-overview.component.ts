import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Car } from 'src/app/data/dto/cat.dto';

export enum CarOverviewMode {
  ACCEPT_DECLINE,
  DELETE
}

@Component({
  selector: 'app-car-overview',
  templateUrl: './car-overview.component.html',
  styleUrls: ['./car-overview.component.scss']
})
export class CarOverviewComponent {
  modetype = CarOverviewMode;
  @Input() mode : CarOverviewMode = CarOverviewMode.ACCEPT_DECLINE;

  @Input() car : Car | undefined;
  @Input() disabled : boolean = false;
  @Output() onAccept : EventEmitter<Car> = new EventEmitter();
  @Output() onDecline : EventEmitter<Car> = new EventEmitter();
  @Output() onDelete : EventEmitter<Car> = new EventEmitter();
}
