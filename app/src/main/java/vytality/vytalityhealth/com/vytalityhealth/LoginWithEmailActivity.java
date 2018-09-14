package vytality.vytalityhealth.com.vytalityhealth;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginWithEmailActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private static final String TAG = "LoginWithEmailActivity";
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);

        final EditText mEmailAddressEditText;
        final EditText mEmailPasswordEditText;
        final Button mLoginButton;
        final CheckBox mEnableTouchIdCheckbox;
        final TextView mForgotPasswordTextView;

        actionBar = getSupportActionBar();
        setActionBarTitle();

        mContext = LoginWithEmailActivity.this;
        mAuth = FirebaseAuth.getInstance();

        mEmailAddressEditText = findViewById(R.id.editText_email);
        mEmailPasswordEditText = findViewById(R.id.editText_password);
        mLoginButton = findViewById(R.id.btn_logIn);
        mEnableTouchIdCheckbox = findViewById(R.id.checkBox_enableTouchId);
        mForgotPasswordTextView = findViewById(R.id.textView_forgotPassword);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmailAddressEditText.getText().toString();
                password = mEmailPasswordEditText.getText().toString();

                signIn(email, password);
            }
        });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "SignInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent homeIntent = new Intent(mContext, HomeActivity.class);
                        startActivity(homeIntent);
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(mContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
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
