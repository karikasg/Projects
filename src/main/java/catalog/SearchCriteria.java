package catalog;

import static catalog.Validators.isBlank;

public final class SearchCriteria {
    private String contributor;
    private String title;


    public SearchCriteria(String contributor, String title) {
        this.contributor = contributor;
        this.title = title;
    }

    public static SearchCriteria createByBoth(String contributor, String title) {
        if (isBlank(contributor) || isBlank(title)) {
            throw new IllegalArgumentException("Contributor and title must not be empty");
        }
        return new SearchCriteria(contributor, title);
    }

    public static SearchCriteria createByContributor(String contributor) {
        if (isBlank(contributor)) {
            throw new IllegalArgumentException("Contributor must not be empty");
        }
        return new SearchCriteria(contributor, null);
    }

    public static SearchCriteria createByTitle(String title) {
        if (isBlank(title)) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        return new SearchCriteria(null, title);
    }

    public String getContributor() {
        return contributor;
    }

    public String getTitle() {
        return title;
    }

    public boolean hasContributor() {
        if (contributor == null) {
            return false;
        }
        return true;
    }

    public boolean hasTitle() {
        if (title == null) {
            return false;
        }
        return true;
    }

}
