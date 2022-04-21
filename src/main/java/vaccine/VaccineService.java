package vaccine;

import activity.Coordinate;
import activity.TrackPoint;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Optional;
import java.util.Scanner;

public class VaccineService {

    DataSource dataSource;
    VaccineDAO vaccineDAO;

    public VaccineService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.vaccineDAO = new VaccineDAO(dataSource);
    }

    public boolean registerCitizen() {
        String name;
        String zip;
        String city;
        int age;
        String email;
        String taj;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Kérem a nevet: ");
        name = scanner.nextLine();
        if (!isValidName(name)) {
            System.out.println("A név nem lehet üres!");
            return false;
        }

        System.out.print("Kérem az irányítószámot: ");
        zip = scanner.nextLine();
        if (!isValidZip(zip)) {
            System.out.println("Hibás irányítószám!");
            return false;
        }
        if (zip.startsWith("1")) {
            city = "Budapest";
        } else {
            Optional<String> cityO = vaccineDAO.getCityByZip(zip);
            if (cityO.isEmpty()) {
                System.out.println("Nem létező irányítószám!");
                return false;
            } else {
                city = cityO.get();
            }
        }
        System.out.println("City: " + city);

        System.out.print("Kérem az életkort: ");
        String ageS = scanner.nextLine();
        if (!isValidAge(ageS)) {
            System.out.println("Hibás életkor!");
            return false;
        }
        age = Integer.parseInt(ageS);

        System.out.print("Kérem az email címet: ");
        email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Hibás email cím!");
        } else {
            System.out.print("Kérem az email címet újra: ");
            String email2 = scanner.nextLine();
            if (!email.equals(email2)) {
                System.out.println("Nem egyezik meg!");
                return false;
            }
        }

        System.out.print("Kérem a TAJ számot: ");
        taj = scanner.nextLine();
        if (!isValidTajNr(taj)) {
            System.out.println("Hibás TAJ szám!");
            return false;
        }

        if (vaccineDAO.saveCitizen(new Citizen(name, zip, age, email, taj, 0, LocalDate.of(1, 1, 1)))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        return ((email.indexOf("@") > 0) && (email.indexOf(".", email.indexOf("@")) > (email.indexOf("@") + 1)) && (!email.endsWith(".")));
    }

    private boolean isValidAge(String ageS) {
        int age;
        try {
            age = Integer.parseInt(ageS);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        if (age > 10 && age < 150) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidZip(String zip) {
        if (zip.length() != 4) {
            return false;
        }
        return true;
    }

    private boolean isValidName(String name) {
        if (name == null) {
            return false;
        }

        if (name.length() == 0) {
            return false;
        }
        return true;
    }

    private boolean isValidTajNr(String tajNr) {

        int sum = 0;
        char chr;
        if (tajNr.length() != 9) {
            return false;
        }
        try {
            long taj = Integer.parseInt(tajNr);
        }
        catch (NumberFormatException nfe) {
            return false;
        }

        for (int i = 0; i < tajNr.length() - 1; i++) {
            chr = tajNr.charAt(i);
            if ((chr < '0') || (chr > '9')) {
                return false;
            }
            if (i % 2 == 0) {
                sum += (chr - 48) * 3;
            } else {
                sum += (chr - 48) * 7;
            }
        }
        return (tajNr.charAt(8) - 48) == (sum % 10);
    }

    public boolean registerMassCitizens(Path path) {
        String name;
        String zip;
        int age;
        String email;
        String taj;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String[] elements;
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                elements = line.split(";");
                name = elements[0];
                zip = elements[1];
                age = Integer.parseInt(elements[2]);
                email = elements[3];
                taj = elements[4];

                vaccineDAO.saveCitizen(new Citizen(name, zip, age, email, taj, 0, LocalDate.of(1, 1, 1)));
            }
        } catch (
                IOException ioe) {
            throw new IllegalStateException("File beolvasási hiba", ioe);
        }

        return true;
    }

    public boolean vaccination() {
        String taj;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kérem a TAJ számot: ");
        taj = scanner.nextLine();
        if (!isValidTajNr(taj)) {
            System.out.println("Hibás TAJ szám!");
            return false;
        }
        Optional<Citizen> citizen = vaccineDAO.getCitizenByTaj(taj);
        if (citizen.isEmpty()) {
            System.out.println("Ezzel a TAJ számmal nem történt regisztráció!");
            return false;
        }
//        if (citizen.get().)


                vaccineDAO.vaccinate(taj);

        return true;
    }
}
