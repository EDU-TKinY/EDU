package concept;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Administrator extends JPanel{

	private MainFrame main;
	private int snum;
	private JLabel paneltitle;
	private JButton btn;

	public Administrator(MainFrame m,String s,String name) {
		main = m;
	     this.setLayout(null);
	     this.setSize(4000, 2000);
	     btn = new JButton(main.PanelNames[0]+"に移動");
	}

public void draw(){
	/*準備*/
	JPanel p = new JPanel();
	snum = 0;
	
	/*戻るボタン*/
	if(this.paneltitle != null)
		this.remove(paneltitle);
	 paneltitle = new JLabel("これは"
             +"管理者モード"+"のパネルです");
     paneltitle.setBounds(0, 5, 400, 40);
     this.add(paneltitle);

     btn.setBounds(150, 50, 200, 40);
     btn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             pc(main.PanelNames[0],null);
         }
     });
     this.add(btn);

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
