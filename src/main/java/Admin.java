import org.omg.CORBA.Environment;

import java.util.Scanner;

/**
 * Created by Albin on 2017-10-19.
 */
public class Admin {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int option = 0;

        password(sc);

        System.out.println("Admin Menu");
        System.out.println("1: Number of empty seats");
        System.out.println("2. List empty seats");
        System.out.println("3. List of customer and seat number, order by seat number");
        System.out.println("4. List of customer and seat number, order by customer number");
        System.out.println("5. Assign a customer to a seat");
        System.out.println("6: Remove a seat assignment");
        System.out.println("7: quit");
        do{
           option = sc.nextInt();
            switch (option){
                case 0:

                    break;
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                default:

                    break;
            }
        } while (option < 8);
    }

    private static void password(Scanner sc) {
        int count = 3;
        while ( count > 0){
            System.out.print("Enter admin password: ");
            String password = sc.next();
            if(password.equals("password")) {
                System.out.println("Logging in...");
                return;
            }
            System.out.println("Incorrect Password! Attempts left: " + --count);
        }
        System.exit(0);
    }


}
