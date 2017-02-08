import java.util.ArrayList;

public class AI extends Thread{

	int BLACK_PLAYER = 1;
	int WHITE_PLAYER = 2;
	int level;
	double time;
	int passed_turns = 0;
	double cur_time;
	Position best_move = null;
	int[][] ai_board;

	public AI(double time){
		this.time = time;
	}

	public void send_AI_board(int[][] ai_board) {
		this.ai_board = ai_board;
	}
	
	public Position get_best_move(){
		return best_move;
	}

	private Position alpha_beta_decision(int[][] ai_board, int starting_level) throws InterruptedException {
		Position result = null;
		int max = Integer.MIN_VALUE;
		ArrayList<Position> legal_moves = Game.legal_moves(ai_board, WHITE_PLAYER);
		if(legal_moves.size() == 1){
			best_move = legal_moves.get(0);
			throw new InterruptedException();
		}
		for (int k = 0; k < legal_moves.size(); k++) {
			int[][] new_ai_board = Game.copy_board(ai_board);
			set_piece_ai(new_ai_board, legal_moves.get(k), WHITE_PLAYER);
			int cur_value = min_value(new_ai_board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
			if (cur_value > max) {
				max = cur_value;
				result = legal_moves.get(k);
			}
		}
		return result;
	}

	private int max_value(int[][] ai_board, int alpha, int beta, int cur_level) throws InterruptedException {
		if (isTerminal(ai_board, cur_level)) {
			return evaluate_board(ai_board, WHITE_PLAYER);
		}
		int max = Integer.MIN_VALUE;
		ArrayList<Position> legal_moves = Game.legal_moves(ai_board, WHITE_PLAYER);
		for (int k = 0; k < legal_moves.size(); k++) {
			passed_turns = 0;
			int[][] new_ai_board = Game.copy_board(ai_board);
			set_piece_ai(new_ai_board, legal_moves.get(k), WHITE_PLAYER);
			int cur_value = min_value(new_ai_board, alpha, beta, cur_level + 1);
			if (cur_value > max) {
				max = cur_value;
			}
			if (max >= beta) {
				return max;
			}
			alpha = Math.max(alpha, max);
		}
		if(legal_moves.size() == 0){
			if(passed_turns == 1){
				return evaluate_board(ai_board, WHITE_PLAYER);
			}
			passed_turns++;
			return min_value(ai_board,alpha,beta,cur_level + 1);
		}
		return max;
	}

	private int min_value(int[][] ai_board, int alpha, int beta, int cur_level) throws InterruptedException {
		if (isTerminal(ai_board, cur_level)) {
			return evaluate_board(ai_board, WHITE_PLAYER);
		}
		int min = Integer.MAX_VALUE;
		ArrayList<Position> legal_moves = Game.legal_moves(ai_board, BLACK_PLAYER);
		for (int k = 0; k < legal_moves.size(); k++) {
			passed_turns = 0;
			int[][] new_ai_board = Game.copy_board(ai_board);
			set_piece_ai(new_ai_board, legal_moves.get(k), BLACK_PLAYER);
			int cur_value = max_value(new_ai_board, alpha, beta, cur_level + 1);
			if (cur_value < min) {
				min = cur_value;
			}
			if (min <= alpha) {
				return min;
			}
			beta = Math.min(beta, min);
		}
		if(legal_moves.size() == 0){
			if(passed_turns == 1){
				return evaluate_board(ai_board, WHITE_PLAYER);
			}
			passed_turns++;
			return max_value(ai_board,alpha,beta,cur_level + 1);
		}
		return min;
	}

	private int evaluate_board(int[][] ai_board, int player) {
		int sum = 0;
		for (int i = 0; i < ai_board.length; i++) {
			for (int j = 0; j < ai_board[0].length; j++) {
				if (ai_board[i][j] == player) {
					sum++;
				}
			}
		}
		return sum;
	}
	
	private boolean isTerminal(int[][] ai_board, int cur_level) throws InterruptedException{
		if(cur_level == level){
			return true;
		}
		int sum = 0;
		for (int i = 0; i < ai_board.length; i++) {
			for (int j = 0; j < ai_board[0].length; j++) {
				if (ai_board[i][j] == WHITE_PLAYER || ai_board[i][j] == BLACK_PLAYER ) {
					sum++;
				}
			}
		}
		if(sum == 64){
			return true;
		}
		if(!(System.currentTimeMillis() - cur_time < time * 1000)){
			throw new InterruptedException();
		}
		return false;
	}

	static void set_piece_ai(int[][] ai_board, Position position, int player) {
		ai_board[position.geti()][position.getj()] = player;
		ArrayList<Position> legal_from = position.get_legal_from();
		Game.flip(ai_board, position, player, legal_from);
	}

	@Override
	public void run() {
		while(true){
			try{
				level = 1;
				cur_time = System.currentTimeMillis();
				while (true) {
					best_move = alpha_beta_decision(ai_board, level);
					level++;
				}
			} catch(InterruptedException e){
				return;
			}
			
		}
	}
	
	

}
