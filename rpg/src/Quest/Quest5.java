package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest5 extends Quest{

	public Quest5(){
		super.maxGoal = 1;
		super. name = "Quest5";
		super. contents = "곰을 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("정말 대단해, 도적들을 혼자서 소탕할 줄이야 자네라면 이 일을 맡겨도 되겠군");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("깊은숲은 원래 신성한 고목들이 모인곳이라네 하지만 모험가들의 이야기에따르면 무언가 문제가 있는모양이야");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("자네가 가서 확인해주게!");
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
		if(monster.name.equals("곰") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE5 = true;
		User.player.quest = Quest.whoAmI("Quest6");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
