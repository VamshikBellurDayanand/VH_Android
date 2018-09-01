package vytality.vytalityhealth.com.vytalityhealth;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button mLoginWithFacebookButton;
        final Button mLoginWithGoogleButton;
        final Button mLoginWithEmailButton;
        final Button mLoginWithMobileNumberButton;

        actionBar = getSupportActionBar();
        setActionBarTitle();

        mLoginWithFacebookButton = findViewById(R.id.btn_logIn_with_fb);
        mLoginWithGoogleButton = findViewById(R.id.btn_logIn_with_google);
        mLoginWithEmailButton = findViewById(R.id.btn_email);
        mLoginWithMobileNumberButton = findViewById(R.id.btn_mobile);

        mLoginWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginWithEmailIntent = new Intent(LoginActivity.this, LoginWithEmailActivity.class);
                startActivity(loginWithEmailIntent);
            }
        });

        mLoginWithMobileNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginWithMobileNoIntent = new Intent(LoginActivity.this, LoginWithMobileNumberActivity.class);
                startActivity(loginWithMobileNoIntent);
            }
        });
    }

    public void setActionBarTitle(){
        if(actionBar != null) {
            actionBar.setTitle(getResources().getText(R.string.back));
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        } else {
            Log.e(TAG, "Action bar is null");
        }
    }
}
