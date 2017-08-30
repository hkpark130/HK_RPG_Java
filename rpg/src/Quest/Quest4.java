package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest4 extends Quest{

	public Quest4(){
		super.maxGoal = 1;
		super. name = "Quest4";
		super. contents = "���� �θ��� 1���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("�Ǹ��ϱ�! �̷��� ���� ���ƿ��ٴ�");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
					epiLeftImg.setImage(new Image(getClass().getResource("../reImages/storePeople.png").toString()));
					epiText.setText("�̺���, ������! ū�ϳ���!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("��ǰ�� ����Ǵ� ��� ��θ� �������� ��ġä�� ���̻��� ��ǰ�� ������ ����� ��Ȳ�̿�");
				});
			break;
			case 4:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("������ ���� ���ܿ��� �ذ��ϰڽ��ϴ�! �������� �θ��� ��ġ��ٸ� �������� �ڿ������� ���صɰ��̾�");
				});
			break;
			case 5:
				Platform.runLater(()->{
					epiText.setText("�ڳװ� ���� �������� �θ��� ��ġ���ְ�!");
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
		if(monster.name.equals("���� �θ�") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE4 = true;
		User.player.quest = Quest.whoAmI("Quest5");
		User.player.gold += 1000;
		
	}
}
