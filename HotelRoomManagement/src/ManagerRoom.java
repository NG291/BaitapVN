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
        System.out.println("Dien thong tin phong can them:");
        System.out.println("Nhap so phong:");
        String numberRoom = scanner.nextLine();
        System.out.println("Nhap loai phong");
        String typeRoom = scanner.nextLine();
        System.out.println("Gia phong");
        String price = scanner.nextLine();
        System.out.println("Nhap tinh trang phong");
        String roomStatus = scanner.nextLine();
        if (isInteger(numberRoom) && (isDouble(price)) && !isRoomNumberExist(numberRoom)) {
            Room room = new Room(Integer.parseInt(numberRoom), typeRoom, Double.parseDouble(price), roomStatus);
            listRooms.add(room);
            saveToFile();
            System.out.println("Them phong thanh cong!");
        } else {
            System.out.println("Loi! hoac trung so phong");
        }

    }
    public void showRoom() {
        for (Room room : listRooms) {
            System.out.println(room);
        }
    }

    public void deleteRoom() {
        System.out.println("Xoa phong");
        System.out.println("Nhap so phong ban can xoa");
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
            System.out.println("khong tim thay");
        } else {
            System.out.println("Xoa thanh cong");
        }
        saveToFile();
    }

    public void updateRoom() {
        System.out.println("Sua phong");
        System.out.println("Nhap so phong ban can sua");
        String numberRoomUpdate = scanner.nextLine();
        boolean found = false;
        for (Room room : listRooms) {
            if (room.getNumberRoom() ==Integer.parseInt(numberRoomUpdate)) {
                System.out.println("Nhap loai phong");
                String newTypeRoom = scanner.nextLine();
                System.out.println("Nhap gia phong");
                String newPrice = scanner.nextLine();
                System.out.println("Tinh trang phong");
                String roomStatus = scanner.nextLine();
                if(!isDouble(newPrice)) {
                    room.setTypeRoom(newTypeRoom);
                    room.setPrice(Double.parseDouble(newPrice));
                    room.setRoomStatus(roomStatus);
                    found = true;
                    break;
                }
                else {
                    System.out.println("Nhap sai kieu du lieu. Nhap lai! ");
                }
            }
        }
        if (!found) {
            System.out.println("So phong khong ton tai!");
        } else {
            System.out.println("Sua thanh cong");
        }
        saveToFile();
    }
}