package vytality.vytalityhealth.com.vytalityhealth;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProfileAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ProfileAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return ActivitiesTabFragment.newInstance(position + 1);
        }  else {
            return ProfileInfoTabFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Activity";
            case 1:
                return "My Profile info";
            default:
                return null;
        }
    }
}
