import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {
	public String read(String filename){
		return read(new File(filename));
	}
	
	public String read(File file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String output = "";
			String line;
			while((line = br.readLine()) != null){
				output += line + "\n";
			}
			return output;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;	
		}
	}
}
