package OOP.ec22518.MP.contributions;

class House_jpp317487 implements Visitable {
    Room[][] rooms = new Room[2][2];
      public House_jpp317487(){
          rooms[0][0] = Contributions.newRoomByUsername("ec22960");
          rooms[0][1] = Contributions.newRoomByUsername("ec221099");
          rooms[1][0] = Contributions.newRoomByUsername("ec22960");
          rooms[1][1] = Contributions.newRoomByUsername("jpp317487");
      }
      public int[] directionToCoords(Direction direction, int[]coords ){
          if(direction.equals(Direction.TO_NORTH)){
              coords[0]--;
          }else if(direction.equals(Direction.TO_SOUTH)){
              coords[0]++;
          }else if(direction.equals(Direction.TO_EAST)){
              coords[1]++;
          }else if(direction.equals(Direction.TO_WEST)){
              coords[1]--;
          }

          return coords;
      }
      public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom){
          int[]coords = {0,0};
          Direction direction = rooms[coords[0]][coords[1]].visit(visitor,directionVistorArrivesFrom);
          boolean loop = true;
          while(loop == true){
              coords = directionToCoords(direction, coords);
              if(coords[0] < 0 ||  coords[1] < 0 ||  coords[0] > 1||  coords[0] > 1){
                  loop = false;
              }
              else{
                  direction = rooms[coords[0]][coords[1]].visit(visitor,directionVistorArrivesFrom);
              }
          }
          return direction;
      }
}
