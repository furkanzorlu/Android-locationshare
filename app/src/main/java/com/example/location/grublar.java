package com.example.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class grublar extends AppCompatActivity {
    EditText girisKullanıcı;
    Button girisButton;
 String username;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<String> list;
    RecyclerView userrecy;
    Grubadepter adapter;
    String grub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grublar);
        girisKullanıcı=findViewById(R.id.girisKullanıcı);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        girisButton=findViewById(R.id.girisButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
       username=getIntent().getExtras().getString("Kadi");
        list=new ArrayList<>();
        userrecy=findViewById(R.id.userrecy);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(grublar.this,2);
        userrecy.setLayoutManager(layoutManager);
        adapter=new Grubadepter(grublar.this,list,grublar.this,grub);
        userrecy.setAdapter(adapter);
        listele();
        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               grub=girisKullanıcı.getText().toString();

                 if (!girisKullanıcı.getText().toString().equals("")){
                     FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                     DatabaseReference databaseReference=firebaseDatabase.getReference("asd").child(grub).child(username);
                     databaseReference.child(username).child(grub).setValue(grub);
                     Intent intent=new Intent(getApplicationContext(),a.class);
                     intent.putExtra("Kadi",username);
                     intent.putExtra("grub",grub);
                     startActivity(intent);
                 }
                 else {
                     Toast.makeText(getApplicationContext(),"Lütfen Bir Grup Adı giriniz",Toast.LENGTH_LONG).show();
                 }






            }
        });

    }
    public void listele(){


        databaseReference.child("asd").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                //Log.i("kullanıcıfdg:",snapshot.getKey());
                list.add(snapshot.getKey());
                adapter.notifyDataSetChanged();
                //if (!snapshot.getKey().equals(username)){
                //list.add(snapshot.getKey());
                //adapter.notifyDataSetChanged();
                //}

            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}