package Model.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.BO.BookBO;
import Model.Bean.Category;

public class CategoryDAO {
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;

	public Category findCategory(String id) throws SQLException, ClassNotFoundException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Select * from Category where id=?";

		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, id);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			int _id = rs.getInt("Id");
			String name = rs.getString("Name");
			Category category = new Category(_id, name);
			return category;
		}
		return null;
	}

	public int insertCategory(Category category) throws SQLException, ClassNotFoundException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		try {
			st = (Statement) conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = 0;
		String insert = "INSERT INTO Category (name) VALUES (?)";
		preSt = (PreparedStatement) conn.prepareStatement(insert);
		preSt.setString(1, category.getName());
		result = preSt.executeUpdate();
		return result;
	}

	public ArrayList<Category> getAllCategory() throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		ArrayList<Category> list = new ArrayList<Category>();
		String sql = "Select * from Category";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Category category = new Category();
			category.setId(id);
			category.setName(name);
			list.add(category);
		}
		return list;
	}

	public int updateCategory(Category category) throws SQLException, ClassNotFoundException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Update Category set Name =? where id=? ";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);

		pstm.setString(1, category.getName());
		pstm.setFloat(2, category.getId());
		result = pstm.executeUpdate();
		return result;
	}

	public int deleteCategory(String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		BookBO bookBO = new BookBO();
		bookBO.deleteBookCategory(id);
		String sql = "Delete From Category where id= ?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, id);
		result = pstm.executeUpdate();
		return result;
	}
}
