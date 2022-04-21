package vaccine;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/vaccine?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        }
        catch (SQLException throwables) {
            throw new IllegalStateException("Cannot reach database", throwables);
        }

        Flyway flyway = Flyway.configure().locations("db/migration/vaccine").dataSource(dataSource).load();
        flyway.migrate();

        VaccineService vaccineService = new VaccineService(dataSource);
        VaccineDAO vaccineDAO = new VaccineDAO(dataSource);

        System.out.println(vaccineDAO.getCitizenByTaj("028185798"));

        Scanner scanner = new Scanner(System.in);
        int i;
        do {
            printMenu();
            System.out.print("Kérem a menüpontot: ");
            i = scanner.nextInt();
            scanner.nextLine();
            switch (i) {
                case 1: {
                    if (!vaccineService.registerCitizen()) {
                        System.out.println("Sikertelen regisztráció, kérem kezdje elölről!");
                    };
                    break;
                }
                case 2: {
                        vaccineService.registerMassCitizens(Path.of("src/main/resources/massregistration.csv"));
                        //csak azt ellenőrzi, hogy a taj számmal van-e már regisztrálva, azt kihagyja,
                        //egyébként feltételezi, hogy a csv file létrehozásakor már validálva voltak az adatok
                    break;
                }
                case 3: {
//                        generateVaccinePlan();
                    break;
                }
                case 4: {
                        vaccineService.vaccination();
                    break;
                }
                case 5: {
//                        vaccinationFault();
                    break;
                }
                case 6: {
//                        report();
                    break;
                }
            }
        } while (i != 7);


    }

    private static void printMenu() {
        System.out.println("1. Regisztráció");
        System.out.println("2. Tömeges regisztráció");
        System.out.println("3. Generálás ");
        System.out.println("4. Oltás");
        System.out.println("5. Oltás meghiúsulás");
        System.out.println("6. Riport");
        System.out.println("7. Kilépés");
    }
}
