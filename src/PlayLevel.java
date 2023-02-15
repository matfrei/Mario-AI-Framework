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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PlayLevel {
    public static String getLevel(String filepath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
        }
        return content;
    }

    public static void main(String[] args) {

	// TODO: improve argument parsing, its _very_ basic :-)
       	if (args.length < 1){
	     System.out.println("Usage: java -jar simulator.jar <filepath to level as txt>");
	}
	else
	{
	     String level = args[0];
	     MarioGame game = new MarioGame(); 
	     MarioResult result = game.runGame(new agents.human.Agent(), getLevel(level), 120, 0, true);
	}
	
    }
}
