package rpg;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import Item.*;
import User.User;
import User.Warrior;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.*;
import javafx.stage.*;

public class TownController implements Initializable {
	@FXML Button btnPlay;
	@FXML Button btnStop;
	@FXML Button developer;
	@FXML Button btnRest;
	@FXML Button btnStore;
	@FXML Button btnOutside;
	@FXML Button btnInfo;
	@FXML Button btnInven;
	@FXML Button btnHall;
	@FXML ScrollPane scrollPane;
	int epiScene = 0;
	Parent buyPr = null;
	Parent upgradePr = null;
	Parent sellPr = null;
	Boolean stopFlag = false;
	Media media = new Media(getClass().getResource("../Audio/tnt.wav").toString());
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	private Stage primaryStage;
	Stage dia = new Stage(StageStyle.UNDECORATED);
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		if(!Episode.EPISODE1){
			Platform.runLater(() -> {
			Stage episodeDia = new Stage(StageStyle.UNDECORATED);
			primaryStage = (Stage) btnStore.getScene().getWindow();
			episodeDia.initModality(Modality.WINDOW_MODAL);
			episodeDia.initOwner(primaryStage);
			epiScene = 0;
			Parent episodePr = null;
			
			try {
				episodePr = FXMLLoader.load(getClass().getResource("episode.fxml"));
				Scene scene = new Scene(episodePr);
				episodeDia.setScene(scene);
				episodeDia.show();		
			}catch(Exception e){}
			

			Button episodeOut = (Button) episodePr.lookup("#epiOut");
			
			Button episodeNext = (Button) episodePr.lookup("#epiBtn");
			ImageView epiLeftImg = (ImageView) episodePr.lookup("#epiLeftImg");
			ImageView epiRightImg = (ImageView) episodePr.lookup("#epiRightImg");
			ImageView epiBackImg = (ImageView) episodePr.lookup("#epiBackImg");
			TextArea epiText = (TextArea) episodePr.lookup("#epiText");
			epiText.setEditable(false);
			epiText.setWrapText(true);
			
			episodeNext.setOnAction(epi -> {
				epiScene++;
				User.player.quest.episode(epiScene,epiLeftImg,epiRightImg,epiBackImg,epiText,episodeDia);
				
			});
			
			
			episodeOut.setOnAction(stopevent -> {
				Platform.runLater(() -> {
					episodeDia.close();
				});
			});
			});
			
		}
		
		dia.initModality(Modality.WINDOW_MODAL);

		bgmPlay(null); // ���� bgm����

