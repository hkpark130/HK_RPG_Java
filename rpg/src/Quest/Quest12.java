package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest12 extends Quest{

	public Quest12(){
		super.maxGoal = 3;
		super. name = "Quest12";
		super. contents = "정예 주술사 3마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("자네가 활약해준덕분에 적들의 전초기지를 장악할 수 있게되었다네!");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("하지만 이제부터 시작이라는걸 잊지말게나 적들은 아직 많은 전력을 가지고 있다네");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("먼저 적들의 전력을 조금식 깎아나가면 좋겠지. 자네와 같은 소수정예의 활약을 기대하겠네!");
				});
			break;
			case 4:
				Platform.runLater(()->{
					epiText.setText("먼저 적들의 전력을 조금식 깎아나가면 좋겠지. 자네와 같은 소수정예의 활약을 기대하겠네!");
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
		if(monster.name.equals("정예 주술사") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE12 = true;
		User.player.quest = Quest.whoAmI("Quest13");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
