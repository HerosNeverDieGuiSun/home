 package hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class HiveJdbcTest {
    private static final String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    private static final String HOST = "192.168.1.200:10021";
    private static final String URL = "jdbc:hive://" + HOST + "/default";
    
    public static void main(String[] args) throws Exception {
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(URL, "", "");
        Statement stmt = conn.createStatement();
        
        String hql = "select *  from (SELECT tid,count(tid) as count FROM EBP_1  group by tid) "
        		+ "as orders order by orders.count desc limit 4";
       /* String hql = "select orders.tid from (SELECT tid,count(tid) as count FROM EBP_1 where uid=7 group by tid) "
        		+ "as orders order by orders.count desc";*/
        System.out.println("------------"+hql);
        ResultSet res = stmt.executeQuery(hql);
        while (res.next()) {
        	
            System.out.println(res.getInt(1));
        }

       /* hql = "select id, name, score from student where class='class2'";
        System.out.println(hql);
        res = stmt.executeQuery(hql);
        while (res.next()) {
            System.out.println(res.getString("id") + "   \t" + 
                    res.getString("name") + '\t' + res.getInt("score"));
        }
        
        hql = "select class, count(*) from student group by class";
        System.out.println(hql);
        res = stmt.executeQuery(hql);
        while (res.next()) {
            System.out.println(res.getString(1)  + '\t' + res.getInt(2));
        }
*/
        res.close();
        stmt.close();
        conn.close();
    }
}
/*
*这是一段新注释
*/