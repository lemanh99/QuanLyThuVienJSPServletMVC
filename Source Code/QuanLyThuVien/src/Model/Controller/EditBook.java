package Model.Controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.BO.BookBO;
import Model.BO.CategoryBO;
import Model.Bean.Book;
import Model.Bean.Category;

/**
 * Servlet implementation class EditCategory
 */
@MultipartConfig
@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryBO categoryBO = new CategoryBO();
	private BookBO bookBO = new BookBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditBook() {
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
		if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String id = (String) request.getParameter("id");

			Category category = null;

			String errorString = null;
			Book book = new Book();
			ArrayList<Category> list = null;
			try {
				book = bookBO.findBook(id);
				list = categoryBO.listCategory();

			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}
			request.setAttribute("book", book);
			request.setAttribute("categoryList", list);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/edit_book.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category_id = request.getParameter("category");
		String count = request.getParameter("count");
		Part file = request.getPart("fileImage");
		String fileName = request.getParameter("image_str");
		if (!getFilename(file).equals("")) {
			String savePath = getServletContext().getRealPath("/") + "Resources\\img\\products";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			fileName = extractfilename(file);
			file.write(savePath + File.separator + fileName);
		}
//		String filePath = savePath + File.separator + fileName;

		Category category = new Category();
		try {
			category = categoryBO.findCategory(category_id);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setCategory(category);
		book.setAmount(count);
		book.setImage(fileName);
		int result = 0;
		String errorString = null;
		try {
			result = bookBO.updateBook(book);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (result == 0 && errorString == null) {
			errorString = "Chỉnh sửa thất bại";
		}
		if (result == 1)
			errorString = "Chỉnh sửa thành công";
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);

		doGet(request, response);

	}

	private String extractfilename(Part file) {
		String cd = file.getHeader("content-disposition");
		String[] items = cd.split(";");
		for (String string : items) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "";
	}

}
