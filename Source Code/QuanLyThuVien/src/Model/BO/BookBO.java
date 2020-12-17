package Model.BO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import Model.Bean.Book;
import Model.DAO.BookDAO;

public class BookBO {
	BookDAO BookDAO = new BookDAO();

	public Book findBook(String id) throws ClassNotFoundException, SQLException {

		return BookDAO.findBook(id);
	}

	public int insertBook(Book book) throws SQLException, ClassNotFoundException {
		int result = 0;
		result = BookDAO.insertBook(book);
		String s ="ss";
		
		return result;
	}

	public ArrayList<Book> listBook() throws ClassNotFoundException, SQLException {
		return BookDAO.getAllBook();
	}
	public ArrayList<Book> searchBook(String name_search) throws ClassNotFoundException, SQLException {
		return BookDAO.getSearchBook(name_search);
	}

	public boolean deleteBook(String id) throws ClassNotFoundException, SQLException {
		int result = BookDAO.deleteBook(id);
		if (result != 0)
			return true;
		return false;
	}
	public int deleteBookCategory(String category_id) throws ClassNotFoundException, SQLException {
		return BookDAO.deleteBookCategory(category_id);
	}
	public boolean deleteAllBook() throws ClassNotFoundException, SQLException {
		int result = BookDAO.deleteAllBook();
		if (result != 0)
			return true;
		return false;
	}

	public int updateBook(Book Book) throws ClassNotFoundException, SQLException {
		return BookDAO.updateBook(Book);
	}

}
