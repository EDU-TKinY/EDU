package Class;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Account
 */

@WebServlet("/Account")
public class Account{

	public Student student;
	public static ArrayList<Student> students = new ArrayList<Student>();
	//public ArrayList<Subject> subjects = new ArrayList<Subject>();
	//public ArrayList<students> stu_sub = new ArrayList<students>();
	public ArrayList<Subject> subjects = new ArrayList<Subject>();//科目とそれを履修する学生をひとまとめにする
	//subjects.add(students);で追加すればよい？

	public int Stu_ID;

	public Account() {
		Stu_ID = 0;
	}

public void make_account(int id, String pass,double average) {

	//
	//int ID = students.size();
	//System.out.println(ID);
	//

	student = new Student(id,pass);
	students.add(student);
	student.setInfo(average);
	student.save_data(id);
	System.out.println("新規学生 ID = " + id + "パスワード = " + pass + "を登録しました");
}

public void load_data() {

	String array[];
	String str;
	int id = 0;

	int week;
	double goal;
	double relation;
	double understand;
	double studytime;
	double ev;

	try {
		File dir = new File("/opt/apache-tomcat-9.0.6/webapps/EDU/students/");//ディレクトリを選択
		if(!dir.exists()) {
			dir.mkdir();
		}
		//System.out.println("dirname = "+ dir.getParent());
		File[] files = dir.listFiles();//その中身を保持

		if(files == null){//1つもファイルがない場合の処理
			System.out.println("ファイル無し");
		}else {

		//int i = files.length;
		/*
		while(i!=files.length) {
		//System.out.println(files[i].getName()); //ファイル名の総呼び出し
		i++;
		}
		 */
		//System.out.println("Loaded");

		for(File file : files) {
			//if(file.isDirectory()) {/*System.out.println("Dir open");*/}
			//if(file.isFile()) {/*System.out.println("File open");*/}

			if(!file.exists())continue;//ファイルがないなら次
			else if(file.isFile()) {//ファイルがある
				//System.out.print("Hello:");
				//System.out.println(file.getName());

				//読み込み処理
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				LineNumberReader lnr = new LineNumberReader(br);

					str = lnr.readLine();
					//1行目でアカウント作成
					array = str.split("[\\s]+");
					id = Integer.parseInt(array[0]);
					Student student = new Student(id,array[1]);
					//1行目ここまで

					//System.out.println("ID:"+id);

					while((str = lnr.readLine()) != null) {
					//2行目を順にStudySituに入れる
					array = str.split(" ");
					week = Integer.parseInt(array[0]);
					goal = Double.parseDouble(array[1]);
					relation = Double.parseDouble(array[2]);
					understand = Double.parseDouble(array[3]);
					studytime = Double.parseDouble(array[4]);
					ev = Double.parseDouble(array[5]);


					//System.out.println(week + " "+ goal+" "+relation+" "+understand+" "+studytime+" "+ev);
					//System.out.println("load" + week + " "+"under:"+understand+"time:"+studytime+" ");
					student.setSitu(week-1,goal,relation,understand,studytime, ev);
				}
				students.add(student);
				lnr.close();
				//System.out.println("next");
				//System.out.println();
			}

		}
		}
		}catch(IOException e) {
			System.out.println(e);
		}
	System.out.println("Loaded");
	Stu_ID = id+1;
	return;
	}

public void showall() {

	System.out.println("週を入力してください");
	@SuppressWarnings("resource")
	int week = new Scanner(System.in).nextInt();
	boolean Flag = false;

	double EV = student.Study[week].getEv();
	ArrayList<Double> list = new ArrayList<Double>();

	for(Student stu : students) {
		list.add(stu.Study[week].getEv());
	}

	Collections.sort(list);

	for(double ev : list) {
		if(ev == EV && Flag != true) {
			System.out.print(ev);
			System.out.println("←you");
			Flag = true;
		}else {
		System.out.println(ev);
		}
	}

}


public void login() {
	//何をしますか
	//学習状況の入力
	//背比べ
	//他アカウントとの背比べ
	//students.get(Stu_ID).Study[1].getEv();
	System.out.println("何を行いますか");
	System.out.println("1:学習状況入力, 2:学習状況参照, 3:推定点比較");

	@SuppressWarnings("resource")
	Scanner Action = new Scanner(System.in);
	int action;// = Action.nextInt();

	while(Action.hasNextInt()){
		action = Action.nextInt();

		switch(action) {
			case 1:
				student.login();
				break;
			case 2:
				student.showdata();
				break;
			case 3:
				showall();
				break;
			default:
				return;
		}
		System.out.println("何を行いますか");
		System.out.println("1:学習状況入力, 2:学習状況参照, 3:推定点比較");

	}
}


@SuppressWarnings("resource")
public void action() {

	int ID;
	String pass;
	double average;

	System.out.println("どちらを行いますか");
	System.out.println("新規アカウント作成:1 ログイン:2");
	Scanner Action = new Scanner(System.in);
	int action = Action.nextInt();

	if(action == 1) {
		System.out.println("パスワードを入力してください(文字列,数字)");
		Scanner Pass = new Scanner(System.in);
		pass = Pass.next();
		Scanner ave = new Scanner(System.in);
		average = ave.nextDouble();
		make_account(Stu_ID, pass,average);

		System.out.println("立ち上げ直してください");
	}
	if(action == 2) {
		System.out.println("IDとパスワードを入力してください");
		Scanner Login = new Scanner(System.in);
		ID = Login.nextInt();
		pass = Login.next();

		for(Student stu : students) {
			if(ID == stu.getId()) {
				if(pass.equals(stu.getPass())) {
					student = stu;
					System.out.println("ログインします");
					login();
				}else {
					System.out.println("パスワードが違います");
				}
			}
		}
	}

}

public static void main(String[] args) {

	//int login;

	Account acc = new Account();
	//String pass;

	//int ID;
	acc.load_data();//全データのロード
	System.out.println("Load Ended");

	acc.action();

	System.out.println("終了します");

}


}

