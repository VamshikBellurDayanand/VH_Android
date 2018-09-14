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

public class ConnectionsFragment extends Fragment {

    private ConnectionsAdapter mConnectionsAdapter;
    private ViewPager mViewPager;
    private FragmentManager fragmentManager;

    public ConnectionsFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connections, container, false);
        fragmentManager = getChildFragmentManager();

        mConnectionsAdapter = new ConnectionsAdapter(getContext(), fragmentManager);
        mViewPager = view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(mConnectionsAdapter);

        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs_connections);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        tabLayout.bringToFront();

        return view;
    }

}
