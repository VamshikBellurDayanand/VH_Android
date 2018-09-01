package vytality.vytalityhealth.com.vytalityhealth;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginWithEmailActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private static final String TAG = "LoginWithEmailActivity";

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

        mEmailAddressEditText = findViewById(R.id.editText_email);
        mEmailPasswordEditText = findViewById(R.id.editText_password);
        mLoginButton = findViewById(R.id.btn_logIn);
        mEnableTouchIdCheckbox = findViewById(R.id.checkBox_enableTouchId);
        mForgotPasswordTextView = findViewById(R.id.textView_forgotPassword);

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