		if (!stopFlag) { // �ݺ����
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					mediaPlayer.stop();
					bgmPlay(null);
				}
			});
		} // ����� ���� ����

		//-----------------------------------��ư �̺�Ʈ----------------------------------------------
		
		Platform.runLater(() -> {
			btnHall.setOnAction(hallevent->{		//����Ʈ Ȧ ��ư �̺�Ʈ
				Episode.EPISODE1 = true;
				Stage hallDia = new Stage(StageStyle.UNDECORATED);
				primaryStage = (Stage) btnHall.getScene().getWindow();
				hallDia.initModality(Modality.WINDOW_MODAL);
				hallDia.initOwner(primaryStage);
				
				Parent hallPr = null;

				try {
					hallPr = FXMLLoader.load(getClass().getResource("hall.fxml"));
					Scene scene = new Scene(hallPr);
					hallDia.setScene(scene);
					hallDia.show();		
				}catch(Exception e){}
				
				Button hallOut = (Button) hallPr.lookup("#exit");
				Button complete = (Button) hallPr.lookup("#success");
				TextArea contents = (TextArea) hallPr.lookup("#contents");
				contents.setEditable(false);
				contents.setText(User.player.quest.contents);
				contents.setWrapText(true);
				
				
				if(Episode.EPISODE1 && (User.player.quest.goal < User.player.quest.maxGoal)){
					complete.setVisible(false);
				}
				
				complete.setOnAction(completeevent->{
					User.player.quest.reward();
					User.player.hp = User.player.maxHp;
					User.player.point += 5;

					hallDia.close();
				});
				
				hallOut.setOnAction(cancelevent -> {
					hallDia.close();
				}); // ��� ��ư
			});							//����Ʈ Ȧ ��ư �̺�Ʈ ��
						
			btnStore.setOnAction(event->{
				Stage storeDia = new Stage(StageStyle.UNDECORATED);
				primaryStage = (Stage) btnStore.getScene().getWindow();
				storeDia.initModality(Modality.WINDOW_MODAL);
				storeDia.initOwner(primaryStage);
				
				Parent storePr = null;
				
				try {
					storePr = FXMLLoader.load(getClass().getResource("store.fxml"));
					Scene scene = new Scene(storePr);
					storeDia.setScene(scene);
					storeDia.show();		
				}catch(Exception e){}
				
				Button storeOut = (Button) storePr.lookup("#storeOut");
				Button btnBuy = (Button) storePr.lookup("#btnBuy");
				Button btnSell = (Button) storePr.lookup("#btnSell");
				Button btnGradeUp = (Button) storePr.lookup("#btnGradeUp");
				
				btnSell.setOnAction(sellevent->{				//���� �ȱ� â
					primaryStage = (Stage) btnBuy.getScene().getWindow();
					Stage sellDia = new Stage(StageStyle.UNDECORATED);
					sellDia.initModality(Modality.WINDOW_MODAL);
					sellDia.initOwner(primaryStage);
					
					try {
						sellPr = FXMLLoader.load(getClass().getResource("sell.fxml"));
					} catch (Exception e) {}
					
					Scene sellScene = new Scene(sellPr);
					sellDia.setScene(sellScene);
					sellDia.show();
					Button btnExit = (Button) sellPr.lookup("#exit");		//������ ��ư
					Button btnItemSell[] = new Button[12];					//9���� �ȱ� ��ư
					Label nameItem[] = new Label[12];					//9���� ������ �̸� ��
					Label gradeItem[] = new Label[12];					//9���� ������ ��� ��
					Label costItem[] = new Label[12];					//9���� ������ ��� ��
					ImageView imgItem[] = new ImageView[12];			//9���� ������ �̹���
					
					for(int i = 0 ; i < 12 ; i++){
						btnItemSell[i] = (Button) sellPr.lookup("#btnSell"+(i+1));	/*��ȭ ��ư id*/
						nameItem[i] = (Label) sellPr.lookup("#sellItemName"+(i+1));	/*������ �̸� ��id*/
						gradeItem[i] = (Label) sellPr.lookup("#sellItemGrade"+(i+1));	/*������ ��� ��id*/
						costItem[i] = (Label) sellPr.lookup("#sellItemCost"+(i+1));	/*������ ��� ��id*/
						imgItem[i] = (ImageView) sellPr.lookup("#sellItemImg"+(i+1)); /*������ �̹��� id*/
						if(User.player.inven.get(i)==null){	//��� ���� �� ��ư ��Ȱ��
							imgItem[i].setImage(new Image(getClass().getResource("../reImages/invenEmpty.jpg").toString()));
							btnItemSell[i].setVisible(false);
						}else{
							nameItem[i].setText(User.player.inven.get(i).name);
							gradeItem[i].setText("GRADE : "+new Integer(User.player.inven.get(i).grade).toString());
							costItem[i].setText("COST : "+new Integer(User.player.inven.get(i).cost).toString());
							imgItem[i].setImage(new Image(getClass().getResource(User.player.inven.get(i).src).toString()));
						}
					}
					
					btnItemSell[0].setOnAction(sellgradeevent1->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(0),0,imgItem[0],nameItem[0],gradeItem[0],costItem[0],btnItemSell[0]);
					});
					btnItemSell[1].setOnAction(sellgradeevent2->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(1),1,imgItem[1],nameItem[1],gradeItem[1],costItem[1],btnItemSell[1]);
					});
					btnItemSell[2].setOnAction(sellgradeevent3->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(2),2,imgItem[2],nameItem[2],gradeItem[2],costItem[2],btnItemSell[2]);
					});
					btnItemSell[3].setOnAction(sellgradeevent4->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(3),3,imgItem[3],nameItem[3],gradeItem[3],costItem[3],btnItemSell[3]);
					});
					btnItemSell[4].setOnAction(sellgradeevent5->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(4),4,imgItem[4],nameItem[4],gradeItem[4],costItem[4],btnItemSell[4]);
					});
					btnItemSell[5].setOnAction(sellgradeevent6->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(5),5,imgItem[5],nameItem[5],gradeItem[5],costItem[5],btnItemSell[5]);
					});
					btnItemSell[6].setOnAction(sellgradeevent7->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(6),6,imgItem[6],nameItem[6],gradeItem[6],costItem[6],btnItemSell[6]);
					});
					btnItemSell[7].setOnAction(sellgradeevent8->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(7),7,imgItem[7],nameItem[7],gradeItem[7],costItem[7],btnItemSell[7]);
					});
					btnItemSell[8].setOnAction(sellgradeevent9->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(8),8,imgItem[8],nameItem[8],gradeItem[8],costItem[8],btnItemSell[8]);
					});
					btnItemSell[9].setOnAction(sellgradeevent9->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(9),9,imgItem[9],nameItem[9],gradeItem[9],costItem[9],btnItemSell[9]);
					});
					btnItemSell[10].setOnAction(sellgradeevent9->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(10),10,imgItem[10],nameItem[10],gradeItem[10],costItem[10],btnItemSell[10]);
					});
					btnItemSell[11].setOnAction(sellgradeevent9->{			//�Ǹ� ��ư Ŭ�� �̺�Ʈ
						sellItemMethod(User.player.inven.get(11),11,imgItem[11],nameItem[11],gradeItem[11],costItem[11],btnItemSell[11]);
					});
					
					btnExit.setOnAction(buyexitevent->{	//��ȭâ �ݱ�
						Platform.runLater(() -> {
							sellDia.close();
						});
					});
				});								//���� �ȱ� â ��
				
				btnGradeUp.setOnAction(upgradeevent->{				//������ ��ȭ â
					Platform.runLater(() -> {
						primaryStage = (Stage) btnGradeUp.getScene().getWindow();
						Stage upgradeDia = new Stage(StageStyle.UNDECORATED);
						upgradeDia.initModality(Modality.WINDOW_MODAL);
						upgradeDia.initOwner(primaryStage);
						
						try {
							upgradePr = FXMLLoader.load(getClass().getResource("upgrade.fxml"));
						} catch (Exception e) {}
						
						Scene upGradeScene = new Scene(upgradePr);
						upgradeDia.setScene(upGradeScene);
						upgradeDia.show();
						Button btnExit = (Button) upgradePr.lookup("#exit");		//������ ��ư
						Button btnUpItemUp[] = new Button[12];					//9���� ��ȭ ��ư
						Label nameUpItem[] = new Label[12];					//9���� ������ �̸� ��
						Label gradeUpItem[] = new Label[12];					//9���� ������ ��� ��
						Label costUpItem[] = new Label[12];					//9���� ������ ��� ��
						ImageView imgItem[] = new ImageView[12];			//9���� ������ �̹���
						
						for(int i = 0 ; i < 12 ; i++){
							btnUpItemUp[i] = (Button) upgradePr.lookup("#btnUpgrade"+(i+1));	/*��ȭ ��ư id*/
							nameUpItem[i] = (Label) upgradePr.lookup("#upItemName"+(i+1));	/*������ �̸� ��id*/
							gradeUpItem[i] = (Label) upgradePr.lookup("#upItemGrade"+(i+1));	/*������ ��� ��id*/
							costUpItem[i] = (Label) upgradePr.lookup("#upItemCost"+(i+1));	/*������ ��� ��id*/
							imgItem[i] = (ImageView) upgradePr.lookup("#upItemImg"+(i+1)); /*������ �̹��� id*/
							if(User.player.inven.get(i)==null){	//��� ���� �� ��ư ��Ȱ��
								btnUpItemUp[i].setVisible(false);
							}else{
								nameUpItem[i].setText(User.player.inven.get(i).name);
								gradeUpItem[i].setText("GRADE : "+new Integer(User.player.inven.get(i).grade).toString());
								costUpItem[i].setText("COST : "+new Integer(User.player.inven.get(i).cost).toString());
								imgItem[i].setImage(new Image(getClass().getResource(User.player.inven.get(i).src).toString()));
							}
						}
						
						btnUpItemUp[0].setOnAction(upgradeevent1->{			//��ȭ ��ư Ŭ�� �̺�Ʈ
							
							upgradeItemMethod(0);
							gradeUpItem[0].setText("GRADE : "+new Integer(User.player.inven.get(0).grade).toString());
							costUpItem[0].setText("COST : "+new Integer(User.player.inven.get(0).cost).toString());
						});
						btnUpItemUp[1].setOnAction(upgradeevent2->{
							upgradeItemMethod(1);
							gradeUpItem[1].setText("GRADE : "+new Integer(User.player.inven.get(1).grade).toString());
							costUpItem[1].setText("COST : "+new Integer(User.player.inven.get(1).cost).toString());
						});
						btnUpItemUp[2].setOnAction(upgradeevent3->{
							upgradeItemMethod(2);
							gradeUpItem[2].setText("GRADE : "+new Integer(User.player.inven.get(2).grade).toString());
							costUpItem[2].setText("COST : "+new Integer(User.player.inven.get(2).cost).toString());
						});
						btnUpItemUp[3].setOnAction(upgradeevent4->{
							upgradeItemMethod(3);
							gradeUpItem[3].setText("GRADE : "+new Integer(User.player.inven.get(3).grade).toString());
							costUpItem[3].setText("COST : "+new Integer(User.player.inven.get(3).cost).toString());
						});
						btnUpItemUp[4].setOnAction(upgradeevent5->{
							upgradeItemMethod(4);
							gradeUpItem[4].setText("GRADE : "+new Integer(User.player.inven.get(4).grade).toString());
							costUpItem[4].setText("COST : "+new Integer(User.player.inven.get(4).cost).toString());
						});
						btnUpItemUp[5].setOnAction(upgradeevent6->{
							upgradeItemMethod(5);
							gradeUpItem[5].setText("GRADE : "+new Integer(User.player.inven.get(5).grade).toString());
							costUpItem[5].setText("COST : "+new Integer(User.player.inven.get(5).cost).toString());
						});
						btnUpItemUp[6].setOnAction(upgradeevent7->{
							upgradeItemMethod(6);
							gradeUpItem[6].setText("GRADE : "+new Integer(User.player.inven.get(6).grade).toString());
							costUpItem[6].setText("COST : "+new Integer(User.player.inven.get(6).cost).toString());
						});
						btnUpItemUp[7].setOnAction(upgradeevent8->{
							upgradeItemMethod(7);
							gradeUpItem[7].setText("GRADE : "+new Integer(User.player.inven.get(7).grade).toString());
							costUpItem[7].setText("COST : "+new Integer(User.player.inven.get(7).cost).toString());
						});
						btnUpItemUp[8].setOnAction(upgradeevent9->{
							upgradeItemMethod(8);
							gradeUpItem[8].setText("GRADE : "+new Integer(User.player.inven.get(8).grade).toString());
							costUpItem[8].setText("COST : "+new Integer(User.player.inven.get(8).cost).toString());
						});
						btnUpItemUp[9].setOnAction(upgradeevent9->{
							upgradeItemMethod(9);
							gradeUpItem[9].setText("GRADE : "+new Integer(User.player.inven.get(9).grade).toString());
							costUpItem[9].setText("COST : "+new Integer(User.player.inven.get(9).cost).toString());
						});
						btnUpItemUp[10].setOnAction(upgradeevent9->{
							upgradeItemMethod(10);
							gradeUpItem[10].setText("GRADE : "+new Integer(User.player.inven.get(10).grade).toString());
							costUpItem[10].setText("COST : "+new Integer(User.player.inven.get(10).cost).toString());
						});
						btnUpItemUp[11].setOnAction(upgradeevent9->{
							upgradeItemMethod(11);
							gradeUpItem[11].setText("GRADE : "+new Integer(User.player.inven.get(11).grade).toString());
							costUpItem[11].setText("COST : "+new Integer(User.player.inven.get(11).cost).toString());
						});
						
						
						btnExit.setOnAction(buyexitevent->{	//��ȭâ �ݱ�
							
								upgradeDia.close();
								
						});
	
					});
				});			//������ ��ȭ â ��
				
				btnBuy.setOnAction(buyevent->{				//���� ���� â
					Platform.runLater(() -> {
						primaryStage = (Stage) btnBuy.getScene().getWindow();
						
						Stage buyDia = new Stage(StageStyle.UNDECORATED);
						buyDia.initModality(Modality.WINDOW_MODAL);
						buyDia.initOwner(primaryStage);
						
						try {
							buyPr = FXMLLoader.load(getClass().getResource("buy.fxml"));
						} catch (Exception e) {}
						
						Scene buyScene = new Scene(buyPr);
						buyDia.setScene(buyScene);
						buyDia.show();
						
						Button btnExit = (Button) buyPr.lookup("#btnExit");		//������ ��ư
						Label myGold = (Label) buyPr.lookup("#myGold");		//���� ���� ��� id
						myGold.setText(new Integer(User.player.gold).toString());	//���� ��� ��
						Button ItemBuy[] = new Button[13];					//13���� ���� ��ư
						Label nameItem[] = new Label[13];					//13���� ������ �̸� ��
						ImageView imgItem[] = new ImageView[13];			//13���� ������ �̹���
						
						Item item[] = new Item[13];			//13���� ������ ��ü ����
						
						for(int i = 0 ; i < 13 ; i++){
							ItemBuy[i] = (Button) buyPr.lookup("#ItemBuy"+(i+1));	/*���� ��ư id*/
							nameItem[i] = (Label) buyPr.lookup("#nameItem"+(i+1));	/*������ �̸� ��id*/
							imgItem[i] = (ImageView) buyPr.lookup("#imgItem"+(i+1)); /*������ �̹��� id*/
							if(i >= 1){
								item[i] = randItem();	//11���� ������ ���� ��
								imgItem[i].setImage(new Image(getClass().getResource(item[i].src).toString()));	//11���� ������ �̹��� ��
								nameItem[i].setText(item[i].name);	/*11���� ������ �̸� ��*/
							}
						}
						
						ItemBuy[0].setOnAction(item1buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= Item.whoAmI("hpPotion").cost)//1,2���� ���� ����
								buyPotion(Item.whoAmI("hpPotion"));
							
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[1].setOnAction(item2buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[1].cost)
								buyItem(item[1]);
							myGold.setText(new Integer(User.player.gold).toString());
						});	
						ItemBuy[2].setOnAction(item3buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[2].cost)
								buyItem(item[2]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[3].setOnAction(item4buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[3].cost)
								buyItem(item[3]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[4].setOnAction(item5buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[4].cost)
								buyItem(item[4]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[5].setOnAction(item6buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[5].cost)
								buyItem(item[5]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[6].setOnAction(item7buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[6].cost)
								buyItem(item[6]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[7].setOnAction(item8buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[7].cost)
								buyItem(item[7]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[8].setOnAction(item9buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[8].cost)
								buyItem(item[8]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[9].setOnAction(item10buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[9].cost)
								buyItem(item[9]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[10].setOnAction(item11buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[10].cost)
								buyItem(item[10]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[11].setOnAction(item12buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[11].cost)
								buyItem(item[11]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						ItemBuy[12].setOnAction(item13buyevent->{				//������ ���� �̺�Ʈ
							if(User.player.gold >= item[12].cost)
								buyItem(item[12]);
							myGold.setText(new Integer(User.player.gold).toString());
						});
						
						nameItem[0].setOnMouseClicked(item1labelevent->{
							infBuyItem(buyPr,Item.whoAmI("hpPotion"));
						});		//1��° ������  �̸� Ŭ��
						imgItem[0].setOnMouseClicked(item1labelevent->{
							infBuyItem(buyPr,Item.whoAmI("hpPotion"));
						});		//1��° ������  ���� Ŭ��
						nameItem[1].setOnMouseClicked(item2labelevent->{
							infBuyItem(buyPr,item[1]);
						});		//2��° ������  �̸� Ŭ��
						imgItem[1].setOnMouseClicked(item2labelevent->{
							infBuyItem(buyPr,item[1]);
						});		//2��° ������  ���� Ŭ��
						nameItem[2].setOnMouseClicked(item3labelevent->{
							infBuyItem(buyPr,item[2]);
						});		//3��° ������  �̸� Ŭ��
						imgItem[2].setOnMouseClicked(item3labelevent->{
							infBuyItem(buyPr,item[2]);
						});		//3��° ������  ���� Ŭ��
						nameItem[3].setOnMouseClicked(item4labelevent->{
							infBuyItem(buyPr,item[3]);
						});		//4��° ������  �̸� Ŭ��
						imgItem[3].setOnMouseClicked(item4labelevent->{
							infBuyItem(buyPr,item[3]);
						});		//4��° ������  ���� Ŭ��
						nameItem[4].setOnMouseClicked(item5labelevent->{
							infBuyItem(buyPr,item[4]);
						});		//5��° ������  �̸� Ŭ��
						imgItem[4].setOnMouseClicked(item5labelevent->{
							infBuyItem(buyPr,item[4]);
						});		//5��° ������  ���� Ŭ��
						nameItem[5].setOnMouseClicked(item6labelevent->{
							infBuyItem(buyPr,item[5]);
						});		//6��° ������  �̸� Ŭ��
						imgItem[5].setOnMouseClicked(item6labelevent->{
							infBuyItem(buyPr,item[5]);
						});		//6��° ������  ���� Ŭ��
						nameItem[6].setOnMouseClicked(item7labelevent->{
							infBuyItem(buyPr,item[6]);
						});		//7��° ������  �̸� Ŭ��
						imgItem[6].setOnMouseClicked(item7labelevent->{
							infBuyItem(buyPr,item[6]);
						});		//7��° ������  ���� Ŭ��
						nameItem[7].setOnMouseClicked(item8labelevent->{
							infBuyItem(buyPr,item[7]);
						});		//8��° ������  �̸� Ŭ��
						imgItem[7].setOnMouseClicked(item8labelevent->{
							infBuyItem(buyPr,item[7]);
						});		//8��° ������  ���� Ŭ��
						nameItem[8].setOnMouseClicked(item9labelevent->{
							infBuyItem(buyPr,item[8]);
						});		//9��° ������  �̸� Ŭ��
						imgItem[8].setOnMouseClicked(item9labelevent->{
							infBuyItem(buyPr,item[8]);
						});		//9��° ������  ���� Ŭ��
						nameItem[9].setOnMouseClicked(item10labelevent->{
							infBuyItem(buyPr,item[9]);
						});		//10��° ������  �̸� Ŭ��
						imgItem[9].setOnMouseClicked(item10labelevent->{
							infBuyItem(buyPr,item[9]);
						});		//10��° ������  ���� Ŭ��
						nameItem[10].setOnMouseClicked(item11labelevent->{
							infBuyItem(buyPr,item[10]);
						});		//11��° ������  �̸� Ŭ��
						imgItem[10].setOnMouseClicked(item11labelevent->{
							infBuyItem(buyPr,item[10]);
						});		//11��° ������  ���� Ŭ��
						nameItem[11].setOnMouseClicked(item12labelevent->{
							infBuyItem(buyPr,item[11]);
						});		//12��° ������  �̸� Ŭ��
						imgItem[11].setOnMouseClicked(item12labelevent->{
							infBuyItem(buyPr,item[11]);
						});		//12��° ������  ���� Ŭ��
						nameItem[12].setOnMouseClicked(item13labelevent->{
							infBuyItem(buyPr,item[12]);
						});		//13��° ������  �̸� Ŭ��
						imgItem[12].setOnMouseClicked(item13labelevent->{
							infBuyItem(buyPr,item[12]);
						});		//13��° ������  ���� Ŭ��

						
						
						
						btnExit.setOnAction(buyexitevent->{	//����â �ݱ�
							Platform.runLater(() -> {
								buyDia.close();
							});
						});
					});
					
					
				});		//���� ���� â ��
				
				
				
				storeOut.setOnAction(cancelevent -> {
					Platform.runLater(() -> {
						storeDia.close();
					});
				}); // ��� ��ư
				
			});	//�ڷ��� ���� ��ư ����
			
			developer.setOnAction(event -> {
				Stage episodeDia = new Stage(StageStyle.UNDECORATED);
				primaryStage = (Stage) btnStore.getScene().getWindow();
				episodeDia.initModality(Modality.WINDOW_MODAL);
				episodeDia.initOwner(primaryStage);
				epiScene = 0;
				Parent episodePr = null;
				
				try {
					episodePr = FXMLLoader.load(getClass().getResource("episode.fxml"));
					Scene scene = new Scene(episodePr);
					episodeDia.setScene(scene);
					episodeDia.show();		
				}catch(Exception e){}
				
				Button episodeOut = (Button) episodePr.lookup("#epiOut");
				
				Button episodeNext = (Button) episodePr.lookup("#epiBtn");
				ImageView epiLeftImg = (ImageView) episodePr.lookup("#epiLeftImg");
				ImageView epiRightImg = (ImageView) episodePr.lookup("#epiRightImg");
				ImageView epiBackImg = (ImageView) episodePr.lookup("#epiBackImg");
				TextArea epiText = (TextArea) episodePr.lookup("#epiText");
				epiText.setEditable(false);
				epiText.setWrapText(true);
				
				episodeNext.setOnAction(epi -> {
					epiScene++;
					User.player.quest.episode(epiScene,epiLeftImg,epiRightImg,epiBackImg,epiText,episodeDia);
					
				});
				
				
				episodeOut.setOnAction(stopevent -> {
					Platform.runLater(() -> {
						episodeDia.close();
					});
				}); // stop rest
				
			}); // �ڷ��� ������ â������

			btnRest.setOnAction(event -> {
				dia = new Stage(StageStyle.UNDECORATED);
				primaryStage = (Stage) btnStore.getScene().getWindow();
				dia.initModality(Modality.WINDOW_MODAL);
				dia.initOwner(primaryStage);
				Parent restPr = null;
				try {
					btnRest.setDisable(true);
					restPr = FXMLLoader.load(getClass().getResource("rest.fxml"));
					Scene scene = new Scene(restPr);
					dia.setScene(scene);
					dia.show();
					Label restCost = (Label) restPr.lookup("#restCostLabel");
					restCost.setText(new Integer(User.player.lev * 20).toString()); // ������
																						// ��ʰ��
					Button goRest = (Button) restPr.lookup("#goRestBtn");
					Button stopRest = (Button) restPr.lookup("#stopRestBtn");

					goRest.setOnAction(restevent -> {

						Platform.runLater(() -> {
							try {
								if (User.player.gold >= User.player.lev * 20) {
									User.player.gold -= User.player.lev * 20;
									User.player.hp = User.player.maxHp;
									
								}
								btnRest.setDisable(false);
								dia.close();
							} catch (Exception e) {
							}
						});
					}); // go rest

					stopRest.setOnAction(stopevent -> {

						Platform.runLater(() -> {
							try {
								btnRest.setDisable(false);
								dia.close();
							} catch (Exception e) {
							}
						});
					}); // stop rest
				} catch (Exception e) {
					btnRest.setDisable(false);
				}
				btnRest.setDisable(false);
			}); // �ڷ��� ���� â������

			btnOutside.setOnAction(event -> {
				primaryStage = (Stage) btnOutside.getScene().getWindow();

				Platform.runLater(() -> {
					bgmStop(null);
					primaryStage.close();

					FXMLLoader outLoader = new FXMLLoader(getClass().getResource("map.fxml"));
					Parent parentOut = null;
					try {
						parentOut = outLoader.load();
					} catch (IOException ex) {
					}
					Scene scene = new Scene(parentOut);
					primaryStage.setScene(scene); // ȭ���̵�
					primaryStage.show();

				});

			}); // �̰� ���������� ��ư

			btnInfo.setOnAction(event -> {
				
				dia = new Stage(StageStyle.UNDECORATED);
				primaryStage = (Stage) btnStore.getScene().getWindow();
				dia.initModality(Modality.WINDOW_MODAL);
				dia.initOwner(primaryStage);
				
				Parent infoPr = null;
				
				try {

					btnInfo.setDisable(true);

					infoPr = FXMLLoader.load(getClass().getResource("info.fxml"));

					Scene scene = new Scene(infoPr);

					dia.setScene(scene);
				
					/*ComboBox skillBox = (ComboBox) infoPr.lookup("#skills");
					
					
						skillsList.clear();;
						for(int i = 0; i<User.player.skills.size() ;i++){
							 skillsList.add(User.player.skills.get("Skill"+(i+1)).name);
						}	
						skillBox.setItems(FXCollections.observableArrayList(skillsList));*/
					
					Label levLabel = (Label) infoPr.lookup("#userLevLabel");// user lev
					levLabel.setText(new Integer(User.player.lev).toString());
					Label strLabel = (Label) infoPr.lookup("#str");// user str
					strLabel.setText(new Integer(User.player.str).toString());
					Label dexLabel = (Label) infoPr.lookup("#dex");// user dex
					dexLabel.setText(new Integer(User.player.dex).toString());
					Label vitLabel = (Label) infoPr.lookup("#vit");// user vit
					vitLabel.setText(new Integer(User.player.vit).toString());
					
					Label pointLabel = (Label) infoPr.lookup("#point");// user
																		// point
					pointLabel.setText(new Integer(User.player.point).toString());
					// �ɷ�ġ�� ����Ʈ ��
					
					Label atkEffect = (Label) infoPr.lookup("#atkEffect");
					atkEffect.setText(User.player.atkItem.abilityName);

					Label atkGrade = (Label) infoPr.lookup("#atkGrade");// user
																		// wiz
					atkGrade.setText(new Integer(User.player.atkItem.grade).toString());
					Label atkName = (Label) infoPr.lookup("#atkName");// user
																		// wiz
					atkName.setText(User.player.atkItem.name);
					Label atk = (Label) infoPr.lookup("#atk");// user wiz
					atk.setText(new Integer(User.player.atkItem.atk).toString());
					ImageView atkImg = (ImageView) infoPr.lookup("#atkImg");
					atkImg.setImage(new Image(getClass().getResource(User.player.atkItem.src).toString()));
					// �������� atk Item�� ���,�ɷ�,�̸�,����
					
					Label defGrade = (Label) infoPr.lookup("#defGrade");// user
																		// wiz
					defGrade.setText(new Integer(User.player.defItem.grade).toString());
					Label defName = (Label) infoPr.lookup("#defName");// user
																		// wiz
					defName.setText(User.player.defItem.name);
					Label def = (Label) infoPr.lookup("#def");// user wiz
					def.setText(new Integer(User.player.defItem.def).toString());
					ImageView defImg = (ImageView) infoPr.lookup("#defImg");
					defImg.setImage(new Image(getClass().getResource(User.player.defItem.src).toString()));
					// �������� def Item�� ���,�ɷ�,�̸�,����

					Label questSubject = (Label) infoPr.lookup("#questSubject");// user
																				// wiz
					questSubject.setText(User.player.quest.name);
					TextArea questContents = (TextArea) infoPr.lookup("#questContents");// user
					questContents.setWrapText(true);
					questContents.setText(User.player.quest.contents);
					Label goal = (Label) infoPr.lookup("#goal");// user wiz
					goal.setText("���෮ " + new Integer(User.player.quest.goal).toString());
					// ����Ʈ ���� ���

					Label exp = (Label) infoPr.lookup("#exp");
					exp.setText(new Integer(User.player.exp).toString());
					Label maxExp = (Label) infoPr.lookup("#maxExp");
					maxExp.setText(new Integer(User.player.maxExp).toString());// exp
					Label hp = (Label) infoPr.lookup("#hp");
					hp.setText(new Integer(User.player.hp).toString());
					Label maxHp = (Label) infoPr.lookup("#maxHp");
					maxHp.setText(new Integer(User.player.maxHp).toString());// hp
					
					// ������ ����ġ,ü��,����
					
					ProgressBar expBar = (ProgressBar) infoPr.lookup("#expBar");
					expBar.setStyle("-fx-accent: green;");
					Task expTask = new Task<Integer>() {
						protected Integer call() throws Exception {
							updateProgress(User.player.exp, User.player.maxExp);
							return null;
						}
					};
					expBar.progressProperty().bind(expTask.progressProperty());
					Thread expThread = new Thread(expTask);
					expThread.start(); // expBar

					ProgressBar hpBar = (ProgressBar) infoPr.lookup("#hpBar");
					hpBar.setStyle("-fx-accent: red;");
					Task hpTask = new Task<Integer>() {
						protected Integer call() throws Exception {
							updateProgress(User.player.hp, User.player.maxHp);
							return null;
						}
					};
					hpBar.progressProperty().bind(hpTask.progressProperty());
					Thread hpThread = new Thread(hpTask);
					hpThread.start(); // hpBar


					Button strUPBtn = (Button) infoPr.lookup("#strUP");
					Button dexUPBtn = (Button) infoPr.lookup("#dexUP");
					Button vitUPBtn = (Button) infoPr.lookup("#vitUP");;
					Button cancelBtn = (Button) infoPr.lookup("#cancel");

					cancelBtn.setOnAction(cancelevent -> {
						Platform.runLater(() -> {
							expTask.cancel();
							hpTask.cancel();
							
							dia.close();
						});
					}); // ��� ��ư

					
					
					strUPBtn.setOnAction(strevent -> {
						Task<Void> task = new Task<Void>() {
							protected Void call() throws Exception {
								User.player.str++;
								User.player.point--;
								Platform.runLater(() -> {
									strLabel.setText(new Integer(User.player.str).toString());
									pointLabel.setText(new Integer(User.player.point).toString());
								});
								
								return null;
							}
						};

						Thread thread = new Thread(task);
						thread.setDaemon(true);
						if (User.player.point > 0){
							thread.start();
							
						}
					});// strUP
					dexUPBtn.setOnAction(dexevent -> {
						Task<Void> task = new Task<Void>() {
							protected Void call() throws Exception {
								User.player.dex++;
								User.player.point--;
								Platform.runLater(() -> {
									dexLabel.setText(new Integer(User.player.dex).toString());
									pointLabel.setText(new Integer(User.player.point).toString());
								});
								
								
								return null;
							}
						};

						Thread thread = new Thread(task);
						thread.setDaemon(true);
						if (User.player.point > 0)
							thread.start();
					});// dexUP
					vitUPBtn.setOnAction(vitevent -> {
						Task<Void> task = new Task<Void>() {
							protected Void call() throws Exception {
								User.player.vit++;
								User.player.point--;
								User.player.maxHp += 10;
								User.player.hp += 10;
								Platform.runLater(() -> {
									vitLabel.setText(new Integer(User.player.vit).toString());
									pointLabel.setText(new Integer(User.player.point).toString());
									maxHp.setText(new Integer(User.player.maxHp).toString());
									hp.setText(new Integer(User.player.hp).toString());
								});
								return null;
							}
						};

						Thread thread = new Thread(task);
						thread.setDaemon(true);
						if (User.player.point > 0)
							thread.start();
					});// vitUP
					

					// �ɷ�ġ �ø��� ��ư

				} catch (Exception e) {
				}
				dia.show();
				btnInfo.setDisable(false);

			}); // �ڷ��� ���� ��ư ���� ��.. ��Ƴ�..
			
			
			//invenDia�� �κ��丮 â
			//itemInfoDia�� ������ ����â   ->  �� â�� 3���� (��޿ɼ�)
			btnInven.setOnAction(eventInvven->{
				Stage invenDia = new Stage(StageStyle.UNDECORATED);
				primaryStage = (Stage) btnInven.getScene().getWindow();
				invenDia.initModality(Modality.WINDOW_MODAL);
				invenDia.initOwner(primaryStage);
				
				Parent invenPr = null;
				
				try {
					invenPr = FXMLLoader.load(getClass().getResource("inven.fxml"));
					Scene scene = new Scene(invenPr);
					invenDia.setScene(scene);
					invenDia.show();		//������� �κ�â �����ֱ�

					Button invenCancelBtn = (Button) invenPr.lookup("#invenCancelBtn");
					Label goldLabel = (Label) invenPr.lookup("#goldLabel");
					ImageView imgItem1 = (ImageView) invenPr.lookup("#item1");
					ImageView imgItem2 = (ImageView) invenPr.lookup("#item2");
					ImageView imgItem3 = (ImageView) invenPr.lookup("#item3");
					ImageView imgItem4 = (ImageView) invenPr.lookup("#item4");
					ImageView imgItem5 = (ImageView) invenPr.lookup("#item5");
					ImageView imgItem6 = (ImageView) invenPr.lookup("#item6");
					ImageView imgItem7 = (ImageView) invenPr.lookup("#item7");
					ImageView imgItem8 = (ImageView) invenPr.lookup("#item8");
					ImageView imgItem9 = (ImageView) invenPr.lookup("#item9");
					ImageView imgItem10 = (ImageView) invenPr.lookup("#item10");
					ImageView imgItem11 = (ImageView) invenPr.lookup("#item11");
					ImageView imgItem12 = (ImageView) invenPr.lookup("#item12");
					
					goldLabel.setText(new Integer(User.player.gold).toString());
					
					
					if(User.player.inven.get(0) != null){
						
						imgItem1.setImage(new Image(getClass().getResource(User.player.inven.get(0).src).toString()));
						
						imgItem1.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(0),0);
							
						});
					}
					
					if(User.player.inven.get(1) != null){
						imgItem2.setImage(new Image(getClass().getResource(User.player.inven.get(1).src).toString()));
						
						imgItem2.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(1),1);
							
						});
					}
					
					
					if(User.player.inven.get(2) != null){
						imgItem3.setImage(new Image(getClass().getResource(User.player.inven.get(2).src).toString()));
						
						imgItem3.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(2),2);
							
						});
					}
					
					if(User.player.inven.get(3) != null){
						imgItem4.setImage(new Image(getClass().getResource(User.player.inven.get(3).src).toString()));
						
						imgItem4.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(3),3);
							
						});
					}
					
					if(User.player.inven.get(4) != null){
						imgItem5.setImage(new Image(getClass().getResource(User.player.inven.get(4).src).toString()));
						
						imgItem5.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(4),4);
							
						});
					}
					
					if(User.player.inven.get(5) != null){
						imgItem6.setImage(new Image(getClass().getResource(User.player.inven.get(5).src).toString()));
						
						imgItem6.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(5),5);
							
						});
					}
					
					if(User.player.inven.get(6) != null){
						imgItem7.setImage(new Image(getClass().getResource(User.player.inven.get(6).src).toString()));
						
						imgItem7.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(6),6);
							
						});
					}
					
					if(User.player.inven.get(7) != null){
						imgItem8.setImage(new Image(getClass().getResource(User.player.inven.get(7).src).toString()));
						
						imgItem8.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(7),7);
							
						});
					}
					
					if(User.player.inven.get(8) != null){
						
						imgItem9.setImage(new Image(getClass().getResource(User.player.inven.get(8).src).toString()));
						
						imgItem9.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(8),8);
							
						});
					}
					
					if(User.player.inven.get(9) != null){
						
						imgItem10.setImage(new Image(getClass().getResource(User.player.inven.get(9).src).toString()));
						
						imgItem10.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(9),9);
							
						});
					}
					if(User.player.inven.get(10) != null){
						
						imgItem11.setImage(new Image(getClass().getResource(User.player.inven.get(10).src).toString()));
						
						imgItem11.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(10),10);
							
						});
					}
					if(User.player.inven.get(11) != null){
						
						imgItem12.setImage(new Image(getClass().getResource(User.player.inven.get(11).src).toString()));
						
						imgItem12.setOnMouseClicked(eventit-> {
							primaryStage = (Stage) invenCancelBtn.getScene().getWindow();
							itemInf(primaryStage,User.player.inven.get(11),11);
							
						});
					}
					
					
					
					invenCancelBtn.setOnAction(cancelevent -> {
						
						Platform.runLater(() -> {
							invenDia.close();
						});
					}); // ��� ��ư
					
				}catch(Exception e){}
				
			});
			
			
			
			
		});

	}// �̰� �̴ϼȶ����� �޼ҵ���

	void sellItemMethod(Item item,int index, ImageView imageView, Label label, Label label2, Label label3,
			Button button) {
		
		User.player.gold += item.cost;
		User.player.inven.set(index, null);
		
		imageView.setImage(new Image(getClass().getResource("../reImages/invenEmpty.jpg").toString()));
		label.setText("");
		label2.setText("");
		label3.setText("");
		button.setVisible(false);
		
	}

	void upgradeItemMethod(int index) {
		
		Random rand = new Random();
		int percent = rand.nextInt(100);
		if(User.player.gold >= User.player.inven.get(index).cost){
			if(User.player.inven.get(index).grade<=percent || User.player.inven.get(index).type.equals("potion")){		//��ȭ ����
				User.player.gold -= User.player.inven.get(index).cost;
				User.player.inven.get(index).grade++;
				Item.itemSetUp(User.player.inven.get(index));
			}else{									//��ȭ ����
				User.player.gold -=User.player.inven.get(index).cost;
				User.player.inven.get(index).grade--;
				Item.itemSetUp(User.player.inven.get(index));
			}
		}
	}			//��ȭ�޼���

	void buyPotion(Item item) {		//���� ���� �޼���
		int index = 0;
		Iterator<Item> itr = User.player.inven.iterator();
		Item thisItem = new Item();		//�� ������
		
		while(itr.hasNext()){
			thisItem = itr.next();
			
			if(thisItem == null){						//������ ���� ��
				User.player.gold -= item.cost;
				User.player.inven.set(index, item);
				return;
			}else if(thisItem.name.equals(item.name) && (thisItem.grade == item.grade)){	//���� ��� ������ �̹� ������ ���� ��
				User.player.gold -= item.cost;
				thisItem.quantity++;
				Item.itemSetUp(thisItem);
				return;
			}
			
			index++;
		}	
	}

	void buyItem(Item item) {		//������ ���� �޼���
		int index = 0;
		Iterator<Item> itr = User.player.inven.iterator();
		Item thisItem = new Item();
		
		while(itr.hasNext()){
			thisItem = itr.next();
			
			if(thisItem == null){
				User.player.gold -= item.cost;
				User.player.inven.set(index, item);
				return;
			}
			
			index++;
		}
		
	}

	void itemInf(Stage primaryStage,Item myItem,int index){

		Stage itemInfoDia = new Stage(StageStyle.UTILITY);
		itemInfoDia.initModality(Modality.WINDOW_MODAL);
		itemInfoDia.initOwner(primaryStage);
		
		Parent itemInfoPr = null;
		try {
			itemInfoPr = FXMLLoader.load(getClass().getResource("itemInfo.fxml"));
		} catch (Exception e) {}
		
		Scene itemInfoScene = new Scene(itemInfoPr);
		itemInfoDia.setScene(itemInfoScene);
		itemInfoDia.show();
		
		Label nameLabel = (Label) itemInfoPr.lookup("#itemName");	
		Label atkLabel = (Label) itemInfoPr.lookup("#itAtk");	
		Label defLabel = (Label) itemInfoPr.lookup("#itDef");	
		Label typeLabel = (Label) itemInfoPr.lookup("#itType");	
		Label gradeLabel = (Label) itemInfoPr.lookup("#itGrade");	
		Label costLabel = (Label) itemInfoPr.lookup("#itCost");	
		Label quantityLabel = (Label) itemInfoPr.lookup("#itQuantity");	
		Button equip = (Button) itemInfoPr.lookup("#equip");
		ImageView imgItem = (ImageView) itemInfoPr.lookup("#itImg");
		
		nameLabel.setText(myItem.name);
		atkLabel.setText(new Integer(myItem.atk).toString());
		defLabel.setText(new Integer(myItem.def).toString());
		typeLabel.setText(myItem.type);
		gradeLabel.setText(new Integer(myItem.grade).toString());
		costLabel.setText(new Integer(myItem.cost).toString());
		quantityLabel.setText(new Integer(myItem.quantity).toString());
		imgItem.setImage(new Image(getClass().getResource(myItem.src).toString()));
		
		if(myItem.type.equals("potion")){
			atkLabel.setVisible(false);
			defLabel.setVisible(false);
			equip.setVisible(false);	
		}else if(myItem.type.equals("atk") || myItem.type.equals("staff")){
			defLabel.setVisible(false);
		}else if(myItem.type.equals("def")){
			atkLabel.setVisible(false);
		}
		
		equip.setOnAction(event->{
			Item temp = myItem;
			if(myItem.type.equals("atk") || myItem.type.equals("staff")){
				User.player.inven.set(index, null);
				User.player.inven.set(index, User.player.atkItem);
				User.player.atkItem = temp;
				itemInfoDia.close();
				primaryStage.close();
			}else{
				User.player.inven.set(index, null);
				User.player.inven.set(index, User.player.defItem);
				User.player.defItem = temp;
				itemInfoDia.close();
				primaryStage.close();
			}
			
		});	//��� ����
		
	}	//������ ����â ��޿ɼ�
	
	public void bgmStop(ActionEvent e) {
		stopFlag = true;
		mediaPlayer.stop();
		btnPlay.setDisable(false);
		btnStop.setDisable(true);
	}

	public void bgmPlay(ActionEvent e) {
		stopFlag = false;
		mediaPlayer.play();
		btnPlay.setDisable(true);
		btnStop.setDisable(false);
	}
	
	void infBuyItem(Parent buyPr,Item item){
		Label infName = (Label) buyPr.lookup("#infName");
		Label infGrade = (Label) buyPr.lookup("#infGrade");
		Label infCost = (Label) buyPr.lookup("#infCost");
		Label infAtk = (Label) buyPr.lookup("#infAtk");
		Label infDef = (Label) buyPr.lookup("#infDef");
		infName.setText(item.name);
		infGrade.setText("GRADE : "+new Integer(item.grade).toString());
		infCost.setText("COST : "+new Integer(item.cost).toString());
		if(item.type.equals("atk")){
			infAtk.setText("ATK : "+new Integer(item.atk).toString());
			infDef.setText("DEF : ");
		}else if(item.type.equals("def")){
			infDef.setText("DEF : "+new Integer(item.def).toString());
			infAtk.setText("ATK : ");
		}else{
			infAtk.setText("ATK : ");
			infDef.setText("DEF : ");
		}
	}
	
	Item randItem(){
		Random rand = new Random();
		int atkCode;
		int defCode;
		
		while(true){
			atkCode =rand.nextInt(14)+1000;
			defCode =rand.nextInt(7)+2000;
			switch(atkCode){
				case ItemCode.Dagger:
					return Item.whoAmI("Sword");
				case ItemCode.atk2:
					return Item.whoAmI("atk2");
				case ItemCode.atk3:
					return Item.whoAmI("atk3");
				case ItemCode.atk4:
					return Item.whoAmI("atk4");
				case ItemCode.atk5:
					return Item.whoAmI("atk5");
				case ItemCode.atk6:
					return Item.whoAmI("atk6");
					
				//...
			}
			
			switch(defCode){
				case ItemCode.Clothes:
					return Item.whoAmI("Clothes");
				case ItemCode.def2:
					return Item.whoAmI("def2");
				case ItemCode.def3:
					return Item.whoAmI("def3");
				case ItemCode.def4:
					return Item.whoAmI("def4");
				case ItemCode.def5:
					return Item.whoAmI("def5");
				case ItemCode.def6:
					return Item.whoAmI("def6");
					
				//...
			}
		}
	}
}
