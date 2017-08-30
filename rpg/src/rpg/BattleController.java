package rpg;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Item.*;
import Monster.*;
import Quest.*;
import Skill.*;
import User.User;
import javafx.application.Platform;
import javafx.concurrent.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.*;
import javafx.stage.*;

public class BattleController implements Initializable{
	@FXML Button btnAttack;
	@FXML Button btnTool;
	@FXML Button btnOut;
	@FXML Label myAtk;
	@FXML Label myDef;
	@FXML Label myHp;
	@FXML Label myMp;
	@FXML Label comAtk;
	@FXML Label comDef;
	@FXML Label comHp;
	@FXML Label comLv;
	@FXML Label comName;
	@FXML Label isBossLabel;
	@FXML Label round;
	@FXML ImageView battleStage;
	Label hpToolGrade;
	Label hpToolQuan;
	Label mpToolGrade;
	Label mpToolQuan;
	@FXML ImageView comImg;
	@FXML ImageView comAttackImg;
	@FXML ProgressBar myHpBar;
	@FXML ProgressBar comHpBar;
	@FXML TextArea textAction;
	String battleActionSet = "";
	Button btnHpTool;
	Button btnMpTool;
	Button btnQ;
	ImageView patBackImg;
	Label count;
	Button btnReset;
	Label comPat;
	Label userPat;
	@FXML Label atkItemLabel;
	@FXML Label atkItemAbility;

	Parent dropPr = null;
	Parent toolPr = null;
	Parent patPr = null;
	public Stage dia = new Stage(StageStyle.UTILITY);
	
	Random rand = new Random();
	Monster monster;
	public Stage primaryStage;
	boolean attacking = false;
	public static boolean battleStart = false;
	boolean flagPattern = true;
	Item poth = null;
	Item pothio = null;
	int roundStage = 1;
	public static int userDmg = 0;	//유저 피해 데미지
	public static int comDmg = 0;	//컴퓨터 피해 데미지
	Thread monThread;
	public static int patCount = 0;
	public boolean texting = false;
	public boolean texted = false;
	int dungeonLev = 1;
	
	synchronized public int getORIncRoundStage(boolean incr){
		if(incr)
			return ++roundStage;
		else
			return roundStage;
	}
	
	synchronized public boolean getTexting(){
		return texting;
	}
	
	synchronized public void setTexting(boolean flag){
		texting = flag;
	}
	
	synchronized public boolean getTexted(){
		return texted;
	}
	
	synchronized public void setTexted(boolean flag){
		texted = flag;
	}
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		battleStart = false;
		Random rand = new Random();
		isBossLabel.setVisible(false);
		myAtk.setText( new Integer(User.player.str).toString()+" + "+new Integer(User.player.atkItem.atk).toString() );
		myDef.setText(new Integer(User.player.dex).toString()+" + "+User.player.defItem.def);
		myHp.setText(new Integer(User.player.hp).toString());
		myHpBar.setStyle("-fx-accent: red;");
		atkItemLabel.setText(" * 장비중인 아이템 :\n"+User.player.atkItem.name);
		atkItemAbility.setText(" * 아이템 특수 능력 :\n"+User.player.atkItem.abilityName);
		
		if(MapController.map.equals("canyon")){
			monster = Monster.whoAmI("Monster3");		
		}else if(MapController.map.equals("canyon2")){
			monster = Monster.whoAmI("Monster3");
		}else if(MapController.map.equals("canyon3")){
			monster = Monster.whoAmI("Monster2");
		}		//canyon
		else if(MapController.map.equals("rocky")){
			monster = Monster.whoAmI("Monster7");		
		}else if(MapController.map.equals("rocky2")){
			monster = Monster.whoAmI("Monster7");		
		}else if(MapController.map.equals("rocky3")){
			monster = Monster.whoAmI("Monster5");		
		}		//rocky
		else if(MapController.map.equals("derelict")){
			monster = Monster.whoAmI("Monster9");		
		}else if(MapController.map.equals("derelict2")){
			monster = Monster.whoAmI("Monster11");		
		}		//delel
		else if(MapController.map.equals("ice")){
			monster = Monster.whoAmI("Monster13");		
		}else if(MapController.map.equals("ice2")){
			monster = Monster.whoAmI("Monster14");		
		}else if(MapController.map.equals("ice3")){
			monster = Monster.whoAmI("Monster15");		
		}		//ice
		else if(MapController.map.equals("near")){
			monster = Monster.whoAmI("Monster17");		
		}else if(MapController.map.equals("near2")){
			monster = Monster.whoAmI("Monster19");		
		}else if(MapController.map.equals("near3")){
			monster = Monster.whoAmI("Monster21");		
		}		//near
		else if(MapController.map.equals("final")){
			monster = Monster.whoAmI("Monster20");		
		}else if(MapController.map.equals("final2")){
			monster = Monster.whoAmI("Monster24");		
		}		//final
		
