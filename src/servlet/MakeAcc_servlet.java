package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Class.Account;

/**
 * Servlet implementation class MakeAcc_servlet
 */
@WebServlet("/MakeAcc_servlet")
public class MakeAcc_servlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	@Override

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		System.out.println("make_acc?");

		response.setContentType("text/html;charset=UTF-8");

		String PASS = request.getParameter("PASS");

		String R1 = request.getParameter("Relation_1");
		double r1 = Double.parseDouble(R1);
		String R2 = request.getParameter("Relation_2");
		double r2 = Double.parseDouble(R2);

		double average = (r1+r2)/2;
		double Rela = average/4;

		HttpSession session = request.getSession();
		//HttpSession session = session;
		Account acc = (Account)session.getAttribute("acc");
		acc.make_account(acc.Stu_ID, PASS,Rela);

		//request.setAttribute("acc",acc);
		session.setAttribute("acc",acc);
		session.setAttribute("ID",acc.Stu_ID);
		session.setAttribute("Week", -1);

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>アカウント登録</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>" + "アカウントID:" + acc.Stu_ID + ", パスワード: "+ PASS + "を登録しました" + "</p>");
		out.println("<p><a href=\"login_main.jsp\">ログインする</a></p>");
		out.println("</body>");
		out.println("</html>");

	}

}
