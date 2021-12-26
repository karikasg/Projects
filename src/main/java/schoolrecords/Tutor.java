package schoolrecords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tutor {
    private String name;
    private List<Subject> subjects;

    public Tutor(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public boolean tutorTeachingSubject(Subject subject) {
        for (Subject subj : subjects) {
            if (subject.getSubjectName().equals(subj.getSubjectName())) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

}
