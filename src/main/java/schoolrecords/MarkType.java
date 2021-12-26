package schoolrecords;

public enum MarkType {
    A(5, "excellent"), B(4, "very good"), C(3, "improvement needed"), D(2, "close fail"), F(1, "fail");

    private int rate;
    private String rating;

    MarkType(int rate, String rating) {
        this.rate = rate;
        this.rating = rating;
    }

    public int getRate() {
        return rate;
    }

    public String getRating() {
        return rating;
    }

}

