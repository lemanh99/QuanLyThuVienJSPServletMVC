package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.Bean.Book;
import Model.Bean.Category;
import Model.Bean.User;

public class UserDAO {
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;

	public User getUser(String username, String password) throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Select * from User where username=? and password=?";

		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String id = rs.getString("id");
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			user.setPassword(password);
			return user;
		}
		return null;
	}

	public User findUser(String username) throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Select * from User where username=?";

		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String id = rs.getString("id");
			String password = rs.getString("password");
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			user.setPassword(password);
			return user;
		}
		return null;
	}

}
