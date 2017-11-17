import java.io.IOException;

/**
 * Created by Albin on 2017-10-23.
 */
public class UIFunctions {
    
    /**
     * Clears the console
     */
    public static void clear() {
        try
        {
            /*

            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");


            */
            
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                // Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                // Runtime.getRuntime().exec("clear");
                // System.out.print("\033\143");
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

    /**
     * Wait for users input
     */
    public static void waitForUser(){
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *  Standard divider for graphics
     */
    public static void divider() {
        System.out.println("========================================");
    }


}
