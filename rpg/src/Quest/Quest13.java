package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest13 extends Quest{

	public Quest13(){
		super.maxGoal = 1;
		super. name = "Quest13";
		super. contents = "정예 간부 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("자네덕분에 적들의 전력이 상당히 감소되었다네 이대로 나아간다면 예언을 막을 수 있을겠군");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("현재 마왕성으로가는 길목을 소수의 정예마왕군이 지키고있다네, 자네의 힘이 필요하네!");
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
		if(monster.name.equals("정예 간부") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE13 = true;
		User.player.quest = Quest.whoAmI("Quest14");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
