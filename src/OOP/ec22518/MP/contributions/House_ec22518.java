package OOP.ec22518.MP.contributions;

import java.util.Scanner;
public class House_ec22518 implements Visitable {
    public static Room [][] rooms;
    public House_ec22518()
    {
        Room Room1 = new Room_ec22518();
        Room Room2 = new Room_ec22612();
        Room Room3 = new Room_ec22981();
        Room Room4 = new Room_ec22598();
        Room Hallway = new Hallway();
        Room AlternateHouse = new AlternateDimension();
        rooms = new Room [][]{{Room1, Hallway, Room2}, {Hallway, Hallway, AlternateHouse}, {Room3, Hallway, Room4 }};
    }
    
     static int row =0;
     static int col=0;
     static char choice ='z';
    public Direction visit(Visitor v, Direction d)
    {
        v.tell("Would you like to enter the house");
        
     choice = v.getChoice("a) Yes b)No", new char[]{'a', 'b'});
        
        final char STOP_CODE = 'b';
        
        
       
        while(choice!=STOP_CODE)
        {
            v.tell("From where would you like to enter");
            choice = v.getChoice("From where: a) North b)South", new char[]{'a', 'b'});
            
            switch(choice)
            {
                case 'a':
                row=1;
                col=0;
                break;
                
                case 'b':
                row=0;
                col=0;
                break;
            }
            
            while((row<rooms.length && row!=-1 ) && (col<rooms.length && col!=-1))
            {
            
                d= rooms[row][col].visit(v, d);
                 if(d==Direction.TO_NORTH)
                   {
                     row++;
                    }
                    
                else if(d==Direction.TO_SOUTH)
                     {
                         row--;
                     }
                     
                else if(d==Direction.TO_EAST)
                     {
                     col++;
                     }
                     
                else
                {
                    col--;
                }

            }
            
            
            v.tell("You have exited the house and are now in the garden");
            
            v.tell("You now have the choice to re-enter the house");
            
            choice = v.getChoice("a) Yes b) No", new char [] {'a', 'b'});
        }
        
        return d;
    }
    
    
    static class Hallway extends Room implements Visitable
    {
    
        public Direction visit(Visitor v, Direction d)
        {
            v.tell("You are now in the hallway, and you have the option to go in whatever direction you would like");
            choice = v.getChoice("North(n), South(s), East(e), West(w)", new char[] {'n', 's', 'e', 'w'});
            
            if(row==0)
            {
                if(choice=='n')
                   {
                      return Direction.TO_NORTH;
                    }
                    
                else if(choice=='s')
                     {
                          return Direction.TO_SOUTH;
                     }
                     
                else if(choice=='e')
                     {
                        return Direction.TO_EAST;

                     }
                     
                else
                {
                  return Direction.TO_WEST;

                }
            }
            
            else 
            {
                if(row==1)
                {
                        if(choice=='n')
                       {
                          return Direction.TO_NORTH;
                        }

                    else if(choice=='s')
                         {
                              return Direction.TO_SOUTH;
                         }

                    else if(choice=='e')
                         {
                            return Direction.TO_EAST;

                         }

                    else
                    {
                      return Direction.TO_WEST;

                    }
                }
            }
            
            return Direction.opposite(d);
        }
    
   }
   
   static class AlternateDimension extends Room implements Visitable
   {
       public static Room [][] alternateHouse;
       
       public AlternateDimension()
       {
           alternateHouse = new Room [][]{{null, null, null}, {null, null, null}, {null, null, null}};
       }
       
       public Direction visit(Visitor v, Direction d)
       {
           v.tell("You have stumbled into a portal. You have been transported into an alternate Dimension of the house you are currently in.");
           
           v.tell("In this alternate dimension, you can build your house by choosing the rooms that make it up. You can even include hallways");
           
           
           int room_counter =0;
           v.tell("Your new house consists of 3 floors, which each take a maximum of 3 rooms or hallways");
            v.tell("You may choose where in the house to place your room");
           while(room_counter<9)
           {
               int rooms_left = 9 - room_counter;
               v.tell("You have " + rooms_left + " rooms/hallways left to add");
               v.tell("Would you like to add a hallway or a room?");
               
               char choice = v.getChoice("Hallway (h) or Room (r)", new char []{'h', 'r'});
               
                 if(choice=='h')
                 {
                     int row_number = inputInt("In which floor would you like to place your hallway");
                     int col_number = inputInt("In which room number would you like to place your hallway");
                     
                     if(!(checkAvailability(alternateHouse, row_number-1, col_number-1)))
                     {
                         v.tell("WARNING: You have already place a room/hallway in that spot. Would you like to replace it with a new hallway?");
                         
                         choice = v.getChoice("Yes(y) or No(n)", new char []{'y', 'n'});
                         
                         if(choice=='y')
                         {
                             alternateHouse[row_number-1][col_number-1] = new Hallway();
                         }
                     }
                     
                     else
                     {
                         alternateHouse[row_number-1][col_number-1] = new Hallway();
                         room_counter++;
                     }
                 }
                 
                 
                else
                {
                   String room_username = inputString("What is the username associated with the room you would like to add");

                   Room new_room = Contributions.newRoomByUsername(room_username);
                   
                   while(new_room==null)
                   {
                       room_username = inputString("Please input a valid username");
                       new_room = Contributions.newRoomByUsername(room_username);
                       
                   }
                   
                   int row_number = inputInt("In which floor would you like to place your hallway");
                    int col_number = inputInt("In which room number would you like to place your hallway");
                    
                     if(!(checkAvailability(alternateHouse, row_number-1, col_number-1)))
                     {
                         v.tell("WARNING: You have already place a room/hallway in that spot. Would you like to replace it with a new room?");
                         
                         choice = v.getChoice("Yes(y) or No(n)", new char []{'y', 'n'});
                         
                         if(choice=='y')
                         {
                             alternateHouse[row_number-1][col_number-1] = new_room;
                         }
                     }
                     
                     else
                     {
                         alternateHouse[row_number-1][col_number-1] = new_room;
                         room_counter++;
                     }
                   
                }
              
           }
           
            v.tell("Well done, you have now completed the building of your house");
               v.tell("You now have option of replacing the original house with this alternate one");
               v.tell("If you say no, the new house you created will be lost");
               v.tell("If you say yes, you wont be able to visit an alternate dimension again, and this new house will be fixed in eternity");
               
               char choice2 = v.getChoice("Yes(y) or No(n)", new char []{'y', 'n'});
               
               if(choice=='y')
               {
                   rooms = alternateHouse;
               }
               
           
            return Direction.opposite(d);
       }
       
       public static String inputString(String message)
       {
           String answer;
        
            Scanner scanner = new Scanner(System.in);
            System.out.println(message);
            answer = scanner.nextLine();
            
            return answer;
       }
       
       
       public static int inputInt(String message)
       {
           String answer = inputString(message);
           
           int number;
           
           try
           {
               number=Integer.parseInt(answer);
           }
           
           catch (Exception a)
           {
               number = inputInt("Please input a valid number");
           }
           
           while(number>3 || number<=0)
           {
               number = inputInt("Please input a floor or room number between 1 and 3 (inclusive)");
           }
           
           return number;
       }
       
       public static boolean checkAvailability(Room [][] r, int i, int j)
       {
           return r[i][j]==null;
       }
       
   }
}
