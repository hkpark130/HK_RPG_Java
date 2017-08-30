package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest14 extends Quest{

	public Quest14(){
		super.maxGoal = 1;
		super. name = "Quest14";
		super. contents = "분신2를 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("엄청 강력한 마왕군을 만났단 말인가 아직도 그런 전력을 숨겨두다니 무슨생각이지?");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("그렇다고 해도 멈출 수는 없네, 이대로 진격한다면 의식장에 다다를수있을것이야.");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("이번이 마지막 전투가 될수도 있다네 힘내주시게!");
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
		if(monster.name.equals("분신2") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE14 = true;
		User.player.quest = Quest.whoAmI("Quest15");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
