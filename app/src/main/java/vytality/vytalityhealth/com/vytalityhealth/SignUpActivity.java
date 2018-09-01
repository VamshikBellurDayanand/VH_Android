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

import static vytality.vytalityhealth.com.vytalityhealth.Util.*;
import static vytality.vytalityhealth.com.vytalityhealth.Util.SignUpOptions.SIGN_UP_WITH_FACEBOOK;
import static vytality.vytalityhealth.com.vytalityhealth.Util.SignUpOptions.SIGN_UP_WITH_GOOGLE;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG ="SignUpActivity";

    private Context mContext;
    private ActionBar actionBar;
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
}
