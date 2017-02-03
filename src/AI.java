import java.util.ArrayList;

public class AI {
	
	Position best_move;
	int level;
	
	public AI(){
		best_move = null;
		level = 1;
	}

	public Position execute_move(int[][] ai_board, ArrayList<Position> legal_moves) {
		
		//while(true){
			minimax(ai_board, legal_moves);
			return best_move;
		//	level++;
//		}
	}
	
	public void minimax(int[][] ai_board, ArrayList<Position> legal_moves){
		int max = 0;
		Position cur_best_move = null;
		for(int i = 0; i < legal_moves.size(); i++){
			int current_move = Game.set_piece_ai(ai_board, legal_moves.get(i), 2);
			if(current_move > max){
				max = current_move;
				cur_best_move = legal_moves.get(i);
			}
		}	
		best_move = cur_best_move;
	}
	
	public int max_value(int[][] ai_board, ArrayList<Position> legal_moves, int cur_level){
		if(level == cur_level){
			
		}
		int max = Integer.MIN_VALUE;
		for(int k = 0; k < legal_moves.size(); k++){
			int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
			for(int i = 0 ; i < ai_board.length; i++){
				for(int j = 0; j < ai_board[0].length; j++){
					new_ai_board[i][j] = ai_board[i][j];
				}
			}
			Game.set_piece_ai(new_ai_board, legal_moves.get(k), 2);
			ArrayList<Position> new_legal_moves = Game.legal_moves(new_ai_board, 1);
			max = Math.max(max,min_value(new_ai_board, new_legal_moves, cur_level+1));
		}
		return max;
	}
	
	public int min_value(int[][] ai_board, ArrayList<Position> legal_moves, int cur_level){
		if(level == cur_level){
			
		}
		return 0;
	}

}
