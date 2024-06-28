import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ManagerRoom mr = new ManagerRoom();
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1: Chuc nang them phong moi");
                System.out.println("2: Chuc nang Sua thong tin phong");
                System.out.println("3: Chuc nang xoa phong");
                System.out.println("4: Chuc nang hien thị phong");
                System.out.println("5: Thoat ");
                int chocie = Integer.parseInt(scanner.nextLine());
                if (chocie == 5){
                    break;
                }
                switch (chocie) {
                    case 1:
                        mr.addRoom();
                        break;
                    case 2:
                        mr.updateRoom();
                        break;
                    case 3:
                        mr.deleteRoom();
                        break;
                    case 4:
                        mr.showRoom();
                        break;
                    default:
                        System.out.println("Nhap lai!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
