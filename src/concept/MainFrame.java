package concept;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*MainFrame:
 * 全てのクラスを管理するクラス*/
public class MainFrame extends JFrame {
	/*インスタンスフィールド
	 * Student student:
	 * 			学生の情報を保持
	 * 			現状使用していない
	 * SubjectList subject:
	 * 			科目の情報を保持
	 * KnowledgeList knowledge:
	 * 			知識のデータを保持
	 * 			現状使用していない
	 * 			知識間の関係を保持したい
	 * String knowledgeName:
	 * 			マップ間を遷移する際に選択する知識・科目名
	 * int totalSubjectNumber:
	 * 			合計科目数を保持
	 * CreateMemo memo:
	 * 			メモを
	 * Container contentPane:
	 *
	 * String[] PanelNames:
	 *
	 * RelationMap map_relation:
	 *
	 * SubjectMap map_subject:
	 *
	 * KnowledgeMap map_knowledge:
	 *
	 * Administrator map_administrator
	 *
	 * boolean knowledge_flag:
	 *
	 * 		   relation_flag:
	 *			科目を読み込む前に矢印を書くのを防止するフラグ*/
	private Student student;
	private SubjectList subject;
	private KnowledgeList knowledge;
	private String selectName;
	private int totalSubjectNumber;
	private CreateMemo memo;
	public Container contentPane;
	public String[] PanelNames = {"RelationMap","SubjectMap","KnowledgeMap","Administrator"};
	RelationMap map_relation = new RelationMap(this,PanelNames[0]);
	SubjectMap map_subject = new SubjectMap(this,PanelNames[1],null);
	KnowledgeMap map_knowledge = new KnowledgeMap(this,PanelNames[2],null);
	Administrator map_administrator = new Administrator(this,PanelNames[3],null);
	public boolean knowledge_flag = false;
	public boolean relatoinal_flag = false;

	/*mainのコンストラクタ*/
	public MainFrame(){
		JScrollPane sp = new JScrollPane(map_relation);
		//sp.getViewport().setView(map_relation);
		sp.setPreferredSize(new Dimension(100, 800));
		this.add(sp);

		/*科目間マップ設定*/
		map_relation.setVisible(true);
		relatoinal_flag = true;
		this.add(map_subject);

		/*科目マップ設定*/
		map_subject.setVisible(false);
		this.add(map_knowledge);

		/*知識-科目マップ設定*/
		map_knowledge.setVisible(false);

		/*管理者画面設定*/
		map_administrator.setVisible(false);
		this.add(map_administrator);

		/*画面の大きさ設定*/
		this.setBounds(100, 100, 1000, 1000);

		/*コンストラクタ呼び出し:
		 * */
		student = new Student(this);
		subject = new SubjectList(this);
		knowledge = new KnowledgeList(this);
		memo = new CreateMemo();;
		selectName = null;

		/*科目の読み込みを実効*/
		preparation();

		/**/
		contentPane = getContentPane();
		contentPane.add(sp);

	}

	/*main関数呼び出し*/
	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		main.setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.setVisible(true);

	}


	/*PanelChange:※バグが存在
	 * マップ間の遷移を管理
	 * JPanel jp:	遷移元のマップ
	 * String str:	遷移先のマップ名
	 * String name:	選択した科目・知識*/
	public void PanelChange(JPanel jp, String str, String name){

		/*遷移元のマップ名を取得*/
		String p_name = jp.getName();
		/*選択科目知識を保持*/
		selectName = name;

		/*遷移元のマップの表示フラグを折る*/
		if(p_name==PanelNames[0]){
			map_relation = (RelationMap)jp;
			map_relation.setVisible(false);
			relatoinal_flag = false;
		}else if(p_name==PanelNames[1]){
			map_subject = (SubjectMap)jp;
			map_subject.setVisible(false);
		}else if(p_name==PanelNames[2]){
			map_knowledge = (KnowledgeMap)jp;
			map_knowledge.setVisible(false);
			knowledge_flag = false;
		}else if(p_name==PanelNames[3]){
			map_administrator = (Administrator)jp;
			map_administrator.setVisible(false);
		}

		/*バグ回避用
		 *バグ内容：マップ間遷移が正しく行われない場合がある
		 *要因：	PanelChange時に受け渡す遷移元マップが参照できていない
		 *対策：	表示フラグのすべてを一度折ったのち遷移先のフラグのみ立てる*/
		map_relation.setVisible(false);
		map_subject.setVisible(false);
		map_knowledge.setVisible(false);
		map_administrator.setVisible(false);

		/*遷移先マップの表示フラグを立てる*/
		if(str==PanelNames[0]){
			map_relation.pre();
			map_relation.redraw();
			relatoinal_flag = true;
			map_relation.setVisible(true);
		}else if(str==PanelNames[1]){
			map_subject.pre();
			map_subject.draw(name);
			map_subject.setVisible(true);
		}else if(str==PanelNames[2]){
			map_knowledge.pre();
			map_knowledge.draw(name);
			knowledge_flag = true;
			map_knowledge.setVisible(true);
		}else if(str==PanelNames[3]){
			map_administrator.setVisible(true);
			map_administrator.draw();
		}

	}


	/*以下他関数呼び出し*/

	/*preparation:
	 * 	科目情報と学生情報を入手*/
	private void preparation() {
		/*subjectデータ取得*/
		subject.getSubject();
		/*Studentデータ取得*/
		student.Load();
	}

	/*saveStudent:
	 * */
	public void saveStudent(){
		/*Studentデータ保存*/
		subject.saveSubject();
	}

	/**/
	public int getTotalSubjectNumber(){
		return this.totalSubjectNumber;
	}

	/**/
	public SubjectList getSubject(){
		return subject;
	}

	/**/
	public void getKnowledge() {
		knowledge.getKnowledge();
	}

	/**/
	public int getSubscore() {
		return knowledge.getName();
	}

	/**/
	public String getKnowledgeNmae() {
		return selectName;
	}

	/**/
	public void setSubjectNumber(int number) {
		this.totalSubjectNumber = number;
	}

	/**/
	public ArrayList<Knowledge> selectKnowledge(String k){
		return subject.selectKnowledge(k);
	}

	/**/
	public ArrayList<Subject> selectSubject(String s){
		return subject.selectSubject(s);
	}

	/**/
	public void saveSubjectScore(int num,int score) {
		this.subject.saveScore(num,score);
	}

	/**/
	public int getSubjectNumber(String name) {
		return this.getSubject().getNumber(name);
	}

	/**/
	public int getKnowledgeNumber(String sub,String knw){
		return this.subject.getKnowledgeNumber(getSubjectNumber(sub),knw);
	}

	/**/
	public void saveKnowledgescore(String sub,String knw,int score) {
		System.out.println("保存開始");
		this.subject.saveKnowledgeScore(getSubjectNumber(sub),knw,score);
	}

	/*savevoment:コメントを保存
	 * map:コメントが書かれたマップ名(+科目or知識)
	 * cmt:保存するコメント*/
	public void savecoment(String map, String cmt) {
		memo.save(map,cmt);
	}

	/**/
	public ArrayList<String> getcoment() {
		return memo.getcoment();
	}

	/**/
	public void comentdelete(int num) {
		memo.delete(num);
	}

	/**/
	public void setscroll(JTextArea comentarea) {
		JScrollPane comentscrol = new JScrollPane();
		comentscrol.getViewport().setView(comentarea);
		this.add(comentscrol);
		contentPane.add(comentscrol);
	}


}