package concept;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CreateMemo {
	public CreateMemo(){

	}
	/*コメントを書いたログファイルの作成*/
	public void save(String map, String cmt){

		Date now = new Date();
		String day = now.toString();
		String name;
		System.out.println("Date() = " + now);

		/*名前を日付で獲得*/
		name=day.split(" ")[5];
		if(day.split(" ")[1].equals("Jan"))
			name+="01";
		else if(day.split(" ")[1].equals("Feb"))
			name+="02";
		else if(day.split(" ")[1].equals("Mar"))
			name+="03";
		else if(day.split(" ")[1].equals("Apr"))
			name+="04";
		else if(day.split(" ")[1].equals("May"))
			name+="05";
		else if(day.split(" ")[1].equals("Jun"))
			name+="06";
		else if(day.split(" ")[1].equals("Jul"))
			name+="07";
		else if(day.split(" ")[1].equals("Aug"))
			name+="08";
		else if(day.split(" ")[1].equals("Sep"))
			name+="09";
		else if(day.split(" ")[1].equals("Oct"))
			name+="10";
		else if(day.split(" ")[1].equals("Nov"))
			name+="11";
		else if(day.split(" ")[1].equals("Dec"))
			name+="12";
		else
			System.out.println("false");
		name+=day.split(" ")[2];
		name += " ";
		name += day.split(" ")[3];
		System.out.println(name);

		/*コメントファイルの名前を保存するlogファイルの作成*/
		try{
			File file = new File("././object/log/log.txt");
			FileWriter log = new FileWriter(file, true);
			log.write("["+name+"]"+cmt+"\n");
			log.close();
		}catch(IOException e){
			System.out.println(e);
		}
		try{
			File file = new File("././object/log/logAll.txt");
			FileWriter logall = new FileWriter(file, true);
			logall.write("["+name+"]"+cmt+"\n");
			logall.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}

	/* getcoment:
	 * logファイルに存在するコメントを取得*/
	public ArrayList<String> getcoment() {
		File file = new File("././object/log/log.txt");
		ArrayList<String> coment = new ArrayList<String>();
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			str = br.readLine();
			System.out.println(str);
			while(str != null){
				coment.add(str+"\n");
				str=br.readLine();
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return coment;
	}

	/* getAllcoment:
	 * logAllファイルに存在するすべてのコメントを取得*/
	public ArrayList<String> getAllcoment() {
		File file = new File("././object/log/logAll.txt");
		ArrayList<String> coment = new ArrayList<String>();
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			str = br.readLine();
			System.out.println(str);
			while(str != null){
				coment.add(str+"\n");
				str=br.readLine();
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return coment;
	}


	/*delete:
	 * logファイルに存在するnum番目のコメントを削除
	 * num=-1の場合はすべてを削除*/
	public void delete(int num){
		File file = new File("././object/log/log.txt");
		ArrayList<String> coment = new ArrayList<String>();
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			str = br.readLine();
			System.out.println(str);
			while(str != null){
				coment.add(str+"\n");
				str=br.readLine();
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try{
			FileWriter log = new FileWriter(file);
			for(int i=0;i<coment.size();i++){
				if(i != num)
					log.write(coment.get(i));
			}
			log.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}

	/* deleteAll:
	 * logAllファイルに存在するnum番目のコメントを削除
	 * num = -1の場合はすべてを削除*/
	public void deleteAll(int num){
		File file = new File("././object/log/logAll.txt");
		ArrayList<String> coment = new ArrayList<String>();
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			str = br.readLine();
			System.out.println(str);
			while(str != null){
				coment.add(str+"\n");
				str=br.readLine();
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		/*num=-1でなければnum番目のコメントを削除*/
		if(num != -1){
			try{
				FileWriter log = new FileWriter(file);
				for(int i=0;i<coment.size();i++){
					if(i != num)
						log.write(coment.get(i));
				}
				log.close();
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}

}
