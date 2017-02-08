import java.util.ArrayList;

public class Game {

	int BLACK_PLAYER = 1;
	int WHITE_PLAYER = 2;
	GUI gui;
	int[][] board;
	double time;

	public Game(double time){
		gui = new GUI(this);
		board = new int[8][8];
		this.time = time;
	}

	public void set_up(){
		gui.makeGUI();
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
		int starting_player = BLACK_PLAYER;

		ArrayList<Position> positions = legal_moves(board, starting_player);
		gui.updateGUI(board, positions, false);
	}

	public boolean set_piece(Position position, int player){
		board[position.geti()][position.getj()] = player;
		ArrayList<Position> legal_from = position.get_legal_from();
		flip(board, position, player, legal_from);

		int opponent;
		if(player==BLACK_PLAYER){
			opponent=WHITE_PLAYER;
		}else{
			opponent=BLACK_PLAYER;
		}

		ArrayList<Position> legal_moves = legal_moves(board, opponent);
		if(legal_moves.size() == 0){
			legal_moves = legal_moves(board, player);
			gui.updateGUI(board, legal_moves, false);
			return false;
		}
		gui.updateGUI(board, legal_moves, true);
		return true;
	}

	public void execute_ai() {
		AI ai;
		int[][] ai_board = copy_board(board);
		ai = new AI(time);
		ai.send_AI_board(ai_board);
		Position pos = null;
		ai.start();
		try {
			ai.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ai.interrupt();
		pos = ai.get_best_move();
		gui.force_click(pos);
	}

	public static void flip(int[][] board, Position position, int player, ArrayList<Position> legal_from) {
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
						board[cur.geti()-j][cur.getj()-j] = player;
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
	}

	public static ArrayList<Position> legal_moves(int[][] board, int player){
		int BLACK_PLAYER = 1;
		int WHITE_PLAYER = 2;
		ArrayList<Position> positions = new ArrayList<Position>();
		int opponent;
		if(player==BLACK_PLAYER){
			opponent=WHITE_PLAYER;
		}else{
			opponent=BLACK_PLAYER;
		}
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] == player){
					Position p;

					p = check_west(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "west");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_north_west(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "north_west");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_north(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "north");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_north_east(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "north_east");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_east(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "east");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_south_east(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "south_east");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_south(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "south");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
					p = check_south_west(board, opponent, i, j);
					if(p.geti() != - 1){
						Position from = new Position(i,j, "south_west");
						if(positions.contains(p)){
							positions.get(positions.indexOf(p)).insert_legal_from(from);
						} else {p.insert_legal_from(from);
							positions.add(p);
						}
					}
				}
			}
		}
		return positions;
	}

	private static Position check_west(int[][] board, int opponent, int i, int j){
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

	private static Position check_north_west(int[][] board, int opponent, int i, int j){
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

	private static Position check_north(int[][] board, int opponent, int i, int j){
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

	private static Position check_north_east(int[][] board, int opponent, int i, int j){
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

	private static Position check_east(int[][] board, int opponent, int i, int j){
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

	private static Position check_south_east(int[][] board, int opponent, int i, int j){
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

	private static Position check_south(int[][] board, int opponent, int i, int j){
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

	private static Position check_south_west(int[][] board, int opponent, int i, int j){
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

	public static int[][] copy_board(int[][] board){
    int[][] copied_board = new int[board.length][board[0].length];
    for(int i = 0 ; i < copied_board.length; i++){
      for(int j = 0; j < copied_board[0].length; j++){
        copied_board[i][j] = board[i][j];
      }
    }
    return copied_board;
  }

}
