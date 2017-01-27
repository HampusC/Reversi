import java.util.ArrayList;

public class Game {
	public static void main(String args[]){
		
		int[][] board = new int[8][8];
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
		int round = 1;
		
		while(round < 2){ //32
			if(round % 2 == 0){
				turn(board, 2);
			} else {
				turn(board, 1);
			}
			round++;
		}
		
	
		
	}
	
	static void turn(int[][] board, int player){
		ArrayList<Position> positions = legal_moves(board, player);
		for(int m = 0; m < board.length; m++){
			for(int n = 0; n < board[0].length; n++){
				System.out.print(board[m][n]);
			}
			System.out.println();
		}
		for(int i = 0; i < positions.size(); i++){
			Position p = positions.get(i);
			System.out.print(p.geti() + "," + p.getj() + "   ");
			ArrayList<Position> poss = p.get_legal_from();
			for(int j = 0; j < poss.size(); j++){
				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
			}
			System.out.println();
		}
		set_piece(board, positions.get(2), player);
//		positions = legal_moves(board, 2);
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		//Calculate correct position
//		//set_piece(board, positions.get(3), player);
//		
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		set_piece(board, positions.get(1), 2);
//		positions = legal_moves(board, player);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(0), player);
//		positions = legal_moves(board, 2);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(2), 2);
//		positions = legal_moves(board, player);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(1), player);
//		positions = legal_moves(board, 2);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(2), 2);
//		positions = legal_moves(board, player);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(0), player);
//		positions = legal_moves(board, 2);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(1), 2);
//		positions = legal_moves(board, player);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(2), player);
//		positions = legal_moves(board, 2);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(1), 2);
//		positions = legal_moves(board, player);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(2), player);
//		positions = legal_moves(board, 2);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(7), 2);
//		positions = legal_moves(board, player);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(3), player);
//		positions = legal_moves(board, 2);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
//		
//		set_piece(board, positions.get(7), 2);
//		positions = legal_moves(board, player);
//		
//		for(int m = 0; m < board.length; m++){
//			for(int n = 0; n < board[0].length; n++){
//				System.out.print(board[m][n]);
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < positions.size(); i++){
//			Position p = positions.get(i);
//			System.out.print(p.geti() + "," + p.getj() + "   ");
//			ArrayList<Position> poss = p.get_legal_from();
//			for(int j = 0; j < poss.size(); j++){
//				System.out.print(poss.get(j).geti() + "," + poss.get(j).getj() + " " + poss.get(j).get_direction() + " ");
//			}
//			System.out.println();
//		}
		
	}
	
	static void set_piece(int[][] board, Position position, int player){
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
	}
	
	static ArrayList<Position> legal_moves(int[][] board, int player){
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
	
	static Position check_west(int[][] board, int opponent, int i, int j){
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
	
	static Position check_north_west(int[][] board, int opponent, int i, int j){
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

	static Position check_north(int[][] board, int opponent, int i, int j){
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
	
	static Position check_north_east(int[][] board, int opponent, int i, int j){
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
	
	static Position check_east(int[][] board, int opponent, int i, int j){
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

	static Position check_south_east(int[][] board, int opponent, int i, int j){
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

	static Position check_south(int[][] board, int opponent, int i, int j){
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

	static Position check_south_west(int[][] board, int opponent, int i, int j){
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
