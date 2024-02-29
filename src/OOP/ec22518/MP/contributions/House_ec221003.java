package OOP.ec22518.MP.contributions;

import java.util.HashMap;

class RoomConnectionInfo {
	HashMap<Direction, Room> neighbours;
	Room currentRoom;
	RoomConnectionInfo(Room currentRoom) {
		this.currentRoom = currentRoom;
		this.neighbours = new HashMap<Direction, Room>();
	}
	void SetDirection(Direction direction, Room room) {
		this.neighbours.put(direction, room);
	}
	void RemoveDirection(Direction direction) {
		this.neighbours.remove(direction);
	}
	Room GetRoom(Direction direction) {
		return this.neighbours.get(direction);
	}
}
public class House_ec221003 implements Visitable {
	HashMap<String, Room> rooms;
	HashMap<Room, RoomConnectionInfo> roomNeighbours;

	House_ec221003() {
		super();
		Room_ec22882 andyRoom = new Room_ec22882();
		Room_ec22617 zakRoom = new Room_ec22617();
		Room_ec22941 ionRoom = new Room_ec22941();
		Room_ec221003 cheehoRoom = new Room_ec221003();
		Room_ec19389 sheshaneRoom = new Room_ec19389();
		Room_ec22429 cristinaRoom = new Room_ec22429();

		this.rooms = new HashMap<String, Room>();
		this.rooms.put("andyRoom", andyRoom);
		this.rooms.put("zakRoom", zakRoom);
		this.rooms.put("ionRoom", ionRoom);
		this.rooms.put("cheehoRoom", cheehoRoom);
		this.rooms.put("sheshaneRoom", sheshaneRoom);
		this.rooms.put("cristinaRoom", cristinaRoom);

		this.roomNeighbours = new HashMap<Room, RoomConnectionInfo>();
		RoomConnectionInfo andyRoomNeighbours = new RoomConnectionInfo(andyRoom);
		RoomConnectionInfo zakRoomNeighbours = new RoomConnectionInfo(zakRoom);
		RoomConnectionInfo ionRoomNeighbours = new RoomConnectionInfo(ionRoom);
		RoomConnectionInfo cheehoRoomNeighbours = new RoomConnectionInfo(cheehoRoom);
		RoomConnectionInfo sheshaneRoomNeighbours = new RoomConnectionInfo(sheshaneRoom);
		RoomConnectionInfo cristinaRoomNeighbours = new RoomConnectionInfo(cristinaRoom);
		final RoomConnectionInfo[] graph = {andyRoomNeighbours, zakRoomNeighbours, ionRoomNeighbours, cheehoRoomNeighbours, sheshaneRoomNeighbours, cristinaRoomNeighbours};
		this.roomNeighbours.put(andyRoom, andyRoomNeighbours);
		this.roomNeighbours.put(zakRoom, zakRoomNeighbours);
		this.roomNeighbours.put(ionRoom, ionRoomNeighbours);
		this.roomNeighbours.put(cheehoRoom, cheehoRoomNeighbours);
		this.roomNeighbours.put(sheshaneRoom, sheshaneRoomNeighbours);
		this.roomNeighbours.put(cristinaRoom, cristinaRoomNeighbours);
		this.setup_roomInterconnects(graph);

		System.out.println("House ec221003 created!");
	}

	void setup_roomInterconnects(RoomConnectionInfo[] graph) {
		if (graph.length == 6) {
			// this is the format I drew on paper.

			RoomConnectionInfo q1 = graph[0];
			RoomConnectionInfo q2 = graph[1];
			RoomConnectionInfo q3 = graph[2];
			RoomConnectionInfo q4 = graph[3];
			RoomConnectionInfo q5 = graph[4];
			RoomConnectionInfo q6 = graph[5];

			// q1 connects to NESW {q2, q4, q6, q4}
			q1.SetDirection(Direction.TO_NORTH, q2.currentRoom);
			q1.SetDirection(Direction.TO_EAST, q4.currentRoom);
			q1.SetDirection(Direction.TO_SOUTH, q6.currentRoom);
			q1.SetDirection(Direction.TO_WEST, q4.currentRoom);

			// q2 connects to NESW {q5, q3, q4, q6}
			q2.SetDirection(Direction.TO_NORTH, q5.currentRoom);
			q2.SetDirection(Direction.TO_EAST, q3.currentRoom);
			q2.SetDirection(Direction.TO_SOUTH, q4.currentRoom);
			q2.SetDirection(Direction.TO_WEST, q6.currentRoom);

			// q3 connects to NESW {q2, q6, q4, q6}
			q3.SetDirection(Direction.TO_NORTH, q2.currentRoom);
			q3.SetDirection(Direction.TO_EAST, q6.currentRoom);
			q3.SetDirection(Direction.TO_SOUTH, q4.currentRoom);
			q3.SetDirection(Direction.TO_WEST, q6.currentRoom);

			// q4 connects to NESW {q3, q1, q5, q1}
			q4.SetDirection(Direction.TO_NORTH, q3.currentRoom);
			q4.SetDirection(Direction.TO_EAST, q1.currentRoom);
			q4.SetDirection(Direction.TO_SOUTH, q5.currentRoom);
			q4.SetDirection(Direction.TO_WEST, q1.currentRoom);

			// q5 connects to NESW {q1, q4, null, q6}
			q5.SetDirection(Direction.TO_NORTH, q1.currentRoom);
			q5.SetDirection(Direction.TO_EAST, q4.currentRoom);
			// q5.SetDirection(Direction.TO_SOUTH, null);
			q5.SetDirection(Direction.TO_WEST, q6.currentRoom);

			// q6 connects to NESW {q1, q3, q5, q3}
			q6.SetDirection(Direction.TO_NORTH, q1.currentRoom);
			q6.SetDirection(Direction.TO_EAST, q3.currentRoom);
			q6.SetDirection(Direction.TO_SOUTH, q5.currentRoom);
			q6.SetDirection(Direction.TO_WEST, q3.currentRoom);


		} else {
			// rip, you're on your own.
		}
		return;
	}

	@Override
	public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
		
		int stamina = 30;

		// enter house into q5, leave house from q5 towards South direction
		Room currentRoom = this.rooms.get("sheshaneRoom");
		boolean shouldExit = false;
		while(!shouldExit) {
			Direction directionToMove = currentRoom.visit(visitor, directionVistorArrivesFrom);
			stamina -= 1;
			if (stamina <= 0 || directionToMove == null || (currentRoom == this.rooms.get("sheshaneRoom") && directionToMove == Direction.TO_SOUTH)) {
				shouldExit = true;
			} else {
				RoomConnectionInfo currentRoomConnectionInfo = this.roomNeighbours.get(currentRoom);
				Room nextRoom = currentRoomConnectionInfo.GetRoom(directionToMove);
				if (nextRoom == null) {
					shouldExit = true;
				} else {
					currentRoom = nextRoom;
				}
			}
		}
		visitor.tell("I'm leaving the house now.");

		return null;
	}

	public static void main(String[] args) {
		House_ec221003 house = new House_ec221003();
		Visitor visitor = new Room_ec221003.Robot();

		house.visit(visitor, Direction.FROM_SOUTH);

		System.out.println("[End of Program]");
	}
}
