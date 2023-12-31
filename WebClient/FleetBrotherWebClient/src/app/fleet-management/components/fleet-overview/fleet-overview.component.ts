import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Fleet } from 'src/app/data/dto/fleet.dto';

@Component({
  selector: 'app-fleet-overview',
  templateUrl: './fleet-overview.component.html',
  styleUrls: ['./fleet-overview.component.scss']
})
export class FleetOverviewComponent {
  @Input() fleet : Fleet | undefined;

  @Input() showDeleteButton : boolean = true;
  @Input() showDetailsButton : boolean = true; 

  @Output() onDelete : EventEmitter<Fleet> = new EventEmitter();
  @Output() onDetails : EventEmitter<Fleet> = new EventEmitter();
}
