package vytality.vytalityhealth.com.vytalityhealth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Collections;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;
import static vytality.vytalityhealth.com.vytalityhealth.Util.SignUpOptions.SIGN_UP_WITH_FACEBOOK;
import static vytality.vytalityhealth.com.vytalityhealth.Util.SignUpOptions.SIGN_UP_WITH_GOOGLE;

public class LoginActivity extends AppCompatActivity {
    private static final int SIGNUP_WITH_GOOGLE = 2;
    private ActionBar actionBar;
    private static final String TAG = "LoginActivity";
    private CallbackManager callbackManager;
    private Context mContext;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

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

        mContext = LoginActivity.this;
        callbackManager = CallbackManager.Factory.create();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mLoginWithFacebookButton = findViewById(R.id.btn_logIn_with_fb);
        mLoginWithGoogleButton = findViewById(R.id.btn_logIn_with_google);
        mLoginWithEmailButton = findViewById(R.id.btn_email);
        mLoginWithMobileNumberButton = findViewById(R.id.btn_mobile);

        mLoginWithFacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getResources().getString(R.string.sign_up_with_fb_dialog_header),
                        getResources().getString(R.string.sign_up_dialog_body),SIGN_UP_WITH_FACEBOOK);
            }
        });

        mLoginWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getResources().getString(R.string.sign_up_with_google_dialog_header),
                        getResources().getString(R.string.sign_up_dialog_body), SIGN_UP_WITH_GOOGLE);
            }
        });

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

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Facebook Success", Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(mContext, HomeActivity.class);
                startActivity(homeIntent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Facebook Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Facebook Failed", Toast.LENGTH_SHORT).show();
                Log.e("Login", "failed" + exception.getMessage());
                exception.printStackTrace();
            }
        });
    }

    public void createDialog(String title, String message, final Util.SignUpOptions signUpOption) {
        AlertDialog.Builder builder;

        try {
            builder = new AlertDialog.Builder(mContext);
            builder.setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (signUpOption) {
                                case SIGN_UP_WITH_FACEBOOK:
                                    LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Collections.singletonList(EMAIL));
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

    public void setActionBarTitle(){
        if(actionBar != null) {
            actionBar.setTitle(getResources().getText(R.string.back));
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        } else {
            Log.e(TAG, "Action bar is null");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGNUP_WITH_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
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
}
