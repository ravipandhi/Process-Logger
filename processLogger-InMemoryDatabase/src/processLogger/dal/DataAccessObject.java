package processLogger.dal;

import java.sql.*;
//import com.almworks.sqlite4java.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataAccessObject {

	static Connection con = null; // connection with the database server
	static Connection con1 = null;
	static Statement st = null; // handles sql statements

	// static ResultSet rs; //table of data

	static int count, i = 1;
	static double size;

	public DataAccessObject(double a) {

		size = a;
		ConnectJDBC();
		SetSize(size);
		CreateLog();
	}

	private static boolean ConnectJDBC() {
		try {

			Class.forName("org.sqlite.JDBC");// loads JDBC driver class
			con = DriverManager.getConnection("jdbc:sqlite::memory:"); // establishes
																		// connection
			// to the in memory database
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
			return false;
		}
		System.out.println("Connected to the database");
		return true;
	}

	private static boolean SetSize(double a) {

		double pc = a * 1000000 / 1024;
		String size1 = "PRAGMA max_page_count=" + pc; // 97666*1024=Approximately
														// 100 MB

		try { // page_size=1024
			st.execute(size1);
		} catch (SQLException e) {
			System.out.println("Could not set the page size");
			e.printStackTrace();
			return false;
		}

		return true;

	}

	private static boolean CreateLog() {
		String sql = "CREATE TABLE Process_info" + "(Process_ID INT     NOT NULL,"
				+ " Number_of_threads            TEXT     NOT NULL, " + " PCPU        TEXT, " + " PMEM        TEXT, " +

				" TIMESTAMP         TEXT)";

		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Could not create the table");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void InsertIntoLog(String a[]) {
		String LoggingTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String sql1 = "INSERT INTO Process_info" + "(Process_ID,Number_of_threads,PCPU,PMEM,TIMESTAMP)" + "values('"
				+ a[0] + "'" + ",'" + a[1] + "'" + ",'" + a[2] + "'" + ",'" + a[3] + "'" + ",'" + time + "')";
		try {
			st.executeUpdate(sql1);
			i++;

		} catch (Exception e) {
			BackupDb(LoggingTime, a[0]);
		}

	}

	public static void BackupDb(String time, String Process_ID) {
		try {
			String sq = "backup to " + Process_ID + "-" + time + ".db";
			st.executeUpdate(sq);// A backup of database is made on the disk

			System.out.println("Log file generated");
			con.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		ConnectJDBC();
		SetSize(size);
		CreateLog();

	}
}
