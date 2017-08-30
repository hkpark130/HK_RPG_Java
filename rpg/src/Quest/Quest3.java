package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest3 extends Quest{

	public Quest3(){
		super.maxGoal = 1;
		super. name = "Quest3";
		super. contents = "������ 1���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("�����߳�. �� �̸����� �������� ����Ʈ����Ѵٳ� �ڳ׵� ���� ��������� ������ ���谡�ΰ�����");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("�׷��ٸ� �ٷ� �����־����� ���ڳ�!");
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
		if(monster.name.equals("����") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE3 = true;
		User.player.quest = Quest.whoAmI("Quest4");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
