package OOP.ec22518.MP.contributions;

import java.util.Random;

/*House has hallway in the middle row and middle column. It also has 'wrap-around' topology in which leaving an East-most room to the East takes the visitor back round to entering a West-most room from the West. 
To leave the house visisor must reach to hallway and choose to leave.
Rooms are stable during the game, however, they will be shuffled for the next game.
Visitor is represented with 'V' on the map.

*/
class House_ec221150 implements Visitable {
        
    public static void main (String[]args) {
    House_ec221150 h = new House_ec221150();
    Visitor v = new IOVisitor(System.out,System.in);
    
    Direction d = h.visit(v, Direction.FROM_NORTH);
    }
    
    static Visitable toVisit;
    static Direction d;
    static Room r;
    
    static final int maxRow = 7; //number of rows in house
    static final int maxColumn = 7; //number of columns in house
    static final int hallwayRow = 3; // row index of hallway
    static final int hallwayColumn = 3; // column index of hallway
    static Room hallway;
    
    int rand1 = (int)(Math.random()*7);
    int rand2 = (int)(Math.random()*7);
    int[] location = new int[]{rand1,rand2}; //current location
    
   // int[] location = getLoc(r, roomIndex); //current location
    int row = location[0];  //current row stored in the array
    int column = location[1];  //current column stored in the array
    
    //Room grid
    static final Room[][] roomIndex = new Room[maxRow][maxColumn]; // 6 rows and 6 columns for rooms. 1 row and 1 column for hallway (+ shaped in the middle)
    
    static class MyVisitor implements Visitor {
        Visitor v;
        Location l;
        MyVisitor(Visitor v, int[] loc) {
            this.v = v;
            this.l = new Location(loc[0],loc[1]);
            }
        
            static class Location{
                int x ;
                int y ;

                //constructor
                Location(int r, int c){
                    this.x=r;
                    this.y=c;
                }

                //constructor
                Location(){
                    int rand1 = (int)(Math.random()*7);
                    int rand2 = (int)(Math.random()*7);
                    r = roomIndex[rand1][rand2];
                    this.x=rand1;
                    this.y=rand2;
               }

                //calculate next location
                private Location nextLoc(Location l, Direction d){

                if(d==Direction.TO_SOUTH){
                    l.x = l.x+1;
                    if(l.x==maxRow){
                        l.x=0;
                        System.out.println("\n[ You walked to North-end side of the house through garden ]");
                    }
                }
                if(d==Direction.TO_NORTH){
                    l.x = l.x-1;
                    if(l.x==-1){
                        l.x=maxRow-1;
                        System.out.println("\n[ You walked to South-end of the house through garden ]");
                    }
                }
                if(d==Direction.TO_EAST){
                    l.y = l.y+1;
                    if(l.y==maxColumn){
                        l.y=0;
                        System.out.println("\n[ You walked to West-end of the house through garden ]");
                    }
                }
                if(d==Direction.TO_WEST){
                    l.y = l.y-1;
                    if(l.y==-1){
                        l.y=maxColumn-1;
                        System.out.println("\n[ You walked to East-end of the house through garden ]");
                    }
                }
                    r = roomIndex[l.x][l.y];
                    if(l.x!=3 && l.y!=3){
                        toVisit = r;
                    }
                return l;
            }//end nextLoc
            
                private Room rUpdate(Location l){
                r = roomIndex[l.x][l.y];
                toVisit = r;
                return r;
                }//end rUpdate
            
            }//end Location class
        
