import java.util.HashSet;
import java.util.ArrayList;

public class Program
{
    public static void main(String[] args) {
	}
}

public class Gameboard
//This class is the game logic, and the containers for the characters and spaces
{
    int fieldHeight = 6;
    int fieldWidth = 5;
    HashSet<String> notSpaces = new HashSet<String>(["11","12","14","15"]) //Finish this later. Check which spaces don't exist.
    ArrayList<Character> characters = new ArrayList<Character>(player, mimick, inverseMimick);
	player = new Character();
	mimick = new Character();
	inverseMimick = new Character();
	
	private enum Inverse
	{
	    UP(DOWN), DOWN(UP), RIGHT(LEFT), LEFT(RIGHT);
	}
	/**
	 * Function for turning row-column pair into String of length 2.
	 * @param row, column
	 * @return String of two digits, the first being the row and the second being the column
	 **/
	public String makeSpaceString(int row, int column){
	    return Integer.toString(row) + Integer.toString(column);
	}
	
    public static boolean spaceIsLegal(String nextSpace){
        return (nextSpace.charAt(0)>0 && nextSpace.charAt(0)<=fieldHeight && nextSpace.charAt(1)>0 && nextSpace.charAt(1)<=fieldWidth && !notSpaces.contains(nextSpace) && spaceEmpty(nextSpace));
    }
    
    public static boolean spaceEmpty(String space){
        for(Character a: characters){
            if(a.getSpace==space){
                return false;
                break;
            }
        }
        return true;
    }
    
	/**
	 * Contains actual game logic. Calls every characters checkNextSpace to see what the current move will do. If player can move, moves him. If others can move, move them.
	 * @param command The direction to try moving player.
	 **/
	private int tryMove(String command){
        if(!spaceIsLegal(player.checkNextSpace(command))){
	        return 0;
	        //code indicates no movement
        }
        else{
        //player can move
            if (player.checkNextSpace(command)==mimick.checkNextSpace(command) ||
                player.checkNextSpace(command)==inverseMimick.checkNextSpace(Inverse.command)){
                    return 1;
                    //code indicates loss--player crushed
            }
            else{
                player.move();
                if(mimick.checkNextSpace(command)==inverseMimick.checkNextSpace(Inverse.command)){
                    return 2;
                    //code indicates non-players bumped
                }
                else{
                    //individual checkers--could use loop for both, but not much time saved after writing inverse command into inverse mimick.
                    if(Program.spaceIsLegal(mimick.checkNextSpace(command))){
                        mimick.move();
                    }
                    if(Program.spaceIsLegal(inverseMimick.checkNextSpace(Inverse.command))){
                        inverseMimick.move();
                    }
                    //I'll define winning conditions for real later
                    if((mimick.getSpace()=="22" || mimick.getSpace()=="24") && (inverseMimick.getSpace()=="22" || inverseMimick.getSpace()=="24"))
                    return 3;
                    //code indicates win
                }
            }
        return 4;
        //code indicates business as usual
	}
	
	
}

public class Character
{
    int row, column, prevRow, prevColumn;
    
    public String getSpace(){
        return Integer.toString(row) + Integer.toString(column);
    }
    
    public Character()
    {
        
    }
    
    public Array checkNextSpace(String command){
        int nextRow = row;
        int nextColumn = column;
        if(command == UP){
            nextRow++;
        }
        if(command == DOWN){
            nextRow--;
        }
        if(command == LEFT){
            nextColumn--;
        }
        if(command == RIGHT){
            nextColumn++;
        }

        return makeSpaceString(nextRow, nextColumn);
    }
    
    public void move(){
        row = nextRow;
        column = nextColumn;
    }
}