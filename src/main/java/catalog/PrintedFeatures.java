package catalog;

import java.util.ArrayList;
import java.util.List;

import static catalog.Validators.isBlank;
import static catalog.Validators.isEmpty;

public class PrintedFeatures implements Feature {

    private List<String> authors = new ArrayList<>();
    private int numberOfPages;
    private String title;

    public PrintedFeatures(String title, int numberOfPages, List<String> authors) {
        if (isEmpty(authors) || numberOfPages <= 0) {
            throw new IllegalArgumentException("Illegal argument");
        }
        else {
            if (isBlank(title)) {
                throw new IllegalArgumentException("Empty title");
            } else {
                this.title = title;
                this.numberOfPages = numberOfPages;
                this.authors = authors;
            }
        }
    }

    public List<String> getAuthors() {
        List<String> result = new ArrayList<String>();
        result.addAll(authors);
        return result;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public List<String> getContributors() {
        List<String> result = new ArrayList<>(authors);
        return result;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
