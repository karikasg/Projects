package activity;

public class ActivityWithTrack implements Activity {

    Track track = new Track();
    ActivityType activityType;

    public ActivityWithTrack(Track track, ActivityType activityType) {
        this.track = track;
        this.activityType = activityType;
    }

    @Override
    public double getDistance() {
        double result = 0;
        for (int i = 1; i < track.getTrackPoints().size(); i++) {
            result += track.getTrackPoints().get(i).getDistanceFrom(track.getTrackPoints().get(i-1));
        }
        return result;
    }

    @Override
    public ActivityType getType() {
        return activityType;
    }
}
