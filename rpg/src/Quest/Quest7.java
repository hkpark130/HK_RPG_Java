package Quest;

import Monster.Monster;
import User.User;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import rpg.*;

public class Quest7 extends Quest{

	public Quest7(){
		super.maxGoal = 1;
		super. name = "Quest7";
		super. contents = "더블헤드를 1마리 퇴치해주세요";
		super.goal = 0;
		
	}
	
	public void episode(int epiScene, ImageView epiLeftImg, ImageView epiRightImg, ImageView epiBackImg,TextArea epiText, Stage episodeDia){
		switch(epiScene){	
			case 1:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("살아움직이는 시체가 있단말인가?");
				});
			break;
			case 2:
				Platform.runLater(()->{
					epiText.setText("말도안돼.. 신성한 숲에 어떻게 그런일이!");
				});
			break;
			case 3:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
					epiLeftImg.setImage(new Image(getClass().getResource("../reImages/restPeople.png").toString()));
					epiText.setText("아마도 그녀석때문일거에요");
				});
			break;
			case 4:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("그녀석이라면?");
				});
			break;
			case 5:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
					epiText.setText("약초를 캐러 깊은숲에 들어갔다가 그녀석을 보게되었어요 커다랗고 탁한색의 판초를 걸치고있지만 어두운곳에서도 그녀석을 밝에 빛나고 있었죠?");
				});
			break;
			case 6:
				Platform.runLater(()->{
					epiText.setText("그리고 그녀석이 자신의 거대한 낫을 휘두르자 주변이 점점 오염되기 시작한거에요 저는 놀라서 황급히 도망쳤어요.");
				});
			break;
			case 7:
				Platform.runLater(()->{
					epiRightImg.setImage(new Image(getClass().getResource("../reImages/hallPeople.png").toString()));
					epiText.setText("그말이 사실이라면 원인은 그자식이군.. 자네는 당장 깊은숲으로 향해서 이 문제를 해결해주기 바라네!");
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
		if(monster.name.equals("더블 헤드") && User.player.quest.goal < maxGoal){
			User.player.quest.goal++;
		}
	};
	
	public void reward(){
		Episode.EPISODE7 = true;
		User.player.quest = Quest.whoAmI("Quest8");
		User.player.gold += 1000;
		User.player.lev++;
	}
}
