package com.example.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class gonderme extends AppCompatActivity {
    TextView textView11, textView22, textView33, textView44, textView55, textView6, textView7, textview111;
    String username;
    String othername;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FusedLocationProviderClient fusedLocationProviderClient;
    String name;
    Button gönder;
    double ab,abc;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonderme);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        username = getIntent().getExtras().getString("username");
        othername = getIntent().getExtras().getString("othername");
        textView11 = findViewById(R.id.textview11);
        textView22 = findViewById(R.id.textview22);
        textView33 = findViewById(R.id.textview33);
        textView44 = findViewById(R.id.textview44);
        textView55 = findViewById(R.id.textview55);
        textview111 = findViewById(R.id.textview111);
        textView6 = findViewById(R.id.textview6);
        textView7 = findViewById(R.id.textview7);
        gönder = findViewById(R.id.gönder);
        gönder.setText(othername + " " + "Konumunu Öğren");
        gönder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("location").child(othername);



                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String enlem=snapshot.child(othername).child("latitudu").getValue().toString();
                        String boylam=snapshot.child(othername).child("longitude").getValue().toString();
                        String ülke=snapshot.child(othername).child("ülke").getValue().toString();
                        String sehir=snapshot.child(othername).child("sehir").getValue().toString();
                        String adres=snapshot.child(othername).child("adres").getValue().toString();
                        textview111.setText(othername +" " + "Konum Bilgisi");
                        textView11.setText(enlem);
                        textView22.setText(boylam);
                        textView33.setText(ülke);
                        textView44.setText(sehir);
                        textView55.setText(adres);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}