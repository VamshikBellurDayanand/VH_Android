package vytality.vytalityhealth.com.vytalityhealth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import static vytality.vytalityhealth.com.vytalityhealth.Util.*;
import static vytality.vytalityhealth.com.vytalityhealth.Util.SignUpOptions.SIGN_UP_WITH_FACEBOOK;
import static vytality.vytalityhealth.com.vytalityhealth.Util.SignUpOptions.SIGN_UP_WITH_GOOGLE;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG ="SignUpActivity";

    private Context mContext;
    private ActionBar actionBar;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    private static final int SIGNUP_WITH_FACEBOOK = 1;
    private static final int SIGNUP_WITH_GOOGLE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_sign_up);

        final Button mSignUpWithFBButton;
        final Button mSignUpWithGoogleButton;
        final Button mSignUpWithEmailButton;

        actionBar = getSupportActionBar();
        setActionBarTitle();

        mSignUpWithFBButton = findViewById(R.id.btn_signUpWithFB);
        mSignUpWithGoogleButton = findViewById(R.id.btn_signUpWithGoogle);
        mSignUpWithEmailButton = findViewById(R.id.btn_signUpWithEmail);

        mContext = SignUpActivity.this;

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mSignUpWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpWithEmailIntent = new Intent(SignUpActivity.this, SignUpWithEmailActivity.class);
                startActivity(signUpWithEmailIntent);
            }
        });

        mSignUpWithFBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getResources().getString(R.string.sign_up_with_fb_dialog_header),
                        getResources().getString(R.string.sign_up_dialog_body),SIGN_UP_WITH_FACEBOOK);
            }
        });

        mSignUpWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getResources().getString(R.string.sign_up_with_google_dialog_header),
                        getResources().getString(R.string.sign_up_dialog_body), SIGN_UP_WITH_GOOGLE);
            }
        });
    }

    public void setActionBarTitle() {
        if(actionBar != null) {
            actionBar.setTitle(getResources().getText(R.string.back));
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        }else {
            Log.e(TAG, "Action bar is null");
        }
    }
    public void createDialog(String title, String message, final SignUpOptions signUpOption) {
        AlertDialog.Builder builder;

        try {
            builder = new AlertDialog.Builder(mContext);
            builder.setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (signUpOption) {
                                case SIGN_UP_WITH_FACEBOOK:
                                    break;
                                case SIGN_UP_WITH_GOOGLE:
                                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                                    startActivityForResult(signInIntent,SIGNUP_WITH_GOOGLE );
                                    break;
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (signUpOption) {
                                case SIGN_UP_WITH_FACEBOOK:
                                    break;
                                case SIGN_UP_WITH_GOOGLE:
                                    break;
                            }

                        }
                    })
                    .show();
        }catch (NullPointerException exception) {
            Log.e(TAG, "Null pointer exception");
            exception.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGNUP_WITH_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        } catch (ApiException e) {
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            e.getMessage();
            Log.e(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            Toast.makeText(this, "Already Signed in", Toast.LENGTH_SHORT).show();
            //  Intent homeIntent = new Intent(this, Home_Activity.class);
            // startActivity(homeIntent);
            //update ui i.e. hide sign in button;
        } else {
            Toast.makeText(this, "Not yet signed in", Toast.LENGTH_SHORT).show();
        }
    }
}
