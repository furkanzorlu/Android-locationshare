package com.example.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Adapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class a extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String username,grub,othername;
    List<String> list;
    RecyclerView userrecy;
    UserAdapter adapter;
    int sifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        list=new ArrayList<>();
        //username=getIntent().getExtras().getString("username");
       // othername=getIntent().getExtras().getString("othername");
        username=getIntent().getExtras().getString("Kadi");
        grub=getIntent().getExtras().getString("grub");
        userrecy=findViewById(R.id.userrecy);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(a.this,2);
        userrecy.setLayoutManager(layoutManager);
        adapter=new UserAdapter(a.this,list,a.this,username);
        userrecy.setAdapter(adapter);
        listele();
    }
    public void listele(){


            databaseReference.child("asd").child(grub).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable String s) {
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
