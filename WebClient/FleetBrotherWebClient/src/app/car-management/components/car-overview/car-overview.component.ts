import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Car } from 'src/app/data/dto/cat.dto';

@Component({
  selector: 'app-car-overview',
  templateUrl: './car-overview.component.html',
  styleUrls: ['./car-overview.component.scss']
})
export class CarOverviewComponent {
  @Input() car : Car | undefined;
  @Input() disabled : boolean = false;
  @Output() onAccept : EventEmitter<Car> = new EventEmitter();
  @Output() onDecline : EventEmitter<Car> = new EventEmitter();
}
