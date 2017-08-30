package rpg;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;


import User.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MapController implements Initializable{
		@FXML Button btnSilver;
		@FXML Button btnSea;
		
		@FXML Button btnCanyon;
		@FXML Button btnCanyon1;
		@FXML Button btnCanyon2;
		
		@FXML Button btnDerelict;
		@FXML Button btnDerelict1;
		
		@FXML Button btnRockyMountain;
		@FXML Button btnRockyMountain1;
		@FXML Button btnRockyMountain2;
		
		@FXML Button btnIce;
		@FXML Button btnIce1;
		@FXML Button btnIce2;
		
		@FXML Button btnCa;
		@FXML Button btnCa1;
		@FXML Button btnCa2;
		
		@FXML Button btnFinal;
		@FXML Button btnFinal1;
		
		@FXML ScrollPane mapScroll;
		
		public static String map = "";
		public static boolean bossMap = false;
		
		private Stage primaryStage;
		Stage dia = new Stage(StageStyle.UNDECORATED);
		
		@Override
		public void initialize (URL location, ResourceBundle resources) {
			dia.initModality(Modality.WINDOW_MODAL);
			dia.initOwner(primaryStage);
			mapScroll.setVbarPolicy(ScrollBarPolicy.NEVER);
			mapScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
			
				Platform.runLater(()->{
					
					btnSilver.setOnAction(event->{	
						primaryStage = (Stage) btnSilver.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							
							FXMLLoader silverLoader = new FXMLLoader(getClass().getResource("town.fxml"));
							Parent parentSilver = null;
							try {
								parentSilver = silverLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentSilver);
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
							
						});	
					});		//이거 Silver가기 버튼
					
					btnSea.setOnAction(event->{
						
						primaryStage = (Stage) btnSilver.getScene().getWindow();
						
						Platform.runLater(()->{
							primaryStage.close();
							
							FXMLLoader silverLoader = new FXMLLoader(getClass().getResource("sea.fxml"));
							Parent parentSilver = null;
							try {
								parentSilver = silverLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentSilver);
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
							
						});	
					});		//정보의 바다
					
					btnCanyon.setOnAction(event->{	
						bossMap = false;
						map = "canyon";
						primaryStage = (Stage) btnCanyon.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("canyon.fxml"));
							Parent parentCanyon = null;
							  
							  
							try {
								parentCanyon = canyonLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentCanyon);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
							
						});
					});		//협곡 버튼		
					
					btnCanyon1.setOnAction(event->{
						if(Episode.EPISODE2){
						bossMap = false;
						map = "canyon2";
						primaryStage = (Stage) btnCanyon.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("canyon.fxml"));
							Parent parentCanyon = null;
							  
							  
							try {
								parentCanyon = canyonLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentCanyon);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
							
						});
						}
					});		//협곡 버튼	
					
					btnCanyon2.setOnAction(event->{	
						if(Episode.EPISODE3 &&  !Episode.EPISODE4){
						bossMap = true;
						map = "canyon3";
						primaryStage = (Stage) btnCanyon.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("canyon.fxml"));
							Parent parentCanyon = null;
							  
							  
							try {
								parentCanyon = canyonLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentCanyon);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
							
						});
						}
					});		//협곡 버튼	
					
					
					
					btnDerelict.setOnAction(event->{	
						if(Episode.EPISODE7){
						bossMap = false;
						map = "derelict";
						primaryStage = (Stage) btnCanyon.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("derelict.fxml"));
							Parent parentCanyon = null;
							  
							  
							try {
								parentCanyon = canyonLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentCanyon);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
							
						});
						}
					});		//Derelict 버튼		
					btnDerelict1.setOnAction(event->{	
						if(Episode.EPISODE8 &&  !Episode.EPISODE9){
						bossMap = true;
						map = "derelict2";
						primaryStage = (Stage) btnCanyon.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("derelict.fxml"));
							Parent parentCanyon = null;
							  
							  
							try {
								parentCanyon = canyonLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentCanyon);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
							
						});
						}
					});		//Derelict 버튼		
							
					
					btnIce.setOnAction(event->{
						if(Episode.EPISODE9){
							
						bossMap = false;
						map = "ice";
						
							map = "ice";
							primaryStage = (Stage) btnCanyon.getScene().getWindow();
							Platform.runLater(()->{
								primaryStage.close();
								FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("ice.fxml"));
								Parent parentCanyon = null;
								  
								  
								try {
									parentCanyon = canyonLoader.load();
								} catch (IOException ex) {	}
								Scene scene = new Scene(parentCanyon);
								
								primaryStage.setScene(scene);	//화면이동
								primaryStage.show();
								
							});
						
						}
					});
					btnIce1.setOnAction(event->{
						if(Episode.EPISODE9){
						bossMap = false;
						map = "ice2";
						if(User.player.lev >= 10){
							map = "ice";
							primaryStage = (Stage) btnCanyon.getScene().getWindow();
							Platform.runLater(()->{
								primaryStage.close();
								FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("ice.fxml"));
								Parent parentCanyon = null;
								  
								  
								try {
									parentCanyon = canyonLoader.load();
								} catch (IOException ex) {	}
								Scene scene = new Scene(parentCanyon);
								
								primaryStage.setScene(scene);	//화면이동
								primaryStage.show();
								
							});
						}
						}
					});
					btnIce2.setOnAction(event->{
						if(Episode.EPISODE10 &&  !Episode.EPISODE11){
						bossMap = true;
						map = "ice3";
						if(User.player.lev >= 10){
							map = "ice";
							primaryStage = (Stage) btnCanyon.getScene().getWindow();
							Platform.runLater(()->{
								primaryStage.close();
								FXMLLoader canyonLoader = new FXMLLoader(getClass().getResource("ice.fxml"));
								Parent parentCanyon = null;
								  
								  
								try {
									parentCanyon = canyonLoader.load();
								} catch (IOException ex) {	}
								Scene scene = new Scene(parentCanyon);
								
								primaryStage.setScene(scene);	//화면이동
								primaryStage.show();
								
							});
						}
						}
					});
					
					
					btnRockyMountain.setOnAction(event->{
						if(Episode.EPISODE4){
						bossMap = false;
						map = "rocky";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("rockymountain.fxml"));
							Parent parentRocky = null;
							  
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});	
					
					btnRockyMountain1.setOnAction(event->{
						if(Episode.EPISODE5){
						bossMap = false;
						map = "rocky2";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("rockymountain.fxml"));
							Parent parentRocky = null;
							  
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});	//바위산
					btnRockyMountain2.setOnAction(event->{
						if(Episode.EPISODE6 &&  !Episode.EPISODE7){
						bossMap = true;
						map = "rocky3";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("rockymountain.fxml"));
							Parent parentRocky = null;
							  
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});
					
					btnCa.setOnAction(event->{
						if(Episode.EPISODE11){
						bossMap = false;
						map = "near";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("near.fxml"));
							Parent parentRocky = null;
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});
					
					btnCa1.setOnAction(event->{
						if(Episode.EPISODE11){
						bossMap = false;
						map = "near2";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("near.fxml"));
							Parent parentRocky = null;
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});
					
					btnCa2.setOnAction(event->{
						if(Episode.EPISODE12 &&  !Episode.EPISODE13){
						bossMap = true;
						map = "near3";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("near.fxml"));
							Parent parentRocky = null;
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});
					
					btnFinal.setOnAction(event->{
						if(Episode.EPISODE13){
						bossMap = false;
						map = "final";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("final.fxml"));
							Parent parentRocky = null;
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});
					
					btnFinal1.setOnAction(event->{
						if(Episode.EPISODE14){
						bossMap = true;
						map = "final2";
						primaryStage = (Stage) btnRockyMountain.getScene().getWindow();
						Platform.runLater(()->{
							primaryStage.close();
							FXMLLoader RockyLoader = new FXMLLoader(getClass().getResource("final.fxml"));
							Parent parentRocky = null;
							  
							try {
								parentRocky = RockyLoader.load();
							} catch (IOException ex) {	}
							Scene scene = new Scene(parentRocky);
							
							primaryStage.setScene(scene);	//화면이동
							primaryStage.show();
						
						});
						}
					});
					
					
				});		
				
			
			
			
		}//이거 이니셜라이즈 메소드임
		
		
	}
