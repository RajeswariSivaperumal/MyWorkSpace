package hitachi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonLibrary {
	static Properties p;

	public static String getProperty(String property) throws IOException
	{
		p=new Properties();
		FileInputStream fs=new FileInputStream(new File("./src/test/resources/constant.properties"));
		p.load(fs);;
		return p.getProperty(property);
	}
}
