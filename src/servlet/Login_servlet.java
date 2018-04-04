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
import Class.Student;

/**
 * Servlet implementation class Login_servlet
 */
@WebServlet("/Login_servlet")
public class Login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */



	/*

    public Login_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	 */



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */



	@SuppressWarnings({ })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String ID = request.getParameter("ID");
		int Id = Integer.parseInt(ID);
		String PASS = request.getParameter("PASS");
		session.setAttribute("Week", -1);


		boolean flag = false;
		   for(Student stu : Account.students){
			   // アカウントとパスワードが一致したならば
			   if(Id == stu.getId()){
				   if( PASS.equals(stu.getPass()) ){
					   flag = true;
					   session.setAttribute("ID", stu.getId());
					   System.out.print("login Success!");
					   break;

				   }else{
					  flag = false;
					  //break;
				   }
			   	}
		   }


		   //フラグが折れてたらエラー
		   //立ってたらログイン
		   if(flag == true) {
			   response.sendRedirect("login_main.jsp");
		   }else {
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<title>エラー</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<p>IDとパスワードが一致しません</p>");
				//再ログインここに書く

				out.println("<form action=\"Login_servlet\" method=\"post\">");
				out.println("ID<input type=\"text\" name=\"ID\" maxlength=\"30\"><br>");
				out.println("PASS<input type=\"password\" name=\"PASS\" maxlength=\"30\"><br>");
				out.println("<input type=\"submit\" value=\"Submit\" />");
				out.println("</form>");

				out.println("<p><a href=\"Hello.jsp\">最初の画面に戻る</a></p>");
				out.println("</body>");
				out.println("</html>");
		   }


	}


}
