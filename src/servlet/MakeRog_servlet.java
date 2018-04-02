package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Class.Account;

/**
 * Servlet implementation class MakeRog_servlet
 */
@WebServlet("/MakeRog_servlet")
public class MakeRog_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String Week = request.getParameter("Week");
		int week = Integer.parseInt(Week)-1;
		String Under = request.getParameter("Under");
		double under = Double.parseDouble(Under);
		String Time = request.getParameter("Time");
		double time = Double.parseDouble(Time);

		Account acc = (Account)session.getAttribute("acc");
		//String ID = session.getAttribute("ID");
		int ID = (Integer)session.getAttribute("ID");
		acc.students.get(ID).setRog(week,under,time);
		acc.students.get(ID).CalSitu2(week);
		session.setAttribute("acc", acc);

		response.sendRedirect("login_main.jsp");
	}
}
