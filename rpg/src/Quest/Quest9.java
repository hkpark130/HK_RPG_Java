package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest9 extends Quest{

	public Quest9(){
		super.maxGoal = 1;
		super. name = "Quest9";
		super. contents = "사이클롭스를 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("모험가들의 정보에의하면 황무지에는 거대한 사이클롭스가 산다고 한다네,");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("되도록이면 피하고싶은 적이지만 마왕군 전진기지로 갈려면 반드시 통과해야하는 길에 사이클롭스의 거처가 있다고하네");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("사이클롭스를 무찌르고 전진기지로의 진로를 열어주길 바라네!");
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
		if(monster.name.equals("사이클롭스") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE9 = true;
		User.player.quest = Quest.whoAmI("Quest10");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
