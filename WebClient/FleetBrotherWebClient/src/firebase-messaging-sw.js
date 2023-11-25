importScripts('https://www.gstatic.com/firebasejs/10.6.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/10.6.0/firebase-messaging-compat.js');

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
firebase.initializeApp({
    apiKey: "AIzaSyDM9uDH71OjUhydk7sK-utarrQmGe_sYiE",
    authDomain: "fleetbrotheraut.firebaseapp.com",
    projectId: "fleetbrotheraut",
    storageBucket: "fleetbrotheraut.appspot.com",
    messagingSenderId: "446181553044",
    appId: "1:446181553044:web:8d01986620c7dadac85581"
});
  
// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();
