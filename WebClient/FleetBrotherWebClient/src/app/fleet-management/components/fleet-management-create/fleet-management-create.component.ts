import { Component } from '@angular/core';
import { MatBottomSheetRef } from '@angular/material/bottom-sheet';
import { Fleet } from 'src/app/data/dto/fleet.dto';


@Component({
  selector: 'app-fleet-management-create',
  templateUrl: './fleet-management-create.component.html',
  styleUrls: ['./fleet-management-create.component.scss']
})
export class FleetManagementCreateComponent {
  inputName : string = "";
  inputDescription : string = ""

  constructor(private bottomSheetRef : MatBottomSheetRef<FleetManagementCreateComponent>){

  }

  onSubmit(){
    this.bottomSheetRef.dismiss({
      name: this.inputName,
      description: this.inputDescription
    })
  }
}
