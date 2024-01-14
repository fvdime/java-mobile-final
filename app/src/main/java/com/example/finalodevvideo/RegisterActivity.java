package com.example.finalodevvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, lastNameInput, passwordInput;
    private Button signup;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        nameInput = findViewById(R.id.firstName);
        lastNameInput = findViewById(R.id.lastName);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        signup = findViewById(R.id.registerButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (name.isEmpty()) {
                    nameInput.setError("Required! PLS!");
                    return;
                }

                if (lastName.isEmpty()) {
                    nameInput.setError("Required! PLS!");
                    return;
                }

                if (email.isEmpty()) {
                    nameInput.setError("Required! PLS!");
                    return;
                }

                if (password.isEmpty()) {
                    nameInput.setError("Required! PLS!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        FirebaseFirestore database = FirebaseFirestore.getInstance();
                        Map<String, Object> user = new HashMap<>();
                        user.put("Name: ", name);
                        user.put("Last Name: ", lastName);
                        database.collection("users").document(fAuth.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Success");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Failed");
                            }
                        });
                    }
                    else {
                        Exception e = task.getException();
                        if (e != null) {
                            Toast.makeText(RegisterActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Button login = (Button) findViewById(R.id.loginButton);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }
}