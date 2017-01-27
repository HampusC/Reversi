import java.util.ArrayList;

public class Position {
	private int i;
	private int j;
	private ArrayList<Position> legal_from;
	private String direction;
	
	public Position(int i, int j){
		this.i = i;
		this.j = j;
		legal_from = new ArrayList<Position>();
	}
	
	public Position(int i, int j, String direction){
		this.i = i;
		this.j = j;
		this.direction = direction;
	}
	
	public int geti(){
		return i;
	}
	
	public int getj(){
		return j;
	}
	
	public void seti(int i){
		this.i = i;
	}
	
	public void setj(int j){
		this.j = j;
	}
	
	public void insert_legal_from(Position position){
		legal_from.add(position);
	}
	
	public ArrayList<Position> get_legal_from(){
		return legal_from;
	}
	
	public String get_direction(){
		return direction;
	}
	
	@Override
	public boolean equals(Object p) {
	    if (((Position)p).geti() == this.geti() && ((Position)p).getj() == this.getj()) { 
	    	return true;
	    }
	    else return false;
	}
}
