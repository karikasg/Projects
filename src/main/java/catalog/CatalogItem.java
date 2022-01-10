package catalog;

import java.util.ArrayList;
import java.util.List;

import static catalog.Validators.isBlank;
import static catalog.Validators.isEmpty;

public class CatalogItem {

    private String registrationNumber;
    private int price;
    private List<Feature> features = new ArrayList<>();

    public CatalogItem(String registrationNumber, int price, Feature... features) {
        if (isBlank(registrationNumber)) {
            throw new IllegalArgumentException("Bad registration number");
        } else if (price < 0) {
            throw new IllegalArgumentException("Wrong price");
        } else if (features.length == 0) {
            throw new IllegalArgumentException("Empty features");
        }
        else {
            this.registrationNumber = registrationNumber;
            this.price = price;
            for (Feature f : features) {
            this.features.add(f);
            }
        }
    }

    public int numberOfPagesAtOneItem() {
        int pages = 0;
        for (Feature f : features) {
            if (f instanceof PrintedFeatures) {
                pages += ((PrintedFeatures) f).getNumberOfPages();
            }
        }
        return pages;
    }

    public int fullLengthAtOneItem() {
        int time = 0;
        for (Feature f : features) {
            if (f instanceof AudioFeatures) {
                time += ((AudioFeatures) f).getLength();
            }
        }
        return time;
    }

    public boolean hasAudioFeature() {
        for (Feature f : features) {
            if (f instanceof AudioFeatures) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPrintedFeature() {
        for (Feature f : features) {
            if (f instanceof PrintedFeatures) {
                return true;
            }
        }
        return false;
    }

    public List<String> getContributors() {
        List<String> result = new ArrayList<>();
        for (Feature f : features) {
            result.addAll(f.getContributors());
        }
        return result;
    }

    public List<String> getTitles() {
        List<String> result = new ArrayList<>();
        for (Feature f : features) {
            result.add(f.getTitle());
        }
        return result;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getPrice() {
        return price;
    }
}
