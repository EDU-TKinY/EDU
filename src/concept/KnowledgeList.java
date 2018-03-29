package concept;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Knowledge{
	private int score;
	private String name;

	public Knowledge(String sub,int num){
	score = num;
	name = sub;
	}

	public String getName() {
		// TODO 自動生成されたメソッド・スタブ
		return this.name;
	}

	public int getScore(){
		return this.score;
	}

	public String getSubject(){
		return this.name;
	}

	public void saveScore(int s){
		this.score = s;
	}
}

public class KnowledgeList {
private MainFrame main;
private ArrayList<Knowledge> list = new ArrayList<Knowledge>();

	public KnowledgeList(MainFrame m){
		main = m;
	}

	public void getKnowledge(){
			/**/
			try{
			      File file = new File("././object/knowledge.txt");
			      BufferedReader br = new BufferedReader(new FileReader(file));

			      String str = null;//br.readLine();

			      int num;
			      String name;

			      while((str = br.readLine()) != null){
			    	if(str.length() != 0){
			    		name = str;
			    		str = br.readLine();
			    		num = Integer.parseInt(str);
			    		Knowledge know = new Knowledge(name,num);
			    		list.add(know);
			    		//System.out.println(name);
			    	}
			      }
			      br.close();
			    }catch(FileNotFoundException e){
			      System.out.println(e);
			    }catch(IOException e){
			      System.out.println(e);
			    }
			/**/

			 /**/

			  }

	public int getName() {
		// TODO 自動生成されたメソッド・スタブ
		return list.get(0).getScore();
	}

}
