package schoolrecords;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassRecords {
    private String className;
    private Random random;
    private List<Student> students = new ArrayList<>();

    public ClassRecords(String className, Random random) {
        this.className = className;
        this.random = random;
    }

    public boolean addStudent(Student student) {
        for (Student st : students) {
            if (student.getName().equals(st.getName())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public boolean removeStudent(Student student) {
        for (Student st : students) {
            if (student.getName().equals(st.getName())) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public double calculateClassAverage() {
        if (students.size() == 0) {
            throw new ArithmeticException("No student in the class, average calculation aborted!");
        }
        int marksSum = 0;
        int markNumbers = 0;
        for (Student st : students) {
            marksSum += st.getMarksSum();
            markNumbers += st.getMarkNumbers();
        }
        if (markNumbers == 0) {
            throw new ArithmeticException("No marks present, average calculation aborted!");
        }
        return Math.floor(100.0 * marksSum / markNumbers) / 100;
    }

    public double calculateClassAverageBySubject(Subject subject) {
        if (students.size() == 0) {
            throw new ArithmeticException("No student in the class, average calculation aborted!");
        }
        double avgSum = 0;
        int avgNumbers = 0;
        double studentAvgBySubject;
        for (Student st : students) {
            studentAvgBySubject = st.calculateSubjectAverage(subject);
            if (studentAvgBySubject != 0) {
                avgSum += studentAvgBySubject;
                avgNumbers++;
            }
        }
        if (avgNumbers == 0) {
            throw new ArithmeticException("No marks present, average calculation aborted!");
        }
        return (100.0 * avgSum / avgNumbers) / 100;
    }

    public Student findStudentByName(String name) {
        if (students.size() == 0) {
            throw new IllegalStateException("No students to search!");
        }
        if (isEmpty(name)) {
            throw new IllegalArgumentException("Student name must not be empty!");
        }
        for (Student st : students) {
            if (st.getName().equals(name)) {
                return st;
            }
        }
        throw new IllegalArgumentException("Student by this name cannot be found! " + name);
    }

    public Student repetition() {
        if (students.size() == 0) {
            throw new IllegalStateException("No students to select for repetition!");
        }
        return students.get(random.nextInt(students.size()));
    }


    public List<StudyResultByName> listStudyResults() {
        List<StudyResultByName> studyResultByNames = new ArrayList<>();
        for (Student st : students) {
            studyResultByNames.add(new StudyResultByName(st.getName(), st.calculateAverage()));
        }
        return studyResultByNames;
    }

    public String listStudentNames() {
        StringBuilder names = new StringBuilder();
        for (Student st : students) {
            names.append(", ").append(st.getName());
        }
        return names.delete(0, 2).toString();
    }

    private boolean isEmpty(String name) {
        if (name == null || name == "") {
            return true;
        }
        return false;
    }

    public String getClassName() {
        return className;
    }
}
