import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextFileReader {
	public String read(String filename){
		return read(new File(filename));
	}
	
	public String read(File file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String output = "";
			String line;
			
			br.mark(1);
			if (br.read() != 0xFEFF)
			  br.reset();
			
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
