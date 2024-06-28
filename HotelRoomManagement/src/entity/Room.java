package entity;

public class Room {
    int numberRoom;
    String typeRoom;
    double price;
    String roomStatus;

    public Room(int numberRoom, String typeRoom, double price, String roomStatus) {
        this.numberRoom = numberRoom;
        this.price = price;
        this.typeRoom = typeRoom;
        this.roomStatus = roomStatus;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "numberRoom=" + numberRoom +
                ", typeRoom='" + typeRoom + '\'' +
                ", price=" + price +
                ", roomStatus='" + roomStatus + '\'' +
                '}';
    }
}
