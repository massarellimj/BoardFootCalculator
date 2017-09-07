/**
 * 
 */
package main.java.util;

import javax.swing.UIManager;

/**
 * Utility class used for loading project resources
 *
 * @author Michael Massarelli
 */
public class CoreUtils {


    public static ClassLoader getClassLoaderForResources() {
	ClassLoader cl = (ClassLoader) UIManager.get("ClassLoader");
	if (cl == null)
	    cl = Thread.currentThread().getContextClassLoader();
	return cl;
    }

}
