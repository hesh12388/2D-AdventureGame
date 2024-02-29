package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_ec22836 implements Visitable {
    
    //This method is to get int inputs from the user
    public static int inputInt(String message)
    {
        String text_input;
        int answer;
        Scanner scanner = new Scanner(System.in);
    
        System.out.println(message);
    
        text_input = scanner.nextLine();
        answer = Integer.parseInt(text_input);
    
        return answer;
    }
    
    public Direction visit(Visitor v, Direction d)
    {
        Room_ec22836 room1 = new Room_ec22836();
        Room_ec22711 room2 = new Room_ec22711();
        Room_ec22919 room3 = new Room_ec22919();
        
        Direction newDirection;
    
        System.out.println("Welcome To My House!");
        int userChoice = inputInt("(1) Room 1  (2) Room 2  (3) Room 3 (4) Exit");
    
        while (userChoice != 4)
        {
            if (userChoice == 1)
            {
                newDirection = room1.visit(v, d);
            }
            else if (userChoice == 2)
            {
                newDirection = room2.visit(v, d);
            }
            else if (userChoice == 3)
            {
                newDirection = room3.visit(v, d);
            }
        
            userChoice = inputInt("(1) Room 1  (2) Room 2  (3) Room 3 (4) Exit");
        }
    
        System.out.println("Thank you for visitng my house!");
        System.out.println("Goodbye!");
        
        return d;
    }
}
