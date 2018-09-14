package vytality.vytalityhealth.com.vytalityhealth;

public class UserActivity {
    private String activityDescription, activityTime, activityLogoSrc;

    public UserActivity(String activityDescription, String activityTime, String activityLogoSrc) {
        this.activityDescription = activityDescription;
        this.activityTime = activityTime;
        this.activityLogoSrc = activityLogoSrc;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public String getActivityLogoSrc() {
        return activityLogoSrc;
    }
}
