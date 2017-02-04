public class Action {
	private int value;
	private Position pos;

	public Action(int value, Position pos){
		this.value = value;
		this.pos = pos;
	}

	public int getValue(){
		return value;
	}

	public Position getPosition(){
		return pos;
	}
}
