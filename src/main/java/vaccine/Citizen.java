package vaccine;

import java.time.LocalDate;

public class Citizen {

    private String name;
    private String zip;
    private int age;
    private String email;
    private String tajNr;
    private int numberOfVaccination;
    private LocalDate lastVaccination;

    public Citizen(String name, String zip, int age, String email, String tajNr, int numberOfVaccination, LocalDate lastVaccination) {
        this.name = name;
        this.zip = zip;
        this.age = age;
        this.email = email;
        this.tajNr = tajNr;
        this.numberOfVaccination = numberOfVaccination;
        this.lastVaccination = lastVaccination;
    }

    public String getName() {
        return name;
    }

    public String getZip() {
        return zip;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getTajNr() {
        return tajNr;
    }

    public int getNumberOfVaccination() {
        return numberOfVaccination;
    }

    public LocalDate getLastVaccination() {
        return lastVaccination;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", zip='" + zip + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", tajNr='" + tajNr + '\'' +
                ", numberOfVaccination=" + numberOfVaccination +
                ", lastVaccination=" + lastVaccination +
                '}';
    }
}
