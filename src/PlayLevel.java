//package out;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import engine.core.MarioGame;
import engine.core.MarioResult;
import engine.core.MarioAgent;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import java.awt.geom.Point2D;
import java.util.ArrayList;


public class PlayLevel {
    public static void printResults(MarioResult result) {
        System.out.println("****************************************************************");
        System.out.println("Game Status: " + result.getGameStatus().toString());
        System.out.println("Percentage Completion: " + result.getCompletionPercentage());
        System.out.println("Lives: " + result.getCurrentLives());
	System.out.println("Coins: " + result.getCurrentCoins()); 
        System.out.println("Remaining Time: " + (int) Math.ceil(result.getRemainingTime() / 1000f));
        System.out.println("Mario State: " + result.getMarioMode());
        System.out.println("Mushrooms collected: " + result.getNumCollectedMushrooms());
	System.out.println("Fire Flowers collected: " + result.getNumCollectedFireflower());
        System.out.println("Total Kills: " + result.getKillsTotal());
	System.out.println("Kills by Stomps: " + result.getKillsByStomp());
	System.out.println("Kills by Fireballs: " + result.getKillsByFire());
	System.out.println("Kills by Shells: " + result.getKillsByShell());
	System.out.println("Kills by Falls: " + result.getKillsByFall());
	System.out.println("Bricks: " + result.getNumDestroyedBricks());
	System.out.println("Jumps: " + result.getNumJumps());
	System.out.println("Max X Jump: " + result.getMaxXJump());
	System.out.println("Max Air Time: " + result.getMaxJumpAirTime());
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
	String  agent = "astar";
	boolean render = false;
	
	// FIXXXME: argument parsing is very hacky

	 if (args.length > 0){
	    level = args[0];
	 }

	if (args.length > 1){
	    agent = args[1];
	 }
	
	if (args.length > 2){
	    if (args[2].equals("render")){
		render = true;
	    }
	    else {
		render = false;
	    }
	 }

	//String[] level_filename = level.split("/",0);
	//String out_filename = level_filename[level_filename.length-1].split(".txt",0)[0]+"_coords.txt";
	
	MarioGame game = new MarioGame(); 
	MarioResult result = game.runGame(getAgent(agent), getLevel(level), 20, 0, render);
	printResults(result);
	printCoords(result.agentCoords);	
	//writeCoordsToFile(result.agentCoords, out_filename);
    }

    public static void printCoords(ArrayList<Point2D> agentCoords) {
      for (int i = 0; i < agentCoords.size();i++){ 		      
	  System.out.println(agentCoords.get(i).getX() + " , " +  agentCoords.get(i).getY()); 		
      }
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

   public static MarioAgent getAgent(String agentname) {
       MarioAgent agent;
       switch (agentname) {
            case "andysloane":  agent = new agents.andySloane.Agent();
                     break;
	    case "glennhartmann":  agent = new agents.glennHartmann.Agent();
                     break;
            case "robinbaumgarten": 
            case "astar":
            default: agent = new agents.robinBaumgarten.Agent();
                     break;
	     
        }
       return agent;
    }
}