        public void tell (String s) {v.tell(s);}
        public int takeGold(int n) {return v.takeGold(n);}
        public void giveGold(int n){v.giveGold(n);}
        public boolean hasEqualItem(Item x){return v.hasEqualItem(x);}
        public boolean hasIdenticalItem(Item x){return v.hasIdenticalItem(x);}
        public boolean giveItem(Item x){return v.giveItem(x);}
        public char getChoice(String s,char[] arr){return v.getChoice(s,arr);}
    }//end MyVisitor class
    
    
    public Direction visit(Visitor rv, Direction d) {

        MyVisitor v = new MyVisitor(rv,location);
        v.l.rUpdate(v.l);

        
        boolean leave = false;
        v.tell("\n\n[ You have entered a House containing 36 rooms ]");
        
          while(!leave){
              
            tellLoc(v);
              
            if(r!=hallway){
                v.tell("\n[ You are entering the room  " + roomIndex[v.l.x][v.l.y] +" ]");
                delay(v);
                d = toVisit.visit(v, d);
                v.tell("[ You just left the Room contributed by "+roomIndex[v.l.x][v.l.y]+" "+d+". ]");
                v.l = v.l.nextLoc(v.l,d);
            }
        
            //hallway cases
            while(r==hallway && !leave){
                
                v.tell("\n[ Choose what to do next ]");
                char c1 = v.getChoice("1. Go forward \n2. Look around for other rooms \n3. Leave the house", new char[]{1,2,3});
                switch(c1){
                    case '1':
                        v.tell("[ You chose to go forward ]");
                        v.l = v.l.nextLoc(v.l, d);

                        tellLoc(v);
                        if(r!=hallway){
                            v.tell("\n[ You are entering the Room " + roomIndex[v.l.x][v.l.y] + " ]");
                            delay(v);
                            this.toVisit = r;
                            d = toVisit.visit(v, d);
                            v.tell("\n[ You just left the Room "+roomIndex[v.l.x][v.l.y]+" "+d+". ]");
                            v.l = v.l.nextLoc(v.l, d);
                        }
                        break;
                    case '2':
                        v.tell("[ You chose to look around for other rooms ]");
                        tellLoc(v);
                        v.tell("[ Choose which way to go ]");
                        
                        final String A = "1. Rooms in the North-West of the house";
                        final String B = "2. Rooms in the North-East of the house ";
                        final String C = "3. Rooms in the South-West of the house ";
                        final String D = "4. Rooms in the South-East of the house";
                        
                        char c2 = v.getChoice("\n"+ A+"\n"+B+"\n"+C+"\n"+D, new char[]{1,2,3,4});
                        int randN;
                        switch(c2){
                            case '1':
                                randN = (int)(Math.random()*3);
                                v.l.x = randN;
                                randN = (int)(Math.random()*3);
                                v.l.y = randN;
                                tellLoc(v);
                                v.tell("\n[ You are entering the room " + roomIndex[v.l.x][v.l.y]+" ]");
                                delay(v);
                                d = toVisit.visit(v, d);
                                v.tell("\n[ You just left the Room "+roomIndex[v.l.x][v.l.y]+" "+d+". ]");
                                v.l = v.l.nextLoc(v.l, d);
                                break;
                            case '2':
                                randN = (int)(Math.random()*3);
                                v.l.x = randN;
                                randN = (int)(Math.random()*3)+4;
                                v.l.y = randN;
                                tellLoc(v);
                                v.tell("\n[ You are entering the room " + roomIndex[v.l.x][v.l.y]+" ]");
                                delay(v);
                                d = toVisit.visit(v, d);
                                v.tell("\n[ You just left the Room "+roomIndex[v.l.x][v.l.y]+" "+d+". ]");
                                v.l = v.l.nextLoc(v.l, d);
                                break;
                            case '3':
                                randN = (int)(Math.random()*3)+4;
                                v.l.x = randN;
                                randN = (int)(Math.random()*3);
                                v.l.y = randN;
                                tellLoc(v);
                                v.tell("\n[ You are entering the room " + roomIndex[v.l.x][v.l.y]+" ]");
                                delay(v);
                                d = toVisit.visit(v, d);
                                v.tell("\n[ You just left the Room "+roomIndex[v.l.x][v.l.y]+" "+d+". ]");
                                v.l = v.l.nextLoc(v.l, d);
                                break;
                            case '4':
                                randN = (int)(Math.random()*3)+4;
                                v.l.x = randN;
                                randN = (int)(Math.random()*3)+4;
                                v.l.y = randN;
                                tellLoc(v);
                                v.tell("\n[ You are entering the room " + roomIndex[v.l.x][v.l.y]+" ]");
                                delay(v);
                                d = toVisit.visit(v, d);
                                v.tell("\n[ You just left the Room "+roomIndex[v.l.x][v.l.y]+" "+d+". ]");
                                v.l = v.l.nextLoc(v.l, d);
                                
                                break;
                            default:
                                randN = (int)(Math.random()*6);
                                v.l.x = randN;
                                randN = (int)(Math.random()*6);
                                v.l.y = randN;
                                tellLoc(v);
                                v.tell("\n[ You are entering the room " +roomIndex[v.l.x][v.l.y]+" ]");
                                delay(v);
                                d = toVisit.visit(v, d);
                                v.tell("\n[ You just left the Room "+roomIndex[v.l.x][v.l.y]+" "+d+". ]");
                                v.l = v.l.nextLoc(v.l, d);
                        }
                        
                        break;
                    case '3':
                        leave = true;
                        break;
                    default:
                        v.tell("[ Invalid choice, you are continuing same direction ]");
                        v.l = v.l.nextLoc(v.l, d);
                        if(r!=hallway){
                            v.tell("\n[ You are entering the room " + r+" ]");
                            delay(v);
                            this.toVisit = r;
                            d = toVisit.visit(v, d);
                            v.tell("\n[ You just left the Room "+r+" "+d+". ]");
                            v.l = v.l.nextLoc(v.l, d);
                        }
                        break;
                }//end hallway cases
            }//end hallway
              
              //if not hallway visit next room then go back to loop
              if(r!=hallway){
                  
                    tellLoc(v);
                    v.tell("\n[ You are entering the room " + r+" ]");
                    delay(v);
                    this.toVisit = r;
                    d = toVisit.visit(v, d);
                    v.tell("\n[ You just left the Room "+r+" "+d+". ]");
                    v.l = v.l.nextLoc(v.l, d);
                  }
                  
          }//end while (in house)
        System.out.println("[ You are leaving the house! ]");
        
        int randN2 = (int)(Math.random()*4)+1;
        
        if(randN2==1){
            d = Direction.TO_NORTH;
            v.tell("You're heading north exit");
        }
        
        if(randN2==2){
            d = Direction.TO_EAST;
            v.tell("You're heading east exit");
        }
        if(randN2==3){
            d = Direction.TO_SOUTH;
            v.tell("You're heading south exit");
        }
        if(randN2==4){
            d = Direction.TO_WEST;
            v.tell("You're heading west exit");
        }
        
        return d;
    }//end visit
    

    
        //get location
        private int[] getLoc(Room key, Room[][] rI){
            for(int i=0; i<maxRow; i++){
                
                for(int j=0; j<maxColumn; j++){
                    
                    if(roomIndex[i][j]==key){
                        int[] loc=new int[2];
                        loc[0]= i;
                        loc[1]= j;
                        return loc;
                    }
                }
            }
        return new int[] {-1,-1};
        }//end getLoc
    
