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
		super. contents = "마왕을 저지해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("마왕의 부활의식이 진행되고있다네 그리고 마왕의 측근들이 그곳을 지키고있지");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("현재 모든 마왕군의 전력이 그곳을 지키고 있다고 봐도 무방하다네, 이대로 의식을 막지못하면 세상은 다시 혼란을 맡게될것이야!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("어떻게 해서든 측근들을 처치하고 마왕이 부활하기 전에 의식을 저지해야한다네!");
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
		if(monster.name.equals("마왕") && User.player.quest.goal < maxGoal){
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
