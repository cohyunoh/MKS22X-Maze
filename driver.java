import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class driver{
  public static void main(String[] args) {
    String filename = "data" + args[0] + ".dat";
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.

        f.setAnimate(true);
        System.out.println(f.solve());
        System.out.println(f);
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }catch(IllegalStateException e){
        System.out.println("The Maze is Not A GOOD SETUP");
      }
  }
}
