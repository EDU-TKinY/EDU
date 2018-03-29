package concept;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.IIOException;

class Subject{
	private String name;
	int year;
	int score;
	private ArrayList<String> beforeList = new ArrayList<String>();
	private ArrayList<Knowledge> knowledgeList = new ArrayList<Knowledge>();

	public Subject(String n, int y, int s,ArrayList<String> bl, ArrayList<Knowledge> k){
		this.name = n;
		this.year = y;
		this.score = s;
		this.beforeList = bl;
		this.knowledgeList = k;
	}

	public void add(String n, int y, int s,ArrayList<String> bl, ArrayList<Knowledge> k){
		this.name = n;
		this.year = y;
		this.score = s;
		this.beforeList = bl;
		this.knowledgeList = k;
	}

	public String getName(){
		return this.name;
	}

	public int getYear(){
		return this.year;
	}

	public ArrayList<String> getNextList(){
		return this.beforeList;
	}

	public ArrayList<Knowledge> getKnowledge(){
		return this.knowledgeList;
	}
	public Knowledge getKnowledge(int i){
		return this.knowledgeList.get(i);
	}

	public Boolean searcSubjecth(String k){
		for(int i=0;i<this.knowledgeList.size();i++){
			if(this.knowledgeList.get(i).getName().equals(k)){
				return true;
			}
		}
		return false;
	}

	public Boolean searcKnowledge(String k){
		for(int i=0;i<knowledgeList.size();i++){
			if(knowledgeList.get(i).getName().equals(k)){
				return true;
			}
		}
		return false;
	}

	public void saveScore(int _score){
		this.score = _score;
	}

	public int getScore() {
		return this.score;
	}
}

//class Knowledge{
//	private int score;
//	private String name;
//
//	public Knowledge(String sub,int num){
//	score = num;
//	name = sub;
//	}
//
//	public String getName() {
//		// TODO 自動生成されたメソッド・スタブ
//		return this.name;
//	}
//
//	public int getScore(){
//		return this.score;
//	}
//
//	public String getSubject(){
//		return this.name;
//	}
//
//	public void saveScore(int s){
//		this.score = s;
//	}
//}


public class SubjectList {
private ArrayList<Subject> list = new ArrayList<Subject>();
private ArrayList<String> nameList = new ArrayList<String>();
private MainFrame main;
private int subjectNumber;
public SubjectList(MainFrame m) {
		// TODO 自動生成されたコンストラクター・スタブ
		main = m;
	}

	/*GetObject:name.txtより科目名の取得*/
	public void getSubject(){
		/**/
	//	ArrayList<String> nameList = new ArrayList<String>();
		 String str = null;
		try{
		      File file = new File("././object/nameList.txt");
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      br.readLine();
//		      subjectNumber = Integer.parseInt(br.readLine());
//		      main.setSubjectNumber(subjectNumber);
		      while((str = br.readLine()) != null){
		    	  nameList.add(str);
		      }
		        br.close();
		        main.setSubjectNumber(nameList.size());
		    }catch(FileNotFoundException e){
		      System.out.println(e);
		    }catch(IOException e){
		      System.out.println(e);
		    }
		for(int t=0;t<nameList.size();t++){
			try{
				String st = nameList.get(t);
				File file = new File("././object/"+st+".txt");
				BufferedReader br = new BufferedReader(new FileReader(file));
				str = br.readLine();
				if(str.length() != 0){
					String name = null;
					String kname = null;
					int i=0,ki=0,s=0;

					ArrayList<Knowledge> olist = new ArrayList<Knowledge>();
					ArrayList<String> slist = new ArrayList<String>();
					if(str == "\n"){
						str = br.readLine();
					}
					System.out.println(str);
					name = str;
					System.out.println(str);
					str = br.readLine();
					System.out.println(str);
					i=Integer.parseInt(str);
					str = br.readLine();
					s = Integer.parseInt(str);
					str=br.readLine();
					str = br.readLine();
					while(str.length() != 0){
						slist.add(str);
						str = br.readLine();
					}
		    	// System.out.println(str);
					str=br.readLine();
					while(str.length() != 0 ){
						kname =str;
						str = br.readLine();
						ki = Integer.parseInt(str);
						olist.add(new Knowledge(kname, ki));
						str = br.readLine();
						if(str == null)
							break;
					}
					Subject a =new Subject(name,i,s,slist,olist);
					list.add(a);
				}
				br.close();
			}catch(FileNotFoundException e){
				System.out.println(e);
		    }catch(IOException e){
		    	System.out.println(e);
		    }
		}
	}

	public void saveSubject(){
		String save;
		if(nameList != null){
			System.out.println("Save開始");

			for(int i=0; i<nameList.size();i++){
				save = nameList.get(i);
				try{
					File file = new File("././object/"+save+".txt");
					if(checkBeforeWritefile(file)){
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
						pw.println(list.get(i).getName());
						pw.println(list.get(i).getYear());
						pw.println(list.get(i).getScore());
						pw.println();
						for(int n=0;n<list.get(i).getNextList().size();n++){
							pw.println(list.get(i).getNextList().get(n));
						}
						pw.println();
						for(int k=0;k<list.get(i).getKnowledge().size();k++){
							pw.println(list.get(i).getKnowledge().get(k).getName());
							pw.println(list.get(i).getKnowledge().get(k).getScore());
						}
						pw.close();
					}else{
						System.out.println("false");
					}
				}catch(IIOException e){
					System.out.println(e);
				} catch (IOException e) {
				// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
	}
 private boolean checkBeforeWritefile(File file) {
	 if(file.exists()){
		 if(file.isFile() && file.canWrite()){
			 return true;
		 }
	 }
	 return false;
	}



	/*Getname:科目データのnumber目を返す*/
	public String getName(int number){
		return list.get(number).getName();
	}
	public Knowledge getKnowledge(int number){
		return list.get(number).getKnowledge(0);
	}
	public int getScore(int number){
	return list.get(number).getScore();
	}

	/*select:特定の知識を持つ科目のみを選択*/
	public ArrayList<Subject> selectSubject(String k){
		System.out.println(k+"を探索");
		ArrayList<Subject> select = new ArrayList<Subject>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).searcSubjecth(k)){
				select.add(list.get(i));
			}
		}
		if(select.size() == 0){
			System.out.println(k+"は見つかりませんでした");
			return list;
		}
		System.out.println("発見");
		return select;
		}

	/*科目内の知識リスト*/
	public ArrayList<Knowledge> selectKnowledge(String s){
		//System.out.println(s);
		ArrayList<Knowledge> select = new ArrayList<Knowledge>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName() == s){
				System.out.println("科目"+s+"発見");
				return list.get(i).getKnowledge();
			}
		}
		System.out.println("searchfailed");
		return null;
	}

	public int getYear(int num) {
		return list.get(num).getYear();
	}

	public int getNumber(String name){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(name))
				return i;
		}
		return 0;
	}

	public void saveScore(int num,int score){
		list.get(num).saveScore(score);
	}

	public void saveKnowledgeScore(int num, String knw, int score) {
		System.out.println("保存中");
	list.get(num).getKnowledge(getKnowledgeNumber(num,knw)).saveScore(score);
	}

	public int getKnowledgeNumber(int num, String knw) {
		for(int i=0;i<list.get(num).getKnowledge().size();i++){
			if(list.get(num).getKnowledge().get(i).getName().equals(knw)){
				return i;
			}
		}
		return 0;
	}

	public ArrayList<String> getNext(int num) {
		return list.get(num).getNextList();
	}

}
