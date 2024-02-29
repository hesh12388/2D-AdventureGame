package OOP.ec22518.MP.contributions;

class House_ec22546 implements Visitable {

    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    /*
    House Layout
    --------------------------------
    BEDROOM   HALLWAY3     BATHROOM       
    HALLWAY1  LIVING ROOM  HALLWAY4     
    ENTRANCE  HALLWAY2     EXIT
    --------------------------------
    */
    public static void main(String[] args){
        Item crystal = new Item ("Crystal Ornament");
        Item wallet = new Item("Wallet");
        Item key = new Item("Key");
        Item creditCard = new Item("Credit Card");
        Item jewelleryBox = new Item("Jewellery Box");
        Item ring = new Item("Ring");
        Item earrings = new Item("Gold Earrings");

        Item[] items = new Item[] {crystal, wallet, key, creditCard, jewelleryBox, ring, earrings};
        
        class exit extends Room {
            public Direction visit (Visitor v, Direction arrive){
                v.tell("Homeowner: Well that's everything! I hope you liked my house. Thank you for coming!");
                    
                String question = "[It's time to leave, would you like to a)thank the homeowner or b)leave quickly]";
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                    
                if(choice=='a'){
                    v.tell("You: Thank you for the wonderful tour! It's definitely given me a better idea on how I want to decorate my home!");
                }
                    
                else if (choice=='b'){
                    v.tell("I'll keep it in mind when doing my own house! Bye!");
                }
                    
                else{
                    v.tell("[Invalid choice. You left without saying a word.]");
                }

                return Direction.opposite(arrive);
            }
        }
            
        class bathroom extends Room {
            public Direction visit (Visitor v, Direction arrive){
                v.tell("Homeowner: This is the bathroom. I recently got it redone.");
                    
                String question = "[You see some gold earrings by the sink-do you a)take them or b)leave them and tell the homeowner instead...?]";
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                    
                if (choice=='a'){
                    v.giveItem(earrings);
                }
                    
                else if (choice=='b'){
                    v.tell("You: Be careful! Those earrings might fall in the sink.\r\nHomeowner: Ah! I'm so clumsy! Thank you, I'll put them away.");
                }
                    
                else{
                    v.tell("[Invalid input. You get nothing.]");
                }
                    
                exit BRA = new exit();
                BRA.visit(v, arrive);
                    
                return arrive;
            }
        }

        class hallway4 extends Room {
            public Direction visit (Visitor v, Direction arrive){
                v.tell("Homeowner: This is my last hallway. I won't waste your time here.");
                    
                String question = "[QUICK! You see a wedding ring on the floor-do you a)take it or b)return it to the homeowner?]";
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                    
                if (choice=='a'){
                    v.giveItem(ring);
                }
                    
                else if (choice=='b'){
                    v.tell("You: Here! I found this on the floor.\r\nHomeowner: Oh! Thank you so much! You have no idea how long I've been looking for this!");
                }
                    
                else{
                    v.tell("[Invalid choice. Keep moving.]");
                }

                bathroom H4A = new bathroom();
                H4A.visit(v, arrive);
                    
                return arrive;
            }
        }
            
        class livingRoom extends Room {
            public Direction visit (Visitor v, Direction arrive){
            //public int livingRoomM(int gold){
                v.tell("Homeowner: The main event! My living room! Do you like it?");
                
                v.tell("[You see a small jewellery box on the coffee table. It looks like it needs a key...]");
                
                if ((!v.hasEqualItem(key)) && (!v.hasIdenticalItem(key))){
                    v.tell("[Looks like you don't have a key. Keep moving.]");
                }
                    
                else{
                    String question = "[The key you took earlier seems to look about right for the keyhole of the jewellery box-do you a)take the jewellery box or b)leave it and compliment the living room...?]";
                    char[] options = {'a', 'b'};
                    char choice = v.getChoice(question, options);
                        
                    if (choice=='a'){
                        v.giveItem(jewelleryBox);
                    }
                        
                    else if (choice=='b'){
                        v.tell("You: Your living room is stunning! I love the decor!\r\nHomeowner: Thanks! It's my favourite room!");
                    }
                }
            
                hallway4 LRA = new hallway4();
                LRA.visit(v, arrive);

                return arrive;
            }
        }

        class hallway3 extends Room {
            public Direction visit (Visitor v, Direction arrive){
            //public int hallway3M(int gold){
                v.tell("Homeowner: Another hallway! Let's keep moving.");
                
                String question = "[QUICK! You see some keys lying on a table-do you a)take them or b)leave them?]";
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                    
                if(choice=='a'){
                    v.giveItem(key);
                }

                else if (choice=='b'){
                    v.tell("Homeowner: Let's go!");
                }
                    
                else{
                    v.tell("[Invalid option. You get nothing and will move on.]");
                }
                    
                livingRoom H3A = new livingRoom();
                H3A.visit(v, arrive);

                return arrive;
            }
        }
        
        class bedroom extends Room {
            public Direction visit (Visitor v, Direction arrive){
            //public int bedroomM(int gold){
                v.tell("Homeowner: You are now in the bedroom! See anything you like?");
                    
                String question = "[You see a wallet lying on the desk-do you a)take it or b)leave it and compliment her painting instead...";
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                    
                if (choice=='a'){
                    v.giveItem(wallet);
                }
                    
                else if (choice=='b'){
                    v.tell("You: That's a lovely painting on your wall.\r\nHomeowner: Thank you, it's the first nice thing I bought for myself.");
                }
                    
                v.tell("Let's move on and go Eastwards.");

                hallway3 BA = new hallway3();
                BA.visit(v, arrive);
                    
                return arrive;
            }
        }
        
        class hallway2 extends Room {
            //public int hallway2M(int gold){
            public Direction visit (Visitor v, Direction arrive){
                v.tell("Homeowner: We're in the hallway! There's not much to see here but you can take a look around...");
                    
                String question = ("[You see a credit card lying on a table in the hallway-do you a)take it or b)leave it and compliment the lilac coloured walls...?]");
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                    
                if(choice=='a'){
                    v.giveItem(creditCard);
                }
                    
                else if(choice=='b'){
                    v.tell("You: I love the colour of the walls! It's so unique!\r\nHomeowner: Thank you! It's been my favourite colour since I was a child.");
                }
                    
                else{
                    v.tell("[Invalid input. Take nothing.]");
                }

                livingRoom H2A = new livingRoom();
                H2A.visit(v, arrive);
                    
                return arrive;
            }
        }
            
        class hallway1 extends Room {
            //public int hallway1M(int gold){
            public Direction visit (Visitor v, Direction arrive){
                v.tell("Homeowner: We are now in the hallway! Take a look around...");
                    
                String question = "[You see a crystal ornament in the hallway-do you a)take it or b)leave it and compliment it instead...?]";
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                    
                if (choice=='a'){
                    v.giveItem(crystal);
                }
                    
                else if (choice=='b'){
                    v.tell("You: That's a beautiful ornament you have!\r\nHomeowner: Thank you! My late grandmother gave it to me.");
                }
                    
                else {
                    v.tell("[Invalid input. You get nothing.]");
                }
                    
                String question2 = "Where would you like to go from here? a)North or b)East?";
                char[] options2 = {'a', 'b'};
                char choice2 = v.getChoice(question2, options2);
                    
                if (choice2=='a'){
                    bedroom H1A = new bedroom();
                    H1A.visit(v, arrive);
                }
                    
                else if(choice2=='b'){
                    livingRoom H1B = new livingRoom();
                    H1B.visit(v, arrive);
                }
                    
                else{
                    v.tell("That's not a option. We'll go North.");
                    bedroom H1C = new bedroom();
                    H1C.visit(v, arrive);
                }
                    
                return arrive;
            }
        }
                
        class entrance extends Room {
            public Direction visit (Visitor v, Direction arrive){
                v.tell("Welcome to my house!\nLet me show you around...");
            
                String question = "Would you like to go a)North  b)East?";
                char[] options = {'a', 'b'};
                char choice = v.getChoice(question, options);
                
                if (choice=='a'){
                    hallway1 EA = new hallway1();
                    EA.visit(v, arrive);
                }
                
                else if (choice=='b'){
                    hallway2 EB = new hallway2();
                    EB.visit(v, arrive);
                }
                
                else{
                    v.tell("That's not an option. We'll go East.");
                    hallway2 EC = new hallway2();
                    EC.visit(v, arrive);
                }
                
                int gold = 60;
                for (int i = 0; i < items.length; i++){
                    if ((v.hasEqualItem(items[i])) || (v.hasIdenticalItem(items[i]))){
                        gold-=10;
                    }
                }

                v.giveGold(gold);

                v.tell("[This was actually a setup by the homeowner due to the increase in theft in the neighbourhood. She wanted to see if one of the neighbours, possibly you, were the culprit. However, each time you didn't steal, she rewarded you with some gold. You got " + gold + ".]");

                if (gold == 60){
                    v.tell("[You didn't steal anything. Congratulations, you got " +gold+ " units of gold and are off her suspect list.]");
                }
                
                else if ((gold >= 40) && (gold < 60)){
                    v.tell("[You stole a few things but she won't press charges. Instead, she'll give you a warning since you have been neighbours for so long, but she's keeping a close eye on you. You got " +gold+ " units of gold.]");
                }
                
                else if (gold < 40){
                    v.tell("[You stole a lot of things and she will press charges. The police are on their way. You got " +gold+ " units of gold. Maybe use it to get a lawyer.]");
                }
                    
                return arrive;
            }
        }
        entrance START = new entrance();
    }
}
