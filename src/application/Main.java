package application;

import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Main extends Application {

	final int BOARD_SIZE = 50;
	Board board;
	Rectangle[][] squares;

	boolean RUNNING = false;

	@Override
	public void start(Stage primaryStage) {

		board = new Board(BOARD_SIZE);
		squares = new Rectangle[BOARD_SIZE][BOARD_SIZE];

		try {
			GridPane grid = new GridPane();

			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					Rectangle cell = new Rectangle(15, 15);
					squares[i][j] = cell;
					cell.setFill(Color.BLACK);

					cell.setStrokeWidth(0.4);
					cell.setStroke(Color.GRAY);

					final int row = i;
					final int col = j;

					cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent t) {
							cell.setFill(Color.LIGHTGREEN);
							board.setCellToLive(row, col);
						}
					});

					GridPane.setRowIndex(cell, i);
					GridPane.setColumnIndex(cell, j);

					grid.add(cell, j, i);
				}
			}

			Button primary = new Button("Start/Stop/Resume");
			primary.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					RUNNING = !RUNNING;
					System.out.println(RUNNING);
				}
			});
			grid.add(primary, 20, 51, 10, 8);

			Button random = new Button("Randomize");
			random.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					for (int i = 0; i < BOARD_SIZE; i++) {
						for (int j = 0; j < BOARD_SIZE; j++) {
							int next = new Random().nextInt(6);
							if (next < 5) {
								board.setCellToEmpty(i, j);
								squares[i][j].setFill(Color.BLACK);
							} else {
								board.setCellToEmpty(i, j);
								squares[i][j].setFill(Color.LIGHTGREEN);
							}
						}
					}
				}
			});

			grid.add(random, 20, 61, 10, 8);

			Button clear = new Button("Clear");
			clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					for (int i = 0; i < BOARD_SIZE; i++) {
						for (int j = 0; j < BOARD_SIZE; j++) {
							board.setCellToEmpty(i, j);
							squares[i][j].setFill(Color.BLACK);
						}
					}
				}
			});
			grid.add(clear, 20, 71, 10, 8);

			Scene scene = new Scene(grid, 800, 1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
