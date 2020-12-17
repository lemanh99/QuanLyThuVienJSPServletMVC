package Model.BO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.Reader;
import Model.DAO.ReaderDAO;

public class ReaderBO {
	ReaderDAO readerDAO = new ReaderDAO();

	public int insertReader(String name, String identify, String book_id, String end_day)
			throws ParseException, ClassNotFoundException, SQLException {
		
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("dd/MM/yyyy");
		Date lFromDate1 = datetimeFormatter1.parse(end_day);
		Timestamp end = new Timestamp(lFromDate1.getTime());
		return readerDAO.insertReader(name, identify, book_id, end);
	}

	public ArrayList<Reader> getListReader(String status) throws ClassNotFoundException, SQLException {
		return readerDAO.getListReader(status);
	}
	public ArrayList<Reader> getListSearch(String data_search, String status) throws ClassNotFoundException, SQLException {
		return readerDAO.getListSearch(data_search, status);
	}
	public void changeStatus(String id) throws ClassNotFoundException, SQLException {
		int result = readerDAO.updateStatus(id);
	}
	public void deleteBookReader(String book_id) throws ClassNotFoundException, SQLException {
		int result =  readerDAO.deleteReaderBook(book_id);

	}
	public void deleteBookReaderCategory(String category_id) throws ClassNotFoundException, SQLException {
		int result =  readerDAO.deleteReaderBookCategory(category_id);

	}
	public void deleteAllReader() throws ClassNotFoundException, SQLException {
		int result =  readerDAO.deleteAllReader();

	}
}
