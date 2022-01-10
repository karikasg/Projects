package catalog;

import java.util.ArrayList;
import java.util.List;

import static catalog.Validators.isBlank;
import static catalog.Validators.isEmpty;

public class AudioFeatures implements Feature {

    private List<String> composer = new ArrayList<>();
    private int length;
    private List<String> performers = new ArrayList<>();
    private String title;


    public AudioFeatures(String title, int length, List<String> performers, List<String> composer) {
        if (isBlank(title)) {
            throw new IllegalArgumentException("Empty title");
        } else if (isEmpty(performers) || isEmpty(composer)) {
            throw new IllegalArgumentException("Empty list");
        } else if (length <= 0) {
            throw new IllegalArgumentException("Wrong length");
        } else {
            this.title = title;
            this.length = length;
            this.performers = performers;
            this.composer = composer;
        }
    }

    public AudioFeatures(String title, int length, List<String> performers) {
        if (isBlank(title)) {
            throw new IllegalArgumentException("Empty title");
        } else if (isEmpty(performers)) {
            throw new IllegalArgumentException("Empty list");
        } else if (length <= 0) {
            throw new IllegalArgumentException("Wrong length");
        } else {
            this.title = title;
            this.length = length;
            this.performers = performers;
        }
    }


    public int getLength() {
        return length;
    }

    @Override
    public List<String> getContributors() {
        List<String> result = new ArrayList<String>(composer);
        result.addAll(performers);
        return result;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
