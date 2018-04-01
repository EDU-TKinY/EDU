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
}

%>

<br><br>
他の学生との比較

<div class ="content">

<p id="tab">
<%int tab = 0;
for(tab = 0; tab < 15; tab++){%>
	<a href="#<%=tab+1%>" class="<%=tab+1%>" onclick="ChangeTab('<%=tab+1%>');return false;"><%=tab+1%></a>
<%}%>

	<!--
	<a href="#2" class="2" onclick="ChangeTab('2');return false;">2</a>
	<a href="#3" class="3" onclick="ChangeTab('3');return false;">3</a>
	<a href="#4" class="4" onclick="ChangeTab('4');return false;">4</a>
	<a href="#5" class="5" onclick="ChangeTab('5');return false;">5</a>
	<a href="#6" class="6" onclick="ChangeTab('6');return false;">6</a>
	<a href="#7" class="7" onclick="ChangeTab('7');return false;">7</a>
	<a href="#8" class="8" onclick="ChangeTab('8');return false;">8</a>
	<a href="#9" class="9" onclick="ChangeTab('9');return false;">9</a>
	<a href="#10" class="10" onclick="ChangeTab('10');return false;">10</a>
	<a href="#11" class="11" onclick="ChangeTab('11');return false;">11</a>
	<a href="#12" class="12" onclick="ChangeTab('12');return false;">12</a>
	<a href="#13" class="13" onclick="ChangeTab('13');return false;">13</a>
	<a href="#14" class="14" onclick="ChangeTab('14');return false;">14</a>
	<a href="#15" class="15" onclick="ChangeTab('15');return false;">15</a>
 -->
</p>


<%
//List<List<Double>> Stu_list = new ArrayList<List<Double>>();
List<double[]> Stu_list = new ArrayList<double[]>();
double[] list= new double[15];

int flag = 0;
int weeks = 0;
int loop = 0;

for(weeks = 0; weeks < 15; weeks++){

%>
<div id="<%=weeks+1%>" class="content">
<%
//System.out.println("");
//System.out.println(weeks+1);
%>

<%=weeks+1 %>週目<br>
<%
//Stu_list.add(new ArrayList<Double>(15));
//Stu_list.add(@ArrayList list);
		loop=0;
		for(Student stu : acc.students){
			//Stu_list.get(weeks).add(stu.Study[loop++].getEv());//上書きじゃなくてaddだからエラー？
			//Stu_list[15] = stu.Study[loop++].getEv();
			list[loop]
					=
					stu.Study[loop].//こいつがだめらしい
					getEv();//やっぱだめ、原因は配列オーバーぽい
			loop++;
			}
		Stu_list.add(list);
		Arrays.sort(list);//ここでぬるぽ
		//Collections.sort(Stu_list.get(weeks));

	for(Double Ev : Stu_list.get(weeks)){%>
<%=Ev%>
<%if(Ev == acc.students.get(ID).Study[weeks].getEv() && flag == 0){%>←you<%flag = 1;}%><br>
<%}flag = 0;%>
</div>
<%}%>

</div>
<!--
<a href="login_main.jsp" onclick="<%acc.load_data();session.setAttribute("acc",acc);%>">更新</a>
 -->

<script type="text/javascript">
ChangeTab('1');
</script>

</body>
</html>