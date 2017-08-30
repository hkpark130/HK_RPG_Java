package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest14 extends Quest{

	public Quest14(){
		super.maxGoal = 1;
		super. name = "Quest14";
		super. contents = "�н�2�� 1���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("��û ������ ���ձ��� ������ ���ΰ� ������ �׷� ������ ���ܵδٴ� ������������?");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("�׷��ٰ� �ص� ���� ���� ����, �̴�� �����Ѵٸ� �ǽ��忡 �ٴٸ����������̾�.");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("�̹��� ������ ������ �ɼ��� �ִٳ� �����ֽð�!");
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
		if(monster.name.equals("�н�2") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE14 = true;
		User.player.quest = Quest.whoAmI("Quest15");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
