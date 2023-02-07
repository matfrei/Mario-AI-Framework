import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import engine.core.MarioGame;
import engine.core.MarioResult;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import java.awt.geom.Point2D;
import java.util.ArrayList;



public class PlayLevel {
    public static void printResults(MarioResult result) {
        System.out.println("****************************************************************");
        System.out.println("Game Status: " + result.getGameStatus().toString() +
                " Percentage Completion: " + result.getCompletionPercentage());
        System.out.println("Lives: " + result.getCurrentLives() + " Coins: " + result.getCurrentCoins() +
                " Remaining Time: " + (int) Math.ceil(result.getRemainingTime() / 1000f));
        System.out.println("Mario State: " + result.getMarioMode() +
                " (Mushrooms: " + result.getNumCollectedMushrooms() + " Fire Flowers: " + result.getNumCollectedFireflower() + ")");
        System.out.println("Total Kills: " + result.getKillsTotal() + " (Stomps: " + result.getKillsByStomp() +
                " Fireballs: " + result.getKillsByFire() + " Shells: " + result.getKillsByShell() +
                " Falls: " + result.getKillsByFall() + ")");
        System.out.println("Bricks: " + result.getNumDestroyedBricks() + " Jumps: " + result.getNumJumps() +
                " Max X Jump: " + result.getMaxXJump() + " Max Air Time: " + result.getMaxJumpAirTime());
        System.out.println("****************************************************************");
	
    }

    public static String getLevel(String filepath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
        }
        return content;
    }

    public static void main(String[] args) {
	String level = "./levels/original/lvl-1.txt";
	
	if (args.length > 0){
	    level = args[0];
	    }

	String[] level_filename = level.split("/",0);
	String out_filename = level_filename[level_filename.length-1].split(".txt",0)[0]+"_coords.txt";
	System.out.println(out_filename);
	MarioGame game = new MarioGame();  //.runGame(new agents.robinBaumgarten.Agent(), getLevel(level), 20, 0, true); 

	MarioResult result = game.runGame(new agents.robinBaumgarten.Agent(), getLevel(level), 20, 0, true);
	printResults(result);
	writeCoordsToFile(result.agentCoords, out_filename);
    }

    public static void writeCoordsToFile(ArrayList<Point2D> agentCoords,String filename) {
      try {
      FileWriter writer = new FileWriter(filename);
      for (int i = 0; i < agentCoords.size();i++){ 		      
	  writer.write(agentCoords.get(i).getX() + " , " +  agentCoords.get(i).getY()); 		
	  writer.write("\n"); 
      }   
      writer.close();
      System.out.println("Successfully wrote agent coords to file " + filename + ".");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
    
}
