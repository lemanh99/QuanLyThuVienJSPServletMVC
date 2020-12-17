package Model.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.GetCookie;
import Model.BO.UserBO;
import Model.Bean.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBO userBO = new UserBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
			// TODO Auto-generated method stub
			String errorString = null;
			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}
			request.getSession().removeAttribute("Check");
			// Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/UserManual");
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);
		System.out.println("Day" + remember);
		String errorString = null;
		User user = new User();

		try {
			user = userBO.getAccount(username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			user = null;
			errorString = "Lỗi kết nối cơ sở dữ liệu";
			e.printStackTrace();
		}
		if (user != null) {
			request.getSession().setAttribute("User", user);
			if (remember) {

				GetCookie.storeUserCookie(response, user);
			}
			// Ngược lại xóa Cookie
			else {
				GetCookie.deleteUserCookie(response);
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/UserManual");
			dispatcher.forward(request, response);
		} else {
			if (errorString == null)
				errorString = "Sai tên tài khoản hoặc mật khẩu";
			request.setAttribute("errorString", errorString);
			doGet(request, response);
		}

	}

}
