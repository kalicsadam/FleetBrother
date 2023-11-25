import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private snackBar : MatSnackBar){

  }

  ngOnInit(): void {
    this.requestPermission();
  }

  title = 'FleetBrotherWebClient';
  sideBarOpened: boolean = false;

  firebaseConfig = {
    apiKey: "AIzaSyDM9uDH71OjUhydk7sK-utarrQmGe_sYiE",
    authDomain: "fleetbrotheraut.firebaseapp.com",
    projectId: "fleetbrotheraut",
    storageBucket: "fleetbrotheraut.appspot.com",
    messagingSenderId: "446181553044",
    appId: "1:446181553044:web:8d01986620c7dadac85581"
  }; 

  vapidKey : string = "BIfadVXaaoWDuBjhdHRmAcMKQzUpzA2AQna_-eq13bnfOWeycB1P5LU5P-pVGRFcvR1eoYI2w1dJwc_zpPJYF-A"

  token : string = ""

   // Initialize Firebase
  app = initializeApp(this.firebaseConfig); 

  messaging = getMessaging();

  getFcmToken() {
    getToken(this.messaging, { vapidKey: this.vapidKey }).then((currentToken) => {
      if (currentToken) {
        this.token = currentToken;
        console.log(this.token)
        this.registerPopupService()
        // Send the token to your server and update the UI if necessary
      } else {
        // Show permission request UI
        console.log('No registration token available. Request permission to generate one.');
      }
    }).catch((err) => {
      console.log('An error occurred while retrieving token. ', err);
    });
  }

  requestPermission() {
    console.log('Requesting permission...');
    Notification.requestPermission().then((permission) => {
      if (permission === 'granted') {
        console.log('Notification permission granted.');
        this.getFcmToken()
      }
    })
  }

  registerPopupService(){
    onMessage(this.messaging, (payload) => {
      console.log('Message received. ', payload);
      let snackBarRef = this.snackBar.open(payload.notification?.body ?? "");
    });
  }
}
