import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class Graph {
	//GIve the location of the folder where graph database will be stored
	private static final File File =new File("C:/Users/dell/Documents/Neo4j/Test11"); 
	public static void main(String[] args) throws JsonProcessingException, IOException {
		//location of text file containing json below
		String filename= "D:/Romit/intern/IITP/twitter data/test.txt";
		String jsontext="";
		String createdat="",twittertext="",id_str="",name="",scrname,replyto_status="",replyto_user="",replyto_name="",location,pcreated,tweetedat="",description;
		String profilebackgroundimage,profileimage;
		long id=0,favouritecount=0,retweetcount=0;
		int followcount,friendscount,listedcount,favouritescount,statusescount;
	     JSONParser parser = new JSONParser();
	     try {
	 		FileReader fr= new FileReader(filename);
	 	
	 		BufferedReader br=new BufferedReader(fr);
	 		String text;
	 	
	 		while ((text=br.readLine())!=null)
	 		{
	 			
	 		  jsontext=text;
	              if(jsontext.length()!=0){
	 		
	 				
	 				   try {     
	 		        	    JSONObject jsonObject = (JSONObject) parser.parse(jsontext);
	 		        	   if (jsonObject.get("created_at")!= null) {
	 		                createdat = (String) jsonObject.get("created_at");
	 		                System.out.println(createdat);
	 		        	   }
	 		                tweetedat=createdat;
	 		        	  
	 		        	   if (jsonObject.get("text")!= null) {
	 		                 twittertext = (String) jsonObject.get("text");
	 		                System.out.println(twittertext);
	 		        	   }
	 		               if (jsonObject.get("id")!= null){
	 		                id = (long) jsonObject.get("id");
	 		               System.out.println(id);
	 		         
	 		               }
	 		               
	 		              if (jsonObject.get("id_str")!= null){
	 		               id_str = (String) jsonObject.get("id_str");
	 		               System.out.println(id_str);
	 		              }
	 		              if (jsonObject.get("in_reply_to_status_id")!= null)
	 		              {
	 		              replyto_status = (String) jsonObject.get("in_reply_to_status_id_str");
	 		              replyto_user=(String) jsonObject.get("in_reply_to_user_id_str");
	 		             replyto_name=(String) jsonObject.get("in_reply_to_screen_name");
	 		               System.out.println(replyto_status);
	 		               favouritecount= (long) jsonObject.get("favorite_count");
	 		              retweetcount= (long) jsonObject.get("retweet_count");
	 		              }
	 		               // loop array
	 		       
	 		               JsonNode json = new ObjectMapper().readTree(jsontext);
	 		               JsonNode user_fields = json.get("user");
	 		             	name = user_fields.get("name").asText();
	 		             	System.out.println(name);
	 		             	scrname=user_fields.get("screen_name").asText();
	 		             	System.out.println(scrname);
	 		             	location=user_fields.get("location").asText();
	 		             	pcreated=user_fields.get("created_at").asText();
	 		                 followcount=user_fields.get("followers_count").asInt();
	 		                System.out.println("followers count="+followcount);
	 		                friendscount=user_fields.get("friends_count").asInt();
	 		                System.out.println("friends count="+followcount);
	 		               statusescount=user_fields.get("statuses_count").asInt();
	 		               favouritescount=user_fields.get("favourites_count").asInt();
	 		               listedcount=user_fields.get("listed_count").asInt();
	 		               description=user_fields.get("description").asText();
		 		            profilebackgroundimage=user_fields.get("profile_background_image_url").asText();;
		 		            profileimage=user_fields.get("profile_image_url").asText();;
	 		               
	 		              //Graph database part begins here-  
	 		                
	 		               GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
	 		      		GraphDatabaseService db= dbFactory.newEmbeddedDatabase(File);
	 		      		Transaction tx = db.beginTx();
	 		      		try {
	 		      		
	 		      			Node tweetNode = db.createNode(twitter.TWEET);
	 		      			tweetNode.setProperty("Text", twittertext);
	 		      			tweetNode.setProperty("Created at", createdat);	
	 		      			tweetNode.setProperty("Fvaourite count",favouritecount);	
	 		      			tweetNode.setProperty("Retweet count", retweetcount);	
	 		      			
	 		      			Node userNode = db.createNode(twitter.USER);
	 		      			userNode.setProperty("Name", name);
	 		      			userNode.setProperty("Screen name", scrname);
	 		      			userNode.setProperty("ID", id_str);
	 		      			userNode.setProperty("Location", location);
	 		      			userNode.setProperty("Description", description);
	 		      			userNode.setProperty("Profile created", pcreated);
	 		      			userNode.setProperty("Follow count", followcount);
	 		      			userNode.setProperty("Friends count", friendscount);
	 		      			userNode.setProperty("Statuses count", statusescount);
	 		      			userNode.setProperty("Listed count", listedcount);
	 		      			userNode.setProperty("Favourites count", favouritescount);
	 		      			userNode.setProperty("Profile background image URL", profilebackgroundimage);
	 		      			userNode.setProperty("Profile image URL", profileimage);
	 		      			
	 		      			Relationship relationship = userNode.createRelationshipTo(tweetNode,TweetRelationships.TWEETS);
	 		      			if (replyto_status != null) {
	 		      			relationship.setProperty("In reply to tweet id",replyto_status);
	 		      			relationship.setProperty("In reply to user id",replyto_user);
	 		      			relationship.setProperty("In reply to user",replyto_name);
	 		      			}
	 		      			relationship.setProperty("Tweeted at",tweetedat);
	 		      			
	 		      			
	 		      			
	 		            JSONObject getObject = (JSONObject) jsonObject.get("entities");
	 		            JSONArray getHTArray =(JSONArray) getObject.get("hashtags");
	 		           JSONArray getUMArray =(JSONArray) getObject.get("user_mentions");
                        int lh=getHTArray.size();
                        int lu=getUMArray.size();
	 		        	 String hashtagsArray[]=new String[lh];
	 		        	 String usermentionsArray[]=new String[lu];
	 		        	 if(lh>0){
	 		           for(int j = 0; j < lh; j++)
	 		          {
	 		        	  JSONObject objects = (JSONObject) getHTArray.get(j);
	 		        	 String hashtagdata =(String) objects.get("text");
	 		        	 hashtagsArray[j]=hashtagdata;
	 		        	tweetNode.setProperty("Hashtags", hashtagdata);	
	 		        	  System.out.println("hashtagdata is " +hashtagdata);
	 				   }}
	 		           if(lu>0){
	 		          for(int k = 0; k < lu; k++)
	 		          { 
	 		        	  JSONObject objects3 = (JSONObject) getUMArray.get(k);
	 		        	 String usermentiondata =(String) objects3.get("id_str");
	 		        	 usermentionsArray[k]=usermentiondata;
	 		        	tweetNode.setProperty("User Mentioned", usermentiondata);
	 		        	  System.out.println("user mentioned  is " + usermentiondata);
	 				   }
	 		           }
	 	    			tx.success();
 		      			//db.shutdown();
 		      		}
 		      	 finally
 		      	 {
 		      	     tx.close();
 		      	   db.shutdown();
 		      	 }
 		      		   System.out.println(" Graph database Done successfully");
 		      	
	 				   }
	 				  
	 		             catch (ParseException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 						System.out.println("1");
	 				        	}   
	 				      catch (NullPointerException npe) {
	 						System.out.println("Null pointed exception....no need to worry :P ");
	 						 npe.printStackTrace();
	 				       	}   
	 				      catch (StringIndexOutOfBoundsException st)
	 				       {
	 					     st.printStackTrace();
	 					    System.out.println("3");
	 				            }
	 				
	 				  //  }
	 			   } }
	 			br.close();
	     }
	     catch (FileNotFoundException e){
             e.printStackTrace();
             } 
	 		
	 		catch (IOException e){
	 			System.out.println(e.getMessage());
	 		}
	     catch (StringIndexOutOfBoundsException st)
	       {
		     st.printStackTrace();
	            }
	     
	 		}
	     }   
