package application;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class CompareTime extends Application  {
	@Override
	public void start(Stage primaryStage) {
		//Create instance of TimeGUI, use it to create pane for scene
		TimeGUI timeGUI = new TimeGUI();
		Pane pane = timeGUI.createPane();
		
		
		//listeners
		/*Compare the intervals*/
		timeGUI.btnCompare.setOnAction(e ->  {
			try {
				Time startTime1 = new Time(timeGUI.tfStartTime1.getText());
				Time endTime1 = new Time(timeGUI.tfEndTime1.getText());
				Time startTime2 = new Time(timeGUI.tfStartTime2.getText());
				Time endTime2 = new Time(timeGUI.tfEndTime2.getText());
				Interval<Time> interval1 = new Interval<>(startTime1, endTime1);
				Interval<Time> interval2 = new Interval<>(startTime2, endTime2);
				String timeCompareMessage = 
						interval1.subinterval(interval2) ? "Interval 1 is a sub-interval of interval 2" :
							interval2.subinterval(interval1)	 ? "Interval 2 is a sub-interval of interval 1" :
								interval1.overlaps(interval2) ? "The intervals overlap" :
									"The intervals are disjoint";
				timeGUI.tfOutput.setText(timeCompareMessage);
				System.out.println(timeCompareMessage);
			}
			catch(InvalidTimeException ex) {
				System.out.println(ex); // or throw a new Exception?
			}
		});
		
		timeGUI.btnCheck.setOnAction(e -> {
			try {
				Time startTime1 = new Time(timeGUI.tfStartTime1.getText());
				Time endTime1 = new Time(timeGUI.tfEndTime1.getText());
				Time startTime2 = new Time(timeGUI.tfStartTime2.getText());
				Time endTime2 = new Time(timeGUI.tfEndTime2.getText());
				Time timeCheck = new Time(timeGUI.tfTimeCheck.getText());
				Interval<Time> interval1 = new Interval<>(startTime1, endTime1);
				Interval<Time> interval2 = new Interval<>(startTime2, endTime2);
				
				String timeCheckMessage = 
						interval1.within(timeCheck) && interval2.within(timeCheck) ? "Both intervals " :
							interval1.within(timeCheck) ? "Only interval 1 " :
								interval2.within(timeCheck ) ? "Only interval 2 " :
									"Neither interval ";
				timeGUI.tfOutput.setText(timeCheckMessage + "contains the time " + timeCheck);
				System.out.println(timeCheckMessage + "contains the time " + timeCheck);
				
			} catch(InvalidTimeException ex) {
				System.out.println(ex); // or throw a new Exception?
			}
			
		});
		
		
		//Assign pane to scene, set up stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Time Interval Checker");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	class TimeGUI {
		//Create TextFields
		TextField tfStartTime1 = new TextField();
		TextField tfEndTime1 = new TextField();
		TextField tfStartTime2 = new TextField();
		TextField tfEndTime2 = new TextField();
		TextField tfTimeCheck = new TextField();
		TextField tfOutput = new TextField();
		
		//Create buttons
		Button btnCompare = new Button("Compare Intervals");
		Button btnCheck = new Button("Check Time");
		
		//No-arg constructor
		public TimeGUI() {
			 
		}
		
		
		public GridPane createPane() {
			//Create pane and set up basic layout
			GridPane pane = new GridPane();
			pane.setPadding(new Insets(20, 20, 10, 20));
			pane.setVgap(5);
			pane.setHgap(5);
			
			//Create and add Labels
			Label lblStart = new Label("Start Time");
			Label lblEnd = new Label("End Time");
			pane.add(lblStart, 1, 0);
			pane.add(lblEnd, 2, 0);
			pane.add(new Label("Time Interval 1"), 0, 1);
			pane.add(new Label("Time Interval 2"), 0, 2);
			pane.add(new Label("Time to Check"), 0, 5);
			
			//Add TextFields
			pane.add(tfStartTime1, 1, 1);
			pane.add(tfEndTime1, 2, 1);
			pane.add(tfStartTime2, 1, 2);
			pane.add(tfEndTime2, 2, 2);
			pane.add(tfTimeCheck, 1, 5, 2, 1);
			pane.add(tfOutput, 0, 7, 3, 3);
			tfOutput.setEditable(false); 
			
			//Add Buttons
			pane.add(btnCompare, 0, 4, 3, 1);
			pane.add(btnCheck, 0, 6, 3, 1);
			
			//Adjust Button width
			btnCompare.setMaxWidth(Double.MAX_VALUE);
			btnCheck.setMaxWidth(Double.MAX_VALUE);
			 
			//Adjust alignments
			GridPane.setHalignment(lblStart, HPos.CENTER);
			GridPane.setHalignment(lblEnd, HPos.CENTER);
			tfStartTime1.setAlignment(Pos.BASELINE_CENTER);
			tfEndTime1.setAlignment(Pos.BASELINE_CENTER);
			tfStartTime2.setAlignment(Pos.BASELINE_CENTER);
			tfEndTime2.setAlignment(Pos.BASELINE_CENTER);
			tfTimeCheck.setAlignment(Pos.BASELINE_CENTER);
			 
			
			 //Return pane
			return pane;
		}
	
		
		public String compareIntervals() {
			return "";
		}
	
	}

}
