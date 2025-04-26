package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/*in this class i create the interface with all the process that need to complete the project*/
public class Process {

	private File file;

	private ComboBox<Vertex> cmboSource;
	private ComboBox<Vertex> cmboTarget;
	private ComboBox<String> cmboFilter = new ComboBox<>();
	private int clicked = 0;

	private Bounds bounds;
	private int numberOfLines;
	private StackPane imgPane = new StackPane();
	private TextArea txtAreaPath = new TextArea();
	private TextField txtDistance = new TextField();
	private TextField txtCost = new TextField();
	private TextField txtTime = new TextField();
	private Button btnRun = new Button("Run");
	private BorderPane bp = new BorderPane();
	private boolean t = true;
	ScaleTransition scaleTransition;
	Dijkstra dijkstra = new Dijkstra();

	// in the constructor i create the fx with all the nodes that needed and read
	// the file with initialize the D T
	public Process() {
		readFile();
		ImageView imgView = new ImageView("pic.jpg");
		imgView.fitHeightProperty().bind(imgPane.heightProperty());
		imgView.fitWidthProperty().bind(imgPane.widthProperty());
		imgPane.getChildren().add(imgView);
		imgPane.setAlignment(Pos.CENTER);

		imgPane.setMinSize(950, 600);
		imgPane.setPrefSize(950, 600);
		imgPane.setMaxSize(950, 600);

		cmboSource = new ComboBox<>(FXCollections.observableArrayList(dijkstra.getGraph().getVertices()));
		cmboTarget = new ComboBox<>(FXCollections.observableArrayList(dijkstra.getGraph().getVertices()));
		cmboFilter.getItems().addAll("Price", "Time", "Distance");

		VBox box = new VBox(15);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 20, 0, 0));

		GridPane gridPane = new GridPane();
		gridPane.setHgap(30);
		gridPane.setVgap(15);

		Label lblSource = new Label("Source: ");
		Label lblTarget = new Label("Target: ");
		Label lblFilter = new Label("Filter: ");

		lblSource.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:#004080;");
		lblTarget.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:#004080;");
		lblFilter.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:#004080;");

		gridPane.add(lblSource, 0, 0);
		gridPane.add(lblTarget, 0, 1);
		gridPane.add(lblFilter, 0, 2);

		gridPane.add(cmboSource, 1, 0);
		gridPane.add(cmboTarget, 1, 1);
		gridPane.add(cmboFilter, 1, 2);

		cmboSource.setStyle(
				"-fx-font-size: 20px;-fx-pref-width: 300px;-fx-pref-height: 40px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';");

		cmboTarget.setStyle(
				"-fx-font-size: 20px;-fx-pref-width: 300px;-fx-pref-height: 40px;-fx-font-weight: bold;-fx-font-family:'Times New Roman';");

		cmboFilter.setStyle(
				"-fx-font-size: 20px;-fx-pref-width: 300px;-fx-pref-height: 40px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';");

		Label lblPath = new Label("Path:");
		Label lblDistance = new Label("Distance:");
		Label lblCost = new Label("Cost:");
		Label lblTime = new Label("Time:");

		lblPath.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:  #004080;");
		lblDistance.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:  #004080;");
		lblTime.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:  #004080;");
		lblCost.setStyle(
				"-fx-font-size: 20px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:  #004080;");

		txtAreaPath.setStyle("-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill: #950606;");
		txtAreaPath.setEditable(false);
		txtDistance.setStyle(
				"-fx-font-size: 15px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill: #950606;");
		txtDistance.setEditable(false);
		txtCost.setStyle(
				"-fx-font-size: 15px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill: #950606;");
		txtCost.setEditable(false);
		txtTime.setStyle(
				"-fx-font-size: 15px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill: #950606;");
		txtTime.setEditable(false);

		GridPane distancePane = new GridPane(10, 20);

		distancePane.add(lblDistance, 0, 0);
		distancePane.add(txtDistance, 1, 0);

		distancePane.add(lblCost, 0, 1);
		distancePane.add(txtCost, 1, 1);

		distancePane.add(lblTime, 0, 2);
		distancePane.add(txtTime, 1, 2);

		btnRun.setStyle(
				"-fx-background-color:  #004080;-fx-text-fill: white;-fx-font-size: 20px;-fx-background-radius: 30px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';");
		btnRun.setAlignment(Pos.CENTER);
		scale(btnRun);
		box.getChildren().addAll(gridPane, btnRun, lblPath, txtAreaPath, distancePane);
		box.setAlignment(Pos.CENTER);
		VBox boxButton = new VBox();
		boxButton.setPadding(new Insets(0, 0, 10, 0));
		boxButton.setAlignment(Pos.BASELINE_RIGHT);

		Label word = new Label("World Map");

		word.setStyle(
				"-fx-font-size: 50px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill:  #004080;");
		scaleTransition = new ScaleTransition(Duration.seconds(1), word);
		scaleTransition.setByX(0.2);
		scaleTransition.setByY(0.2);
		scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
		scaleTransition.setAutoReverse(true);
		scaleTransition.play();
		word.setOnMouseClicked(e -> {
			if (t) {
				scaleTransition.stop();
				t = false;
			} else {
				scaleTransition.play();
				t = true;
			}

		});
		bp.setCenter(imgPane);
		bp.setRight(box);
		bp.setBottom(boxButton);

		bp.setTop(word);
		bp.setAlignment(word, Pos.CENTER);
		Platform.runLater(() -> {
			applayCities(imgPane);
		});

	}

	public BorderPane pane() {
		runButton();
		return bp;
	}

	// in the runButton method that show the paths in the image and give the weights
	public void runButton() {
		btnRun.setOnAction(e -> {
			dijkstra.initializeTable();
			Line[] lines = new Line[numberOfLines];
			int i = 0;
			for (var node : imgPane.getChildren()) {
				if (node instanceof Line) {
					lines[i] = (Line) node;
					i++;
				}
			}

			imgPane.getChildren().removeAll(FXCollections.observableArrayList(lines));
			numberOfLines = 0;

			if (cmboSource.getValue() != null && cmboTarget.getValue() != null && cmboFilter.getValue() != null) {
				txtAreaPath.clear();
				txtDistance.clear();
				txtCost.clear();
				txtTime.clear();

				boolean valid = dijkstra.dijkstra(cmboSource.getValue(), cmboTarget.getValue(), cmboFilter);

				if (valid) {
					getPath(cmboTarget.getValue(), txtAreaPath, imgPane);
					txtDistance
							.setText(String.format("%.3f", getTotalWeight(cmboTarget.getValue(), "distance")) + " $");
					txtCost.setText(String.format("%.3f", getTotalWeight(cmboTarget.getValue(), "price")) + " $");

					String num = String.valueOf(getTotalWeight(cmboTarget.getValue(), "time"));
					String split[] = num.split("\\.");
					if (split.length > 1) {
						int hours = Integer.parseInt(split[0]);
						double n = Double.parseDouble("0." + split[1]);
						int minutes = (int) (n * 60);

						txtTime.setText(hours + "H :" + String.format("%02d", minutes) + "M");
					} else {
						txtTime.setText(split[0] + "H");
					}

				} else {
					txtAreaPath
							.setText("There is no path from " + cmboSource.getValue() + " to " + cmboTarget.getValue());
				}
			} else {
				txtAreaPath.setText("Please select Source, Target, and Filter.");
			}
		});
	}

	// this method calculate the weights from the table depend on the weight type
	private double getTotalWeight(Vertex target, String weightType) {
		double totalWeight = 0.0;
		Vertex current = target;

		while (dijkstra.getTable()[current.getNumber()].getPath() != null) {
			Vertex prev = dijkstra.getTable()[current.getNumber()].getPath();
			Edge edge = prev.getEdgeWith(current);
			if (edge != null) {
				totalWeight += edge.getWeight(weightType);
			}
			current = prev;
		}

		return totalWeight;
	}

	// this method to read the vertex and edges from the file
	public void readFile() {
		file = new File("Data.txt");
		try {
			int numberOfVertices = 0;
			int numberOfEdges = 0;
			Scanner sc = new Scanner(file);
			if (sc.hasNext()) {
				String[] arr = sc.nextLine().split(" ");
				numberOfVertices = Integer.parseInt(arr[0].trim());
				numberOfEdges = Integer.parseInt(arr[1].trim());
			}

			dijkstra.graph(numberOfVertices);

			int counter = 0;
			while (sc.hasNext() && counter < numberOfVertices) {
				String[] capitalInfo = sc.nextLine().split(" ");
				Capital capital = new Capital(capitalInfo[0], Double.parseDouble(capitalInfo[1].trim()),
						Double.parseDouble(capitalInfo[2].trim()));
				dijkstra.getGraph().setVertex(counter, capital);
				counter++;
			}

			counter = 0;
			while (sc.hasNext() && counter < numberOfEdges) {
				String[] connection = sc.nextLine().split(" ");
				Vertex startVertex = dijkstra.getGraph().findVertex(connection[0].trim());
				Vertex endVertex = dijkstra.getGraph().findVertex(connection[1].trim());
				if (startVertex != null && endVertex != null) {
					double cost = Double.parseDouble(connection[2].trim());
					double time = Double.parseDouble(connection[3].trim());
					dijkstra.getGraph().addEdge(startVertex, endVertex, cost, time);
				}
				counter++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// this method to apply the cities in the screen and it should know the image
	// height and width to put the city exactly where it should
	public void applayCities(StackPane imgPane) {
		Vertex[] cities = dijkstra.getGraph().getVertices();
		bounds = imgPane.getBoundsInParent();

		for (int i = 0; i < cities.length; i++) {
			Vertex city = cities[i];
			if (city != null) {
				Capital capital = city.getCapital();

				double x = (((capital.getLongitude() + 180) / 360 * bounds.getWidth()));
				double y = ((capital.getLatitude() - 90) / -180 * bounds.getHeight());

				Label cityLabel = new Label();
				cityLabel.setText(capital.getCapitalName());

				cityLabel.setTranslateX(x - bounds.getWidth() / 2);
				cityLabel.setTranslateY(y - bounds.getHeight() / 2);
				cityLabel.setStyle(
						"-fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: 'Times New Roman';-fx-text-fill: #001f3f;");

				cityLabel.setOnMouseClicked(e -> {
					clicked++;
					if (clicked == 1) {
						cmboSource.setValue(city);
					} else if (clicked == 2) {
						cmboTarget.setValue(city);
						clicked = 0;
					}
				});

				imgPane.getChildren().addAll(cityLabel);
			}
		}
	}

	// this method to take the path from the table
	public void getPath(Vertex vertex, TextArea txtPath, StackPane imgPane) {
		if (vertex == null)
			return;

		if (dijkstra.getTable()[vertex.getNumber()].getPath() != null) {

			getPath(dijkstra.getTable()[vertex.getNumber()].getPath(), txtPath, imgPane);
			txtPath.setText(txtPath.getText() + " --> ");

			Capital capital1 = vertex.getCapital();
			Capital capital2 = dijkstra.getTable()[vertex.getNumber()].getPath().getCapital();

			double x1 = (((capital1.getLongitude() + 180) / 360 * bounds.getWidth()));
			double y1 = ((capital1.getLatitude() - 90) / -180 * bounds.getHeight());

			double x2 = (((capital2.getLongitude() + 180) / 360 * bounds.getWidth()));
			double y2 = ((capital2.getLatitude() - 90) / -180 * bounds.getHeight());

			Line line = new Line();
			line.setStartX(x2);
			line.setEndX(x1);
			line.setStartY(y2);
			line.setEndY(y1);
			line.setManaged(false);
			line.setStrokeWidth(2);
			imgPane.getChildren().add(line);
			numberOfLines++;
		}

		txtPath.setText(txtPath.getText() + vertex.toString());
	}

	private void scale(Button button) {
		ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
		scaleUp.setToX(1.1);
		scaleUp.setToY(1.1);
		ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), button);
		scaleDown.setToX(1.0);
		scaleDown.setToY(1.0);
		button.setOnMouseEntered(e -> scaleUp.play());
		button.setOnMouseExited(e -> scaleDown.play());
	}
}
