import { AfterViewInit, Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Car } from 'src/app/data/dto/car.dto';
import { Measurement } from 'src/app/data/dto/measurement.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { CarReportingService } from 'src/app/shared/services/car-reporting.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnChanges, AfterViewInit {
  @Input() car : Car | undefined;
  @Input() schema : Schema | undefined;
  @Input() exportDisabled :boolean | null = false;
  @Output() exportedData : EventEmitter<any[]> = new EventEmitter();

  measurements : Measurement[] = []
  rawData : any[] = []
  displayedColumns: string[] = ["timestamp"];
  dataSource : MatTableDataSource<any> = new MatTableDataSource();

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private carReportingService : CarReportingService){}

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.car != null && this.schema != null){
      this.fetchData()
      this.displayedColumns.push(...(this.schema.fields.map(schema => schema.key)))
    }
  }

  fetchData(){
    this.carReportingService.getMeasurements(this.car!.id, this.schema!.id).subscribe(measurements =>{
      this.measurements = measurements
      this.rawData = measurements.map(m => {
        let obj = {...({timestamp: m.timestamp}), ...(m.data)}
        return obj;
      })
      this.dataSource.data = this.rawData
    })
  }

  export(){
    this.exportedData.emit(this.rawData)
  }

  convertData(element : any){
    if(element instanceof Array){
      return element.join(", ")
    }
    return element?.toString() ?? "";
  }
}
