package Class;

import java.math.BigDecimal;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class StudySitu
 */
@WebServlet("/StudySitu")
public class StudySitu{

	double Goal;//目標
	double StudyTime;//学習時間
	double Understand;//理解度
	double Relation;//関連科目
	double Ev;//推定点

	public StudySitu(/*double goal,double Relation, double StudyTime, double Understand*/) {
		Goal = 60;
		Relation = 0;
		Understand = 0;
		StudyTime = 0;
		Ev = 0;
	}


	public double getGoal() {
		return Goal;
	}
	public void setGoal(double goal) {
		Goal = goal;
	}
	public double getStudyTime() {
		return StudyTime;
	}
	public void setStudyTime(double studyTime) {
		StudyTime = studyTime;
	}
	public double getUnderstand() {
		return Understand;
	}
	public void setUnderstand(double understand) {
		Understand = understand;
	}
	public double getRelation() {
		return Relation;
	}
	public void setRelation(double relation) {
		Relation = relation;
	}

	public double getEv() {
		return Ev;
	}

	public void setEv() {
		CalEv1();
	}



	public void CalEv1() {
		Ev = 3.08 * Relation + 0.16 * Understand + 0.18 * StudyTime;
		BigDecimal ev = new BigDecimal(Ev);
		BigDecimal bd1 = ev.setScale(3,BigDecimal.ROUND_HALF_UP);
		Ev = bd1.doubleValue();

	}

	public void CalEv2(double CalUnder) {
		Ev = 3.08 * Relation + 0.16 * CalUnder + 0.18 * StudyTime;
		BigDecimal ev = new BigDecimal(Ev);
		BigDecimal bd1 = ev.setScale(3,BigDecimal.ROUND_HALF_UP);
		Ev = bd1.doubleValue();
	}



}