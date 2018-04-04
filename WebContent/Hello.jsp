<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="Class.*"%>
    <%@ page import="servlet.*" %>
    <%@ page import="java.util.*"%>
<!--<%@ page import="javax.jdo.*"%> -->
    <%@ page import="javax.servlet.*"%>
    <%@ page import="javax.servlet.http.*"%>
	<%@ page import="java.io.File" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
Account acc = new Account();
acc.load_data();
//String filea = new File(".").getAbsoluteFile().getParent();
//System.out.println(filea);
session.setAttribute("acc",acc);
%>

<br>

	アカウント作成：
	アカウント作成の情報を入力してください
	<form action="./MakeAcc_servlet" method="post">
<table>
	<tr>
		<td>パスワード</td>
		<td><input type="text" name="PASS" maxlength="30"><br></td>
	</tr>

	<tr>
		<td>関連科目1の成績</td><td><input type="text" name="Relation_1" maxlength="30"></td>
	</tr>
	<tr>
		<td>関連科目2の成績</td><td><input type="text" name="Relation_2" maxlength="30"></td>
	</tr>
</table>
    <input type="submit" value="Submit" />
	</form>


<a href="Hello.jsp" >利用登録</a>


	ログイン：
	ログイン情報を入力してください

	<form action="./Login_servlet" method="post">
		ID<input type="text" name="ID" maxlength="30"><br>
		PASS<input type="password" name="PASS" maxlength="30"><br>
    <input type="submit" value="Submit" />
	</form>

</body>
</html>
