package meetingrooms;

import java.util.Scanner;

public class MeetingRoomController {

    private Office office = new Office();

    public static void main(String[] args) {
        MeetingRoomController meetingRoomController = new MeetingRoomController();
        meetingRoomController.runMenu();
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int i;
        do {
            printMenu();
            i = scanner.nextInt(); scanner.nextLine();
            switch (i) {
                case 1: {
                    readOffice();
                    break;
                }
                case 2: {
                    office.printNames();
                    break;
                }
                case 3: {
                    office.printNamesReverse();
                    break;
                }
                case 4: {
                    office.printEvenNames();
                    break;
                }
                case 5: {
                    office.Areas();
                    break;
                }
                case 6: {
                    System.out.print("Kérem a keresett nevet: ");
                    office.printMeetingRoomsWithName(scanner.nextLine());
                    break;
                }
                case 7: {
                    System.out.print("Kérem a keresett névtöredéket: ");
                    office.printMeetingRoomsContain(scanner.nextLine());
                    break;
                }
                case 8: {
                    System.out.print("Kérem a keresett méretet: ");
                    office.printAreasLargerThan(scanner.nextInt());
                    break;
                }
            }
        } while (i != 9);
    }

    public void readOffice() {
        String name;
        int length;
        int width;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kérem a tárgyaló nevét: ");
        name = scanner.nextLine();
        System.out.print("Kérem a tárgyaló szélességét: ");
        width = scanner.nextInt();scanner.nextLine();
        System.out.print("Kérem a tárgyaló ");
        length = scanner.nextInt();scanner.nextLine();
        office.addMeetingRoom(new MeetingRoom(name, length, width));
    }

    public void printMenu() {
        System.out.println("1. Tárgyaló rögzítése");
        System.out.println("2. Tárgyalók sorrendben");
        System.out.println("3. Tárgyalók visszafele sorrendben");
        System.out.println("4. Minden második tárgyaló");
        System.out.println("5. Területek");
        System.out.println("6. Keresés pontos név alapján");
        System.out.println("7. Keresés névtöredék alapján");
        System.out.println("8. Keresés terület alapján");
        System.out.println("9. Kilépés");
    }
}
