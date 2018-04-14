<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@ page import="Class.*"%>
    <%@ page import="javax.management.Query"%>

    <%@ page import="java.util.Arrays"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="java.util.Collections"%>
    <%@ page import="java.math.BigDecimal"%>



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
document.getElementById(tabname).style.display='block';
}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学習状況管理ページ</title>
</head>
<body>


<%
Account acc = (Account)session.getAttribute("acc");
int ID = (Integer)session.getAttribute("ID");
int week = 0;
int thisweek = (Integer)session.getAttribute("Week");
%>


学習状況の入力(その週に既に1度書き込んでる場合、何も起こりません)
	<form action="./MakeRog_servlet" method="post" accept-charset="UTF-8">
<table>
<tr>
	<td>週</td><td><input type="text" name="Week" maxlength="30"></td>
</tr>
<tr>
	<td>学習時間</td><td><input type="text" name="Time" maxlength="30"></td>
</tr>
<tr>
		<td>重要だと思った単語1</td><td><input type="text" name="word1"></td>
		<td>単語1説明</td><td><input type="text" name="exp1" maxlength="50"></td>
		</tr><tr>
		<td>重要だと思った単語2</td><td><input type="text" name="word2" maxlength="30"></td>
		<td>単語2説明</td><td><input type="text" name="exp2" maxlength="50"></td>
		</tr><tr>
		<td>重要だと思った単語3</td><td><input type="text" name="word3" maxlength="30"></td>
		<!-- <td>単語3説明</td><td><input type="text" name="Under" maxlength="50"></td> -->
		<td>単語3説明</td><td><textarea name="exp3"></textarea></td>

</tr>
</table>
    <input type="submit" value="Submit" />
	</form>

<br>学習状況の表示<br>

<%
if(thisweek != -1){
if(acc.students.get(ID).Study[thisweek].getEv() < 60 &&
		acc.students.get(ID).
		Study[thisweek].
		getUnderstand()!=0.0){
%>
追加でおおよそ<%=acc.students.get(ID).Support(thisweek)%>分の学習が必要と思われます。(本実装までお待ち下さい)
<%}}%>

<br>

<table>
<%for(int i = 0; i < 15; i++){%>
<tr>
<td><%= i+1 %>週目</td>
</tr>
<% if(acc.students.get(ID).Study[i].getStudyTime() != 0.0){ %>
<tr>
<td>
理解度：|
<% for(int roop = 0; roop < acc.students.get(ID).Study[i].getUnderstand() / 5 ; roop++){%>+<%}%>
<% for(int space = 0; space < 20-(acc.students.get(ID).Study[i].getUnderstand() / 5) ; space++){%><font color=white>+</font><%}%>
|
</td>

</tr>

<tr>
<td>学習時間：<%= acc.students.get(ID).Study[i].getStudyTime()+"   " %></td>
<td>推定値  ：<%= acc.students.get(ID).Study[i].getEv() %></td>
</tr>
<%}%>
<%}%>
</table>

<br><br>
他の学生との推定値比較

<div class ="content">

<p id="tab">
<%int tab = 0;
for(tab = 0; tab < 15; tab++){%>
	<a href="#<%=tab+1%>" class="<%=tab+1%>" onclick="ChangeTab('<%=tab+1%>');return false;"><%=tab+1%></a>
<%}%>
</p>

<%
double[] histgram = new double[11];
int point = 0;
int Pointer = 0;
int decimal = 0;
int flag = 0;
int weeks = 0;

for(weeks = 0; weeks < 15; weeks++){%>
<div id="<%=weeks+1%>" class="content">
	<%=weeks+1%>週目<br>
<%

for(Student stu : acc.students){
	BigDecimal pointer = new BigDecimal((stu.Study[weeks].getEv()/10));
	BigDecimal bd1 = pointer.setScale(0,BigDecimal.ROUND_DOWN);
	Pointer = bd1.intValue();
	if(stu.Study[weeks].getEv() == acc.students.get(ID).Study[weeks].getEv() && flag == 0){
		decimal = Pointer;
		flag = 1;
	}
	histgram[Pointer]++;
}

//読み込み

for(int point_roop = 0; point_roop < 11; point_roop++){%>
<%if(point_roop != 10){ %>
|<%=point_roop*10%> ~ <%=(point_roop+1)*10-1%>|点:
<%}else{%>
|<%=point_roop*10%>|点:
<%}%>
<%for(int hist_roop = 0; hist_roop < histgram[point_roop]; hist_roop++){%>*<%}
histgram[point_roop] = 0;
if(point_roop == decimal){%>←you<%}%><br>
<%}flag = 0;%>
</div><%}%>

</div>

<br>
<br>
<a href="login_main.jsp" onclick="<%acc.students.clear();acc.load_data();session.setAttribute("acc",acc);%>">更新</a>
<script type="text/javascript">ChangeTab('1');</script>

</body>
</html>



<!--
%
/*
double[] Stu_list = new double[acc.students.size()];
int flag = 0;
int weeks = 0;
int loop = 0;
for(weeks = 0; weeks < 15; weeks++){*/
><div id="
%=//weeks+1%>" class="content">
%=//weeks+1 %>週目<br>
%
/*
		loop=0;
		for(Student stu : acc.students){
			Stu_list[loop++] = stu.Study[weeks].getEv();
			}
		Arrays.sort(Stu_list);
	for(double Ev : Stu_list){
*/
%>
%=//Ev%>
%//if(Ev == acc.students.get(ID).Study[weeks].getEv() && flag == 0){%>←you
%//flag = 1;}%><br>
%//}flag = 0;%></div>
%//}%>
-->

</div>
<!-- %);/*))System.out.println(acc.students.size());*/%> -->
