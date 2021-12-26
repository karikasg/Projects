package schoolrecords;

public class StudyResultByName {
    private String studentName;
    private double studyAverage;

    public StudyResultByName(String name, double average) {
        this.studentName = name;
        this.studyAverage = average;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getStudyAverage() {
        return studyAverage;
    }
}
