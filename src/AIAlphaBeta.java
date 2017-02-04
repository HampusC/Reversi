import java.util.ArrayList;

public class AIAlphaBeta {

  int level;

	public AIAlphaBeta(){}

	public Position execute_move(int[][] ai_board, double time) {

    Position best_move = null;
		level = 2;
    double cur_time = System.currentTimeMillis();
		while(System.currentTimeMillis() - cur_time < time*1000){
			best_move = alpha_beta_decision(ai_board, level);
			level++;
		}
    System.out.println(level);
		return best_move;

	}

	private Position alpha_beta_decision(int[][] ai_board, int starting_level){

    Position result = null;
    int max = Integer.MIN_VALUE;
    ArrayList<Position> legal_moves = Game.legal_moves(ai_board, 2);
    for(int k = 0; k < legal_moves.size(); k++){

      int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
      for(int i = 0 ; i < new_ai_board.length; i++){
        for(int j = 0; j < new_ai_board[0].length; j++){
          new_ai_board[i][j] = ai_board[i][j];
        }
      }
      Game.set_piece_ai(new_ai_board, legal_moves.get(k), 2);
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
      return evaluate_board(ai_board, 2);
    }

    int max = Integer.MIN_VALUE;
    ArrayList<Position> legal_moves = Game.legal_moves(ai_board, 2);
    for(int k = 0; k < legal_moves.size(); k++){

      int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
      for(int i = 0 ; i < new_ai_board.length; i++){
        for(int j = 0; j < new_ai_board[0].length; j++){
          new_ai_board[i][j] = ai_board[i][j];
        }
      }
      Game.set_piece_ai(new_ai_board, legal_moves.get(k), 2);
      int cur_value = min_value(new_ai_board, alpha, beta, cur_level+1);
      if(cur_value > max){
        max = cur_value;
      }
      alpha = Math.max(alpha, max);
      if(beta <= alpha){
        return max;
      }
    }
    return max;
  }



	private int min_value(int[][] ai_board, int alpha, int beta, int cur_level){
    if(cur_level == level){
      return evaluate_board(ai_board, 1);
    }
    int min = Integer.MAX_VALUE;
    ArrayList<Position> legal_moves = Game.legal_moves(ai_board, 1);
    for(int k = 0; k < legal_moves.size(); k++){

      int[][] new_ai_board = new int[ai_board.length][ai_board[0].length];
      for(int i = 0 ; i < new_ai_board.length; i++){
        for(int j = 0; j < new_ai_board[0].length; j++){
          new_ai_board[i][j] = ai_board[i][j];
        }
      }
      Game.set_piece_ai(new_ai_board, legal_moves.get(k), 1);
      int cur_value = max_value(new_ai_board, alpha, beta, cur_level+1);
      if(cur_value < min){
        min = cur_value;
      }
      beta = Math.min(beta, min);
      if(beta <= alpha){
        return min;
      }
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
}
