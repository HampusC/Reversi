import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI implements ActionListener{

	JFrame frame;
	JButton[][] buttons;
	Game game;
	ArrayList<Position> current_legal_moves;
	int current_player;

	public GUI(Game game){
		frame = new JFrame("Black's turn");
		buttons = new JButton[8][8];
		this.game = game;
		current_legal_moves = new ArrayList<Position>();
		current_player = 1;
	};

	public void makeGUI(){
		frame.setLayout(new GridLayout(8, 8));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
			 	buttons[i][j] = new JButton();
				buttons[i][j].setName(""+i+j);
				buttons[i][j].setEnabled(false);
				buttons[i][j].addActionListener(this);
				buttons[i][j].setPreferredSize(new Dimension(50, 50));
				frame.add(buttons[i][j]);
			}
		}
	 	frame.setSize(1000,400);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton source = (JButton)e.getSource();
		int i = Character.getNumericValue(source.getName().charAt(0));
		int j = Character.getNumericValue(source.getName().charAt(1));
		Position p = null;
		for(int k = 0; k < current_legal_moves.size(); k++){
			if(current_legal_moves.get(k).geti() == i && current_legal_moves.get(k).getj() == j){
				p = current_legal_moves.get(k);
			}
		}
		game.set_piece(p, current_player);
		if(current_player == 1){
			current_player = 2;
			frame.setTitle("White's turn");
		} else {
			current_player = 1;
			frame.setTitle("Black's turn");
		}
  }

	public void updateGUI(int[][] board, ArrayList<Position> legal_moves){
		current_legal_moves = legal_moves;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				Color color = Color.GREEN;
				switch(board[i][j]){
					case 0: break;
					case 1: color = Color.BLACK; break;
					case 2: color = Color.WHITE; break;
				}
				buttons[i][j].setBackground(color);
				buttons[i][j].setEnabled(false);
			}
		}
		for(int i = 0; i < legal_moves.size(); i++){
			buttons[legal_moves.get(i).geti()][legal_moves.get(i).getj()].setBackground(Color.RED);
			buttons[legal_moves.get(i).geti()][legal_moves.get(i).getj()].setEnabled(true);
		}
	}

}
