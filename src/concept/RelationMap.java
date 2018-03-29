package concept;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RelationMap extends JPanel {
	/*メンバ変数*/

	/*main:mainクラス*/
	MainFrame main;

	/*ボタン群
	 * start:起動ボタン
	 * save:変更点保存ボタン
	 * bun1:科目ボタン*/
	JButton start,save;
	Button[] bun1;

	/*paneltitle:マップ名を表示するラベル*/
	//JLabel paneltitle;

	String str;
	/**/
	Color[] color;
	Graphics g;
	private ArrowShape[] arrow = new ArrowShape[100];

	/**/
	int y_axis,total;
	int[] x_axis={0,0,0,0,0,0,0,0,0};
	int num = 0;

	/*Flag群
	 *select_flag:科目読み込みを監視するフラグ
					  科目を読み込む前にアクセスをすることを阻止
	 *arrow_flag:矢印を描画できたか監視するフラグ
	     			ボタンを矢印より前面に出すことを阻止*/
	boolean select_flag = false;
	boolean arrow_flag = false;

	ArrayList<String> nl;
	SubjectList select  =null;
	JPanel p;

	/*コメント用
	 * comentareat:コメントを表示するエリア
	 * coment:コメントの内容を保持するリスト
	 * addarea:コメントを書き込むエリア
	 * comentbtn:コメント保存ボタン
	 * deletebtn1:最古コメント削除ボタン
	 * deletebtn2:最新コメント削除*/
	JTextArea comentarea,addarea;
	ArrayList<String> coment;
	JButton comentbtn,deletebtn1,deletebtn2;



	/*RelationMapのコンストラクタ
	 * m:mainクラス*/
	public RelationMap(MainFrame m,String s){
		main = m;
		str = s;

		/*スクロールの設定*/
		//setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(2200, 1000));
		main.getContentPane().add(this);

		/*科目間マップの設定*/
		this.setName("RelationMap");
		this.setLayout(null);
		this.setSize(400,200);
		setBounds(0, 0, 1000, 1000);

		/*システム起動ボタン*/
		start = new JButton("Start");
		start.setBounds(20, 50, 150, 40);
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pc(main.PanelNames[0],null);
			}
		});

		this.add(start);



	}
	/**/

	public void pre(){
		this.remove(start);
		this.removeAll();
	}

	public void redraw(/*Graphics g*/){
		System.out.println("redraw");
		save = new JButton("読み込み");
		save.setBounds(100, 10, 150, 40);
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.saveStudent();
			}
		});
		this.add(save);

		/*変数初期化*/
		y_axis=0;


		total = main.getTotalSubjectNumber();
		bun1 = new Button[total];
		color = new Color[total];
		select   = main.getSubject();
		int hoseiX,hoseiY;
		for(num=0;num<total;num++){
			y_axis=select.getYear(num);
			hoseiX = 0;
			hoseiY = 1/4*y_axis*100;
			//System.out.println(select.getName(num)+select.getScore(num));
			if(main.getSubject().getScore(num) >= 80){
				color[num] =new Color(135,206,250);
			}
			else if(select.getScore(num) < 80 && select.getScore(num) >= 70){
				color[num] = new Color(116,250,102);
			}
			else if(select.getScore(num) >= 60){
				color[num] = new Color(255,125,129);
			}
			if(select.getYear(num) == 1)
				hoseiX = 15;
			else if(select.getYear(num) == 2)
				hoseiX = 5;
			else if(select.getYear(num) == 3)
				hoseiX = 20;
			else if(select.getYear(num) == 7)
				hoseiX = 45;
			else if(select.getYear(num) == 8)
				hoseiX = 50;
			bun1[num] = new Button(select.getName(num));
			bun1[num].setFont(new Font("MS ゴシック",Font.BOLD,16));
			bun1[num].setBounds(10+x_axis[y_axis]*200+hoseiX*20, 10+y_axis*100+hoseiY, 180, 40);
			bun1[num].setBackground(color[num]);
			this.add(bun1[num]);
			//this.setBackground(Color.getHSBColor(50, 7, 95));
			x_axis[y_axis]++;
		}
		for(num=0;num<total;num++){
			bun1[num].addActionListener(new ActionListener(){
				int _num = num;
				public void actionPerformed(ActionEvent e){
					pc(main.PanelNames[1],select.getName(_num));
				}
			});
		}
		for(int i=0;i<9;i++){
			x_axis[i] = 0;
		}

		/*コメント機能
		 *コメント表示*/
		comentarea = new JTextArea(10,80);
		comentarea.setBounds(100, 800, 400, 150);
		comentarea.setLineWrap(true);//折り返し
		comentarea.setWrapStyleWord(true);//単語ごとに区切って折り返し
		coment=main.getcoment();
		for(int i=0;i<coment.size();i++){
			comentarea.append(coment.get(i));
		}
		//main.setscroll(comentarea);
		//	         JScrollPane comentscrol = new JScrollPane();
		//	         comentscrol.getViewport().setView(comentarea);
		//	         comentscrol.setPreferredSize(new Dimension(2000, 80));
		this.add(comentarea);
		//main.contentPane.add(comentscrol);
		/*コメント機能
		 * コメント追加*/
		addarea = new JTextArea();
		addarea.setBounds(600, 900, 200, 50);
		addarea.setLineWrap(true);
		addarea.setWrapStyleWord(true);
		this.add(addarea);
		/*ボタン*
		 * saveボタン*/
		comentbtn = new JButton();
		comentbtn = new JButton("コメント保存");
		comentbtn.setBounds(600, 950, 150, 30);
		comentbtn.addActionListener(new ActionListener(){
			String str = null;
			public void actionPerformed(ActionEvent e){
				str = addarea.getText();
				if(str.equals("管理者モード"))
					pc(main.PanelNames[3],null);
				else if(str != null){
					savecoment(str);
					pc(main.PanelNames[0],null);
				}
			}
		});
		this.add(comentbtn);
		/*ボタン
		 * 最古コメント削除ボタン*/
		deletebtn1 = new JButton();
		deletebtn1 = new JButton("コメント削除");
		deletebtn1.setBounds(100, 950, 150, 30);
		deletebtn1.addActionListener(new ActionListener(){
			String str = null;
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
			 String str = null;
			 public void actionPerformed(ActionEvent e){
				 main.comentdelete(coment.size()-1);
				 pc(main.PanelNames[0],null);
			 }
		 });
		 this.add(deletebtn2);

		 //this.add(p);
		 select_flag = true;
		 //	    	Point a=new Point(10,10);
		 //            Point b=new Point(100,100);
		 //            arrow[99]=new ArrowShape(a,b);
	}

	/*科目間の矢印描画
	 * */
	public void paintComponent(Graphics g){
		if(select_flag && main.relatoinal_flag){
			Graphics2D gY=(Graphics2D)g;
			gY.setColor(Color.black);
			Graphics2D gX=(Graphics2D)g;
			int count = 0;
			int hosei = 0;
			select = main.getSubject();
			for(num = 0;num < total;num++){
				nl = select.getNext(num);
				if(!nl.get(0).equals("sub")){

					/*後続科目の矢印*/
					for(int i=0;i<nl.size();i++){

						/*後続科目の場合*/
						if(select.getYear(num) > select.getYear(select.getNumber(nl.get(i)))
								//&& !(select.getYear(num) >= 3)/*表示矢印を減らす場合*/
								//&& !(bun1[num].getX() >= 1000)/*表示矢印を減らす場合*/
								){
							hosei = 2;
							Point a=new Point(bun1[num].getX()+75,bun1[num].getY()-hosei);
							Point b=new Point(bun1[select.getNumber(nl.get(i))].getX()+75+hosei,
									bun1[select.getNumber(nl.get(i))].getY()+40+hosei);
							arrow[count]=new ArrowShape(a,b);
							gY.setColor(Color.BLACK);
							gY.draw(arrow[count]);
							Ellipse2D startpoint = new Ellipse2D.Double();
							startpoint.setFrameFromCenter(a.getX(), a.getY(),a.getX()+2.5,a.getY()+2.5);
							gY.setPaint(Color.black);
							gY.fill(startpoint);
							count ++;
						}

						/*同時並行の場合*/
						else if(select.getYear(num) == select.getYear(select.getNumber(nl.get(i)))
								/*&& select.getYear(num) == 3*/){

							/*同時並行科目が右側にあるとき*/
							if(bun1[num].getX() < bun1[select.getNumber(nl.get(i))].getX()){
								Point a=new Point(bun1[num].getX()+150,bun1[num].getY()+20);
								Point b=new Point(bun1[select.getNumber(nl.get(i))].getX(),
										bun1[select.getNumber(nl.get(i))].getY()+20);
								arrow[count]=new ArrowShape(a,b);
								gX.setColor(Color.RED);
								gX.draw(arrow[count]);
								count ++;
							}

							/*同時並行科目が左側にあるとき*/
							if(bun1[num].getX() > bun1[select.getNumber(nl.get(i))].getX()){
								Point a=new Point(bun1[num].getX(),bun1[num].getY()+20);
								Point b=new Point(bun1[select.getNumber(nl.get(i))].getX()+150,
										bun1[select.getNumber(nl.get(i))].getY()+20);
								arrow[count]=new ArrowShape(a,b);
								gX.setColor(Color.RED);
								gX.draw(arrow[count]);
								count ++;
							}

						}

						if(count >30)
							break;
					}
					//gX.setColor(Color.RED);
					//gX.draw(arrow[99]);
				}
			}
			System.out.println("out");
		}
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
