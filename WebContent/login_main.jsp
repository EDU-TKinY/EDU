<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@ page import="Class.*"%>
    <%@ page import="javax.management.Query"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="java.util.Collections"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
function ChangeTab(tabname){
document.getElementById('1').style.display='none';
document.getElementById('2').style.display='none';
document.getElementById('3').style.display='none';

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
//Account acc = new Account();
Account acc = (Account)session.getAttribute("acc");

//ArrayList<Student>student = acc.students;
//acc.load_data();

int ID = (Integer)session.getAttribute("ID");
int week = 0;

for(int i = 0; i < 15; i++){
%>
	<%= i+1 %>週目
	理解度：<%= acc.students.get(ID).Study[i].getUnderstand() +"   "%>
	学習時間：<%= acc.students.get(ID).Study[i].getStudyTime()+"   " %>
	推定値：<%= acc.students.get(ID).Study[i].getEv() %>
<br>
<%
//session.getAttribute("accID")なるものを使う
}
//PersistenceManager pm = null;
//try {
//pm = PMF.get().getPersistenceManager();
//Query query1 = pm.newQuery(Account.class);

//List<Account> accs = (List<Account>) query1.execute();
//} finally {
//	if (pm != null && !pm.isClosed())
//			pm.close();
//}

%>

<br><br>
他の学生との比較

<div class ="content">

<p id="tab">
	<a href="#1" class="1" onclick="ChangeTab('1');return false;">1</a>
	<a href="#2" class="2" onclick="ChangeTab('2');return false;">2</a>
	<a href="#3" class="3" onclick="ChangeTab('3');return false;">3</a>
</p>


<%int flag = 0; %>


	<div id="1" class="content">
1週目<br>
<%

week = 0;

ArrayList<Double> list1 = new ArrayList<Double>();
for(Student stu : acc.students) {
	list1.add(stu.Study[week].getEv());
}
Collections.sort(list1);

flag = 0;
for(Double Ev : list1){
	%>
	<%= Ev %>
<%if(Ev == acc.students.get(ID).Study[week].getEv() && flag == 0){%>
←you
<%
flag = 1;
} %>

	<br>
<%
}
%>
</div>


<div id="2" class="content">
2週目<br>
<%
week = 1;
flag = 0;

ArrayList<Double> list2 = new ArrayList<Double>();
for(Student stu : acc.students) {
	list2.add(stu.Study[week].getEv());
}
Collections.sort(list2);


for(Double Ev : list2){
	%>

	<%= Ev %>

<%if(Ev == acc.students.get(ID).Study[week].getEv() && flag == 0){%>
←you
<%
flag = 1;
} %>
	<br>
<%
}
%>
</div>

<div id="3" class="content">
3週目<br>
<%
week = 2;
flag = 0;

ArrayList<Double> list3 = new ArrayList<Double>();
for(Student stu : acc.students) {
	list3.add(stu.Study[week].getEv());
}
Collections.sort(list3);

for(Double Ev : list3){
	%>

	<%= Ev %>

<%if(Ev == acc.students.get(ID).Study[week].getEv() && flag == 0){%>
←you
<%
flag = 1;
} %>
<br>
<%
}
%>
</div>

</div>

<script type="text/javascript">
ChangeTab('1');
</script>

</body>
</html>