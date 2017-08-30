package rpg;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import User.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NetworkController implements Initializable{
		@FXML Button btnStart;
		@FXML Button btnSend;
		@FXML Button btnExit;
		@FXML TextField txtInput;
		@FXML TextArea txtDisplay;
		public static boolean serverOn = false;
		public static String masege = "";
		private Stage primaryStage;
		Stage dia = new Stage(StageStyle.UNDECORATED);
		Client client;
		
		@Override
		public void initialize (URL location, ResourceBundle resources) {
		
			dia.initModality(Modality.WINDOW_MODAL);
			dia.initOwner(primaryStage);
			client = new Client();	
			
			btnStart.setOnAction(e->{
				serverOn = true;

				client.startClient();
				
				btnStart.setVisible(false);
			});
			
			btnSend.setOnAction(e->{
				if(serverOn)
					client.send(/*RootController.userLogID+*/"플레이어 : "+txtInput.getText());
			});
			
			
			
			btnExit.setOnAction(exitevent->{
				
				primaryStage = (Stage) btnStart.getScene().getWindow();
				
				client.stopClient();
				
				Platform.runLater(() -> {
					
					primaryStage.close();

					FXMLLoader outLoader = new FXMLLoader(getClass().getResource("map.fxml"));
					Parent parentOut = null;
					try {
						parentOut = outLoader.load();
					} catch (IOException ex) {
					}
					Scene scene = new Scene(parentOut);
					primaryStage.setOnCloseRequest(event->client.stopClient());
					serverOn = false;
					primaryStage.setScene(scene); // 화면이동
					primaryStage.show();
					
				});
				
			});
			
		}//이거 이니셜라이즈 메소드임
		
		public class Client{
			Socket clsocket;
			Thread clithread;
			void startClient() {
				clithread = new Thread() {
					@Override
					public void run() {
						try {
							clsocket = new Socket();
							clsocket.connect(new InetSocketAddress("localhost", 5001));
							Platform.runLater(()->{
								if(NetworkController.serverOn){
									
									client.send("플레이어가 접속하였습니다.");
								}
							});
						} catch(Exception e) {
							Platform.runLater(()->{
								if(NetworkController.serverOn){
									
									displayText("네트워크 접속 불가");
								}
							});
							if(!clsocket.isClosed()) { 
								try {
									clsocket.close();
									clithread.stop();
								} catch (Exception e1) {} 
							}
							return;
						}
						receive();
					}
				};
				clithread.start();
			}
			
			void stopClient() {
				client.send(/*RootController.userLogID+*/"플레이어가 종료하였습니다.");
				
				try {
					Thread.sleep(3);
					if(clsocket!=null && !clsocket.isClosed()) {
						
						clsocket.close();				
					}
				} 
				catch (Exception e) {}
			}	
			
			void receive() {
				while(true) {
					try {
						byte[] byteArr = new byte[100];
						InputStream inputStream = clsocket.getInputStream();
	
						int readByteCount = inputStream.read(byteArr);
						
						if(readByteCount == -1) { throw new IOException(); }
						
						String data = new String(byteArr, 0, readByteCount, "UTF-8");
						
						Platform.runLater(()->{
							displayText(data);
						});
					} catch (Exception e) {
						Platform.runLater(()->{
							displayText("[서버 통신 안됨]");
						});
						stopClient();
						break;
					}
				}
			}
			
			Thread sendthread;
			void send(String data) {
				sendthread = new Thread() {
					@Override
					public void run() {
						try {		
							byte[] byteArr = data.getBytes("UTF-8");
							
							OutputStream outputStream = clsocket.getOutputStream();
							
							outputStream.write(byteArr);
						
							outputStream.flush();
							
						} catch(Exception e) {
							Platform.runLater(()->{
								displayText("[서버 통신 안됨]");
							});
							stopClient();
							sendthread.stop();
						}				
					}
				};
				sendthread.start();
			}
			

		}
		
		void displayText(String text) {
			txtDisplay.setWrapText(true);
			txtDisplay.appendText(text + "\n");
		}	

}
