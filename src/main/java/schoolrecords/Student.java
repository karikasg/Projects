package schoolrecords;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Mark> marks = new ArrayList<>();

    public Student(String name) {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("Student name must not be empty!");
        } else {
            this.name = name;
        }
    }

    public void grading(Mark mark) {
        if (mark == null) {
            throw new NullPointerException("Mark must not be null!");
        } else {
            marks.add(mark);
        }
    }

    public double calculateAverage() {
        int sum = 0;
        if (marks.size() == 0) {
            return 0.0;
        }
        for (Mark mark : marks) {
            sum += mark.getMarkType().getRate();
        }
        return Math.floor(100.0 * sum / marks.size()) / 100;
    }

    public double calculateSubjectAverage(Subject subject) {
        int sum = 0;
        int items = 0;
        for (Mark mark : marks) {
            if (mark.getSubject().getSubjectName().equals(subject.getSubjectName())) {
                sum += mark.getMarkType().getRate();
                items++;
            }
        }
        if (items == 0) {
            return 0.0;
        }
        return Math.floor(100.0 * sum / items) / 100;
    }

    public String getName() {
        return name;
    }

    public int getMarkNumbers() {
        return marks.size();
    }

    public int getMarksSum() {
        int result = 0;
        for (Mark mark : marks) {
            result += mark.getMarkType().getRate();
        }
        return result;
    }

    private boolean isEmpty(String name) {
        return (name == null || name.equals(""));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Mark mark : marks) {
            result.append(", ").append(mark.getSubject().getSubjectName()).append(": ").append(mark);
        }
        return name + " marks: " + result.delete(0, 2);
    }
}
