import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Maze{
  private char[][] maze;
  private boolean animate;//false by default
  private int[] coordOfE;
  private int[] currentCoord;

   /*Constructor loads a maze text file, and sets animate to false by default.
     1. The file contains a rectangular ascii maze, made with the following 4 characters:
     '#' - Walls - locations that cannot be moved onto
     ' ' - Empty Space - locations that can be moved onto
     'E' - the location of the goal (exactly 1 per file)
     'S' - the location of the start(exactly 1 per file)
     2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!
     3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
        throw a FileNotFoundException or IllegalStateException
   */

   public Maze(String filename) throws FileNotFoundException{
       //COMPLETE CONSTRUCTOR
       readInMaze(filename);
       animate = false;
       coordOfE = findE();
   }

   public boolean setupCorrect(){
     String mazeString = toString();
     return mazeString.contains("S") && mazeString.contains("E") && (mazeString.indexOf("S") == mazeString.lastIndexOf("S")) && (mazeString.indexOf("E") == mazeString.lastIndexOf("E"));
   }

   private int[] findS(){
     int[] ans = new int[2];
     for(int r = 0; r < maze.length; r++){
       for(int c = 0; c < maze[0].length; c++){
         if(maze[r][c] == 'S'){
           ans[0] = r;
           ans[1] = c;
         }
       }
     }
     return ans;
   }

   private int[] findE(){
     int[] ans = new int[2];
     for(int r = 0; r < maze.length; r++){
       for(int c = 0; c < maze[0].length; c++){
         if(maze[r][c] == 'E'){
           ans[0] = r;
           ans[1] = c;
         }
       }
     }
     return ans;
   }

   private void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }


   public void setAnimate(boolean b){
       animate = b;
   }





   public void clearTerminal(){
       //erase terminal, go to top left of screen.
       System.out.println("\033[2J\033[1;1H");
   }



   /*Wrapper Solve Function returns the helper function
     Note the helper function has the same name, but different parameters.
     Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
   */

   public int solve(){
     //find the location of the S.
     int rOfS = findS()[0];
     int cOfS = findS()[1];
     //erase the S
     maze[rOfS][cOfS] = '@';
     //and start solving at the location of the s.
     return solve(rOfS,cOfS);
   }

   /*
     Recursive Solve function:
     A solved maze has a path marked with '@' from S to E.
     Returns the number of @ symbols from S to E when the maze is solved,
     Returns -1 when the maze has no solution.

     Postcondition:
       The S is replaced with '@' but the 'E' is not.
       All visited spots that were not part of the solution are changed to '.'
       All visited spots that are part of the solution are changed to '@'
   */
   private int solve(int row, int col){ //you can add more parameters since this is private
       //automatic animation! You are welcome.
       if(animate){
           clearTerminal();
           System.out.println(this);
           wait(20);
       }
       //COMPLETE SOLVE

       for(int i = 0; i < 4; i++){
         if(move(i, row, col)){
           if(solve())
         }
       }
       return -1; //so it compiles
  }

   private boolean move(int i, int r, int c){
     String unmoveables = "@.#";
     if(i == 0){
       if(unmoveables.contains(maze[r - 1][c] + "")){
         return false;
       }else{
         maze[r - 1][c] = '@';
         return true;
       }
     }else if(i == 1){
       if(unmoveables.contains(maze[r + 1][c] + "")){
         return false;
       }else{
         maze[r + 1][c] = '@';
         return true;
       }
     }else if(i == 2){
       if(unmoveables.contains(maze[r][c - 1] + "")){
         return false;
       }else{
         maze[r][c - 1] = '@';
         return true;
       }
     }else{
       if(unmoveables.contains(maze[r][c + 1] + "")){
         return false;
       }else{
         maze[r][c + 1] = '@';
         return true;
       }
     }
   }

  public void readInMaze(String filename) throws FileNotFoundException{
    File fileIn = new File(filename);
    Scanner inpt = new Scanner(fileIn);
    int len = 0;
    int wid = 0;
    String oneLine = "";
    while(inpt.hasNextLine()){
      len ++;
      oneLine = inpt.nextLine();
    }
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
        line += maze[r][c];
      }
      line += "\n";
      ans += line;
    }
    return ans;
  }
}
