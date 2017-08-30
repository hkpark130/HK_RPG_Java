package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest9 extends Quest{

	public Quest9(){
		super.maxGoal = 1;
		super. name = "Quest9";
		super. contents = "����Ŭ�ӽ��� 1���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("���谡���� ���������ϸ� Ȳ�������� �Ŵ��� ����Ŭ�ӽ��� ��ٰ� �Ѵٳ�,");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("�ǵ����̸� ���ϰ���� �������� ���ձ� ���������� ������ �ݵ�� ����ؾ��ϴ� �濡 ����Ŭ�ӽ��� ��ó�� �ִٰ��ϳ�");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("����Ŭ�ӽ��� ����� ������������ ���θ� �����ֱ� �ٶ��!");
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
		if(monster.name.equals("����Ŭ�ӽ�") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE9 = true;
		User.player.quest = Quest.whoAmI("Quest10");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
