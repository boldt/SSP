package de.uni_luebeck.itm.pit;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.configuration.Configuration;

import eu.spitfire.ssp.Initializer;
import eu.spitfire.ssp.server.handler.SemanticCache;
import eu.spitfire.ssp.server.handler.cache.JenaTdbSemanticCache;

/**
 * Created by Dennis on 2015-05-22
 */
public class Main {

    private static Set<String> getFiles(String path) {
        Set<String> files = new HashSet<>();
        File directory = new File(path);
        if(!directory.exists()) {
            String message = "Configured directory from ssp.properties (" + path +") does not exists!";
            throw new IllegalArgumentException(message);
        } else if(!directory.isDirectory()) {
            String message = "Configured directory from ssp.properties (" + path +") is no directory!";
            throw new IllegalArgumentException(message);
        } else {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    files.addAll(getFiles(file.getAbsolutePath()));
                } else {
                    if (file.getAbsolutePath().endsWith("ttl") || file.getAbsolutePath().endsWith("rdf")) {
                        files.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return files;
    }	
	
	public Main() throws Exception {

    	final Path dbDirectory = Paths.get("./dbDirectory");
    	final Path spatialIndexDirectory = Paths.get("./spatialIndexDirectory");
    	
    	mkdir(dbDirectory);
    	mkdir(spatialIndexDirectory);
    	
        Initializer initializer = new Initializer("ssp.properties") {

            @Override
            public SemanticCache createSemanticCache(Configuration config){
                String[] ontologyDirectories = config.getStringArray("cache.ontology.directory");
                Set<String> ontologyFiles = new HashSet<>();

                for(String directory : ontologyDirectories) {
                    ontologyFiles.addAll(getFiles(directory));
                }

                String tdbDirectory = config.getString("cache.tdb.directory");
                

                return new JenaTdbSemanticCache(
                    this.getIoExecutor(), this.getInternalTasksExecutor(), tdbDirectory, ontologyFiles
                );            	
            	
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
