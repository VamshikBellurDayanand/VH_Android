package vytality.vytalityhealth.com.vytalityhealth;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class SignUpWithEmailActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private static final String TAG = "SignUpWithEmailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_email);

        final EditText mNameEditText;
        final EditText mEmailAddressEditText;
        final EditText mPasswordEditText;
        final Button mCreateAccountButton;

        actionBar = getSupportActionBar();
        setActionBarTitle();

        mNameEditText = findViewById(R.id.editText_name);
        mEmailAddressEditText = findViewById(R.id.editText_email);
        mPasswordEditText = findViewById(R.id.editText_password);
        mCreateAccountButton = findViewById(R.id.btn_createAcnt);
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
