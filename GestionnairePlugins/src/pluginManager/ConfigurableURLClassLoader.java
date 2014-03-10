package pluginManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ConfigurableURLClassLoader extends URLClassLoader {

	public ConfigurableURLClassLoader(URL[] urls) {
		super(urls);
	}
	
	public void addURL(String path) {
		
		File file = new File(path); 
		URL url;
		try {
			url = file.toURI().toURL();
			addURL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
