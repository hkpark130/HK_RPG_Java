package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest3 extends Quest{

	public Quest3(){
		super.maxGoal = 1;
		super. name = "Quest3";
		super. contents = "도적을 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("수고했네. 난 이마을의 기사단장인 나이트라고한다네 자네도 또한 마왕토벌에 힘써줄 모험가인가보군");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("그렇다면 바로 일해주었으면 좋겠네!");
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
		if(monster.name.equals("도적") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE3 = true;
		User.player.quest = Quest.whoAmI("Quest4");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
