import { Component } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { Schema } from 'src/app/data/dto/schema.dto';
import { SchemaManagementService } from 'src/app/shared/services/schema-management.service';
import { SchemaCreateComponent } from '../../components/schema-create/schema-create.component';
import { SchemaCreationRequestBody } from 'src/app/data/requestbody/schema-creation.dto';
import { MessageDialogService } from 'src/app/shared/services/message-dialog.service';

@Component({
  selector: 'app-schema-management-page',
  templateUrl: './schema-management-page.component.html',
  styleUrls: ['./schema-management-page.component.scss']
})
export class SchemaManagementPageComponent {
  schemas : Schema[] = []

  constructor(
    private schemaService : SchemaManagementService,
    private _bottomSheet: MatBottomSheet,
    private dialogService : MessageDialogService
    ){

  }
  ngOnInit(): void {
    this.fetchData()
  }
  fetchData() {
    this.schemaService.getAllSchemas().subscribe(
      result => {this.schemas = result}
    )
  }

  openCreatePanel(){
    const ref = this._bottomSheet.open(SchemaCreateComponent)
    ref.afterDismissed().subscribe((schema : SchemaCreationRequestBody) => {
      if(schema != null && schema.name != null){
        this.schemaService.createSchema(schema).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Schema has been created.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }
}