        //print location
        private void tellLoc(Visitor v){
            int[] s = getLoc(r, roomIndex);
            if(s[0]==hallwayRow || s[1]==hallwayColumn){
                System.out.print("\n[ You are in the hallway ] \n");
                printBoard();
            }
            else{
                System.out.println("\n[ Your location is ("+(s[0]+1)+","+(s[1]+1)+") ]");
                printBoard();
            }
        }//end tellLoc
    
        private static void delay(Visitor v) { v.getChoice("[ Press space to continue ]", new char[] {' '}); }
    
        private void printBoard(){
                    System.out.println("\n");

                    for (int i = 0; i < maxRow; i++){

                        System.out.print("| ");

                        for (int j = 0; j < maxColumn; j++)

                        {
                            if(i==3){
                                System.out.print("       HALLWAY       | ");
                            }

                            else if(roomIndex[i][j]==hallway){
                                System.out.print("HALLWAY | ");
                            }

                            else if(roomIndex[i][j]==r){
                                System.out.print("          V           | ");
                            }

                            else{
                                System.out.print(roomIndex[i][j] + " | ");
                            }
                        }
                    System.out.println("\n");
                    }
                    System.out.println("");
                }//end printBoard
    
    
    //Constructor to assign rooms to indexes
    private House_ec221150(Room r,int row, int c){
            
            //Hallway on 4th row and 4th column
            if(row==hallwayRow || c==hallwayColumn){
                roomIndex[row][c] = hallway;
            }
            else{
                roomIndex[row][c] = r;
            }
    }
    
    
        //Constructor without a parameter (calls other constructor)
    public House_ec221150(){
        
        String[] usernames= new String[36];
        usernames= getRoomUsername(); //create array for usernames
        shuffleArray(usernames); //shuffle rooms each time game is run
        
        int counter = 0;
        
        for(int i=0; i<maxRow; i++){
            for(int j=0; j<maxColumn; j++){

           /* Random room constructor, commented out due to high number of non-functioning rooms.
                String[] u = Contributions.getRoomUsernames();  Add random rooms from Contributions.java
                c = u[(new Random()).nextInt(u.length)];
                r = Contributions.newRoomByUsername(c);
             */
                
                if(i!=hallwayRow && j!=hallwayColumn){
                    
                    r = newRoom(usernames[counter]);
                    
                    this.toVisit=r;

                    House_ec221150 h1 = new House_ec221150(r, i, j);
                    counter++;
                }
            }
        }
        
        //start from a random point
        int rand = (int)(Math.random()*7);
        r = roomIndex[rand][rand];
    }//end constructor
    
    
    //create new room
    private Room newRoom(String s){
        Room x = null;
        
        if (s.equals("ec22450")) x = new Room_ec22450();
        else if (s.equals("ec221247")) x = new Room_ec221247();
        else if (s.equals("ec22789")) x = new Room_ec22789();
        else if (s.equals("ec22476")) x = new Room_ec22476();
        else if (s.equals("ec22967")) x = new Room_ec22967();
        else if (s.equals("ec22614")) x = new Room_ec22614();
        else if (s.equals("ec22586")) x = new Room_ec22586();
        else if (s.equals("ec22452")) x = new Room_ec22452();
        else if (s.equals("ec22748")) x = new Room_ec22748();
        else if (s.equals("ec22923")) x = new Room_ec22923();
        else if (s.equals("ec22898")) x = new Room_ec22898();
        else if (s.equals("ec22890")) x = new Room_ec22890();
        else if (s.equals("ex21299")) x = new Room_ex21299();
        else if (s.equals("ec22576")) x = new Room_ec22576();
        else if (s.equals("ec22519")) x = new Room_ec22519();
        else if (s.equals("ec22551")) x = new Room_ec22551();
        else if (s.equals("ec22692")) x = new Room_ec22692();
        else if (s.equals("ec22790")) x = new Room_ec22790();
        else if (s.equals("ec22441")) x = new Room_ec22441();
        else if (s.equals("ex21213")) x = new Room_ex21213();
        else if (s.equals("ec22945")) x = new Room_ec22945();
        else if (s.equals("ec22415")) x = new Room_ec22415();
        else if (s.equals("ec22591")) x = new Room_ec22591();
        else if (s.equals("ec22738")) x = new Room_ec22738();
        else if (s.equals("ex20539")) x = new Room_ex20539();
        else if (s.equals("ec221024")) x = new Room_ec221024();
        else if (s.equals("ec22717")) x = new Room_ec22717();
        else if (s.equals("ex21247")) x = new Room_ex21247();
        else if (s.equals("ec221099")) x = new Room_ec221099();
        else if (s.equals("ec22872")) x = new Room_ec22872();
        else if (s.equals("ec22621")) x = new Room_ec22621();
        else if (s.equals("ec22927")) x = new Room_ec22927();
        else if (s.equals("ec22496")) x = new Room_ec22496();
        else if (s.equals("ec22937")) x = new Room_ec22937();
        else if (s.equals("ex21423")) x = new Room_ex21423();
        else if (s.equals("ec22992")) x = new Room_ec22992();
        
        return x;
    }//end newRoom
    
