<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@ page import="Class.*"%>
    <%@ page import="javax.management.Query"%>

    <%@ page import="java.util.Arrays"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="java.util.Collections"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
function ChangeTab(tabname){
<%
int Element_ID = 0;
for(Element_ID = 0; Element_ID < 15; Element_ID++){%>
document.getElementById('<%=Element_ID+1%>').style.display='none';
<%}%>
//document.getElementById('2').style.display='none';
//document.getElementById('3').style.display='none';

document.getElementById(tabname).style.display='block';
}

</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
学習状況の入力
	<form action="./MakeRog_servlet" method="post">
	週
		<input type="text" name="Week" maxlength="30"><br>
	理解度
		<input type="text" name="Under" maxlength="30"><br>
	学習時間
		<input type="text" name="Time" maxlength="30"><br>
    <input type="submit" value="Submit" />
	</form>

<br>

学習状況の表示
<br>
<%
Account acc = (Account)session.getAttribute("acc");
int ID = (Integer)session.getAttribute("ID");
int week = 0;

for(int i = 0; i < 15; i++){%>
	<%= i+1 %>週目
	理解度：<%= acc.students.get(ID).Study[i].getUnderstand() +"   "%>
	学習時間：<%= acc.students.get(ID).Study[i].getStudyTime()+"   " %>
	推定値：<%= acc.students.get(ID).Study[i].getEv() %>
<br>
<%}%>

<br><br>
他の学生との比較

<div class ="content">

<p id="tab">
<%int tab = 0;
for(tab = 0; tab < 15; tab++){%>
	<a href="#<%=tab+1%>" class="<%=tab+1%>" onclick="ChangeTab('<%=tab+1%>');return false;"><%=tab+1%></a>
<%}%>
</p>


<%
double[] Stu_list = new double[acc.students.size()];
int flag = 0;
int weeks = 0;
int loop = 0;

for(weeks = 0; weeks < 15; weeks++){%>
<div id="<%=weeks+1%>" class="content">
<%=weeks+1 %>週目<br>
<%
		loop=0;
		for(Student stu : acc.students){
			Stu_list[loop++] = stu.Study[weeks].getEv();
			}
		Arrays.sort(Stu_list);//ここでぬるぽ
	for(double Ev : Stu_list){
%>
<%=Ev%>
<%if(Ev == acc.students.get(ID).Study[weeks].getEv() && flag == 0){%>←you<%flag = 1;}%><br>
<%}flag = 0;%></div><%}%>

</div>
<%System.out.println(acc.students.size());%>
<a href="login_main.jsp" onclick="<%/*Account ac = new Account();*/acc.students.clear();acc.load_data();session.setAttribute("acc",acc);%>">更新</a>
<script type="text/javascript">ChangeTab('1');</script>

</body>
</html>