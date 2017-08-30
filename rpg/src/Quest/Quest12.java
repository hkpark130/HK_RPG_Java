package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest12 extends Quest{

	public Quest12(){
		super.maxGoal = 3;
		super. name = "Quest12";
		super. contents = "���� �ּ��� 3���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("�ڳװ� Ȱ�����ش��п� ������ ���ʱ����� ����� �� �ְԵǾ��ٳ�!");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("������ �������� �����̶�°� �������Գ� ������ ���� ���� ������ ������ �ִٳ�");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("���� ������ ������ ���ݽ� ��Ƴ����� ������. �ڳ׿� ���� �Ҽ������� Ȱ���� ����ϰڳ�!");
				});
			break;
			case 4:
				Platform.runLater(()->{
					epiText.setText("���� ������ ������ ���ݽ� ��Ƴ����� ������. �ڳ׿� ���� �Ҽ������� Ȱ���� ����ϰڳ�!");
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
		if(monster.name.equals("���� �ּ���") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE12 = true;
		User.player.quest = Quest.whoAmI("Quest13");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
