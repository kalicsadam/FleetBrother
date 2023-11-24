import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Field } from 'src/app/data/dto/field.dto';

@Component({
  selector: 'app-field-overview',
  templateUrl: './field-overview.component.html',
  styleUrls: ['./field-overview.component.scss']
})
export class FieldOverviewComponent {
  @Input() field : Field | undefined

  @Output() onDelete : EventEmitter<Field> = new EventEmitter();
}
