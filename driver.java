import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class driver{
  public static void main(String[] args) {
    try{
      Maze maze = new Maze("Maze1.txt");
      System.out.println(maze);
      System.out.println(maze.setupCorrect());
      maze.setAnimate(true);
      System.out.println(maze.solve());
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }

  }
}
