import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { initializeApp } from 'firebase/app';
import { MessagePayload, getMessaging, getToken, onMessage } from 'firebase/messaging';

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  constructor(private snackBar : MatSnackBar) { }

  private firebaseConfig = {
    apiKey: "AIzaSyDM9uDH71OjUhydk7sK-utarrQmGe_sYiE",
    authDomain: "fleetbrotheraut.firebaseapp.com",
    projectId: "fleetbrotheraut",
    storageBucket: "fleetbrotheraut.appspot.com",
    messagingSenderId: "446181553044",
    appId: "1:446181553044:web:8d01986620c7dadac85581"
  }; 

  private vapidKey : string = "BIfadVXaaoWDuBjhdHRmAcMKQzUpzA2AQna_-eq13bnfOWeycB1P5LU5P-pVGRFcvR1eoYI2w1dJwc_zpPJYF-A"

  token : string = ""

  private app = initializeApp(this.firebaseConfig); 

  private messaging = getMessaging();

  private callback : (message: MessagePayload) => void = () => {}

  initializeFirebase(callback: (message: MessagePayload) => void){
    this.callback = callback;
    this.requestPermission();
  }

  private getFcmToken() {
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

  private registerPopupService(){
    onMessage(this.messaging, (payload) => {
      console.log('Message received. ', payload);
      this.callback(payload)
    });
  }
}
