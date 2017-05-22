import java.io.IOException;
import java.sql.SQLException;

public class Main {
	public static DB db = new DB();
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("start");
		/*
		 * Removes all rows from a table or specified partitions of a table, 
		 * without logging the individual row deletions. 
		 * TRUNCATE TABLE is similar to the DELETE statement with no WHERE clause;
		 *  however, TRUNCATE TABLE is faster and uses fewer system and transaction log resources.
		 */
		db.runSql2("TRUNCATE NOTICE;");
		
		String url = "http://www.ewha.ac.kr/mbs/ewhakr/jsp/board/list.jsp?boardId=13259&id=ewhakr_070200000000&spage=";
		for(int page=1;page<=27;page++)
			EwhaCrawler.crawler(url+page);
		
		System.out.println("done");
	}


}
