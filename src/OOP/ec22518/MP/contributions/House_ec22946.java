package OOP.ec22518.MP.contributions;

class House_ec22946 implements Visitable {
	private Room[][] rooms;
	private int house_size;
	
	public House_ec22946()
	{
		String[] room_usernames = Contributions.getRoomUsernames();
		int number_of_rooms = room_usernames.length;
		
		int minimum_area_of_house = calculate_largest_square_number_smaller_than(number_of_rooms);
		int minimum_length_width_of_house = (int) Math.sqrt(minimum_area_of_house);
		house_size = minimum_length_width_of_house;
		
		rooms = new Room[house_size][house_size];
		
		int rooms_created = 0;
		
		for(int i = 0; i < house_size; i++)
		{
			for(int j = 0; j < house_size; j++)
			{
				String current_room_username = room_usernames[rooms_created];
				rooms[i][j] = Contributions.newRoomByUsername(current_room_username);
				rooms_created++;
			}
		}		
	}
	
	private int calculate_largest_square_number_smaller_than(int n)
	{
		/*
		int maximum_square_number = 0;
		int i = 0;
		int laggy_maximum_square_number = 0;
		while(maximum_square_number < n)
		{
			laggy_maximum_square_number = maximum_square_number;
			i++;
			maximum_square_number = i*i;
		}
		maximum_square_number = laggy_maximum_square_number;
		//maximum_square_number = (i-1)*(i-1);
		return maximum_square_number;
		*/
		return (int) Math.sqrt(n) * (int) Math.sqrt(n);
	}
	
    public Direction visit(Visitor visitor, Direction direction)
	{
		int visitor_current_x_position = 0, visitor_current_y_position = 0;
		int center_of_house_x_and_y = house_size/2;
		
		visitor_current_x_position = center_of_house_x_and_y;
		visitor_current_y_position = center_of_house_x_and_y;
		
        Room center_starting_room = rooms[center_of_house_x_and_y][center_of_house_x_and_y];
		
		Room current_room = center_starting_room;

		Direction last_direction = Direction.FROM_WEST;
		
		while(!visitor_is_out_of_bounds(visitor_current_x_position, visitor_current_y_position))
		{
			current_room = rooms[visitor_current_x_position][visitor_current_y_position];
			//System.out.println("\n" + "\n");
			//System.out.println("x: " +visitor_current_x_position + " y: " +  visitor_current_y_position+ " direction: " + last_direction);
			//System.out.println("\n" + "\n");
				
			try
			{
				last_direction = current_room.visit(visitor, direction);
			}
			catch(Exception e)
			{
				last_direction = Direction.turnLeft(last_direction);
				current_room = rooms[(new java.util.Random()).nextInt(15)][(new java.util.Random()).nextInt(15)];
			}
			
			int[] change_in_coordinates = convert_direction_to_coordinate_change(last_direction);
			
			visitor_current_x_position += change_in_coordinates[0];
			visitor_current_y_position += change_in_coordinates[1];
			if(visitor_current_x_position > house_size || visitor_current_y_position > house_size)
				break;
		}
		return last_direction;
    }
	
	private int[] convert_direction_to_coordinate_change(Direction direction_to_convert)
	{
		if(direction_to_convert == Direction.TO_NORTH)
			return new int[]{0,1};
		else if(direction_to_convert == Direction.TO_EAST)
			return new int[]{1,0};
		else if(direction_to_convert == Direction.TO_SOUTH)
			return new int[]{0,-1};
		else if(direction_to_convert == Direction.TO_WEST)
			return new int[]{-1,0};
		else
			return new int[]{(new java.util.Random()).nextInt(2),(new java.util.Random()).nextInt(2)};
	}
	
	private boolean visitor_is_out_of_bounds(int x, int y)
	{
		return (x > (house_size - 1) || y > (house_size - 1)) || (x < 0 || y < 0);
	}
}
