import java.io.File;

import org.neo4j.cypher.internal.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class CypherTest {
	public static final File File =new File("C:/Users/dell/Documents/Neo4j7"); 
	public static void main(String[] args) {
		        String j="java";
		      GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();

		      GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(File);
              graphDb.execute("MERGE ("+ j+ ":JAVA) RETURN java");
		     // ExecutionEngine execEngine = new ExecutionEngine(graphDb);
		     // ExecutionResult execResult = execEngine.execute("MATCH (java:JAVA) RETURN java");
		     // results = execResult.dumpToString();
		      System.out.println("done");
		      
		   }
		
	}


