import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Maze{
  char[][] maze;
  public static void main(String[] args) {
    try{
      File fileIn = new File("Maze1.txt");
      Scanner input = new Scanner(fileIn);
      while(input.hasNextLine()){
        System.out.println(input.nextLine());
      }

    }catch(FileNotFoundException e){
      System.out.println("File Not Found");
    }
  }

  public void readInMaze(String filename){
    File fileIn = new File(filename);
    Scanner inpt = new Scanner(fileIn);
    int len = 0;
    int wid = 0;
    while(inpt.hasNextLine()){
      len ++;
    }
    String oneLine = inpt.nextLine();
    wid = oneLine.length();
    maze = new char[len][wid];
    inpt = new Scanner(fileIn);
    for(int r = 0; r < maze.length; r++){
      String line = inpt.nextLine();
      for(int c = 0; c < maze[r].length; c++){
        maze[r][c] = line.charAt(c);
      }
    }
  }

  public String toString(){
    String ans = "";
    for(int r = 0; r < maze.length; r++){
      String line = "";
      for(int c = 0; c < maze[r].length; c++){
        line += maze[r][c] + " ";
      }
      line += "\n";
      ans += line;
    }
    return ans;
  }
}
