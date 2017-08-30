package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest15 extends Quest{

	public Quest15(){
		super.maxGoal = 1;
		super. name = "Quest15";
		super. contents = "������ �������ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("������ ��Ȱ�ǽ��� ����ǰ��ִٳ� �׸��� ������ ���ٵ��� �װ��� ��Ű������");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("���� ��� ���ձ��� ������ �װ��� ��Ű�� �ִٰ� ���� �����ϴٳ�, �̴�� �ǽ��� �������ϸ� ������ �ٽ� ȥ���� �ðԵɰ��̾�!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("��� �ؼ��� ���ٵ��� óġ�ϰ� ������ ��Ȱ�ϱ� ���� �ǽ��� �����ؾ��Ѵٳ�!");
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
		//Episode.EPISODE15 = true;
		//User.player.quest = Quest.whoAmI("Quest15");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
