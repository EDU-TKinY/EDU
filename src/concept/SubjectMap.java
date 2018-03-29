 package concept
;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SubjectMap extends JPanel {
	/*インスタンスフィールド
	 * MainFrame:
	 * main	メイン
	 *
	 * jButton:
	 * btn
	 * comentbtn
	 * deletebtn1	最古コメント削除
	 * deletebtn2	最新コメント削除
	 *
	 * */
    private MainFrame main;
    private JButton btn;
    private JLabel paneltitle;
	private JTextArea comentarea,addarea;
    private JTextField subjecttext;
	public String str;
	private String[] down = {"優","良","可","未修得"};
	private ArrayList<String> coment;
	private ArrayList<Knowledge> select;
	private JComboBox[] combo;
	private int kscore,knum;
	/*コメント用
	 * comentareat:コメントを表示するエリア
	 * coment:コメントの内容を保持するリスト
	 * addarea:コメントを書き込むエリア
	 * comentbtn:コメント保存ボタン
	 * deletebtn1:最古コメント削除ボタン
	 * deletebtn2:最新コメント削除*/

    public SubjectMap(MainFrame m,String s,String name){
        main = m;
        str = s;
        this.setName(s);
        this.setLayout(null);
        this.setSize(4000, 2000);
        btn = new JButton("科目間マップ"+"に移動");
        main.getTotalSubjectNumber();
    }

    /*pre():マップ切り替え時にマップで追加したボタン等を削除
     * 引　数:なし
     * 戻り値:なし
     * ボタン等の重複登録を防ぐため*/
    public void pre(){
    	this.removeAll();
    }


    /*draw:科目マップを描画する関数
     * 引　数:選択した科目名 name
     * 戻り値:なし*/
    public void draw(String name){



    	/* 準備
    	 * snum:表示する科目の番号を保持
    	 * score:表示する科目の点数を保持
    	 * bun1:知識を保持,表示する配列
    	 * */
    	int snum = main.getSubjectNumber(name);
    	int score = main.getSubject().getScore(snum);
    	 select = main.selectKnowledge(name);
    	int total = select.size();
    	Button[] bun1 = new Button[total];
    	Color[] color = new Color[total];
    	int x = 0,y = 1;
    	JButton comentbtn,deletebtn1,deletebtn2;


	if(this.paneltitle != null)
		this.remove(paneltitle);
	 paneltitle = new JLabel("これは"
             +main.getKnowledgeNmae()+"のパネルです");
     paneltitle.setBounds(200, 100, 4000, 40);
     paneltitle.setFont(new Font("MS ゴシック",Font.BOLD,24));
     if(score >= 80){
    	 paneltitle.setForeground(Color.blue);
     }
     else if(score < 80 && score >= 70){
    	 paneltitle.setForeground(Color.getHSBColor(0.34f, 1f, 0.74f));
     }
     else if(score >= 60){
    	 paneltitle.setForeground(Color.red);
     }
     this.add(paneltitle);

 	/*戻るボタン*/
     btn.setBounds(150, 50, 200, 40);
     btn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             pc(main.PanelNames[0],null);
         }
     });
     this.add(btn);


     /*科目マップ表示*/
     select = main.selectKnowledge(name);

 	for(knum=0;knum<total;knum++){
 		 if(x> 4){
    		 x=0;
    		 y++;
    	 }
     	System.out.println(select.get(knum).getName()+" "+select.get(knum).getScore());
     	if(select.get(knum).getScore() >= 80){
    		color[knum] =new Color(135,206,250);
    	}
    	else if(select.get(knum).getScore() < 80 && select.get(knum).getScore() >= 70){
    		color[knum] = new Color(116,250,102);
    	}
    	else if(select.get(knum).getScore() >= 60){
    		color[knum] = new Color(255,125,129);
    	}
     	bun1[knum] = new Button(select.get(knum).getName());
     	bun1[knum].setFont(new Font("MS ゴシック",Font.BOLD,18));
     	bun1[knum].setBounds(100+x*300, 30+150*y, 200, 40);
     	bun1[knum].setBackground(color[knum]);
        this.add(bun1[knum]);

        x++;
     }
     for(knum=0;knum<total;knum++){
     	 bun1[knum].addActionListener(new ActionListener(){
     		 int _num = knum;
  	            public void actionPerformed(ActionEvent e){
  	            	pc(main.PanelNames[2],select.get(_num).getSubject());
  	            }
         });
     }

     /*科目点数入力機能*/
     subjecttext = new JTextField(20);
     subjecttext.setBounds(500, 50, 100, 30);
     this.add(subjecttext);
     JButton subjectsave = new JButton("成績を保存");
     subjectsave.setBounds(500+100, 50, 100, 30);
     this.add(subjectsave);
     subjectsave.addActionListener(new ActionListener(){
    	 int _snum = snum;
    	 public void actionPerformed(ActionEvent e){
    		 String str = subjecttext.getText();
    		 if(str != null){
    			 kscore = Integer.parseInt(str);
    			 main.saveSubjectScore(_snum,kscore);
    			 System.out.println(name+"は"+str+"点"+"保存完了");
    		 }
    		pc(main.PanelNames[1],name);
    	 }
     });
     /*知識点数入力機能
     /*知識点入力用プルダウン設定*/
     combo = new JComboBox[total];
      x = 0;
      y = 1;
     for(knum=0;knum<total;knum++){
    	 if(x> 4){
    		 x=0;
    		 y++;
    	 }
      	combo[knum] = new JComboBox(down);
      	combo[knum].setBounds(100+x*300,  30+150*y+50, 150, 20);
      	this.add(combo[knum]);
      	x++;
      }
     /*プルダウンで選択された値を点数に変換して保存*/
     for(knum=0;knum<total;knum++){
    	 combo[knum].addActionListener(new ActionListener(){
    		 int _knum = knum;
    		  String str = null;
    		  	 public void actionPerformed(ActionEvent e){
    		  		 if (combo[_knum].getSelectedIndex() == -1){
    		  			 str = "(not select)";
    		  		 }else{
    		  			 str = (String)combo[_knum].getSelectedItem();
    		  		 }
    		  		 if(str.equals("優")){//優なら80点
    		  			main.saveKnowledgescore(name,select.get(_knum).getName(),80);
    		  		 }
    		  		 else if(str.equals("良")){//良なら70点
    		  			main.saveKnowledgescore(name,select.get(_knum).getName(),70);
    		  		 }
    		  		 else if(str.equals("可")){//可なら60点
    		  			main.saveKnowledgescore(name,select.get(_knum).getName(),60);
    		  		 }
    		  		 else {//未取得なら0点
    		  			main.saveKnowledgescore(name,select.get(_knum).getName(),0);
    		  		 }
    		  		 /*設定後科目内マップへ移動*/
     		    	 pc(main.PanelNames[1],name);
  	            }
         });
     }


     /*コメント機能
 	 *コメント表示*/
      comentarea = new JTextArea();
      comentarea.setBounds(100, 800, 400, 150);
      comentarea.setLineWrap(true);//折り返し
      comentarea.setWrapStyleWord(true);//単語ごとに区切って折り返し
      coment=main.getcoment();
      for(int i=0;i<coment.size();i++){
     	 comentarea.append(coment.get(i));
      }
      this.add(comentarea);
      /*コメント機能
       * コメント追加*/
      addarea = new JTextArea();
      addarea.setBounds(600, 900, 200, 50);
      addarea.setLineWrap(true);
      addarea.setWrapStyleWord(true);
      this.add(addarea);
      /*コメント機能*
       * saveボタン*/
      comentbtn = new JButton();
      comentbtn = new JButton("コメント保存");
      comentbtn.setBounds(600, 950, 150, 30);
      comentbtn.addActionListener(new ActionListener(){
    	  String str = null;
    	  public void actionPerformed(ActionEvent e){
    		  str = addarea.getText();
    		  if(str != null)
    			  savecoment(str);
    		  pc(main.PanelNames[0],null);
    	  }
      });
      this.add(comentbtn);

      /*ボタン
       * 最古コメント削除ボタン*/
      deletebtn1 = new JButton();
      deletebtn1 = new JButton("コメント削除");
      deletebtn1.setBounds(100, 950, 150, 30);
      deletebtn1.addActionListener(new ActionListener(){
    	  public void actionPerformed(ActionEvent e){
    		  main.comentdelete(0);
    		  pc(main.PanelNames[0],null);
    	  }
      });
 	 this.add(deletebtn1);
 	 /*ボタン
 	  * 最新コメント削除ボタン*/
 	 deletebtn2 = new JButton();
 	 deletebtn2 = new JButton("書き直し");
 	 deletebtn2.setBounds(300, 950, 150, 30);
 	 deletebtn2.addActionListener(new ActionListener(){
 		 public void actionPerformed(ActionEvent e){
	            	main.comentdelete(coment.size()-1);
	            	pc(main.PanelNames[0],null);
	            }
	        });
 	 this.add(deletebtn2);


	}

	/*マップ間移動
	 * str:移動先のマップ名
	 * name:選択した科目*/
	public void pc(String str, String name){
		main.PanelChange((JPanel)this, str,name);
	}

	/*コメント保存:マップ名とコメント合わせて保存
	 *cmt:コメント*/
	public void savecoment(String cmt){
		System.out.println(cmt);
		main.savecoment("RelationMap",cmt);
	}
	/*過去に作成したコメントを表示*/
	public ArrayList<String> getcoment(){
		return main.getcoment();
	}

}