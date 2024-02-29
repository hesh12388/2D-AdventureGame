package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_ec22857 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;

    private final Item Sword = new Item("Sword");
    private final Item meat = new Item("meat");
    private final Item Shield = new Item("Shield");
    private final Item Jacket = new Item("Jacket");

    House_ec22857() {
        room1 = new Room_ec22468();
        room2 = new Room_ec22476();
        room3 = new Room_ec221218();
    }

    public Direction visit(Visitor visitor, Direction d) {
        Scanner scanner = new Scanner(System.in);

        visitor.tell("Welcome, Challenger. You have entered a haunted house.");
        visitor.tell("Eerie whispers and distant footsteps surround you.");
        visitor.tell("You find yourself in the first room. A chill runs down your spine. Proceed? (y/n)");

        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            d = room1.visit(visitor, d);

            if (d == Direction.TO_NORTH) {
                visitor.tell("You proceed to the second room, where you feel a sudden cold breeze.");
                visitor.tell("A ghostly figure appears before you, warning you to leave this place.");
                d = room2.visit(visitor, d);

                if (d == Direction.TO_EAST) {
                    visitor.tell("You enter the third room, where you see a flickering candle on a dusty table.");
                    visitor.tell("You spot a sword and a shield lying nearby, and you decide to pick them up.");
                    visitor.giveItem(Sword);
                    visitor.giveItem(Shield);
                    visitor.tell("With the sword and shield in hand, you feel a bit more confident.");
                    visitor.giveGold(3);
                    d = room3.visit(visitor, d);

                    if (d == Direction.TO_WEST) {
                        visitor.tell("You return to the second room, clutching your sword and shield tightly.");
                        visitor.tell("The ghostly figure seems to have vanished, leaving behind a warm jacket.");
                        visitor.giveItem(Jacket);
                        visitor.giveGold(2);
                        d = room2.visit(visitor, d);
                    }
                }
            } else if (d == Direction.TO_SOUTH) {
                visitor.tell("You proceed to the third room, where you find a piece of meat on a plate.");
                visitor.tell("You're not sure if it's safe to eat, but you take it anyway.");
                visitor.giveItem(meat);
                visitor.giveGold(1);
                d = room3.visit(visitor, d);
            }
        }

        visitor.tell("Exhausted and frightened, you decide to leave the haunted house. Farewell!");
        return Direction.opposite(d);
    }
}
