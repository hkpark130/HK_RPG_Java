package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest11 extends Quest{

	public Quest11(){
		super.maxGoal = 1;
		super. name = "Quest11";
		super. contents = "마왕군 간부 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("정찰에 의하면 적들의 전력은 상당히 강하다고 볼수있다네, 먼저 전초기지를 장악한다면 일이 쉬워지겠지.");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("전초기지는 현재 간부급 마왕군 혼자서 지휘하고있는걸로 보고되어있다네");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("마왕군 간부를 쓰러트리고 전초기지를 장악해주기 바라네!");
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
		if(monster.name.equals("마왕군 간부") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE11 = true;
		User.player.quest = Quest.whoAmI("Quest12");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
