package activity;

import java.util.ArrayList;
import java.util.List;

public class Activities {
    List<Activity> activities;

    public long numberOfTrackActivities() {
        return activities.stream().filter(o -> o instanceof ActivityWithTrack).count();
    }

    public long numberOfWithoutTrackActivities() {
        return activities.stream().filter(o -> o instanceof ActivityWithoutTrack).count();
    }

    public Activities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Report> distancesByTypes() {
        List<Report> reports = new ArrayList<>();

        for (ActivityType activityType : ActivityType.values()) {
            int distance = 0;
            for (Activity a : activities) {
                if (a.getType() == activityType) {
                    distance += a.getDistance();
                }
            }
            reports.add(new Report(activityType, distance));
        }
        return reports;
    }
}
