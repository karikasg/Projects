package catalog;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    protected List<CatalogItem> catalogItems = new ArrayList<>();

    public void addItem(CatalogItem catalogItem) {
        catalogItems.add(catalogItem);
    }

    public void deleteItemByRegistrationNumber(String registrationNumber) {
        CatalogItem del = null;
        for (CatalogItem ci : catalogItems) {
            if (ci.getRegistrationNumber().equals(registrationNumber)) {
                del = ci;
            }
        }
        if (del != null) catalogItems.remove(del);
    }
    public double averagePageNumberOver(int pageNumber) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Page number must be positive");
        }
        int page = 0;
        int item = 0;
        for (CatalogItem ci : catalogItems) {
            if (ci.hasPrintedFeature()) {
                if (ci.numberOfPagesAtOneItem() >= pageNumber) {
                    page += ci.numberOfPagesAtOneItem();
                    item++;
                }
            }
        }
        if (page == 0) {
            throw new IllegalArgumentException("No page");
        }
        return Math.round((double)page / item * 10.0) / 10.0;
    }

    public List<CatalogItem> findByCriteria(SearchCriteria searchCriteria) {
        List<CatalogItem> result = new ArrayList<>();

        for (CatalogItem ci : catalogItems) {
            if (isProper(ci,searchCriteria)) {
                result.add(ci);
            }
        }
        return result;
    }

    private boolean isProper(CatalogItem ci, SearchCriteria searchCriteria) {
        for (Feature actual : ci.getFeatures()) {
            if (!searchCriteria.hasTitle()) {
                if (actual.getContributors().contains(searchCriteria.getContributor())) {
                    return true;
                }
            } else if (!searchCriteria.hasContributor()) {
                if (actual.getTitle().equals(searchCriteria.getTitle())) {
                    return true;
                }
            } else if (actual.getContributors().contains(searchCriteria.getContributor()) && actual.getTitle().equals(searchCriteria.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public int getAllPageNumber() {
        int page = 0;
        for (CatalogItem ci : catalogItems) {
            if (ci.hasPrintedFeature()) {
                page += ci.numberOfPagesAtOneItem();
            }
        }
        return page;
    }

    public int getFullLength() {
        int time = 0;
        for (CatalogItem ci : catalogItems) {
            if (ci.hasAudioFeature()) {
                time += ci.fullLengthAtOneItem();
            }
        }
        return time;
    }

    public List<CatalogItem> getAudioLibraryItems() {
        List<CatalogItem> result = new ArrayList<>();
        for (CatalogItem ci : catalogItems) {
            if (ci.hasAudioFeature()) {
                result.add(ci);
            }
        }
        return result;
    }

    public List<CatalogItem> getPrintedLibraryItems() {
        List<CatalogItem> result = new ArrayList<>();
        for (CatalogItem ci : catalogItems) {
            if (ci.hasPrintedFeature()) {
                result.add(ci);
            }
        }
        return result;
    }


}
