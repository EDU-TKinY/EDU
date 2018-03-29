 package concept
;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KnowledgeMap extends JPanel {
    JButton btn;
    MainFrame main;
    String str;
    Button[] bun1;
    int num;
    JTextField savetext;
    int score = 0;
	public String selectName;
	ArrayList<Subject> select;
	Graphics line;
	boolean select_flag = false;
	Color[] color;
	Graphics g;
	int y_axis,total;
	int[] x_axis={0,0,0,0,0,0,0,0,0};
	private String name ;
	 ArrayList<String> nl;
	JPanel p;
	private ArrowShape[] arrow = new ArrowShape[100];

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

	 public KnowledgeMap(MainFrame m,String s,String name){
        main = m;
        str = s;
        name = null;

//        setLayout(new FlowLayout());
//		main.getContentPane().add(this);
//		this.setPreferredSize(new Dimension(2200, 1000));


        System.out.println(main.getKnowledgeNmae()+" Knowledgemap");
        this.setName(s);
        this.setLayout(null);
        this.setSize(400, 200);
        setBounds(0, 0, 1000, 1000);
        btn = new JButton("科目間マップ"+"に移動");
    }


    public void pre(){
//    	if(select != null){
//    	select.clear();
//    	}
    	this.removeAll();
    }

    public void draw(String _name){

    /**/
    	this.removeAll();
    	System.out.println(_name+" Knowledgemap");

    /**/
    	JLabel paneltitle = new JLabel("これは"
    			+main.getKnowledgeNmae()+"のパネルです");
    	paneltitle.setBounds(0, 5, 400, 40);
    	paneltitle.setFont(new Font("MS ゴシック",Font.BOLD,24));
    	this.add(paneltitle);
    	btn.setBounds(150, 50, 200, 40);
         btn.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 pc(main.PanelNames[0],null);
             }
         });
         this.add(btn);
//    	Float f = (float) Math.random();
//    	this.setBackground(Color.getHSBColor(f, 0.5f, 0.8f));
    	System.out.println("redraw_Knw");

    /**/
    	setName(_name);
    	select = main.selectSubject(name);
    	//System.out.println(select.size());
    	int hoseiX,hoseiY;
    	int total = select.size();
    	bun1 = new Button[total];
    	color = new Color[total];

        for(num=0;num<total;num++){
        	y_axis=select.get(num).getYear();
        	hoseiX = 0;
        	hoseiY = 1/4*y_axis*100;
        	//System.out.println(select.getName(num)+select.getScore(num));
        	System.out.println(num);
        	if(select.get(num).getScore() >= 80){
        		color[num] =new Color(135,206,250);
        	}
        	else if(select.get(num).getScore() < 80 && select.get(num).getScore() >= 70){
        		color[num] = new Color(116,250,102);
        	}
        	else if(select.get(num).getScore() >= 60){
        		color[num] = new Color(255,125,129);
        	}
        	if(select.get(num).getYear() == 1)
        		hoseiX = 15;
        	else if(select.get(num).getYear() == 2)
        		hoseiX = 5;
        	else if(select.get(num).getYear() == 3)
        		hoseiX = 20;
        	else if(select.get(num).getYear() == 7)
        		hoseiX = 45;
        	else if(select.get(num).getYear() == 8)
        		hoseiX = 50;
        	bun1[num] = new Button(select.get(num).getName());
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
	 	            	pc(main.PanelNames[1],select.get(_num).getName());
	 	            }
	        });
        }
    	for(int i=0;i<9;i++){
    		x_axis[i] = 0;
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
         /*ボタン*
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
    	Point a=new Point(10,10);
        Point b=new Point(100,100);
        arrow[99]=new ArrowShape(a,b);
    }

    public void paintComponent(Graphics g){
    	if(select_flag && main.knowledge_flag){
    		Graphics2D gY=(Graphics2D)g;
    		gY.setColor(Color.black);
    		Graphics2D gX=(Graphics2D)g;
    		int count = 0;
    		select = main.selectSubject(name);
    		int total = select.size();

    		for(num = 0;num < total;num++){
    			 nl = select.get(num).getNextList();
    			if(!nl.get(0).equals("sub")){

    				/*後続科目の矢印*/
    				for(int i=0;i<nl.size();i++){
    					int number = 0;
    					if( (number = serchNumber(select,nl.get(i))) != -1)
    					/*後続科目の場合*/
    					if(select.get(num).getYear() > select.get(number).getYear()
    							/*&& select.getYear(num) == 3*/){
    						Point a=new Point(bun1[num].getX()+75,bun1[num].getY());
    						Point b=new Point(bun1[number].getX()+75,
    								bun1[number].getY()+40);
    						arrow[count]=new ArrowShape(a,b);
    						gY.setColor(Color.BLACK);
	    					gY.draw(arrow[count]);
	    					count ++;
    					}

    					/*同時並行の場合*/
    					else if(select.get(num).getYear() ==select.get(number).getYear()
    							/*&& select.getYear(num) == 3*/){

    						/*同時並行科目が右側にあるとき*/
    						if(bun1[num].getX() < bun1[number].getX()){
    							Point a=new Point(bun1[num].getX()+150,bun1[num].getY()+20);
    							Point b=new Point(bun1[number].getX(),
    									bun1[number].getY()+20);
    							arrow[count]=new ArrowShape(a,b);
    	    					gX.setColor(Color.RED);
    							gX.draw(arrow[count]);
    	    					count ++;
    						}

    						/*同時並行科目が左側にあるとき*/
    						if(bun1[num].getX() > bun1[number].getX()){
    							Point a=new Point(bun1[num].getX(),bun1[num].getY()+20);
    							Point b=new Point(bun1[number].getX()+150,
    									bun1[number].getY()+20);
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

    public void setName(String _name){
    	name = _name;
    }

    public int serchNumber(ArrayList<Subject> select, String name){
    	int x = -1;
    	for(int i=0;i<select.size();i++){
    		if(select.get(num).equals(name))
    			x = num;
    	}
    	return x;
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