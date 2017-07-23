import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	private static volatile MyLogger instance;
	private Logger logger;
	private ConsoleHandler handler;
	
	private MyLogger(){
		setUp();
	}
	
	public static MyLogger getInstance(){
		if(instance == null){
			instance = new MyLogger();
		}
		return instance;
	}
	public void setUp(){
		logger = Logger.getLogger("myLogger");
		logger.setLevel(Level.ALL);
		handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
	}
	
	public Logger getLogger(){
		return this.logger;
	}
}
