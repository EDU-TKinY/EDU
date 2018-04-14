<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="Class.*"%>
    <%@ page import="servlet.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
履修科目を選択してください<br>

<%
Account acc = (Account)session.getAttribute("acc");
int ID = (Integer)session.getAttribute("ID");

for(Subject subject : acc.students.get(ID).subject){%>
	<a href="login_main.jsp" onclick=session.setAttribute("Subject_ID",<%=subject.getID()%>)> <%=subject.getName()%> </a><br>
<%}%>


</body>
</html>