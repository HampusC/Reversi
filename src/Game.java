import java.util.ArrayList;

public class Game {
	public static void main(String args[]){
		
		int[][] board = new int[8][8];
		board[3][3] = 1;
		board[3][4] = 2;
		board[4][3] = 1;
		board[4][4] = 2;
		int round = 1;
		
		while(round < 32){
			if(round % 2 != 0){
				turn(board, 2);
			} else {
				turn(board, 1);
			}
			round++;
		}
		
	}
	
	static void turn(int[][] board, int player){
		legal_moves(board, player);
	}
	
	static int[][] legal_moves(int[][] board, int player){
		int opponent;
		if(player==1){
			opponent=2;
		}else{
			opponent=1;
		}
		int[][] legal_board = new int[8][8];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] == player){
					
					int[][] legal = new int[8][8];
					
					Position p = check_west(board, opponent, i, j);
					System.out.println(p.geti());
					System.out.println(p.getj());
					System.out.println("=====================");
					
					return legal;
//					int nw = board[i-1][j-1];
//					int nw_counter = 2;
//					while(nw == opponent){
//						nw = board[i-nw_counter][j-nw_counter];
//						nw_counter++;
//						if(i-nw_counter < 0 || j-nw_counter < 0){
//							break;
//						}
//					}
//					
//					int n = board[i-1][j];
//					int n_counter = 2;
//					while(n == opponent){
//						n = board[i-n_counter][j];
//						n_counter++;
//						if(i-n_counter < 0){
//							break;
//						}
//					}
//					
//					int ne = board[i-1][j+1];
//					int ne_counter = 2;
//					while(ne == opponent){
//						ne = board[i-ne_counter][j+ne_counter];
//						ne_counter++;
//						if(i-ne_counter < 0 || j+ne_counter > 7){
//							break;
//						}
//					}
//					int e = board[i][j+1];
//					int e_counter = 2;
//					while(e == opponent){
//						e = board[i][j+e_counter];
//						e_counter++;
//						if(j+e_counter > 7){
//							break;
//						}
//					}
//					int se = board[i+1][j+1];
//					int se_counter = 2;
//					while(se == opponent){
//						se = board[i+se_counter][j+se_counter];
//						se_counter++;
//						if(i+se_counter > 7 || j+se_counter > 7){
//							break;
//						}
//					}
//					int s = board[i+1][j];
//					int s_counter = 2;
//					while(s == opponent){
//						s = board[i+s_counter][j];
//						s_counter++;
//						if(i+s_counter > 7){
//							break;
//						}
//					}
//					int sw = board[i+1][j-1];
//					int sw_counter = 2;
//					while(sw == opponent){
//						sw = board[i+sw_counter][j-sw_counter];
//						sw_counter++;
//						if(i+sw_counter > 7 || j-sw_counter < 0){
//							break;
//						}
//					}
					
				}
			}
		}
		
		return legal_board;
	}
	
	static Position check_west(int[][] board, int opponent, int i, int j){
		Position position = new Position(-1,-1);
		int w_counter = 1;
		if(j-w_counter < 0){
			return position;
		}
		int w = board[i][j-w_counter];
		if(w == 0){
			return position;
		}
		while(w == opponent){
			w_counter++;
			if(j-w_counter < 0){
				return position;
			}
			w = board[i][j-w_counter];
		}
		if(w == 0){
			position.seti(i);
			position.setj(j-w_counter);
		} else {
			return position;
		}
		return position;
	}
	
}