    private static String[] getRoomUsername() {
    String[] a = new String[36];
      
      a[0] = "ec22450";
      a[1] = "ec221247";
      a[2] = "ec22789";
      a[3] = "ec22476";
      a[4] = "ec22967";
      a[5] = "ec22614";
      a[6] = "ec22586";
      a[7] = "ec22452";
      a[8] = "ec22748";
      a[9] = "ec22923";
      a[10] = "ec22898";
      a[11] = "ec22890";
      a[12] = "ex21299";
      a[13] = "ec22576";
      a[14] = "ec22519";
      a[15] = "ec22551";
      a[16] = "ec22692";
      a[17] = "ec22790";
      a[18] = "ec22441";
      a[19] = "ex21213";
      a[20] = "ec22945";
      a[21] = "ec22415";
      a[22] = "ec22591";
      a[23] = "ec22738";
      a[24] = "ex20539";
      a[25] = "ec221024";
      a[26] = "ec22717";
      a[27] = "ex21247";
      a[28] = "ec221099";
      a[29] = "ec22872";
      a[30] = "ec22621";
      a[31] = "ec22927";
      a[32] = "ec22496";
      a[33] = "ec22937";
      a[34] = "ex21423";
      a[35] = "ec22992";
        
    return a;
    }//end getUsername
    
    public String[] shuffleArray(String[] array){
        
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndex = rand.nextInt(array.length);
            String temp = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }//end shuffle
    
}//end class
