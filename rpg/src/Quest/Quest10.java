package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest10 extends Quest{

	public Quest10(){
		super.maxGoal = 3;
		super. name = "Quest10";
		super. contents = "마왕군궁사 3마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("수고했네! 덕분에 전진기지로 진격할 수 있게되었다네 하지만 적들은 절벽위에 자리잡고 있고 유일한 통로를 점거하고 있다네");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("적들의 통로와 전진기지까지의 정찰을 자네에게 맡기겠네");
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
		if(monster.name.equals("마왕군궁사") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE10 = true;
		User.player.quest = Quest.whoAmI("Quest11");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
