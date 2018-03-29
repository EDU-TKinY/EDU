package okada;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Page;

@WebServlet(urlPatterns={"/gets"})
public class Gets extends HttpServlet {

	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();

		request.setCharacterEncoding("UTF-8");
		String user=request.getParameter("user");
		String t1=request.getParameter("text1-2");
		String t2=request.getParameter("text2-2");
		String t3=request.getParameter("text3-2");

		Page.header(out);
		out.println("<p>こんにちは、"+user+"さん！</p>");
		out.println("<p>文章は"+t1+"</p>");



		Page.footer(out);
	}
}