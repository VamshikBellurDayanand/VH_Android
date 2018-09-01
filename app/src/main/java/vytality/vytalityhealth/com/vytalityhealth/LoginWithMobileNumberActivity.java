package vytality.vytalityhealth.com.vytalityhealth;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class LoginWithMobileNumberActivity extends AppCompatActivity {

    private static final String TAG = "LoginWithMobile#Activty";
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_mobile_number);

        final EditText mLoginWithMobileNoEditText;
        final Button mLoginButton;

        actionBar = getSupportActionBar();
        setActionBarTitle();

        mLoginWithMobileNoEditText = findViewById(R.id.editText_mobileNo);
        mLoginButton = findViewById(R.id.btn_logIn);
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
