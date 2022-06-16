package ztool;

import java.sql.ResultSet;

import com.word.util.DBUtil;
import com.word.util.TransDefUtil;

public class TestDBUtil {

	
	public static void main(String[] args) throws Exception{
		DBUtil db= DBUtil.getDBUtil();
		ResultSet rs;

		String sql = "select * from book_word where unit_id = '1' ";
		rs = db.executeQuery(sql);

	
		while (rs.next()){
			System.out.print(rs.getString("word")+"\t");
			System.out.print(TransDefUtil.trans(rs.getString("word_def")));
			System.out.println("");
		}

		db.close();
	}
}
