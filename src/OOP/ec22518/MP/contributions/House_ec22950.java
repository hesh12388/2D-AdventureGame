package OOP.ec22518.MP.contributions;

class House_ec22950 implements Visitable {
    public Direction visit(Visitor visitor, Direction direction)
    {
        //set leaving direction
        Direction finalDirection = direction;

        //create items in the room
        Item Giftcard = new Item("Giftcard");
        Item Bankcard = new Item("Bankcard");

        //Tell user about the items
        visitor.tell("You have found a key.");
        visitor.tell("There are 2 drawers in front of you.");
        visitor.tell("You can only open 1");

        visitor.tell("What will you do?");
        visitor.tell("l: Open the left drawer.");
        visitor.tell("r: Open the right drawer.");

        char[] decision = {'l','r'};
        char oneToOpen = visitor.getChoice("Make your choice", decision);

        //The left drawer gives a gift card
        //The right drawer gives a bank card

        if (oneToOpen == 'l')
        {
            visitor.tell("You opened the left drawer");
            visitor.tell("You found a gift card");
            visitor.giveItem(Giftcard);
            visitor.tell("It contains 5 coins");
            visitor.giveGold(5);
        }

        if (oneToOpen == 'r')
        {
            visitor.tell("You opened the right chest");
            visitor.tell("You found a bank card");
            visitor.giveItem(Bankcard);
            visitor.tell("It contains nothing");
        }

        if (visitor.hasEqualItem(Bankcard))
        {
            visitor.tell("A man approaches you");
            visitor.tell("Man: I see there is nothing left in that bank card");
            visitor.tell("Man: Would you like me to transfer you some money?");
            visitor.tell("y: Yes");
            visitor.tell("n: No");

            char[] toGive = {'y','n'};
            char give = visitor.getChoice("Make your choice", toGive);

            if (give == 'y')
            {
                visitor.tell("Man: Sent!");
                visitor.tell("You recieve 8 coins");
                visitor.giveGold(8);
            }

            if (give == 'n')
            {
                visitor.tell("Man: Oh well");
                visitor.tell("Man: Bye Bye");
            }
        }

        visitor.tell("You now decide its time to move on");
        visitor.tell("You leave the room from the opposite side you entered");
        return finalDirection;
    }
}
