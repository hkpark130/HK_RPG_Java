package Quest;

import java.lang.reflect.Constructor;
import User.User;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import Monster.Monster;

public class Quest {
	public String name = "Quest1";
	public String contents = "����� 10���� ��ġ���ּ���.";
	public int goal = 0;
	public int maxGoal = 0;
	
	
	public static Quest whoAmI(String name){
		try {	
			Class cls = Class.forName("Quest."+name);
			Constructor constr = cls.getConstructor();	
			Quest retobj = (Quest) constr.newInstance();
			return retobj;
		} catch (Throwable e) {}
		return null;
	}

	
	public void questAction(Monster monster){};
	public void reward(){}

	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,
			TextArea epiText) {
		// TODO Auto-generated method stub
		
	}


	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,
			TextArea epiText, Stage episodeDia) {
		// TODO Auto-generated method stub
		
	};
	/*
	 * ����Ʈ ��ü �߰��Ϸ���
	 * ���� ���� ��ü�� reward �޼ҵ带 �����ϸ� �ȴ�
	 * ���� ��.��
	 * */
}
