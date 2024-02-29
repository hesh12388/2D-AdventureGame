package OOP.ec22518.MP.contributions;// Since I get marks for continuity, and A5 is still closed, here are my plans for my house class:
// Decide on other people's rooms to use - complete 14/03 17:51, will be using:
// ec22623 - Rijul's
// ec22616 - Saif's
// ec22943 - Tabassum's

// Decide on a layout for the house
// A tough one to decide on, but looking at everyone's code, I decided on something like this:
// (forgive the scuffed representation)


//                  pool            


////////////////////    //////////////////////////
//             //           //                  //
//             /////    //////                  //
//             //           //                  //
//    rijul's      saif's        tabassum's     //
//             //           //                  //
//             /////    //////                  //
//             //           //                  //
/////////////////          ///////////////////////
               //           //                  
               //  hallway  //                  
               //           //                  
               //           //         
               //           //                  
               /////    //////                  
               //            /                  
               // justin(ent)/
               //            /
               ////        ///                 

// I thought about adding a bathroom on a second floor.
// This would be accessible from the hallway (which has a staircase) and would span the same area as the three rooms below it.
// There will also be an option to jump from the bathroom into the pool, so the pool will need a dry and wet state, too.
// The house also needs an exit, so I will make the pool area the exit (it isn't fenced or anything)

// To summarise my thoughts into criteria:

// Start connecting rooms on first floor together, including the hallway. (DONE - 15/03 20:00)

// Implement pool area and options to do, including a dry and wet state for the user and dynamic dialogue for those states. (DONE - 15/03 22:40)

// Edit hallway to give access to 2nd floor (DONE - 15/03 22:50)

// Implement bathroom and options to do, including a jump into the pool. (DONE!!! - 15/03 23:55 - just before midnight!)

// Polish up any gramamtical errors, bugs, \n lines to make the ui look nicer. Update comments. (DONE - 16/03 00:11)




// With that, I'm satisfied with the functioning of my house. This was incredibly fun to code, despite a VERY annoying bug I encountered on day 1. Of course, I like to give credit where it's due, so:

// Thank you to ec22765 - Maksymilian Jan Matusiak - for giving me an idea on how to sort out the rooms, since I had no idea how to do
// the directions and .visit() notation before this.

// Saif, Tabassum and Rijul for their room code.


// Also, if any GitHub people would like to use any parts of my code, feel free! I don't mind. Just, do credit me AND the people above this, thanks!


class House_ec22462 implements Visitable {
    
    Room j; Room r; Room t; Room s; Room[] allRooms; Room currentRoom; boolean inHouse; boolean bathroom; boolean pool; boolean wet; boolean firstVisitPool; boolean firstVisitBathroom; boolean hallway; boolean towel;
    
    House_ec22462() {
        j = new Room_ec22462();
        r = new Room_ec22623();
        t = new Room_ec22943();
        s = new Room_ec22616();
        
        allRooms = new Room[] {j,s,r,t};
        currentRoom=j; // starting room (mine)
        
        inHouse=true; // exit condition
        
        // rooms that aren't exactly rooms (special states)
        bathroom=false;
        hallway=false;
        pool=false;
        
        // pool-related
        wet=false;
        towel=true;
        
        // dynamic dialogue
        firstVisitPool=true;
        firstVisitBathroom=true;
    }
    
    // outlines all possible scenarios of moving in the house.
    public Direction visit(Visitor v, Direction d) {
        while (inHouse) {
            
            boolean alreadyExecuted=false; // disallows double running of inHallway() in the same run (was a VERY annoying bug until this fixed it)
            
            // for the case where user goes to 2nd floor and returns to hallway immediately
            if (hallway) {if (!alreadyExecuted){inHallway(v,d); hallway=false; alreadyExecuted=true;}}
            
            Direction currentDirection = currentRoom.visit(v,d);
            
            // in room ec22616 (saif's)
            if (currentRoom.equals(s)) {
                if (currentDirection.equals(Direction.TO_WEST)) {r.visit(v,Direction.FROM_WEST);
                    v.tell("\nReturning to Room ec22616 . . .\n");} // go to rijul's room, returns to saif's room 
                
                else if (currentDirection.equals(Direction.TO_EAST)) {t.visit(v,Direction.FROM_NORTH);
                    v.tell("\nReturning to Room ec22616 . . .\n");} // go to tabassum's room, returns to saif's room
                
                else if (currentDirection.equals(Direction.TO_NORTH)) {inPool(v,d);} // go to pool
                
                else if (currentDirection.equals(Direction.TO_SOUTH)) {if (!alreadyExecuted){inHallway(v,d); alreadyExecuted=true;}}
                // goes back to hallway, is subjected to alreadyExecuted treatment
            }
            
            // in room ec22462 (mine)
            if (currentRoom.equals(j)) {if (!alreadyExecuted) {inHallway(v,d); alreadyExecuted=true;}}
            
        // if statements are in this particular order to avoid state and room clashes.
        }
        
        return d; // returns the last direction returned.
}
    
