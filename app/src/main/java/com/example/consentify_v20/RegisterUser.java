package com.example.consentify_v20;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextName,editTextSurname,editTextEmail,editTextPassword;
    private Button btRegister;
    private ProgressBar progressBarRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();
        editTextName = (EditText) findViewById(R.id.firstName);
        editTextSurname = (EditText) findViewById(R.id.surname);
        editTextEmail = (EditText) findViewById(R.id.emailAddress);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBarRegister = (ProgressBar) findViewById(R.id.progressBar);

        btRegister = (Button) findViewById(R.id.register);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }
    private void registerUser(){
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Checking if any of the EditTextss are left empty
        if(name.isEmpty()){
            editTextName.setError("Name is required!"); // sets an error for the user to read
            editTextName.requestFocus();
            return;
        }
        if(surname.isEmpty()){
            editTextSurname.setError("Surname is required!");
            editTextSurname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() <8){
            editTextPassword.setError("Please enter a longer password");
            editTextPassword.requestFocus();
            return;
        }

        progressBarRegister.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,surname,email);
                            //continue video 17:00  https://www.youtube.com/watch?v=Z-RE1QuUWPg
                        }
                    }
                });

    }

}