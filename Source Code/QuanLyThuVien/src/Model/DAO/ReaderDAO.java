package Model.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.BO.BookBO;
import Model.BO.CategoryBO;
import Model.Bean.Book;
import Model.Bean.Category;
import Model.Bean.Reader;

public class ReaderDAO {
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;
	CategoryBO categoryBO = new CategoryBO();


	public int insertReader(String name, String identify, String book_id, Timestamp end_day) throws SQLException, ClassNotFoundException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		try {
			st = (Statement) conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = 0;
		String insert = "INSERT INTO Reader (name, book_id, identity_card, end_day) VALUES (?,?, ?, ?)";
		preSt = (PreparedStatement) conn.prepareStatement(insert);
		preSt.setString(1, name);
		preSt.setString(2, book_id);
		preSt.setString(3, identify);
		preSt.setTimestamp(4, end_day);
		result = preSt.executeUpdate();
		System.out.println("Ketqua" + result);
		return result;
	}

	public ArrayList<Reader> getListReader(String status) throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		ArrayList<Reader> list = new ArrayList<Reader>();
		String sql = "Select * from Reader where status=?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
//		pstm.setBoolean(1, true);
		pstm.setInt(1, Integer.parseInt(status));
		
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String book_id = rs.getString("book_id");
			String identity_card = rs.getString("identity_card");
			Date start = rs.getDate("start_day");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String start_day = dateFormat.format(start);
			Date end = rs.getDate("end_day");
			String end_day = dateFormat.format(end);
			Book book = new Book();
			try {
				BookBO bookBO = new BookBO();
				book =  bookBO.findBook(book_id);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Reader reader = new Reader();
			reader.setId(id);
			reader.setBook_id(book_id);
			reader.setBook(book);
			reader.setName(name);
			reader.setIdentify(identity_card);
			reader.setStart_day(start_day);
			reader.setEnd_day(end_day);
			reader.setStatus(status);
			list.add(reader);
		}
		return list;
	}
	public int updateStatus(String id) throws SQLException, ClassNotFoundException {
		
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Update Reader set status=1  where id=? ";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, id);
		result = pstm.executeUpdate();
		return result;
	}
	public int deleteAllReader() throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Delete From Reader";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		result = pstm.executeUpdate();
		return result;
	}
	public int deleteReader(String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Delete From Reader where id= ?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, id);
		result = pstm.executeUpdate();
		return result;
	}
	public int deleteReaderBook(String book_id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Delete From Reader where book_id= ?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, book_id);
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteReaderBookCategory(String category_id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
//		String sql = "Delete From Reader where book_id= ?";
		String sql = "DELETE Reader FROM Reader LEFT JOIN Book ON Reader.book_id = Book.id WHERE book.category_id=?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, category_id);
		result = pstm.executeUpdate();
		return result;
	}
	public ArrayList<Reader> getListSearch(String data_search, String status) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		data_search="%"+data_search+"%";
		System.out.println(data_search+status+"");
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		ArrayList<Reader> list = new ArrayList<Reader>();
		String sql = "Select * from Reader where name like ? and status=?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, data_search);
		pstm.setInt(2, Integer.parseInt(status));
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			System.out.println("Daaa");
			String id = rs.getString("id");
			String name = rs.getString("name");
			String book_id = rs.getString("book_id");
			String identity_card = rs.getString("identity_card");
			Date start = rs.getDate("start_day");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String start_day = dateFormat.format(start);
			Date end = rs.getDate("end_day");
			String end_day = dateFormat.format(end);
			Book book = new Book();
			try {
				BookBO bookBO = new BookBO();
				book =  bookBO.findBook(book_id);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Reader reader = new Reader();
			reader.setId(id);
			reader.setBook_id(book_id);
			reader.setBook(book);
			reader.setName(name);
			reader.setIdentify(identity_card);
			reader.setStart_day(start_day);
			reader.setEnd_day(end_day);
			reader.setStatus(status);
			list.add(reader);
		}
		return list;
	}
}