    void inHallway (Visitor v, Direction d) { // in hallway
        hallway=true;
        while (hallway) {
            v.tell("\nYou are in the hallway.");
            v.tell("\nDo you want to:");
                char [] ui = {'a','b','c'};
                char option = v.getChoice("\n(a) Go to Room ec22616 (b) Return to Room ec22462 (c) Go upstairs", ui);
                if (option=='a') { // go to saif's room
                    v.tell("\nGoing to Room ec22616 . . .");
                    currentRoom=s;
                    hallway=false;
                }

                else if (option=='b') { // go to my room
                    v.tell("\nReturning to Room ec22462 . . .");
                    currentRoom=j;
                    hallway=false;
                }

                else { // go upstairs to bathroom
                    v.tell("\nGoing upstairs . . .");
                    inBathroom(v,d);
                }
            }
    }
    
    void inPool (Visitor v, Direction d) { // in pool
        pool=true;
        if (firstVisitPool) { // first time entering pool
            v.tell("\nYou are at the pool.");
            v.tell("\nThis is the final area of House ec22462.");
        }
        
        else { // otherwise
            v.tell("\nYou are back at the pool.");
        }
        
        while (pool) {
            v.tell("\nDo you want to:");
            char [] ui = {'a','b','c'};
            char option = v.getChoice("\n(a) Leave House ec22462 (b) Return to Room ec22616 (c) Jump into the pool", ui);
            switch(option) {

                case 'a':
                    if (!wet) { // leave house while dry  
                        v.tell("\nYou leave House ec22462.");
                        v.tell("\nGoodbye!");
                    }
                    
                    if (wet) { // leave house while wet
                        v.tell("\nYou leave House ec22462, soaking wet.");
                        v.tell("\nGoodbye...?");
                    }
                    pool=false;
                    inHouse=false;
                    break;

                case 'b': // return to saif's room
                    v.tell("\nGoing to Room ec22616 . . .");
                    currentRoom=s;
                    pool=false;
                    firstVisitPool=false;
                    break;
                    
                case 'c': // jump into pool
                    if (!wet) { // if not wet already
                        v.tell("\nLetting the intrusive thoughts win, you jump into the pool.");
                        wet=true;
                        v.tell("\nInstant regret follows.");
                        v.tell("\nSome gold also falls out of your pockets. (-5)");
                        v.takeGold(5);
                        v.tell("\nYou notice a dry towel and a change of clothes on the second floor.");
                        v.tell("\nIt appears to be reachable from the hallway.");
                        v.tell("\nWouldn't hurt to dry off before leaving, you know?");
                    }
                    else { // if already wet
                        v.tell("\nOnce again, you jump into the pool.");
                        v.tell("\nYou can't get wet twice, can you?");
                        v.tell("\nBut, you can lose gold twice. (-5)");
                        v.takeGold(5);
                        v.tell("\nBlah blah blah change of clothes, second floor, hallway, you already know.");
                    }
                    break;
            }
            
        }
    }
    
    void inBathroom(Visitor v, Direction d) { // in bathroom
        bathroom=true;
        if (firstVisitBathroom) { // first time entering bathroom
            v.tell("\nYou are on the second floor.");
            v.tell("\nThe room appears to be an bathroom of sorts, but the shower is broken.");
        }
        
        else { // otherwise
            v.tell("\nYou are back in the bathroom.");
        }
        while (bathroom) {
            v.tell("\nDo you want to:");
            char [] ui = {'a','b','c'};
            char option = v.getChoice("\n(a) Return to the hallway (b) Dry yourself (c) Jump to the pool below (???)", ui);
            switch(option) {

                case 'a': // return to hallway
                    v.tell("\nReturning to the hallway . . .");
                    bathroom=false;
                    hallway=true;
                    firstVisitBathroom=false;
                    break;

                case 'b': // dynamic dialogue via if statements depending on wet and towel state (not annotating all of these.)
                    if (towel && !wet) {
                        v.tell("\nWell, you're not wet or anything, but you use the towel.");
                        v.tell("\nBecause I'm nice, I'll let you use it without using up towel uses.");
                        v.tell("\nOh, if you do use the towel and you're wet, you will use up the towel, though.");
                        v.tell("\nIt's a one-use item, so don't waste it!");
                    }
                    
                    else if (towel && wet) {
                        v.tell("\nYou dry yourself off.");
                        towel=false;wet=false;
                        v.tell("\nIf you jump into the pool again, tough luck.");
                    }
                    
                    else if (!towel && !wet) {
                        v.tell("\nYou already dried yourself off!");
                        v.tell("\nDon't get greedy!");
                    }
                        
                    else {
                        v.tell("\nNo. I warned you.");
                        v.tell("\nStay wet.");
                    }
                    break;
                    
                case 'c': // more dynamic dialogue regarding jumping into the pool.
                    if (towel && !wet) {
                        v.tell("\nFor whatever reason, you decide to jump out from a window");
                        v.tell("\nall the way down to the pool below.");
                    }
                        
                    else if (towel && wet) {
                        v.tell("\nBecause one dive into that pool wasn't enough,");
                        v.tell("\nyou do it once again.");
                    }
                        
                    else if (!towel && !wet) {
                        v.tell("\nOk, well, you're dumb, but whatever.");
                        v.tell("\nYou jump into the pool again.");
                        v.tell("\nHave fun staying wet.");
                    }
                        
                    else {
                        v.tell("\nWith absolutely nothing to lose, except some gold,");
                        v.tell("\nyou jump into the pool again.");
                    }
                        
                    v.tell("\nOh, you also lose some gold. (-5)");
                    v.takeGold(5);
                    wet=true;
                    bathroom=false;
                    hallway=false;
                    currentRoom=s;
                    firstVisitBathroom=false;
                    inPool(v,d);
                    break;
            }
                   
        }
    }
            
}