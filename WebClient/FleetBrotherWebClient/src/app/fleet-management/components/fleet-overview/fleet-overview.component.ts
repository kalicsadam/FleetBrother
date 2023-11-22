import { Component, Input } from '@angular/core';
import { Fleet } from 'src/app/data/dto/fleet.dto';

@Component({
  selector: 'app-fleet-overview',
  templateUrl: './fleet-overview.component.html',
  styleUrls: ['./fleet-overview.component.scss']
})
export class FleetOverviewComponent {
  @Input() fleet : Fleet | undefined;
}
