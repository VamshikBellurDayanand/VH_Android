package vytality.vytalityhealth.com.vytalityhealth;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {
    private ProfileAdapter mProfileAdapter;
    private ViewPager mViewPager;
    private FragmentManager fragmentManager;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fragmentManager = getChildFragmentManager();

        mProfileAdapter = new ProfileAdapter(getContext(), fragmentManager);
        mViewPager = view.findViewById(R.id.profileViewPager);
        mViewPager.setAdapter(mProfileAdapter);

        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs_profile);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        tabLayout.bringToFront();

        return view;
    }

}
