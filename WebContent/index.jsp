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
	以下の情報を入力してください
<table>
	<tr>
		<td>パスワード</td>
		<td><input type="password" name="PASS" maxlength="30"><br></td>
	</tr>

	<tr>
		<td>プログラミング基礎の成績</td><td><input type="text" name="Relation_1" maxlength="30"></td>
	</tr>
	<tr>
		<td>プログラミング基礎演習の成績</td><td><input type="text" name="Relation_2" maxlength="30"></td>
	</tr>
</table>
    <input type="submit" value="Submit" />

<br><br>

	ログイン：
	アカウントの情報を入力してください

	<form action="./Login_servlet" method="post">
		ID<input type="number" name="ID" min="1"><br>
		PASS<input type="password" name="PASS" maxlength="30"><br>
    <input type="submit" value="Submit" />
	</form>

<br>
<br>
開発者：瀧下<br>
連絡先：g18tk009@yamanashi.ac.jp<br>
</body>
</html>
