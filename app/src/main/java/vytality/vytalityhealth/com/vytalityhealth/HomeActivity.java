package vytality.vytalityhealth.com.vytalityhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ActionBar actionBar;
    private TextView mAppBarTitle;
    private SearchView mSearchView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            FrameLayout fl = findViewById(R.id.main_fragment);
            fl.removeAllViews();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.main_fragment, new HomeFragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.main_fragment, new ConnectionsFragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.main_fragment, new NotificationsFragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_search:
                    transaction.replace(R.id.main_fragment, new SearchFragment());
                    transaction.commit();
                    mSearchView = findViewById(R.id.searchView);
                    return true;
                case R.id.navigation_profile:
                    transaction.replace(R.id.main_fragment, new ProfileFragment());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
