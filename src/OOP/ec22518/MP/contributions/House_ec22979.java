package OOP.ec22518.MP.contributions;

class House_22979 implements Visitable {
public Direction visit(Visitor visitor, Direction d){
  visitor.tell("Welcome to the Casino degli Spiriti");
  visitor.tell("This house is made up of rooms contributed by: ");

return Direction.FROM_WEST;
}


}
