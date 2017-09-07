/**
 * 
 */
package main.java.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.List;

import org.ini4j.Ini;
import org.ini4j.spi.IniBuilder;

/**
 * Utility class used for reading/writing the .ini configuration file
 *
 * @author Michael Massarelli
 */
public class FileUtils {
    public static final String TAG = "FileUtils";

    private final static String SETTINGS = System.getenv("appdata") + "/United Gasket Calculators/";
    private final static String NAME = "PriceCalculatorSettings.ini";

    static File sf = new File(SETTINGS + NAME);

    /**
     * Saves settings that the user had set when closing application
     * @param onTop Keep application on top
     * @param skin The application skin
     */
    public static void saveSettings(String onTop, String skin){
	if (Files.exists(sf.toPath(), LinkOption.NOFOLLOW_LINKS)) {
	    try {
		Ini ini = new Ini();
		ini.load(sf);
		ini.put("Settings","setAlwaysOnTop", onTop);
		ini.put("Settings","setSkin", skin);
		ini.store(sf);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    if (!Files.exists(new File(SETTINGS).toPath(), LinkOption.NOFOLLOW_LINKS)) {
		try {
		    Files.createDirectory(new File(SETTINGS).toPath());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    try {
		Files.createFile(sf.toPath());
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    try {
		Ini ini = new Ini();
		IniBuilder ib = IniBuilder.newInstance(ini);
		ib.startSection("Settings");
		ini.put("Settings","setAlwaysOnTop", onTop);
		ini.put("Settings","setSkin", skin);
		ib.endSection();
		ib.endIni();
		ini.store(sf);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

    }

    /**
     * Initializing method
     * @return default values for application settings
     */
    public static List<String> initialize() {
	if (Files.exists(sf.toPath(), LinkOption.NOFOLLOW_LINKS)) {
	    return readSettings(sf);
	}
	List<String> defaults = new ArrayList<String>();
	defaults.add("false");
	defaults.add("GraphiteAqua");
	return defaults;
    }

    /**
     * @return a list of all available settings
     * @param file 
     * The .ini file to read settings from
     */
    private static List<String> readSettings(File file) {
	List<String> sets = new ArrayList<String>();
	try {
	    Ini ini = new Ini();
	    ini.load(sf);
	    sets.add(ini.get("Settings").fetch("setAlwaysOnTop",0));
	    sets.add(ini.get("Settings").get("setSkin").replaceAll(" ", ""));
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("ERROR");
	}
	return sets;
    }
}
