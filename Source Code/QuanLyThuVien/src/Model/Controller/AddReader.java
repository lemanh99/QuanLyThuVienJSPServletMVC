package Model.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.BookBO;
import Model.BO.ReaderBO;
import Model.Bean.Book;
import Model.Bean.Category;

/**
 * Servlet implementation class AddReader
 */
@WebServlet("/AddReader")
public class AddReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReaderBO readerBO = new ReaderBO();
	private BookBO bookBO = new BookBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddReader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			// TODO Auto-generated method stub
			String errorString = null;
			ArrayList<Book> list = null;
			try {
				list = bookBO.listBook();
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}
			// Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("errorString", errorString);
			request.setAttribute("bookList", list);
			request.getSession().setAttribute("Check", "AddReader");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/add_reader.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String errorString = null;
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name_reader");
			String book_id = request.getParameter("id_book");
			String identify = request.getParameter("identify");
			String end_day = request.getParameter("end_day");
			try {
				double ident = Double.parseDouble(identify);
				int result = readerBO.insertReader(name, identify, book_id, end_day);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				errorString = "Số chứng minh phải là số";
			}
			if (errorString == null) {
				errorString = "Đã thêm thành công";
			}
			request.setAttribute("errorString", errorString);
			doGet(request, response);
		}
	}

}
