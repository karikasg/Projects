package schoolrecords;

public class Mark {
    private MarkType markType;
    private Subject subject;
    private Tutor tutor;

    public Mark(MarkType markType, Subject subject, Tutor tutor) {
        if (valid(subject, tutor)) {
            this.markType = markType;
            this.subject = subject;
            this.tutor = tutor;
        }
    }

    public Mark(String markType, Subject subject, Tutor tutor) {
        if (valid(subject, tutor)) {
            this.markType = MarkType.valueOf(markType);
            this.subject = subject;
            this.tutor = tutor;
        }
    }

    private boolean valid(Subject subject, Tutor tutor) {
        if (subject != null && tutor != null) {
            return true;
        }
        throw new NullPointerException("Both subject and tutor must be provided!");
    }

    public MarkType getMarkType() {
        return markType;
    }

    public Subject getSubject() {
        return subject;
    }

    public Tutor getTutor() {
        return tutor;
    }

    @Override
    public String toString() {
        return markType.getRating() + "(" + getMarkType().getRate() + ")";
    }
}
