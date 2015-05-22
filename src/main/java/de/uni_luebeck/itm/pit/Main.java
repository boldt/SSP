package de.uni_luebeck.itm.pit;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.configuration.Configuration;

import eu.spitfire.ssp.Initializer;
import eu.spitfire.ssp.server.handler.cache.JenaTdbSemanticCache;
import eu.spitfire.ssp.server.handler.cache.SemanticCache;

/**
 * Created by Dennis on 2015-05-22
 */
public class Main {

	public Main() throws Exception {

    	final Path dbDirectory = Paths.get("./dbDirectory");
    	final Path spatialIndexDirectory = Paths.get("./spatialIndexDirectory");
    	
    	mkdir(dbDirectory);
    	mkdir(spatialIndexDirectory);
    	
        Initializer initializer = new Initializer("ssp.properties") {

            @Override
            public SemanticCache createSemanticCache(Configuration config){
            	JenaTdbSemanticCache cache = new JenaTdbSemanticCache(this.getIoExecutor(), this.getInternalTasksExecutor(), dbDirectory, spatialIndexDirectory);
                return cache;
            }

        };

        initializer.initialize();
	}
	
	private void mkdir(Path p) {
    	File file = new File(p.toAbsolutePath().toString());
    	if (!file.exists()) {
    		file.mkdir();
    	}
	}
	
    public static void main(String[] args) throws Exception {
    	new Main();
    }

}
