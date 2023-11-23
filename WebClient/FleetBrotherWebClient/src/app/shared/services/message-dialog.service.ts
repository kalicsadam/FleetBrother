import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from '../components/sidebar/message-dialog/message-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class MessageDialogService {

  constructor(private dialog: MatDialog) { }

  openSuccessDialog(title: string = "Success", body: string ="Message"){
    this.dialog.open(MessageDialogComponent, {
      data: {
        title: title,
        body: body,
        error: false
      }
    })
  }

  openErrorDialog(title: string = "Error", body: string ="Message"){
    this.dialog.open(MessageDialogComponent, {
      data: {
        title: title,
        body: body,
        error: true
      }
    })
  }
}
