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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpWithEmailActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private static final String TAG = "SignUpWithEmailActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private String name;
    private Context mContext;

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

        mContext = SignUpWithEmailActivity.this;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();

        mNameEditText = findViewById(R.id.editText_name);
        mEmailAddressEditText = findViewById(R.id.editText_email);
        mPasswordEditText = findViewById(R.id.editText_password);
        mCreateAccountButton = findViewById(R.id.btn_createAcnt);

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailAddressEditText.getText().toString();
                name = mNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if(!name.isEmpty() &&!email.isEmpty() && !password.isEmpty()) {
                    createAccount(email, password);
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

    private void createAccount(final String email, final String password) {
        Log.d(TAG, "createAccount:" + email);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "CreateUserWithEmail: Success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mDatabaseRef.child("User").child(user.getUid()).child("UserName").setValue(name);
                            mDatabaseRef.child("User").child(user.getUid()).child("Email").setValue(email);
                            mDatabaseRef.child("User").child(user.getUid()).child("Password").setValue(password);

                            Intent homeIntent = new Intent(mContext, HomeActivity.class);
                            startActivity(homeIntent);
                        } else {
                            Log.e(TAG, "CreateUserWithEmail: failed", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }
}
