package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest10 extends Quest{

	public Quest10(){
		super.maxGoal = 3;
		super. name = "Quest10";
		super. contents = "���ձ��û� 3���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("�����߳�! ���п� ���������� ������ �� �ְԵǾ��ٳ� ������ ������ �������� �ڸ���� �ְ� ������ ��θ� �����ϰ� �ִٳ�");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("������ ��ο� �������������� ������ �ڳ׿��� �ñ�ڳ�");
				});
			break;

			default:
				Platform.runLater(()->{
					episodeDia.close();
				});
			break;
		}
			
	}
	
	public void questAction(Monster monster){
		if(monster.name.equals("���ձ��û�") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE10 = true;
		User.player.quest = Quest.whoAmI("Quest11");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
