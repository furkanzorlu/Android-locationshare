package com.example.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class LoginActivity extends AppCompatActivity {
    EditText girisKullanıcı,sifre;
    Button girisButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        girisKullanıcı=findViewById(R.id.girisKullanıcı);
        sifre=findViewById(R.id.sifre);
        girisButton=findViewById(R.id.girisButton);
       getSupportActionBar().hide();
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=girisKullanıcı.getText().toString();


               // girisKullanıcı.setText("");
                if (!girisKullanıcı.getText().toString().equals("")){
                    ekle(username);
                }else {
                    Toast.makeText(getApplicationContext(),"Lütfen Bir Kullanıcı Adı Giriniz..",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void ekle(final  String Kadi){
        databaseReference.child("Kullanıcılar").child(Kadi).child("kullanıcıadı").setValue(Kadi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<Void> task) {
                if (task.isSuccessful()){
                    if (girisKullanıcı.getText().toString().equals(sifre.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Başarı ile giriş yaptınız",Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this,"Başarı ile kayıt oldunuz",Toast.LENGTH_LONG).show();
                        Intent ıntent=new Intent(LoginActivity.this,MainActivity.class);
                        ıntent.putExtra("Kadi",Kadi);
                        startActivity(ıntent);
                    }else {
                        Toast.makeText(getApplicationContext(),"Yanlış şifre girdiniz",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}