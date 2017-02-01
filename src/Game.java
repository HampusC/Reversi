import java.util.ArrayList;

public class Game {

	GUI gui;
	int[][] board;

	public Game(){
		gui = new GUI(this);
		board = new int[8][8];
	}

	public void run(){

		gui.makeGUI();

		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
		int round = 1;

		gui.updateGUI(board, new ArrayList<Position>());

		while(round < 2){ //32
			if(round % 2 == 0){
				turn(2);
			} else {
				turn(1);
			}
			round++;
		}



	}

	public void turn(int player){
		ArrayList<Position> positions = legal_moves(player);
		gui.updateGUI(board, positions);

	}

	public void set_piece(Position position, int player){
		board[position.geti()][position.getj()] = player;
		ArrayList<Position> legal_from = position.get_legal_from();
		for(int i = 0; i < legal_from.size(); i++){
			Position cur = legal_from.get(i);
			String direction = cur.get_direction();
			switch (direction) {
				case "west":
					for(int j = 1; j < cur.getj() - position.getj(); j++){
						board[cur.geti()][cur.getj()-j] = player;
				 	};
					break;
				case "north_west":
					for(int j = 1; j < cur.getj() - position.getj(); j++){
						board[cur.getj()-j][cur.getj()-j] = player;
				 	};
				 	break;
				case "north":
					for(int j = 1; j < cur.geti() - position.geti(); j++){
						board[cur.geti()-j][cur.getj()] = player;
				 	};
				 	break;
				case "north_east":
					for(int j = 1; j < position.getj() - cur.getj(); j++){
						board[cur.geti()-j][cur.getj()+j] = player;
				 	};
				 	break;
				case "east":
					for(int j = 1; j < position.getj() - cur.getj(); j++){
						board[cur.geti()][cur.getj()+j] = player;
				 	};
				 	break;
				case "south_east":
					for(int j = 1; j < position.getj() - cur.getj(); j++){
						board[cur.geti()+j][cur.getj()+j] = player;
				 	};
				 	break;
				case "south":
					for(int j = 1; j < position.geti() - cur.geti(); j++){
						board[cur.geti()+j][cur.getj()] = player;
				 	};
				 	break;
				case "south_west":
					for(int j = 1; j < cur.getj() - position.getj(); j++){
						board[cur.geti()+j][cur.getj()-j] = player;
				 	};
				 	break;
			}
		}
		int opponent;
		if(player==1){
			opponent=2;
		}else{
			opponent=1;
		}

		ArrayList<Position> legal_moves = legal_moves(opponent);
		gui.updateGUI(board, legal_moves);
	}

	public ArrayList<Position> legal_moves(int player){
		ArrayList<Position> positions = new ArrayList<Position>();
		int opponent;
		if(player==1){
			opponent=2;
		}else{
			opponent=1;
		}
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] == player){

					Position p;

					p = check_west(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "west");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_north_west(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "north_west");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_north(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "north");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_north_east(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "north_east");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_east(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "east");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_south_east(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "south_east");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_south(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "south");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_south_west(opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "south_west");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}

//					Position p;
//					p = check_west(board, opponent, i, j);
//					System.out.println("WEST===" + i + "," + j + " Player: " + player + " Opponent: " + opponent);
//					System.out.println(p.geti());
//					System.out.println(p.getj());
//					System.out.println("NORTH===" + i + "," + j + " Player: " + player + " Opponent: " + opponent);
//					p = check_north(board, opponent, i, j);
//					System.out.println(p.geti());
//					System.out.println(p.getj());
//					System.out.println("EAST===" + i + "," + j + " Player: " + player + " Opponent: " + opponent);
//					p = check_east(board, opponent, i, j);
//					System.out.println(p.geti());
//					System.out.println(p.getj());
//					System.out.println("SOUTH===" + i + "," + j + " Player: " + player + " Opponent: " + opponent);
//					p = check_south(board, opponent, i, j);
//					System.out.println(p.geti());
//					System.out.println(p.getj());


				}
			}
		}

		return positions;
	}

	public Position check_west(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(j-counter < 0){
			return position;
		}
		int w = board[i][j-counter];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(j-counter < 0){
				return position;
			}
			w = board[i][j-counter];
		}
		if(w == 0){
			position.seti(i);
			position.setj(j-counter);
		} else {
			return position;
		}
		return position;
	}

	public Position check_north_west(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(i-counter < 0 || j-counter < 0){
			return position;
		}
		int w = board[i-counter][j-counter];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(i-counter < 0 || j-counter < 0){
				return position;
			}
			w = board[i-counter][j-counter];
		}
		if(w == 0){
			position.seti(i-counter);
			position.setj(j-counter);
		} else {
			return position;
		}
		return position;
	}

	public Position check_north(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(i-counter < 0){
			return position;
		}
		int w = board[i-counter][j];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(i-counter < 0){
				return position;
			}
			w = board[i-counter][j];
		}
		if(w == 0){
			position.seti(i-counter);
			position.setj(j);
		} else {
			return position;
		}
		return position;
	}

	public Position check_north_east(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(i-counter < 0 || j+counter > 7){
			return position;
		}
		int w = board[i-counter][j+counter];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(i-counter < 0 || j+counter > 7){
				return position;
			}
			w = board[i-counter][j+counter];
		}
		if(w == 0){
			position.seti(i-counter);
			position.setj(j+counter);
		} else {
			return position;
		}
		return position;
	}

	public Position check_east(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(j+counter > 7){
			return position;
		}
		int w = board[i][j+counter];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(j+counter > 7){
				return position;
			}
			w = board[i][j+counter];
		}
		if(w == 0){
			position.seti(i);
			position.setj(j+counter);
		} else {
			return position;
		}
		return position;
	}

	public Position check_south_east(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(i+counter > 7 || j+counter > 7){
			return position;
		}
		int w = board[i+counter][j+counter];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(i+counter > 7 || j+counter > 7){
				return position;
			}
			w = board[i+counter][j+counter];
		}
		if(w == 0){
			position.seti(i+counter);
			position.setj(j+counter);
		} else {
			return position;
		}
		return position;
	}

	public Position check_south(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(i+counter > 7){
			return position;
		}
		int w = board[i+counter][j];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(i+counter > 7){
				return position;
			}
			w = board[i+counter][j];
		}
		if(w == 0){
			position.seti(i+counter);
			position.setj(j);
		} else {
			return position;
		}
		return position;
	}

	public Position check_south_west(int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int counter = 1;
		if(i+counter > 7 || j-counter < 0){
			return position;
		}
		int w = board[i+counter][j-counter];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			counter++;
			if(i+counter > 7 || j-counter < 0){
				return position;
			}
			w = board[i+counter][j-counter];
		}
		if(w == 0){
			position.seti(i+counter);
			position.setj(j-counter);
		} else {
			return position;
		}
		return position;
	}
}