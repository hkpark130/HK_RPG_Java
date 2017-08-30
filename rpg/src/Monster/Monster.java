package Monster;

import java.io.IOException;
import java.lang.reflect.Constructor;

import Item.Item;
import User.User;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rpg.BattleController;

public class Monster {
	public String name = "";
	public String skillName = "";
	public int lev = 1;
	public int skillRate = 0;
	public boolean skilled = false;
	public int skillDmg = 1;
	public int hp = 1;
	public int maxHp = 1;
	public int atk = 1;
	public int def = 1;
	public int exp = 1;
	public boolean isBoss = false;
	public String imgSrc = "";
	public Item dropItem = null;
	public int dropRate = 0;
	public String pattern = "";
	
	public static Monster whoAmI(String name){
		try {	
			Class cls = Class.forName("Monster."+name);
			Constructor constr = cls.getConstructor();	
			Monster retobj = (Monster) constr.newInstance();
			return retobj;
		} catch (Throwable e) {}
		return null;
	}
	
	public void pattern(Stage dia,Monster monster, Stage primaryStage, Label userPat,TextArea con, Button patternBtn, Button btnReset,ImageView patBackImg,Label count){
	
			BattleController.battleStart = false;
			
				Platform.runLater(()->{
				Thread th = new Thread(new Runnable(){
	
					@Override
					public void run() {
						int i = 0;		//패턴 공격 반복횟수
						while(i<3){
							try {
								BattleController.patCount = 0;
								for(int j = 0; j<2 ;j++){
									Platform.runLater(()->{
										count.setText(new Integer(BattleController.patCount).toString()+"초");
										BattleController.patCount++;
									});
									Thread.sleep(1000);
									
								}
								
								if(monster.pattern.equals(User.player.stack)){
									
									User.player.stack = "";
									Platform.runLater(()->{
										userPat.setText(User.player.stack);
									});
								}else{
									int userDmg = monster.skillDmg - (User.player.defItem.def + User.player.dex);
									User.player.hp = ((User.player.hp-userDmg)<=0)? 0 : User.player.hp-userDmg;
									User.player.stack = "";
									Platform.runLater(()->{
										userPat.setText(User.player.stack);
										con.appendText("패턴이 틀려서 "+userDmg+"의 피해를 입었습니다.\n");
									});
								}
								i++;
							} catch (Exception e) {}
						}
						
						BattleController.battleStart = true;
						Platform.runLater(()->{
							dia.close();
						});
					}
				});
				
				th.start();
				
				patBackImg.setOnMouseClicked(e->{
					patternBtn.requestFocus();
				});
				
				patternBtn.setOnKeyPressed(event->{
					switch(event.getCode()){
						case Q:
							User.player.stack += "Q";		
							break;
						case W:
							User.player.stack += "W";
							break;
						case E:
							User.player.stack += "E";
							break;
						case R:
							User.player.stack += "R";
							break;		
					}
					
					Platform.runLater(()->{
						userPat.setText(User.player.stack);
					});
					
				});
				
				btnReset.setOnAction(event->{
					
					User.player.stack = "";
					Platform.runLater(()->{
						userPat.setText(User.player.stack);
					});
					patternBtn.requestFocus();
				});
			});
			
			
		
	}
	
	/*
	 * 추가 하려면
	 * 클래스만 추가하면 됨
	 * 주의사항 던전에 5마리 밖에 못 나오니 수정하거나
	 * 던전을 더 만들어야 함
	 * */
	
}