		comHpBar.setStyle("-fx-accent: red;");
		comAtk.setText(new Integer(monster.atk).toString());
		comDef.setText(new Integer(monster.def).toString());
		comHp.setText(new Integer(monster.hp).toString());
		comLv.setText(new Integer(monster.lev).toString());
		comName.setText(monster.name);
		comImg.setImage(new Image(getClass().getResource(monster.imgSrc).toString()));
		
		myHpBar();
		comHpBar();
		patCount = 0;
		
		battleStage.setOnMouseClicked(event->{

			btnOut.requestFocus();
			
		});
		
		Thread roundTh = new Thread(new Runnable(){

			@Override
			public void run() {
				round.setText("Round "+new Integer(getORIncRoundStage(false)).toString());
				
					monThread = new Thread(monAttack);
					monThread.start();
				int roundCount = 2;
				
				try {
					Thread.sleep(5);
					Platform.runLater(()->{
						primaryStage = (Stage)myAtk.getScene().getWindow();
						btnOut.requestFocus();
					});
				} catch (InterruptedException e) {}
				
				while(User.player.hp != 0){
					
					if(roundCount == 2 && getORIncRoundStage(false)==2 ){
						battleStart = false;
						Platform.runLater(()->{
							monThread.stop();
							round.setText("Round "+new Integer(getORIncRoundStage(false)).toString());

							if(MapController.map.equals("canyon")){
								monster = Monster.whoAmI("Monster3");		
							}else if(MapController.map.equals("canyon2")){
								monster = Monster.whoAmI("Monster1");
							}else if(MapController.map.equals("canyon3")){
								monster = Monster.whoAmI("Monster2");
							}		//canyon
							else if(MapController.map.equals("rocky")){
								monster = Monster.whoAmI("Monster7");		
							}else if(MapController.map.equals("rocky2")){
								monster = Monster.whoAmI("Monster5");		
							}else if(MapController.map.equals("rocky3")){
								monster = Monster.whoAmI("Monster6");		
							}		//rocky
							else if(MapController.map.equals("derelict")){
								monster = Monster.whoAmI("Monster10");		
							}else if(MapController.map.equals("derelict2")){
								monster = Monster.whoAmI("Monster11");		
							}		//delel
							else if(MapController.map.equals("ice")){
								monster = Monster.whoAmI("Monster14");		
							}else if(MapController.map.equals("ice2")){
								monster = Monster.whoAmI("Monster14");		
							}else if(MapController.map.equals("ice3")){
								monster = Monster.whoAmI("Monster15");		
							}		//ice
							else if(MapController.map.equals("near")){
								monster = Monster.whoAmI("Monster18");		
							}else if(MapController.map.equals("near2")){
								monster = Monster.whoAmI("Monster19");		
							}else if(MapController.map.equals("near3")){
								monster = Monster.whoAmI("Monster20");		
							}		//near
							else if(MapController.map.equals("final")){
								monster = Monster.whoAmI("Monster21");		
							}else if(MapController.map.equals("final2")){
								monster = Monster.whoAmI("Monster25");		
							}		//final
							
							comHpBar.setStyle("-fx-accent: red;");
							comAtk.setText(new Integer(monster.atk).toString());
							comDef.setText(new Integer(monster.def).toString());
							comHp.setText(new Integer(monster.hp).toString());
							comLv.setText(new Integer(monster.lev).toString());
							comName.setText(monster.name);
							comImg.setImage(new Image(getClass().getResource(monster.imgSrc).toString()));
							monThread = new Thread(monAttack);	
							monThread.start();
						});
						
						myHpBar();
						comHpBar();
						
						roundCount++;
					}else if(roundCount ==3 && getORIncRoundStage(false)==3){
						battleStart = false;
						
						Platform.runLater(()->{
							monThread.stop();
							round.setText("Round "+new Integer(getORIncRoundStage(false)).toString());

							if(MapController.map.equals("canyon")){
								monster = Monster.whoAmI("Monster1");		
							}else if(MapController.map.equals("canyon2")){
								monster = Monster.whoAmI("Monster2");
							}else if(MapController.map.equals("canyon3")){
								monster = Monster.whoAmI("Monster4");
							}		//canyon
							else if(MapController.map.equals("rocky")){
								monster = Monster.whoAmI("Monster5");		
							}else if(MapController.map.equals("rocky2")){
								monster = Monster.whoAmI("Monster6");		
							}else if(MapController.map.equals("rocky3")){
								monster = Monster.whoAmI("Monster8");		
							}		//rocky
							else if(MapController.map.equals("derelict")){
								monster = Monster.whoAmI("Monster11");		
							}else if(MapController.map.equals("derelict2")){
								monster = Monster.whoAmI("Monster12");		
							}		//delel
							else if(MapController.map.equals("ice")){
								monster = Monster.whoAmI("Monster15");		
							}else if(MapController.map.equals("ice2")){
								monster = Monster.whoAmI("Monster15");		
							}else if(MapController.map.equals("ice3")){
								monster = Monster.whoAmI("Monster16");		
							}		//ice
							else if(MapController.map.equals("near")){
								monster = Monster.whoAmI("Monster19");		
							}else if(MapController.map.equals("near2")){
								monster = Monster.whoAmI("Monster20");		
							}else if(MapController.map.equals("near3")){
								monster = Monster.whoAmI("Monster22");		
							}		//near
							else if(MapController.map.equals("final")){
								monster = Monster.whoAmI("Monster23");		
							}else if(MapController.map.equals("final2")){
								monster = Monster.whoAmI("Monster26");		
							}		//final
							
							if(MapController.bossMap){		//보스 등장 이벤트
								Platform.runLater(()->{
									setTexting(true);
									battleActionSet = "BOSS몹 등장!!\n";
									isBossLabel.setVisible(true);
									setTexting(false);
								});
							}
							comHpBar.setStyle("-fx-accent: red;");
							comAtk.setText(new Integer(monster.atk).toString());
							comDef.setText(new Integer(monster.def).toString());
							comHp.setText(new Integer(monster.hp).toString());
							comLv.setText(new Integer(monster.lev).toString());
							comName.setText(monster.name);
							comImg.setImage(new Image(getClass().getResource(monster.imgSrc).toString()));
							monThread = new Thread(monAttack);	
							monThread.start();
						});
						
						myHpBar();
						comHpBar();
					
						break;
					}
				}
			}
			
		});
		
		
			roundTh.start();
		

		
			btnTool.setOnAction(event->{			//도구 버튼
				
				Popup popup = new Popup();
				try {
					toolPr = FXMLLoader.load(getClass().getResource("tool.fxml"));
				} catch (Exception e) {}
				popup.getContent().add(toolPr);
				popup.setAutoHide(true);
				

				primaryStage = (Stage)myAtk.getScene().getWindow();
				
				
				btnHpTool = (Button) toolPr.lookup("#useHpTool");
				btnMpTool = (Button) toolPr.lookup("#useMpTool");
				
				 hpToolGrade = (Label) toolPr.lookup("#hpToolGrade");
				 hpToolQuan = (Label) toolPr.lookup("#hpToolQuan");
				 mpToolGrade = (Label) toolPr.lookup("#mpToolGrade");
				 mpToolQuan = (Label) toolPr.lookup("#mpToolQuan");
				  
				Iterator<Item> itr = User.player.inven.iterator();
				poth = new Item();
				while(itr.hasNext()){
					poth = itr.next();
					if(poth != null){
						
							if(poth.name.equals("HP Potion")){
								
									hpToolGrade.setText(new Integer(poth.grade).toString());
									hpToolQuan.setText(new Integer(poth.quantity).toString());
								
							}else if(poth.name.equals("MP Potion")){
									
									mpToolGrade.setText(new Integer(poth.grade).toString());
									mpToolQuan.setText(new Integer(poth.quantity).toString());
								
							}
						
					}
				}
				
				Button useHpTool = (Button) toolPr.lookup("#useHpTool");
				Button useMpTool = (Button) toolPr.lookup("#useMpTool");
	
				useHpTool.setOnAction(eventHp->{		//HP 포션 사용
					usePotion("HP Potion");
					
				});
				
				useMpTool.setOnAction(eventMp->{		//MP 포션 사용
					usePotion("MP Potion");
				});
				popup.show(primaryStage);		
			
		});
		

		
		btnOut.setOnAction(event->{		//나가기 버튼
			
			monThread.stop();
			roundTh.stop();
			primaryStage = (Stage)myAtk.getScene().getWindow();
			primaryStage.close();
			
			FXMLLoader outLoader = new FXMLLoader(getClass().getResource("map.fxml"));
			Parent parentOut = null;
			try {
				parentOut = outLoader.load();
			} catch (IOException ex) {
			}
			Scene scene = new Scene(parentOut);
			primaryStage.setScene(scene); // 화면이동
			primaryStage.show();
		});
		
