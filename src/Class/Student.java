package Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student {

	private int ID;
	private String Pass;
	public StudySitu[] Study = new StudySitu[15];
	public ArrayList<Subject> subject = new ArrayList<Subject>();
	//先行科目の成績をここに？
	//科目クラス ID, 科目名, 先行科目, 知識(岡田先輩)
	//汎用性のメモ
	//



	//講義名に関する情報を持たせる？

	public Student() {
		//Study = new StudySitu[15];
	}

	public Student(int id, String pass) {
		ID = id;
		Pass = pass;
		//System.out.println("student done");
		for(int i = 0; i < 15; i++) {
		Study[i] = new StudySitu();
		}
		//System.out.println("situation done");
	}

	public int getId() {
		return ID;
	}

	public String getPass() {
		return Pass;
	}

	public void setSitu(int week, double goal, double relation, double understand, double studytime, double ev) {
		//System.out.println("week = "+ (week));
		Study[week].getGoal();
		Study[week].setRelation(relation);
		Study[week].setUnderstand(understand);
		Study[week].setStudyTime(studytime);
		Study[week].setEv(ev);
	}

	public void setInfo(double rela) {
		//System.out.println("目標点と関連科目の成績を教えてください（変更不可）");

		for(int week = 0; week < 15; week++) {
			Study[week].setGoal(60);
			Study[week].setRelation(rela);
			Study[week].setEv(0);
		}
		//System.out.println("情報を保存しました");
	}

	public void setRog(int Week, double Under, double Time) {
		Study[Week].setUnderstand(Under);
		Study[Week].setStudyTime(Time);
		//Study[Week].setEv();
		CalSitu2(Week);
		save_data(ID);
	}


	@SuppressWarnings("resource")
	public void showdata() {

		System.out.println("週を入力してください");
		int week = new Scanner(System.in).nextInt();

		System.out.println("目標: " +Study[week].getGoal());
		System.out.println("関連科目: " +Study[week].getRelation());
		System.out.println("理解度: " +Study[week].getUnderstand());
		System.out.println("学習時間: " +Study[week].getStudyTime());

		Support(week);
	}

	public int Support(int Week) {
		double Under_sum = 0;
		double Time_sum = 0;
		double Study_Ef = 0.0;
		int week = 1;

		for(week = 1; week < Week; week++) {
			Under_sum += Study[week-1].getUnderstand();
			Time_sum += Study[week-1].getStudyTime();
		}
		Study_Ef = (Under_sum/week)/(Time_sum/week);

		double sup = (60-Study[Week].getEv()) / (0.18+(Study_Ef*0.06));

		BigDecimal support = new BigDecimal(sup);
		BigDecimal bd1 = support.setScale(0,BigDecimal.ROUND_UP);
		int Sup = 0;
		Sup = bd1.intValue();

		return Sup;
		//System.out.println("推定点は"+ Study[Week].getEv() +"です");
	}

	public void login() {
		System.out.println("login ID:" + getId() );
		System.out.println("学習状況を入力してください");
		System.out.println("週 理解度 学習時間");

		@SuppressWarnings("resource")
		Scanner Situation = new Scanner(System.in);

		int week = Situation.nextInt()-1;
		int under = Situation.nextInt();
		int Time = Situation.nextInt();

		if(week <= -1) {
			System.out.println("1~15の週を入力してください");
		}
		else {
			Study[week].setUnderstand(under);
			Study[week].setStudyTime(Time);

			CalSitu2(week);
			System.out.println("Ev1=" + Study[week].getEv());
		}
		save_data(ID);
	}

	public void CalSitu1(int Week) {
//setcal();
//単入力 1週目
		Study[Week].CalEv1();
	}

	public void CalSitu2(int Week) {
		//週を考慮
		if(Week == 0) {
			CalSitu1(Week);
		}
		if(Week > 0) {
			double 	Under = (Study[Week].Understand + Study[Week-1].Understand)/2;
			if(Study[Week].Understand >= Study[Week-1].Understand) {
				System.out.println("up");
				Study[Week].CalEv2(Under*1.5);
			}

			if(Study[Week].Understand == Study[Week-1].Understand) {
				System.out.println("keep");
				Study[Week].CalEv2(Under);
			}
			if(Study[Week].Understand <= Study[Week-1].Understand) {
				System.out.println("down");
				Study[Week].CalEv2(Under*0.5);
			}
		}
		save_data(ID);
	}


	public void save_know(String w1, String w2,String  w3,String  e1,String  e2,String e3, int week) {
		try {


			//認知度の情報をテキストファイルに書き込む

			File file = new File("./students/Student"+ ID +"/");
			if(!file.exists()) {
				file.mkdir();
			}
			file = new File("./students/Student" + ID + "/" + week + ".txt");

			if(!file.exists()) {
				file.createNewFile();
			}

				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(w1);
				bw.newLine();
				bw.write(e1);
				bw.newLine();
				bw.newLine();

				bw.write(w2);
				bw.newLine();
				bw.write(e2);
				bw.newLine();
				bw.newLine();

				bw.write(w3);
				bw.newLine();
				bw.write(e3);
				bw.newLine();
				bw.newLine();

				//イメージ
				//1	arusu
				//1 60 13 50 180

				bw.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("Saved!");
	}


	public void save_data(int ID) {
		try {

			//学習状況をテキストファイルに書き込む


			File file = new File("./students/Student" + ID + ".txt");

			//String filea = new File(".").getAbsoluteFile().getParent();
			//File file = new File(filea + "" + ID + ".txt");
			if(!file.exists()) {
				file.createNewFile();
			}

				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);

				System.out.println(ID);
				bw.write(ID + " " + Pass);
				bw.newLine();
				for(int i = 0; i < 15; i++) {
					bw.write(i+1 + " ");
					bw.write(Study[i].getGoal() + " ");
					bw.write(Study[i].getRelation() + " ");
					bw.write(Study[i].getUnderstand() + " ");
					//System.out.print("save Under="+ i + " "+Study[i].getUnderstand()+" ");
					bw.write(Study[i].getStudyTime()+" ");
					//System.out.println("Time="+Study[i].getStudyTime());
					bw.write(Study[i].getEv()+"");
					bw.newLine();
				}

				//イメージ
				//1	arusu
				//1 60 13 50 180

				bw.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("Saved!");
	}

	}
