import { AfterViewInit, Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Measurement } from 'src/app/data/dto/measurement.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { CarReportingService } from 'src/app/shared/services/car-reporting.service';
import { exportExcel } from 'src/app/shared/util/excel.util';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnChanges, AfterViewInit {
  @Input() carId : number = 0;
  @Input() schema : Schema | undefined;

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
    if(this.carId != null && this.schema != null){
      this.fetchData()
      this.displayedColumns.push(...(this.schema.fields.map(schema => schema.key)))
    }
  }

  fetchData(){
    this.carReportingService.getMeasurements(this.carId, this.schema!.id).subscribe(measurements =>{
      this.measurements = measurements
      this.rawData = measurements.map(m => {
        let obj = {...({timestamp: m.timestamp}), ...(m.data)}
        return obj
      })
      this.dataSource.data = this.rawData
    })
  }

  export(){
    exportExcel(this.rawData, this.schema!.name);
  }
}
