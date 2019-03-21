package chollo.model;

public class LocatorPath {
	
	public static String localPathProyect() {
		return System.getProperty("user.dir");
	}
	
	public static String soName() {
		return System.getProperty("os.name");
	}

}
