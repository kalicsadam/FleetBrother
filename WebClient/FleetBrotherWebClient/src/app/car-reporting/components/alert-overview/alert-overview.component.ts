import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Alert, ExistsValueAlert, ForbiddenValueAlert, MaxValueAlert, MinValueAlert } from 'src/app/data/dto/alert.dto';

@Component({
  selector: 'app-alert-overview',
  templateUrl: './alert-overview.component.html',
  styleUrls: ['./alert-overview.component.scss']
})
export class AlertOverviewComponent {
  @Input() alert : Alert | undefined

  @Output() onDelete : EventEmitter<Alert> = new EventEmitter()

  isMinValueAlert(alert : Alert): alert is MinValueAlert {
    return (alert as MinValueAlert).minValue != null
  }

  isMaxValueAlert(alert : Alert): alert is MaxValueAlert {
    return (alert as MaxValueAlert).maxValue != null
  }

  isForbiddenValueAlert(alert : Alert): alert is ForbiddenValueAlert {
    return (alert as ForbiddenValueAlert).forbiddenValue != null
  }

  isExistsValueAlert(alert : Alert): alert is ExistsValueAlert {
    return (alert as ExistsValueAlert).exists != null
  }

  onDeletePressed(alert :Alert){
    this.onDelete.emit(alert);
  }
}
