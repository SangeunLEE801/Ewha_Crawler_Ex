import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.*;

public class EwhaCrawler {
	public static DB db = new DB();
	
	public static String getWebElm(String adress) throws Exception {
		// TODO Auto-generated method stub
		String html = "";
		String inputLine="";
		
		URL ewhaUrl = new URL(adress);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(ewhaUrl.openStream()));

		while ((inputLine = in.readLine()) != null)
			html += inputLine;
		in.close();


		return html;

	}

	public static void crawler(String url) throws Exception {
		// TODO Auto-generated method stub
		String html = getWebElm(url);
		Document doc = Jsoup.parse(html); 
		
		
		//store the cat to database to avoid parsing again
		/*
		 * INSERT INTO table_name (column1, column2, column3, ...)
						VALUES (value1, value2, value3, ...);
		 */
		String sql = "INSERT INTO NOTICE (category, title, date)  VALUES (?, ?, ?);";
		PreparedStatement stmt = db.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		Elements rows = doc.select("#contents tbody tr"); 

	      for (Element row : rows) {
	    	  //pass duplicated notices
	    	  if(row.select("td").first().toString().contains("img")) continue; 

	    	  stmt.setString(1, row.select("td").get(1).text()); 
	    	  stmt.setString(2, row.select("td p a").first().text());
	    	  stmt.setString(3, row.select("td").get(3).text()); 
	    	  
		      stmt.execute();
	      }

		
		
	}


}
