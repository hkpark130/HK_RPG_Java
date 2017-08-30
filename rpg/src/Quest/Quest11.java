package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest11 extends Quest{

	public Quest11(){
		super.maxGoal = 1;
		super. name = "Quest11";
		super. contents = "���ձ� ���� 1���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("������ ���ϸ� ������ ������ ����� ���ϴٰ� �����ִٳ�, ���� ���ʱ����� ����Ѵٸ� ���� ����������.");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("���ʱ����� ���� ���α� ���ձ� ȥ�ڼ� �����ϰ��ִ°ɷ� ����Ǿ��ִٳ�");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("���ձ� ���θ� ����Ʈ���� ���ʱ����� ������ֱ� �ٶ��!");
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
		if(monster.name.equals("���ձ� ����") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE11 = true;
		User.player.quest = Quest.whoAmI("Quest12");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
