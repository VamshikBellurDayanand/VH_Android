package vytality.vytalityhealth.com.vytalityhealth;


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

public class LeaguesOfLoveTabFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private RecyclerView mRecyclerView;
    private ConnectionsRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<UserConnections> connectionsList = new ArrayList<>();

    public LeaguesOfLoveTabFragment() {
    }

    public static LeaguesOfLoveTabFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        LeaguesOfLoveTabFragment lovesOfLoveTabFragment = new LeaguesOfLoveTabFragment();
        lovesOfLoveTabFragment.setArguments(args);
        return lovesOfLoveTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leagues_of_love_tab, container, false);

        mRecyclerView =  view.findViewById(R.id.leaguesOfLoveRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ConnectionsRecyclerAdapter(connectionsList);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setAdapter(mAdapter);

        connectionsList.clear();


        /*
            Need to remove this. Just added to test the Recycler View.
         */
        UserConnections userConnections = new UserConnections("Vamshik", "Santa Clara, CA", "");
        connectionsList.add(userConnections);

        userConnections = new UserConnections("Vachan", "San Jose, CA", "");
        connectionsList.add(userConnections);

        userConnections = new UserConnections("Chaman", "San Francisco, CA", "");
        connectionsList.add(userConnections);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }
}
