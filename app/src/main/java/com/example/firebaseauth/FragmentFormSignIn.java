package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class FragmentFormSignIn extends Fragment {

    private FirebaseAuth mAuth;

    View viewForm;
    EditText etEmail, etPass;
    Button btnSignUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewForm = inflater.inflate(R.layout.activity_fragment_form_sign_in, container, false);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        etEmail = viewForm.findViewById(R.id.etSignInEmail);
        etPass = viewForm.findViewById(R.id.etSignInPassword);
        btnSignUp = viewForm.findViewById(R.id.btnSignInSubmit);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                final String password = etPass.getText().toString();

                //bundle.put...(namaYgAkanDipanggil, Isi)

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Sign in success, update UI with the signed-in user's information
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent i = new Intent(getContext(), Main2Activity.class);
                                    startActivity(i);
                                    Toast.makeText(getActivity(), "Login Sukses!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return viewForm;
    }
}
