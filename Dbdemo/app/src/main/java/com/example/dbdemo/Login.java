package com.example.dbdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static com.example.dbdemo.MainActivity.databaseReference;
import static com.example.dbdemo.MainActivity.firebaseDatabase;

public class Login extends AppCompatActivity {

    EditText l_edUser;
    EditText l_edPass;
    Button l_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_edUser = findViewById(R.id.l_txtUser);
        l_edPass = findViewById(R.id.l_txtPass);
        l_btn = findViewById(R.id.l_btn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");

        l_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();


                        if (l_edUser.getText().toString().equals (data.get("name") )&& l_edPass.getText().toString().equals(data.get("pass"))) {


                            Toast toast1=Toast.makeText(getApplicationContext(),"hiiii",Toast.LENGTH_SHORT);
                            toast1.show();
                        }
                        else
                        {
                            Toast toastel=Toast.makeText(getApplicationContext(),"kuchh toh Gadbad hain",Toast.LENGTH_SHORT);
                            toastel.show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
