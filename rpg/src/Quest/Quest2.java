package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest2 extends Quest{

	public Quest2(){
		super.maxGoal = 1;
		super. name = "Quest2";
		super. contents = "슬라임을 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("어서오게 기다리고있었다네 자기소개는 뒤로미루고 먼저 급한일부터 처리해주지 않겠나?");
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
		if(monster.name.equals("슬라임") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE2 = true;
		User.player.quest = Quest.whoAmI("Quest3");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
