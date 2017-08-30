package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

public class Quest1 extends Quest{

	public Quest1(){
		super. name = "Quest1";
		super. contents = "�����忡�� ���� �Žÿ�.";
		super.goal = 0;
		super.maxGoal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){
			case 1:
				
				Platform.runLater(()->{
					epiText.setText("�ӳ��� ����, ������ ȥ���� ��Ʈ�� ������ �����ߴ�.���� ������� ������� ������ ���εǾ��� ���󿡴� ��ȭ�� ã�ƿԴ�");
				});	
			break;	
			case 2:
				Platform.runLater(()->{
					//epiRightImg.setImage(new Image(getClass().getResource("../reImages/storePeople.png").toString()));
					epiText.setText("������ ������ ���� ������ ������ �ٽú�Ȱ�� ���� ȥ���� �߱��Ѵٰ� �Ѵ�. ������ �������ؼ� ���� ������� ������ ������ ���!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("�����忡�� ���� �Žÿ�.");
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
		
	};
	
	public void reward(){
		User.player.quest = Quest.whoAmI("Quest2");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
