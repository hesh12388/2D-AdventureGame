package OOP.ec22518.MP.contributions;

class House_ex21247 implements Visitable {
    private Room ruum1;
    private Room ruum2;
    private Room ruum3;
    private Room ruum4;
    
    public House_ex21247() 
    {
        ruum1 = new Room_ex21247();
        ruum2 = new Room_ex21247();
        ruum3 = new Room_ec22943();
        ruum4 = new Room_ec22890();
    }
    public Direction visit (Visitor v100,  Direction d1)
    {
    boolean onHouse= true;
    v100.tell("you are in in my room");
    
    while  (onHouse){
       v100.tell("you are in my house, you can enter eiter Room1, Room2, Room3, Room4 or leave my house");
       char[] slelectruum = {'1', '2', '3', '4', '0'};
       char c1= v100.getChoice ("what room do you want to enter (1) for ruum1, (2) for ruum2, (3) for ruum3, (4) for ruum4, ot (0) to leave the house ", slelectruum );
       Boolean visitruum1 = false;
   if (c1 == '1')
   {
     visitruum1= true;
     d1 = ruum1.visit(v100,d1);
   }
     else if (c1 =='2')
   {
    d1= ruum2.visit(v100,d1);
   } 
    else if (c1 =='3')
   {
    d1= ruum3.visit(v100,d1);
   }
    else if (c1 =='4')
   {
    d1= ruum4.visit(v100,d1);
   }
   else if (c1 =='0')
   {
     onHouse= false;
     v100.tell ("you have lfet the hosue");
   }
   else
   {
      v100.tell("there has been a mistake");
   }

  }
  return d1;

  }
}

    
