package vytality.vytalityhealth.com.vytalityhealth;

public class UserConnections {
    private String mUserName, mPlace, mProfilePicUrl;

    public UserConnections(String mUserName, String mPlace, String mProfilePicUrl) {
        this.mUserName = mUserName;
        this.mPlace = mPlace;
        this.mProfilePicUrl = mProfilePicUrl;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmPlace() {
        return mPlace;
    }

    public String getmProfilePicUrl() {
        return mProfilePicUrl;
    }
}
