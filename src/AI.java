import java.util.ArrayList;

public class AI {

  int BLACK_PLAYER = 1;
  int WHITE_PLAYER = 2;
  int level;
  double time;

	public AI(double time){
    this.time = time;
  }

	public Position execute_move(int[][] ai_board) {
    Position best_move = null;
		level = 2;
    double cur_time = System.currentTimeMillis();
		while(System.currentTimeMillis() - cur_time < time*1000){

			best_move = alpha_beta_decision(ai_board, level);

			level++;
		}
		return best_move;
	}

	private Position alpha_beta_decision(int[][] ai_board, int starting_level){
    Position result = null;
    int max = Integer.MIN_VALUE;
    ArrayList<Position> legal_moves = Game.legal_moves(ai_board, WHITE_PLAYER);
    for(int k = 0; k < legal_moves.size(); k++){
      int[][] new_ai_board = Game.copy_board(ai_board);
      set_piece_ai(new_ai_board, legal_moves.get(k), WHITE_PLAYER);
      int cur_value = min_value(new_ai_board, Integer.MIN_VALUE, Integer.MAX_VALUE, starting_level-1);
      if(cur_value > max){
        max = cur_value;
        result = legal_moves.get(k);
      }
    }
    return result;
	}

	private int max_value(int[][] ai_board, int alpha, int beta, int cur_level){
    if(cur_level == level){
      return evaluate_board(ai_board, WHITE_PLAYER);
    }
    int max = Integer.MIN_VALUE;
    ArrayList<Position> legal_moves = Game.legal_moves(ai_board, WHITE_PLAYER);
    for(int k = 0; k < legal_moves.size(); k++){
      int[][] new_ai_board = Game.copy_board(ai_board);
      set_piece_ai(new_ai_board, legal_moves.get(k), WHITE_PLAYER);
      int cur_value = min_value(new_ai_board, alpha, beta, cur_level+1);
      if(cur_value > max){
        max = cur_value;
      }
      if(max >= beta){
        return max;
      }
      alpha = Math.max(alpha,max);
    }
    return max;
  }

	private int min_value(int[][] ai_board, int alpha, int beta, int cur_level){
    if(cur_level == level){
      return evaluate_board(ai_board, BLACK_PLAYER);
    }
    int min = Integer.MAX_VALUE;
    ArrayList<Position> legal_moves = Game.legal_moves(ai_board, BLACK_PLAYER);
    for(int k = 0; k < legal_moves.size(); k++){
      int[][] new_ai_board = Game.copy_board(ai_board);
      set_piece_ai(new_ai_board, legal_moves.get(k), BLACK_PLAYER);
      int cur_value = max_value(new_ai_board, alpha, beta, cur_level+1);
      if(cur_value < min){
        min = cur_value;
      }
      if(min <= alpha){
        return min;
      }
      beta = Math.min(beta, min);
    }
    return min;
	}

  private int evaluate_board(int[][] ai_board, int player){
    int sum = 0;
    for(int i = 0; i < ai_board.length; i++){
      for(int j = 0; j < ai_board[0].length; j++){
        if(ai_board[i][j] == player){
          sum++;
        }
      }
    }
    return sum;
  }

  static void set_piece_ai(int[][] ai_board, Position position, int player){
		ai_board[position.geti()][position.getj()] = player;
		ArrayList<Position> legal_from = position.get_legal_from();
		Game.flip(ai_board, position, player, legal_from);
	}

}
