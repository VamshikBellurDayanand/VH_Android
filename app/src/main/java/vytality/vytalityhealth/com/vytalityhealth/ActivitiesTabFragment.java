package vytality.vytalityhealth.com.vytalityhealth;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesTabFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private RecyclerView mRecyclerView;
    private ActivityRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<UserActivity> activityList = new ArrayList<>();

    public ActivitiesTabFragment() {
    }

    public static ActivitiesTabFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ActivitiesTabFragment activitiesTabFragment = new ActivitiesTabFragment();
        activitiesTabFragment.setArguments(args);
        return activitiesTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities_tab, container, false);

        mRecyclerView =  view.findViewById(R.id.activityRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ActivityRecyclerAdapter(activityList);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setAdapter(mAdapter);

        /*
            Need to remove this. Added for testing Recycler view.
         */
        UserActivity userActivity = new UserActivity("Activity 1", "Created 22 minutes ago",
                "");
        activityList.add(userActivity);

        userActivity = new UserActivity("Activity 2", "Created 1 hour ago", "");
        activityList.add(userActivity);

        userActivity = new UserActivity("Activity 3", "Created 2 hours ago", "");
        activityList.add(userActivity);

        userActivity = new UserActivity("Activity 4", "Created 3 hours ago", "");
        activityList.add(userActivity);

        userActivity = new UserActivity("Activity 5", "Created 4 hours ago", "");
        activityList.add(userActivity);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }
}
