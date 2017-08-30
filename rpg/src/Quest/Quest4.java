package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest4 extends Quest{

	public Quest4(){
		super.maxGoal = 1;
		super. name = "Quest4";
		super. contents = "도적 두목을 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("훌륭하군! 이렇게 빨리 돌아오다니");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
					epiLeftImg.setImage(new Image(getClass().getResource("../reImages/storePeople.png").toString()));
					epiText.setText("이보게, 기사단장! 큰일났소!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("물품이 유통되는 비밀 경로를 도적들이 눈치채서 더이상의 물품의 유통이 어려운 상황이오");
				});
			break;
			case 4:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("문제는 저희 기사단에서 해결하겠습니다! 도적들의 두목을 해치운다면 도적들은 자연스럽게 와해될것이야");
				});
			break;
			case 5:
				Platform.runLater(()->{
					epiText.setText("자네가 가서 도적들의 두목을 해치워주게!");
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
		if(monster.name.equals("도적 두목") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE4 = true;
		User.player.quest = Quest.whoAmI("Quest5");
		User.player.gold += 1000;
		
	}
}
