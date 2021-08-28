package com.example.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button,git;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    FusedLocationProviderClient fusedLocationProviderClient;
    String userId; String name; String email;

    String username;
 double ab,abc;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_map,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (textView6.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"Konum bilgisi alınız",Toast.LENGTH_LONG).show();
            }else {

            if (item.getItemId()==R.id.add_maps){
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                //intent.putExtra("ab", String.valueOf(ab));
                // intent.putExtra("longtide", String.valueOf(textView2));
                try{
                    double adet= Double.parseDouble(textView6.getText().toString());
                    double adet2= Double.parseDouble(textView7.getText().toString());
                    //int adet=Integer.parseInt(a.getText().toString());
                    //double adet=Double.parseDouble(textView1.getText().toString());
                    intent.putExtra("ab",adet);
                    intent.putExtra("abb",adet2);
                }catch(NumberFormatException ex){ // handle your exception
                    ex.printStackTrace();
                }
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1=findViewById(R.id.textview1);
        textView2=findViewById(R.id.textview2);
        textView3=findViewById(R.id.textview3);
        textView4=findViewById(R.id.textview4);
        textView5=findViewById(R.id.textview5);
        textView6=findViewById(R.id.textview6);
        textView7=findViewById(R.id.textview7);

        button=findViewById(R.id.getlocation);
        git=findViewById(R.id.git);

        username=getIntent().getExtras().getString("Kadi");
       // username=getIntent().getExtras().getString("username");

git.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

            Intent intent=new Intent(getApplicationContext(),grublar.class);
            intent.putExtra("Kadi",username);

            startActivity(intent);
        }



});

         fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //asd();
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location locationn=task.getResult();

                            if (locationn != null){

                                try {
                                    Geocoder geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
                                   List<Address> addresses=geocoder.getFromLocation(locationn.getLatitude(),locationn.getLongitude(),1);
                                    String Text = "Enlem: " + addresses.get(0).getLatitude();
                                    String Text2 = "Boylam: " + addresses.get(0).getLongitude();
                                    textView1.setText(Text);
                                    textView2.setText(Text2);

                                   //textView1.setText( addresses.get(0).getLatitude());
                                     ab=  locationn.getLatitude();
                                     abc=locationn.getLongitude();
                                     textView6.setText( String.valueOf(ab));
                                    textView7.setText( String.valueOf(abc));

                                    //System.out.println(ab+abc);
                                    //textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Latidu:</b><br><)font"+ addresses.get(0).getLatitude()));
                                    //textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Latidu:</b><br><)font"+ addresses.get(0).getLongitude()));
                                   // textView2.setText((int) addresses.get(0).getLongitude());
                                    textView3.setText("Ülke: "+addresses.get(0).getCountryName());
                                    textView4.setText("Şehir: "+addresses.get(0).getLocality());
                                    textView5.setText("Adres: "+addresses.get(0).getAddressLine(0));
                                    writeNewUser();
                                }catch (IOException e){
                                    e.printStackTrace();
                                }

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"olmadı",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }else {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                    Toast.makeText(getApplicationContext(),"Bilgilendirme mesajı",Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    private void writeNewUser() {
        //FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        //DatabaseReference databaseReference=firebaseDatabase.getReference("location");
        //databaseReference.child("latitudu").setValue(textView1.getText());
        //databaseReference.child("longitude").setValue(textView2.getText());
        //databaseReference.child("ülke").setValue(textView3.getText());
        //databaseReference.child("sehir").setValue(textView4.getText());
        //databaseReference.child("adres").setValue(textView5.getText());
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("location").child(username);
        databaseReference.child(username).child("latitudu").setValue(textView1.getText());
        databaseReference.child(username).child("longitude").setValue(textView2.getText());
        databaseReference.child(username).child("ülke").setValue(textView3.getText());
        databaseReference.child(username).child("sehir").setValue(textView4.getText());
        databaseReference.child(username).child("adres").setValue(textView5.getText());
        //databaseReference.child("users").child(userId).child("username").setValue(name);
    }



    }


