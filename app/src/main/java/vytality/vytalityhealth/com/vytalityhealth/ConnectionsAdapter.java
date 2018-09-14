package vytality.vytalityhealth.com.vytalityhealth;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ConnectionsAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ConnectionsAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return ConnectionsTabFragment.newInstance(position + 1);
        } else if(position == 1) {
            return TrustedCircleTabFragment.newInstance(position + 1);
        } else {
            return LeaguesOfLoveTabFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Connections";
            case 1:
                return "Trusted Circle";
            case 2:
                return "Leagues of Love";
            default:
                return null;
        }
    }
}
