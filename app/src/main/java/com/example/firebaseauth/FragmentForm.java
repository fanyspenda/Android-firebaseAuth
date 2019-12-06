package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class FragmentForm extends Fragment {

    private FirebaseAuth mAuth;

    View viewForm;
    EditText etEmail, etPass;
    Button btnSignUp;
    Bundle bData = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewForm = inflater.inflate(R.layout.activity_fragment_form, container, false);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        etEmail = viewForm.findViewById(R.id.etEmail);
        etPass = viewForm.findViewById(R.id.etPassword);
        btnSignUp = viewForm.findViewById(R.id.btnSignUpSubmit);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString();
                final String password = etPass.getText().toString();

                //bundle.put...(namaYgAkanDipanggil, Isi)

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Sign in success, update UI with the signed-in user's information
                                    bData.putString("email", email);
                                    bData.putString("password", password);
                                    Fragment fgFormSignIn = new FragmentFormSignIn();

                                    FragmentManager fm = getFragmentManager();
                                    FragmentTransaction ft = fm.beginTransaction();
                                    ft.replace(R.id.frameForm, fgFormSignIn);
                                    ft.commit();
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
