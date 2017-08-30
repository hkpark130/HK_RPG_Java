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
		super. contents = "좀비를 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("나무정령들이 알수없는 무언가에 의해서 오염되었다고?");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("이건 보통일이 아니군..");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("나는 상부에 이일을 보고할테니 자네는 다시한번 깊은숲을 확인해주게!");
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
		if(monster.name.equals("좀비") && User.player.quest.goal < maxGoal){
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
