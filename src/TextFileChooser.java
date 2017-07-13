import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextFileChooser extends JFileChooser{
	
	public TextFileChooser(){
		FileNameExtensionFilter filterCSV = new FileNameExtensionFilter("CSV Files", "csv");
		this.addChoosableFileFilter(filterCSV);
//		FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("Text Files", "txt");
//		this.addChoosableFileFilter(filterTXT);
	}
}
