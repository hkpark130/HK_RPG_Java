package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest7 extends Quest{

	public Quest7(){
		super.maxGoal = 1;
		super. name = "Quest7";
		super. contents = "������带 1���� ��ġ���ּ���";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("��ƿ����̴� ��ü�� �ִܸ��ΰ�?");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("�����ȵ�.. �ż��� ���� ��� �׷�����!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
					epiLeftImg.setImage(new Image(getClass().getResource("../reImages/restPeople.png").toString()));
					epiText.setText("�Ƹ��� �׳༮�����ϰſ���");
				});
			break;
			case 4:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("�׳༮�̶��?");
				});
			break;
			case 5:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
					epiText.setText("���ʸ� ĳ�� �������� ���ٰ� �׳༮�� ���ԵǾ���� Ŀ�ٶ��� Ź�ѻ��� ���ʸ� ��ġ�������� ��ο�������� �׳༮�� �࿡ ������ �־���?");
				});
			break;
			case 6:
				Platform.runLater(()->{
					epiText.setText("�׸��� �׳༮�� �ڽ��� �Ŵ��� ���� �ֵθ��� �ֺ��� ���� �����Ǳ� �����Ѱſ��� ���� ��� Ȳ���� �����ƾ��.");
				});
			break;
			case 7:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("�׸��� ����̶�� ������ ���ڽ��̱�.. �ڳ״� ���� ���������� ���ؼ� �� ������ �ذ����ֱ� �ٶ��!");
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
		if(monster.name.equals("���� ���") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE7 = true;
		User.player.quest = Quest.whoAmI("Quest8");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
