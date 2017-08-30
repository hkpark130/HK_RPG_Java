package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

public class Quest1 extends Quest{

	public Quest1(){
		super. name = "Quest1";
		super. contents = "기사단장에게 말을 거시오.";
		super.goal = 0;
		super.maxGoal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){
			case 1:
				
				Platform.runLater(()->{
					epiText.setText("머나먼 과거, 세상을 혼란에 빠트린 마왕이 존재했다.많은 사람들의 희생으로 마왕은 봉인되었고 세상에는 평화가 찾아왔다");
				});	
			break;	
			case 2:
				Platform.runLater(()->{
					//epiRightImg.setImage(new Image(getClass().getResource("../reImages/storePeople.png").toString()));
					epiText.setText("하지만 예언가의 예언에 따르면 마왕은 다시부활해 세상에 혼돈을 야기한다고 한다. 예언을 막기위해서 많은 사람들이 모험을 떠나게 됬다!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiText.setText("기사단장에게 말을 거시오.");
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
		
	};
	
	public void reward(){
		User.player.quest = Quest.whoAmI("Quest2");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
