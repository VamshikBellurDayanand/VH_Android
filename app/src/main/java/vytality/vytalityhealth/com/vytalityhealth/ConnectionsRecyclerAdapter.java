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

public class ConnectionsRecyclerAdapter extends RecyclerView.Adapter<ConnectionsRecyclerAdapter.ConnectionsViewHolder>{

    private List<UserConnections> connectionsList;

    protected class ConnectionsViewHolder extends RecyclerView.ViewHolder {
        protected TextView mName, mPlace;
        protected ImageView mProfilePic;

        public ConnectionsViewHolder(View view) {
            super(view);
            mName = view.findViewById(R.id.connectionsName);
            mPlace = view.findViewById(R.id.connectionsPlace);
            mProfilePic = view.findViewById(R.id.connectionsProfilePic);
        }
    }

    public ConnectionsRecyclerAdapter(List<UserConnections> connectionsList) {
        this.connectionsList = connectionsList;
    }

    @Override
    public ConnectionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.connections_list_item_layout, parent, false);
        return new ConnectionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConnectionsViewHolder holder, int position) {
        UserConnections userConnections = connectionsList.get(position);
        holder.mName.setText(userConnections.getmUserName());
        holder.mPlace.setText(userConnections.getmPlace());
        if(!userConnections.getmProfilePicUrl().isEmpty()) {
            holder.mProfilePic.setImageBitmap(getBitmapFromURL(userConnections.getmProfilePicUrl()));
        }
    }

    @Override
    public int getItemCount() {
        return connectionsList.size();
    }

    protected static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            return null;
        }
    }

}
