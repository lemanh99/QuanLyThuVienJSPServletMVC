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
import Model.Bean.Reader;

/**
 * Servlet implementation class ManageBook
 */
@WebServlet("/ManageReader")
public class ManageReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReaderBO readerBO = new ReaderBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageReader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		if (request.getSession().getAttribute("User") == null) {
//			String errorString = "Bạn cần đăng nhập trước";
//			request.setAttribute("errorString", errorString);
//			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
//			dispatcher.forward(request, response);
//		} else {
		if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String status = (String) request.getParameter("status");
			if (status == null) {
				status = "0";
				request.getSession().setAttribute("Check", "ManageReader_0");
			} else {
				status = "1";
				request.getSession().setAttribute("Check", "ManageReader_1");
			}
			System.out.println(status);
			String errorString = null;
			ArrayList<Reader> list = null;
//		if(status.equals("1")==false) {
//			status="0";
//		}
			try {
				list = readerBO.getListReader(status);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}
			// Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("readerList", list);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/manage_reader.jsp");
			dispatcher.forward(request, response);
		}
	}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
