package discrete;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Sound {
	
	/* Play a sequence of sounds */
	public static void play(String[] files){
	    byte[] buffer = new byte[4096];
	    for (String filePath : files) {
	    	System.out.print(filePath);
	        File file = new File("samples/"+filePath+".wav");
	        try {
	            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
	            AudioFormat format = stream.getFormat();
	            SourceDataLine line = AudioSystem.getSourceDataLine(format);
	            line.open(format);
	            line.start();
	            while (stream.available() > 0) {
	                int len = stream.read(buffer);
	                line.write(buffer, 0, len);
	            }
	            line.drain();
	            line.close();
	        }
	        catch (Exception e){ e.printStackTrace(); }
	    }
	}
	
	/* Play a single sound */
	public static void play(String filePath){
	    byte[] buffer = new byte[4096];

        File file = new File("samples/"+filePath+".wav");
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            SourceDataLine line = AudioSystem.getSourceDataLine(format);
            PrintWriter out = new PrintWriter(new FileWriter("outputs/consoleOutput.txt"));
            
            line.open(format);
            line.start();
            while (stream.available() > 0) {
                int len = stream.read(buffer);
                out.println(Arrays.toString(buffer));
                line.write(buffer, 0, len);
            }
            
            line.drain();
            line.close();
            out.close();
        }
        catch (Exception e){ e.printStackTrace(); }
	}
	
}
