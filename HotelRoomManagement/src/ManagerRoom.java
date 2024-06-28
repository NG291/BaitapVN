import entity.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerRoom {
    String filePath;
    List<Room> listRooms;
    File file;
    Scanner scanner;

    public ManagerRoom() {
        this.filePath = "HotelRoomManagement/src/InfoRoom.txt";
        this.listRooms = new ArrayList<>();
        this.file = new File(filePath);
        System.out.println(this.file.getAbsoluteFile());
        this.scanner = new Scanner(System.in);
        readToFile();
    }

    public void readToFile() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                int numberRoom = Integer.parseInt(data[0]);
                String typeRoom = data[1];
                double price = Double.parseDouble(data[2]);
                String roomStatus = data[3];
                Room room = new Room(numberRoom, typeRoom, price, roomStatus);
                listRooms.add(room);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Room room : listRooms) {
                bufferedWriter.write(room.getNumberRoom() + "," + room.getTypeRoom() + "," + room.getPrice() + "," + room.getRoomStatus());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isRoomNumberExist(String roomNumber) {
        for (Room room : listRooms) {
            if (room.getNumberRoom() == Integer.parseInt(roomNumber)) {
                return true;
            }
        }
        return false;
    }

    public void addRoom() {
        System.out.println("Additional room information:");
        System.out.println("Enter the room number");
        String numberRoom = scanner.nextLine();
        System.out.println("Enter the type of room");
        String typeRoom = scanner.nextLine();
        System.out.println("Gia phong");
        String price = scanner.nextLine();
        System.out.println("Enter room status");
        String roomStatus = scanner.nextLine();
        if (isInteger(numberRoom) && (isDouble(price)) && !isRoomNumberExist(numberRoom)) {
            Room room = new Room(Integer.parseInt(numberRoom), typeRoom, Double.parseDouble(price), roomStatus);
            listRooms.add(room);
            saveToFile();
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Error! duplicate room numbers");
        }

    }
    public void showRoom() {
        for (Room room : listRooms) {
            System.out.println(room);
        }
    }

    public void deleteRoom() {
        System.out.println("Delete room");
        System.out.println("Enter the room number to delete");
        int numberRoomDelete = Integer.parseInt(scanner.nextLine());
        boolean found = false;
        for (Room room : listRooms) {
            if (room.getNumberRoom() == numberRoomDelete) {
                listRooms.remove(room);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Not found room!");
        } else {
            System.out.println("Deleted successfully");
        }
        saveToFile();
    }

    public void updateRoom() {
        System.out.println("Edit Room");
        System.out.println("Enter the room number you need to repair");
        String numberRoomUpdate = scanner.nextLine();
        boolean found = false;
        for (Room room : listRooms) {
            if (room.getNumberRoom() ==Integer.parseInt(numberRoomUpdate)) {
                System.out.println("Enter room type");
                String newTypeRoom = scanner.nextLine();
                System.out.println("Enter room price");
                String newPrice = scanner.nextLine();
                System.out.println("Enter room status");
                String roomStatus = scanner.nextLine();
                if(isDouble(newPrice)) {
                    room.setTypeRoom(newTypeRoom);
                    room.setPrice(Double.parseDouble(newPrice));
                    room.setRoomStatus(roomStatus);
                    found = true;
                    break;
                }
                else {
                    System.out.println("Wrong data type! Please re-enter ");
                }
            }
        }
        if (!found) {
            System.out.println("Room number does not exist!");
        } else {
            System.out.println("Edited successfully!");
        }
        saveToFile();
    }
}