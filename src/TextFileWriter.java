import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextFileWriter {
	public static boolean save(String filename, String log){

		try {
			System.out.println("FILE NAME == " + filename);
			File file = new File(filename);
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			String[] toWrite = log.split("\\n");
			for(int i = 0; i < toWrite.length; i++){
				fw.write(toWrite[i]);
				fw.write(System.lineSeparator());
			}
			fw.flush();
			fw.close();
			
			return true;
		} catch (IOException e) {
			System.out.println("GIVEN FILENAME IS " + filename);
			e.printStackTrace();
		}
		return false;
	}
}
