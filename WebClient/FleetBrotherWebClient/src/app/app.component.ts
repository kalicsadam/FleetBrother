import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
// Import the functions you need from the SDKs you need
import { LoadingService } from './shared/services/loading.service';
import { FirebaseService } from './shared/services/firebase.service';
import { MessagePayload } from 'firebase/messaging';
import { LoginService } from './shared/services/login.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(
    public loginService : LoginService,
    public firabaseService : FirebaseService,
    private loadingService : LoadingService,
    private ref: ChangeDetectorRef
    ){

  }

  ngOnInit(): void {
    this.firabaseService.initializeFirebase((message : MessagePayload) =>{

    });
    this.loadingService.isLoading.subscribe(value=> {
      this.isLoading = value
      this.ref.detectChanges()
    })
  }

  title = 'Fleet Brother';
  sideBarOpened: boolean = false;
  isLoading : boolean = false;
}
