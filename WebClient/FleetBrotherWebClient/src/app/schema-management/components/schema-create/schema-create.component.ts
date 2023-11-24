import { Component } from '@angular/core';
import { MatBottomSheetRef } from '@angular/material/bottom-sheet';

@Component({
  selector: 'app-schema-create',
  templateUrl: './schema-create.component.html',
  styleUrls: ['./schema-create.component.scss']
})
export class SchemaCreateComponent {
  inputName : string = "";

  constructor(private bottomSheetRef : MatBottomSheetRef<SchemaCreateComponent>){

  }

  onSubmit(){
    this.bottomSheetRef.dismiss({
      name: this.inputName,
    })
  }
}
