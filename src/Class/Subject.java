package Class;

import java.util.ArrayList;

public class Subject {


	public String Subject_ID;
	public String Subject_Name;
	public ArrayList<String> Relations = new ArrayList<String>(); //関連科目を保持する
	public ArrayList<String> Knowledges = new ArrayList<String>(); //知識集合を保持する

	public StudySitu[] Study = new StudySitu[15];

	public String getID() {
		return Subject_ID;
	}

	public String getName() {
		return Subject_Name;
	}

}
