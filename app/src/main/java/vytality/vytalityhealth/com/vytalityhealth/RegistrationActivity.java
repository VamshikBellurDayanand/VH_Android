package vytality.vytalityhealth.com.vytalityhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends Activity {

    private static final String TAG = "RegistrationActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Button mSignUpButton;
        final Button mLoginButton;

        mSignUpButton = findViewById(R.id.btn_signUp);
        mLoginButton = findViewById(R.id.btn_logIn);

        mAuth = FirebaseAuth.getInstance();

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(RegistrationActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        FirebaseUser user = mAuth.getCurrentUser();
        if(account != null || accessToken != null && !accessToken.isExpired() || user != null) {
            Toast.makeText(this, "Already Signed in", Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        } else {
            Toast.makeText(this, "Not yet signed in", Toast.LENGTH_SHORT).show();
        }
    }
}
