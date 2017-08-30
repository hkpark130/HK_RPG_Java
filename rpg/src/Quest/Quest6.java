package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest6 extends Quest{

	public Quest6(){
		super.maxGoal = 1;
		super. name = "Quest6";
		super. contents = "���� 1���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("�������ɵ��� �˼����� ���𰡿� ���ؼ� �����Ǿ��ٰ�?");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("�̰� �������� �ƴϱ�..");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("���� ��ο� ������ �������״� �ڳ״� �ٽ��ѹ� �������� Ȯ�����ְ�!");
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
		Episode.EPISODE7 = true;
		User.player.quest = Quest.whoAmI("Quest7");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
