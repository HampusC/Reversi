import java.util.ArrayList;

public class AI {

	Position best_move;
	int level;
	Action temp;

	public AI(){
		best_move = null;
		level = 3;
	}

	public Position execute_move(int[][] ai_board, ArrayList<Position> legal_moves) {

		level = 1;
		while(level < 3){
			minimax(ai_board, legal_moves, level);
			level++;
			System.out.println("level: " + level + " Coords: " + best_move.geti() + "" + best_move.getj());
		//	System.out.println("==============");
		}
		System.out.println();
			return best_move;

		//	level++;
//		}
	}

	public void minimax(int[][] ai_board, ArrayList<Position> legal_moves, int cur_level){

		Action a = max_value(ai_board, legal_moves, 1);
		best_move = a.getPosition();

	}

	public Action max_value(int[][] ai_board, ArrayList<Position> legal_moves, int cur_level){
		//System.out.println(cur_level);
		if(legal_moves.size() == 0){
			return new Action(-1000, new Position(-1, -1));
		}
		if(level == cur_level){
			int max = Integer.MIN_VALUE;
			Position cur_best_move = null;
			for(int k = 0; k < legal_moves.size(); k++){
				int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
				for(int i = 0 ; i < ai_board.length; i++){
					for(int j = 0; j < ai_board[0].length; j++){
						new_ai_board[i][j] = ai_board[i][j];
					}
				}
				// for(int i = 0 ; i < ai_board.length; i++){
				// 	for(int j = 0; j < ai_board[0].length; j++){
				// 		System.out.print(new_ai_board[i][j]);
				// 	}
				// 	System.out.println();
				// }
				// System.out.println();
				int flipped = Game.set_piece_ai(new_ai_board, legal_moves.get(k), 2);
				if(flipped > max){
					max = flipped;
					cur_best_move = legal_moves.get(k);
				}
			}
			Action action = new Action(max, cur_best_move);
			//System.out.println("max: " + action.getValue() + " " + action.getPosition().geti() + "" + action.getPosition().getj());
			temp = action;
			return action;
		}
		int max = Integer.MIN_VALUE;
		Action res = null;
		for(int k = 0; k < legal_moves.size(); k++){
			int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
			for(int i = 0 ; i < ai_board.length; i++){
				for(int j = 0; j < ai_board[0].length; j++){
					new_ai_board[i][j] = ai_board[i][j];
				}
			}
			Game.set_piece_ai(new_ai_board, legal_moves.get(k), 2);
			// for(int i = 0 ; i < ai_board.length; i++){
			// 	for(int j = 0; j < ai_board[0].length; j++){
			// 		System.out.print(new_ai_board[i][j]);
			// 	}
			// 	System.out.println();
			// }
			ArrayList<Position> new_legal_moves = Game.legal_moves(new_ai_board, 1);
			Action action = min_value(new_ai_board, new_legal_moves, cur_level+1);
			if(action.getValue() > max){
				max = action.getValue();
				res = new Action(max, legal_moves.get(k));
			}
		}
		//System.out.println("max: " + res.getValue() + " " + res.getPosition().geti() + "" + res.getPosition().getj());
		temp = res;
		return res;
	}

	public Action min_value(int[][] ai_board, ArrayList<Position> legal_moves, int cur_level){
		// System.out.println(cur_level);
		if(legal_moves.size() == 0){
			return new Action(-1000, new Position(-1, -1));
		}
		if(level == cur_level){
			//System.out.println(cur_level);
			int min = Integer.MAX_VALUE;
			Position cur_best_move = null;
			for(int k = 0; k < legal_moves.size(); k++){
				int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
				for(int i = 0 ; i < ai_board.length; i++){
					for(int j = 0; j < ai_board[0].length; j++){
						new_ai_board[i][j] = ai_board[i][j];
					}
				}
				int flipped = Game.set_piece_ai(new_ai_board, legal_moves.get(k), 1);
				if(flipped < min){
					min = flipped;
					cur_best_move = legal_moves.get(k);
				}
			}
			Action action = new Action(min, cur_best_move);
			//System.out.println("min: " + action.getValue() + " " + action.getPosition().geti() + "" + action.getPosition().getj());
			temp = action;
			return action;
		}
		int min = Integer.MAX_VALUE;
		Action res = null;
		for(int k = 0; k < legal_moves.size(); k++){
			int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
			for(int i = 0 ; i < ai_board.length; i++){
				for(int j = 0; j < ai_board[0].length; j++){
					new_ai_board[i][j] = ai_board[i][j];
				}
			}
			Game.set_piece_ai(new_ai_board, legal_moves.get(k), 1);
			ArrayList<Position> new_legal_moves = Game.legal_moves(new_ai_board, 2);
			Action action = max_value(new_ai_board, new_legal_moves, cur_level+1);
			if(action.getValue() < min){
				min = action.getValue();
				res = new Action(min, legal_moves.get(k));
			}
		}
		temp = res;
		return res;
	}
}
