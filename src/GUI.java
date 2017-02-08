import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI implements ActionListener{

	int BLACK_PLAYER = 1;
	int WHITE_PLAYER = 2;
	int black_boxes;
	int white_boxes;
	JFrame frame;
	JButton[][] buttons;
	Game game;
	ArrayList<Position> current_legal_moves;
	int current_player;

	public GUI(Game game){
		black_boxes = 2;
		white_boxes = 2;
		frame = new JFrame();
		buttons = new JButton[8][8];
		this.game = game;
		current_legal_moves = new ArrayList<Position>();
		current_player = BLACK_PLAYER;
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
		boolean set = game.set_piece(p, current_player);
		if(set){
			if(current_player == BLACK_PLAYER){
				current_player = WHITE_PLAYER;
				AI_plays();
			} else {
				current_player = BLACK_PLAYER;
			}
		}
		else {
			if(current_player == WHITE_PLAYER){
				AI_plays();
			}
		}
	}
	
	public void AI_plays(){
		for(int l = 0; l < current_legal_moves.size(); l++){
			buttons[current_legal_moves.get(l).geti()][current_legal_moves.get(l).getj()].setEnabled(false);
			buttons[current_legal_moves.get(l).geti()][current_legal_moves.get(l).getj()].setBackground(Color.ORANGE);
		}
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {
				game.execute_ai();
				return null;
			}
		};
		worker.execute();
	}

	public void force_click(Position pos) {
		int i = pos.geti();
		int j = pos.getj();
		buttons[i][j].setEnabled(true);
		buttons[i][j].doClick();
	}

	public void updateGUI(int[][] board, ArrayList<Position> legal_moves, boolean change_player){
		current_legal_moves = legal_moves;
		black_boxes = 0;
		white_boxes = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				Color color = Color.LIGHT_GRAY;
				switch(board[i][j]){
					case 0: break;
					case 1: color = Color.BLACK; black_boxes++; break;
					case 2: color = Color.WHITE; white_boxes++; break;
				}
				buttons[i][j].setBackground(color);
				buttons[i][j].setEnabled(false);
			}
		}
		for(int i = 0; i < legal_moves.size(); i++){
			buttons[legal_moves.get(i).geti()][legal_moves.get(i).getj()].setBackground(Color.PINK);
			buttons[legal_moves.get(i).geti()][legal_moves.get(i).getj()].setEnabled(true);
		}

		String points = "Black: " + black_boxes + "  White: " + white_boxes;
		if(change_player){
			if(current_player == BLACK_PLAYER){
				frame.setTitle("White's turn.   " + points);
			} else {
				frame.setTitle("Black's turn.   " + points);
			}
		} else {
			if(current_player == BLACK_PLAYER){
				frame.setTitle("Black's turn.   " + points);
			} else {
				frame.setTitle("White's turn.   " + points);
			}
		}

		if(black_boxes + white_boxes >= 64 || legal_moves.size() == 0){
			if(black_boxes > white_boxes){
				JOptionPane.showMessageDialog(null, "Black won!");
			} else if(black_boxes < white_boxes){
				JOptionPane.showMessageDialog(null, "White won!");
			} else {
				JOptionPane.showMessageDialog(null, "Tie!");
			}
		}
	}

}
