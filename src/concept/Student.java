package concept;

public class Student {
	private MainFrame main;

	public Student(MainFrame m){
		main = m;
	}

	/*ロード関数：学習者の情報をstudent.txtより読み込む
	 * 				*/
	public void Load(){
		System.out.println("Load 完了");
	}

	/*セーブ関数：学習者の情報をstudent.txt
	 * */

	public void Save(){
System.out.println("Save完了");
	}

}
