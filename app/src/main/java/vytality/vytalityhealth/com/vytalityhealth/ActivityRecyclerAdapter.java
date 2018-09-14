package vytality.vytalityhealth.com.vytalityhealth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ActivityRecyclerAdapter extends RecyclerView.Adapter<ActivityRecyclerAdapter.ActivityViewHolder>{

    private List<UserActivity> activityList;

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        public TextView mActivityDescription, mTime;
        public ImageView mActivityLogo;

        public ActivityViewHolder(View view) {
            super(view);
            mActivityDescription = (TextView) view.findViewById(R.id.profileActivityDesc);
            mTime = (TextView) view.findViewById(R.id.profileActivityTime);
            mActivityLogo = (ImageView) view.findViewById(R.id.profileActivityLogo);
        }
    }

    public ActivityRecyclerAdapter(List<UserActivity> userActivityList) {
        this.activityList = userActivityList;
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_activity_list_item_layout, parent, false);

        return new ActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        UserActivity userActivity = activityList.get(position);
        holder.mActivityDescription.setText(userActivity.getActivityDescription());
        holder.mTime.setText(userActivity.getActivityTime());
        if(!userActivity.getActivityLogoSrc().isEmpty()) {
            holder.mActivityLogo.setImageBitmap(getBitmapFromURL(userActivity.getActivityLogoSrc()));
        }
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            return null;
        }
    }
}
