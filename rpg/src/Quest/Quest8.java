package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest8 extends Quest{

	public Quest8(){
		super.maxGoal = 2;
		super. name = "Quest8";
		super. contents = "유적골렘을 2마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("수고했네! 정말 대단하군");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("지금까지 자네의 기록을 보면 마왕군의 전진기지는 얼마 멀지않은것 같네");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("숲을 넘어가 황무지를 건너면 마왕군의 전초기지를 확인할 수 있을것이야");
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
		if(monster.name.equals("유적골렘") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE8 = true;
		User.player.quest = Quest.whoAmI("Quest9");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
