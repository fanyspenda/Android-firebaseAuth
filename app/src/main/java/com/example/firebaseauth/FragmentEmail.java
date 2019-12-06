package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentEmail extends Fragment {

    View viewEmail;
    TextView tvEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewEmail = inflater.inflate(R.layout.activity_fragment_email, container, false);
        tvEmail = viewEmail.findViewById(R.id.tvEmail);

        try {
            String email = getArguments().getString("email");
            String pass = getArguments().getString("password");

            tvEmail.setText(email);
        }catch (Exception e){
            Toast.makeText(getActivity(), "Tidak ada Data dilempar!", Toast.LENGTH_LONG).show();
        }

        return viewEmail;
    }
}
