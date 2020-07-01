package com.example.afinal.connection;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseConnection {
    private static FirebaseAuth mAuth;


    private static StorageReference mStorageRef;


    private static FirebaseFirestore db;
    private static FirebaseStorage mFirebaseStorage;


     public static  FirebaseAuth ConnectionAuth(){
        return   mAuth = FirebaseAuth.getInstance();
     }
    /*
      public static StorageReference ConnectionStorage(){
        return  mStorageRef = FirebaseStorage.getInstance().getReference();
    }
                En las ulmas verciones tiene problemas con traer datos vacios
    */


     public static FirebaseFirestore ConnectionFirestore(){
         return  db = FirebaseFirestore.getInstance();

     }

     public static FirebaseStorage ConnectionStorage(){
         return mFirebaseStorage = FirebaseStorage.getInstance();
     }
}
