package Model.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import Model.Bean.Category;
import Model.DAO.CategoryDAO;

public class CategoryBO {
	CategoryDAO categoryDAO = new CategoryDAO();

	public Category findCategory(String id) throws ClassNotFoundException, SQLException {

		return categoryDAO.findCategory(id);
	}

	public int insertCategory(Category category) throws SQLException, ClassNotFoundException {
		int result = 0;
		result = categoryDAO.insertCategory(category);
		return result;
	}

	public ArrayList<Category> listCategory() throws ClassNotFoundException, SQLException {

		return categoryDAO.getAllCategory();
	}

	public boolean deleteCategory(String id) throws ClassNotFoundException, SQLException {
		int result = categoryDAO.deleteCategory(id);
		if (result != 0)
			return true;
		return false;
	}

	public int updateCategory(Category category) throws ClassNotFoundException, SQLException {
		return categoryDAO.updateCategory(category);
	}

}
