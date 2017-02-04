public class Main{
  public static void main(String args[]){

    double time;
    if(args.length == 0){
      time = 0.5;
    } else {
      time = Double.parseDouble(args[0]);
    }

    Game game = new Game(time);
    game.set_up();
  }
}