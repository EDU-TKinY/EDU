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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");


		String Week = request.getParameter("Week");
		//String Under = request.getParameter("Under");
		String Time = request.getParameter("Time");

		String Word1 = request.getParameter("word1");
		String Word2 = request.getParameter("word2");
		String Word3 = request.getParameter("word3");
		String Exp1 = request.getParameter("exp1");
		String Exp2 = request.getParameter("exp2");
		String Exp3 = request.getParameter("exp3");

		Word1 = escapeHTML(Word1);
		Word2 = escapeHTML(Word2);
		Word3 = escapeHTML(Word3);
		Exp1 = escapeHTML(Exp1);
		Exp2 = escapeHTML(Exp2);
		Exp3 = escapeHTML(Exp3);


		if(Week.equals("") || /*Under.equals("") ||*/ Time.equals("")) {
			PrintWriter out = response.getWriter();
			//response.setContentType("text/html;charset=UTF-8");
			out.println("<!DOCTYPE html>");
			out.println("<html>");

			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>エラー</title>");
			out.println("</head>");

			out.println("<body>");
			out.println("<p>週か時間が未記入です</p>");
			out.println("<p><a href=\"login_main.jsp\">前のページに戻る</a></p>");
			out.println("</body>");

			out.println("</html>");
		}else {

		int week = Integer.parseInt(Week)-1;
		//double under = Double.parseDouble(Under);
		double time = Double.parseDouble(Time);

		Account acc = (Account)session.getAttribute("acc");
		session.setAttribute("Week", week);

		int ID = (Integer)session.getAttribute("ID");

		//System.out.println("under="+under);
		//System.out.println("time="+time);

		if(acc.students.get(ID).Study[week].getStudyTime() == 0) { //テキストファイルのその週の学習時間が0なら書き込む
		acc.students.get(ID).setRog(week,0,time);
		acc.students.get(ID).CalSitu2(week);
		acc.students.get(ID).save_know(Word1, Word2, Word3, Exp1, Exp2, Exp3, week);

		session.setAttribute("acc", acc);

		}

		response.sendRedirect("login_main.jsp");
		}
	}

public static String escapeHTML(String str) {
	if(str == null)return "";

	str = str.replaceAll("&", "& amp;");
	str = str.replaceAll("<", "& lt;");
	str = str.replaceAll(">", "& gt;");
	str = str.replaceAll("\"","& quot;");
	str = str.replaceAll("'", "& apos;");

	return str;
}

}
