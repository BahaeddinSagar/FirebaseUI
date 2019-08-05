package com.example.thereallyeasyway;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth.getInstance().signOut();
    }


    public void login(View view) {

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers).build();

        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if(requestCode ==1234){
                if(resultCode == RESULT_OK){
                    // Successfully signed in
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Toast.makeText(this, ""+user.getUid(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Error logging in", Toast.LENGTH_LONG).show();
                }


            }
    }
}
