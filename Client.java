import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner getUserInput = new Scanner(System.in);
        while (true) {
            Connect4 game = new Connect4();
            System.out.println(game.toString());
            game.displayGameStatus();

            int userInput = 0;
            while (!game.isGameOver()) {
                // This loop handles user inputs
                while (true) {
                    System.out.print("Enter a number between 0 and 6: ");

                    // Check if the input is an integer
                    if (getUserInput.hasNextInt()) {
                        userInput = getUserInput.nextInt();
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        // Clear invalid input
                        getUserInput.next();
                    }
                }

                try {
                    game.placeChecker(userInput);
                } catch (Exception e) {
                    System.out.println("Caution: " + e.getMessage() + "\n");
                }
                
                System.out.println(game.toString());
                game.displayGameStatus();
            }

            // // continue game
            // System.out.print("Press 'y' to start a new game, or press any other key to exit: ");
            // if (getUserInput.nextLine() != "y") {
            //     break;
            // }
            break;
        }
        getUserInput.close();
    }
}
