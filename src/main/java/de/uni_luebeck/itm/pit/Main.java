package de.uni_luebeck.itm.pit;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Dennis on 2015-05-22
 */
public class Main {

	private static void mkdir(Path p) {
    	File file = new File(p.toAbsolutePath().toString());
    	if (!file.exists()) {
    		file.mkdir();
    	}
	}
	
    public static void main(String[] args) throws Exception {
    	
    	System.out.println("START SSP");
    	
    	final Path dbDirectory = Paths.get("./dbDirectory");
    	final Path spatialIndexDirectory = Paths.get("./ontologies");
    	
    	mkdir(dbDirectory);
    	mkdir(spatialIndexDirectory);

    	eu.spitfire.ssp.Main.main(new String[0]);
    }

}
