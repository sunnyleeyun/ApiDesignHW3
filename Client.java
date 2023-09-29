import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Game myGame = new Game();
        System.out.println(myGame.toString());
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;

        while (!myGame.isGameOver()){
            while (true) {
                System.out.print("Enter a number between 0 and 6: ");

                // Check if the input is an integer
                if (scanner.hasNextInt()) {
                    userInput = scanner.nextInt();

                    // Check if the number is within the desired range
                    if (userInput >= 0 && userInput <= 6) {
                        System.out.println("You entered a valid number: " + userInput);
                        // Exit loop if input is valid
                        break; 
                    } else {
                        System.out.println("Please enter a number between 0 and 6.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    // Clear invalid input
                    scanner.next();
                }
            }
            myGame.placeChecker(userInput);
            System.out.println(myGame.toString());
        }

        scanner.close();
    }
}
