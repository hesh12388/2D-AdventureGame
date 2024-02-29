package OOP.ec22518.MP.contributions;

class House_ec22638 implements Visitable {
private Room RoomN;
private Room RoomS;
private Room RoomW;
private Room RoomE;
    House_ec22638()
     {
        RoomN=new Room_ec22638();
        RoomS=new Room_ec221021();//Makes u do pushups.
        RoomW=new Room_ec22587();//makes u turn on lights.
        RoomE=new Room_ec22804();//gives u random objects
    }
      public Direction visit(Visitor v, Direction d)
       {
        char [] choice={'a','b'};
       char [] room={'N','S'};

      v.tell("Welcome to my house. You are entering the house from the North direction");
    
        RoomN.visit(v,d);
              char c=v.getChoice("You have two options:a)enter other rooms b)return back from the same direction",choice);
            if(c=='a')
            {
               v.tell("You can enter the south room,the east room, the west room");  
               if(d==Direction.FROM_SOUTH)
               {
                    RoomS.visit(v,d);
                    v.tell("You can enter the east room, the west room or you can exit the house from the south direction.");
                   if(d==Direction.FROM_EAST)
                    {
                        RoomE.visit(v,d);
                        char e=v.getChoice("once you have finished the task you can exit from the south room or the north room",room);
                       if(e=='N')
                       {
                            v.tell("Thank you for entering our house. You are exiting from the North side");
                       }
                        else
                        {
                            v.tell("Thank you for entering our house. You are exiting from the South side");
                        }
                   }
              
              
                  else if(d==Direction.FROM_WEST)
                    {
                       RoomW.visit(v,d);
                       char e=v.getChoice("once you have finished the task you can exit from the south room or the north room",room);
                        if(e=='N')
                        {
                           v.tell("Thank you for entering our house. You are exiting from the North side");
                       }
                       else
                        {
                           v.tell("Thank you for entering our house. You are exiting from the South side");
                       }
                   }
                    else
                     {
                        v.tell("Thank you for entering our house");
                     }

               }
                else if(d==Direction.FROM_WEST)
                {
                     RoomW.visit(v,d);
                    v.tell("You can enter the south room and exit,west room or exit from the north direction.");
                    if(d==Direction.FROM_SOUTH)
                      {
                       RoomS.visit(v,d);
                        v.tell("Thank you for entering our house");
                      }
                      else if(d==Direction.FROM_EAST)
                       {
                        RoomE.visit(v,d);
                        char e=v.getChoice("once you have finished the task you can exit from the south room or north room",room);
                           if(e=='N')
                            {
                              v.tell("Thank you for entering our house. You are exiting from the North side");
                            }
                           else
                           {
                            v.tell("Thank you for entering our house. You are exiting from the South side");
                           }
                       }
                       else
                       {
                         v.tell("Thank you for entering our house.You are exiting from the North direction.");
                      }
               }
                else
                 {
                     RoomE.visit(v,d);
                     v.tell("You can enter the south room and exit,enter the west room or exit from the north direction.");
                     if(d==Direction.FROM_SOUTH)
                      {
                       RoomS.visit(v,d);
                        v.tell("Thank you for entering our house");
                   }
                   else if(d==Direction.FROM_WEST)
                    {
                        RoomW.visit(v,d);
                         char e=v.getChoice("once you have finished the task you can exit from the south room or the north room",room);
                        if(e=='N')
                       {
                           v.tell("Thank you for entering our house. You are exiting from the North side");
                        }
                        else
                         {
                             RoomS.visit(v,d);
                            v.tell("Thank you for entering our house. You are exiting from the South side ");
                        }
                    }
                    else
                     {
                         v.tell("Thank you for entering our house.You are exiting from the North direction.");
                    }
               }
           }
            else
           {
             v.tell("Thank you for entering our house");
           }
         return Direction.opposite(d); 
    }
    }
