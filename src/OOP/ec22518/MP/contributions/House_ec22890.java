package OOP.ec22518.MP.contributions;

class House_ec22890 implements Visitable {
    private Room room1;//Hemat
    private Room room2;//Ilyas
    private Room room3;//Hamid
    private Room room4;//Tafsir
    private Room room5;//Naveed


    //creator method
    public House_ec22890(){
        this.room1 = new Room_ec22890();
        this.room2 = new Room_ec22473();
        this.room3 = new Room_ec22626();
        this.room4 = new Room_ec22837();
        this.room5 = new Room_ec22621();
    }
    
    public Direction visit(Visitor v, Direction d) {
        Boolean visited[] = { false, false, false, false, false, false };
        String Recently_visited;
        
        while((visited[0] == false) || (visited[1] == false) || (visited[2] == false) || (visited[3] == false) || (visited[4] == false)){
            
            if(visited[0] == false){
                d = room1.visit(v, d);
                visited[0] = true;
            }
                
            if(d.toString().equals("heading North")){
                d = room2.visit(v, d);
                Recently_visited = "room2";
                visited[1] = true;
                if(d.toString().equals("heading South")){
                    d = room1.visit(v, d);
                }
                d = updated_direction(Recently_visited, d);
            }
            else if(d.toString().equals("heading East")){
                d = room3.visit(v, d);
                Recently_visited = "room3";
                visited[2] = true;
                if(d.toString().equals("heading West")){
                    d = room1.visit(v, d);
                }
                d = updated_direction(Recently_visited, d);
            }
            else if(d.toString().equals("heading West")){
                d = room4.visit(v, d);
                Recently_visited = "room4";
                visited[3] = true;
                if(d.toString().equals("heading East")){
                    d = room1.visit(v, d);
                }
                d = updated_direction(Recently_visited, d);
            }
            else if(d.toString().equals("heading South")){
                d = room5.visit(v, d);
                Recently_visited = "room5";
                visited[4] = true;
                if(d.toString().equals("heading North")){
                    d = room1.visit(v, d);
                }
                d = updated_direction(Recently_visited, d);
            }
        }
        
        return d;
    }
    
    public Direction updated_direction(String Recently_visited, Direction d){
        if (Recently_visited.equals("room2")){
            if(d.toString().equals("heading North")){
                return d.opposite(d);
            }
        }
            
        if (Recently_visited.equals("room3")){
            if(d.toString().equals("heading East")){
                return d.opposite(d);
            }
        } 
            
        if (Recently_visited.equals("room4")){
            if(d.toString().equals("heading West")){
                return d.opposite(d);
            }
        }
        if (Recently_visited.equals("room5")){
            if(d.toString().equals("heading South")){
                return d.opposite(d);
            }
        }
        return d;
    }
}