		btnOut.setOnKeyPressed(event->{		//키보드 입력
			battleStart = true;
			if((event.getCode()==KeyCode.DIGIT1 || event.getCode()==KeyCode.NUMPAD1) && !btnAttack.isDisabled()){
				if(!attacking){
					monsterAttack(User.player.atkItem);
				}
			}
			else if((event.getCode()==KeyCode.DIGIT2 || event.getCode()==KeyCode.NUMPAD2) && !btnTool.isDisabled()){
				Popup popup = new Popup();
				try {
					toolPr = FXMLLoader.load(getClass().getResource("tool.fxml"));
				} catch (Exception e) {}
				popup.getContent().add(toolPr);
				popup.setAutoHide(true);
				

				primaryStage = (Stage)myAtk.getScene().getWindow();
				
				
				btnHpTool = (Button) toolPr.lookup("#useHpTool");
				btnMpTool = (Button) toolPr.lookup("#useMpTool");
				
				 hpToolGrade = (Label) toolPr.lookup("#hpToolGrade");
				 hpToolQuan = (Label) toolPr.lookup("#hpToolQuan");
				 mpToolGrade = (Label) toolPr.lookup("#mpToolGrade");
				 mpToolQuan = (Label) toolPr.lookup("#mpToolQuan");
				  
				Iterator<Item> itr = User.player.inven.iterator();
				poth = new Item();
				while(itr.hasNext()){
					poth = itr.next();
					if(poth != null){
						
							if(poth.name.equals("HP Potion")){
								
									hpToolGrade.setText(new Integer(poth.grade).toString());
									hpToolQuan.setText(new Integer(poth.quantity).toString());
								
							}else if(poth.name.equals("MP Potion")){
									
									mpToolGrade.setText(new Integer(poth.grade).toString());
									mpToolQuan.setText(new Integer(poth.quantity).toString());
								
							}
						
					}
				}
				
				Button useHpTool = (Button) toolPr.lookup("#useHpTool");
				Button useMpTool = (Button) toolPr.lookup("#useMpTool");
	
				useHpTool.setOnAction(eventHp->{		//HP 포션 사용
					usePotion("HP Potion");
				});
				
				useMpTool.setOnAction(eventMp->{		//MP 포션 사용
					usePotion("MP Potion");
				});
				popup.show(primaryStage);
			}					//포션 버튼
			else if(event.getCode()==KeyCode.DIGIT3 || event.getCode()==KeyCode.NUMPAD3){
				monThread.stop();
				roundTh.stop();
				primaryStage = (Stage)myAtk.getScene().getWindow();
				primaryStage.close();
				
				FXMLLoader outLoader = new FXMLLoader(getClass().getResource("map.fxml"));
				Parent parentOut = null;
				try {
					parentOut = outLoader.load();
				} catch (IOException ex) {
				}
				Scene scene = new Scene(parentOut);
				primaryStage.setScene(scene); // 화면이동
				primaryStage.show();
			}		//나가기 버튼
			
			
		});

		
		btnAttack.setOnAction(event->{		//공격버튼
			battleStart = true;
			if(!attacking){
				monsterAttack(User.player.atkItem);
			}
			Platform.runLater(()->{
				btnOut.requestFocus();
			});
		});
		
		
	}
	
	void dropItemEvent(Monster monster){	//아이템 드랍 이벤트 메소드

		Random rand = new Random();
		int rate = rand.nextInt(100);
		primaryStage = (Stage)myAtk.getScene().getWindow();
		dia = new Stage(StageStyle.UNDECORATED);
		dia.initModality(Modality.WINDOW_MODAL);
		dia.initOwner(primaryStage);
		dia.setOpacity(0.8);
				try {
					dropPr = FXMLLoader.load(getClass().getResource("drop.fxml"));
				} catch (Exception e) {}
				
		ImageView dropItemImg = (ImageView)dropPr.lookup("#dropItemImg");
		Button btnGet = (Button) dropPr.lookup("#btnGet");
		Button btnThrow = (Button) dropPr.lookup("#btnThrow");
		Platform.runLater(() -> {
			dropItemImg.setImage(new Image(getClass().getResource(monster.dropItem.src).toString()));
		});
				Scene scene = new Scene(dropPr);
				dia.setScene(scene);
				if(rate < monster.dropRate){
					dia.show();
					btnGet.setOnAction(dropgetevent->{
						
							int index = 0;
							Iterator<Item> itr = User.player.inven.iterator();
							Item thisItem = null;
							
							while(itr.hasNext()){
								thisItem = itr.next();
								
								if(thisItem == null){	
									User.player.inven.set(index, monster.dropItem);
									dia.close();
									return;
								}
								
								index++;
							}
						
					});
				
		
					btnThrow.setOnAction(dropthrowevent->{
						dia.close();
					});
				}
		
	}				//아이템 드랍 메소드
	
	void myHpBar(){
		Platform.runLater(() -> {			
			myHp.setText(new Integer(User.player.hp).toString());
			Task myHpTask = new Task<Integer>() {
				protected Integer call() throws Exception {
				updateProgress(User.player.hp, User.player.maxHp);
					return null;
				}
			};
			myHpBar.progressProperty().bind(myHpTask.progressProperty());
			Thread myHpThread = new Thread(myHpTask);
			myHpThread.start(); // my hp Bar
		});
	}
	
	
	
	void comHpBar(){
		Platform.runLater(() -> {
			comHp.setText(new Integer(monster.hp).toString());
			Task comHpTask = new Task<Integer>() {
				protected Integer call() throws Exception {
				updateProgress((monster.hp <= 0) ? 0 : monster.hp, monster.maxHp);
					return null;
				}
			};
			comHpBar.progressProperty().bind(comHpTask.progressProperty());
			Thread comHpThread = new Thread(comHpTask);
			comHpThread.start(); // com hp Bar
		});
	}
	
	
	Runnable monAttack = new Runnable(){		//몬스터 스레드
		
		@Override
		public void run() {
			userDmg = 0;
			
			while( (monster.hp > 0) && (User.player.hp > 0)){	
				userDmg = monster.atk - (User.player.defItem.def + User.player.dex);
				
				if(!monster.skilled && ((int)(Math.random()*100) <= monster.skillRate) && (battleStart) ){		//스킬 발동
					monster.skilled = true;
					userDmg = monster.skillDmg - (User.player.defItem.def + User.player.dex);
					Platform.runLater(() -> {
						
						setTexting(true);
						battleActionSet = monster.name+"(가)이 "+monster.skillName+"(을)를 시전했습니다.\n";
						textAction.appendText(battleActionSet);		
						setTexting(false);
						
					});
				}
				if(battleStart){
					myHpBar();
				}
				
				while(true){
					
					if( (((int)(Math.random()*8)==7) || ( 0 >= userDmg )) && (battleStart) && !getTexting()){//회피
						setTexted(true);
						try {Thread.sleep(1000);} catch (InterruptedException e) {}
						Platform.runLater(() -> {
							battleActionSet = monster.name+"의 공격을 회피 했습니다.\n";
							textAction.appendText(battleActionSet);
						});
					}else if(battleStart && !getTexting()){			//피격
						setTexted(true);
						try {Thread.sleep(1000);} catch (InterruptedException e) {}
					User.player.hp = ((User.player.hp-userDmg)<=0)? 0 : User.player.hp-userDmg;
					
						Platform.runLater(()->{
							
							battleActionSet = monster.name+"에게  "+userDmg+"의 피해를 입었습니다.\n";
							textAction.appendText(battleActionSet);
						});
											
					}
					if(getTexted()){
						setTexted(false);
						break;
					}
				}
				
				if((MapController.bossMap)&&(roundStage >= 3) && flagPattern && ( (((double)monster.hp/monster.maxHp)*100)<50.0) ){
					
					flagPattern = false;
					Platform.runLater(()->{
					primaryStage = (Stage)myAtk.getScene().getWindow();
					dia = new Stage(StageStyle.UNDECORATED);
					dia.initModality(Modality.WINDOW_MODAL);
					dia.initOwner(primaryStage);
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("pattern.fxml"));	
					try {
						patPr = loader.load();
					} catch (Exception e) {
						
					}
					
					Scene scene = new Scene(patPr);
					dia.setScene(scene);
					
					comPat = (Label) patPr.lookup("#comPat");
					comPat.setText(monster.pattern);
					userPat = (Label) patPr.lookup("#userPat");
					userPat.setText(User.player.stack);
					btnQ = (Button)patPr.lookup("#btnQ");
					btnReset = (Button)patPr.lookup("#btnReset");
					patBackImg = (ImageView)patPr.lookup("#patBackImg");
					count = (Label)patPr.lookup("#count");
					
					dia.show();
					
					monster.pattern(dia,monster, primaryStage, userPat,textAction, btnQ, btnReset,patBackImg,count);
					
					});
					
				}
				
			}
		
			if(User.player.hp==0){		//user 죽음	lose
				battleStart = false;
				Platform.runLater(() -> {
					battleActionSet = "패배...\n";
					textAction.appendText(battleActionSet);
					
					User.player.hp = 1;	
					
					btnAttack.setDisable(true);	
					btnTool.setDisable(true);
	
				});
			}else{						//user 승리 	win		아이템 경험치 추후에 수정
				
				Platform.runLater(() -> {
					battleActionSet = "승리~~!\n";
					textAction.appendText(battleActionSet);
				});
				
				User.player.exp += monster.exp;
				User.player.gold += ((int)(monster.maxHp/User.player.lev)<=0)? monster.lev : (int)(monster.maxHp/User.player.lev);
				if(User.player.exp >= User.player.maxExp){	//레벨업
					Platform.runLater(() -> {
						battleActionSet = "레벨 업~~!\n";
						textAction.appendText(battleActionSet);

					});
					
						User.player.hp = User.player.maxHp;
						User.player.mp = User.player.maxMp;

					User.player.lev++;
					User.player.maxExp = User.player.lev*User.player.lev*70;
					User.player.exp = 0;
					User.player.point += 5;
				}
				
				
				
				User.player.quest.questAction(monster);
				Platform.runLater(() -> {
					
					if(roundStage>3){
						btnAttack.setDisable(true);	
						btnTool.setDisable(true);
					}	
					if(monster.dropRate>0)
					dropItemEvent(monster);
				});
				getORIncRoundStage(true);
				
			}
			
			myHpBar();
			
		}
		
		
	};
	
	synchronized void usePotion(String potionName){
		int index = 0;
		Iterator<Item> itr = User.player.inven.iterator();
		pothio = new Item();
		while(itr.hasNext()){
			pothio = itr.next();
			
			if(pothio != null){
				if(pothio.name.equals("HP Potion")&& potionName.equals("HP Potion") && pothio.quantity>0){		//use hp물약
					pothio.quantity--;
					Item.itemSetUp(pothio);
					User.player.hp = ((User.player.hp+pothio.effect)>User.player.maxHp)? User.player.maxHp : (User.player.hp+pothio.effect) ;
					
						hpToolGrade.setText(new Integer(pothio.grade).toString());
						hpToolQuan.setText(new Integer(pothio.quantity).toString());
				
					myHpBar();
					if(pothio.quantity==0){
						User.player.inven.set(index, null);
						
							hpToolGrade.setText("0");
							hpToolQuan.setText("0");
						
					}
					break;
				}else if(pothio.name.equals("MP Potion")&& potionName.equals("MP Potion") && pothio.quantity>0){
					pothio.quantity--;
					Item.itemSetUp(pothio);
					User.player.mp = ((User.player.mp+pothio.effect)>User.player.maxMp)? User.player.maxMp : (User.player.mp+pothio.effect) ;
					
						mpToolGrade.setText(new Integer(pothio.grade).toString());
						mpToolQuan.setText(new Integer(pothio.quantity).toString());

					if(pothio.quantity==0){
						User.player.inven.set(index, null);
						
							mpToolGrade.setText("0");
							mpToolQuan.setText("0");
						
					}
					break;
				}
				
			}
			index++;
		}
	}
	
	void monsterAttack(Item ability){
		attacking = true;
		comDmg = 0;	
		if(!ability.specialAbility(ability,monster)){
			comAttackImg.setImage(new Image(getClass().getResource("../reImages/at.gif").toString()));
			comDmg = User.player.str + User.player.atkItem.atk - monster.def;
		}
		else{		//스킬발동
			comAttackImg.setImage(new Image(getClass().getResource(User.player.atkItem.abilityEffect).toString()));
			comDmg = ability.abilityAtk + User.player.str + User.player.atkItem.atk - monster.def;
			
			Platform.runLater(()->{
				
				setTexting(true);
				battleActionSet = " @ 아이템 특수능력 "+ability.abilityName+"(이)가 발동 되었습니다.\n";
				textAction.appendText(battleActionSet);
				setTexting(false);
				
			});
		}
		
		
		primaryStage = (Stage)myAtk.getScene().getWindow();
		while(true){
			
			if(  0 >= comDmg  && !getTexting()){//회피
				
				Platform.runLater(()->{
					battleActionSet = monster.name+"(이)가 Player의 공격을 회피 했습니다.\n";
					setTexting(true);
					textAction.appendText(battleActionSet);
					setTexting(false);
					setTexted(true);
				});
					Thread th = new Thread(new Runnable(){
	
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
								
								Platform.runLater(()->{
									comAttackImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
								});
								attacking = false;
							} catch (InterruptedException e) {}
							
						}
						
					});
									
					th.start();
		
			}else if(!getTexting()){			//공격

				Platform.runLater(()->{
					monster.hp = ((monster.hp-comDmg) <= 0) ? 0 : monster.hp-comDmg;	
					battleActionSet = "Player는 "+monster.name+"에게  "+comDmg+"의 피해를 입혔습니다.\n";
					setTexting(true);
					textAction.appendText(battleActionSet);
					setTexting(false);
					setTexted(true);
				});
				
				Thread th = new Thread(new Runnable(){
	
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						
							Platform.runLater(()->{
								comAttackImg.setImage(new Image(getClass().getResource("../reImages/empty.png").toString()));
							});
							attacking = false;
						} catch (InterruptedException e) {}
						
					}
					
				});
								
				th.start();
				
			}
			if(getTexted()){
				setTexted(false);
				break;
			}
			
		}
		
		comHpBar();

		
	}
	
}


