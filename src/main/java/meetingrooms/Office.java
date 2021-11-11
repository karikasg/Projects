package meetingrooms;

import java.util.ArrayList;
import java.util.List;

public class Office {
    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    public void addMeetingRoom(MeetingRoom meetingRoom) {
        meetingRooms.add(meetingRoom);
    }

    public void printNames() {
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.getName());
        }
    }

    public void printNamesReverse() {
        for (int i = meetingRooms.size() - 1; i >= 0; i--) {
            System.out.println(meetingRooms.get(i).getName());
        }
    }

    public void printEvenNames() {
        for (int i = 1; i < meetingRooms.size(); i+=2) {
            System.out.println(meetingRooms.get(i).getName());
        }
    }

    public void Areas() {
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(printMeetingRooms(meetingRoom));
        }
    }

    public void printMeetingRoomsWithName(String name) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (meetingRoom.getName().equals(name)) {
                System.out.println(printMeetingRooms(meetingRoom));
            }
        }
    }

    public void printMeetingRoomsContain(String part) {
            for (MeetingRoom meetingRoom : meetingRooms) {
                if (meetingRoom.getName().contains(part)) {
                    System.out.println(printMeetingRooms(meetingRoom));
                }
            }
    }

    public void printAreasLargerThan(int area) {
            for (MeetingRoom meetingRoom : meetingRooms) {
                if (meetingRoom.getArea()>area) {
                    System.out.println(printMeetingRooms(meetingRoom));
                }
            }
    }

    public String printMeetingRooms(MeetingRoom meetingRoom) {
        return ("Tárgyaló neve: " + meetingRoom.getName() + ", hossza: " + meetingRoom.getLength() +
                ", szélessége: " + meetingRoom.getWidth() + ", területe: " + meetingRoom.getArea());
    }
}
