package alex_photo_studio;

import com.mysql.cj.xdevapi.Table;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author Programmer
 */
public class ALEX_PHOTO_STUDIO extends Application {
    
    private Scene home,view_details;
  
    TextField nm,sz,num,unit_prc,pd,cmt,search_box;
    Label name,_size,number,unit_price,paid,comment,date,balance;
    Label _name,__size,_number,_unit_price,_paid,_comment,_date,_balance,search_l;
    Button delete,edit,power,search;
    ListView listView;
    TableView tableView;
    VBox row,layout2;
    HBox logImg,back_h_power;
    Alert emptyAlert;
    ImageView logView,al_conf,al_delete,al_info;
    private static ObservableList<Record> row1,row2;
    Record record;
   

    
    @Override
    public void start(Stage primaryStage) throws Exception {
  
        //ements like textinput lables etc and their methods
        //buttons
        primaryStage.setFullScreen(true);//full screen scene
        primaryStage.setFullScreenExitHint("");
        //primaryStage.setResizable(false);
        //log image
        Image log = new Image("images/Alex.png");
       logView = new ImageView(log);
       logImg=new HBox();
      logView.setFitHeight(200);
      logView.setPreserveRatio(true);
      logImg.getChildren().add(logView);
      //end log image
        Button add_details = new Button();add_details.setTextFill(Color.CORNFLOWERBLUE);
        Button details = new Button();details.setTextFill(Color.CORNFLOWERBLUE);
        search=new Button(); search.setTextFill(Color.CORNFLOWERBLUE);
        Image i = new Image("images/search.jpg");
      ImageView v = new ImageView(i);
      v.setFitHeight(20);
      v.setPreserveRatio(true);
        //search.setTranslateX(20);
      //search.setTranslateY(20);
      //Setting the size of the button
      search.setPrefSize(20, 20);
      //Setting a graphic to the button
      search.setGraphic(v);
        
        
        //LABELS
         name=new Label("NAME");name.setTextFill(Color.CORNFLOWERBLUE);
         _size=new Label("SIZE");_size.setTextFill(Color.CORNFLOWERBLUE);
         number=new Label("NUMBER");number.setTextFill(Color.CORNFLOWERBLUE);
         unit_price=new Label("UNIT PRICE");unit_price.setTextFill(Color.CORNFLOWERBLUE);//unitprice
         paid=new Label("PAID");paid.setTextFill(Color.CORNFLOWERBLUE);
         comment=new Label("COMMENT");comment.setTextFill(Color.CORNFLOWERBLUE);
         search_l=new Label("Search");search_l.setTextFill(Color.CORNFLOWERBLUE);
         
         //setting image for power btn
         //Creating a graphic (image)
      Image img = new Image("images/power.jpg");
      ImageView view = new ImageView(img);
      view.setFitHeight(80);
      view.setPreserveRatio(true);
      //Creating a Button
      power = new Button();
      //Setting the location of the button
      power.setTranslateX(200);
      power.setTranslateY(25);
      //Setting the size of the button
      power.setPrefSize(80, 80);
      //Setting a graphic to the button
      power.setGraphic(view);
      //image setting ends
        
        //TEXTEDIT BOXS
        search_box=new TextField();search_box.setMinWidth(500);
        //Insets(double top, double right, double bottom, double left)
       nm=new TextField();nm.setMinWidth(480);   
       sz=new TextField();sz.setMinWidth(480);
       num=new TextField();num.setMinWidth(480); 
       num.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            num.setText(newValue.replaceAll("[^\\d]", ""));
            });
       unit_prc=new TextField();unit_prc.setMinWidth(480);
       unit_prc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            unit_prc.setText(newValue.replaceAll("[^\\d]", ""));
            });
       pd=new TextField();pd.setMinWidth(480);
       pd.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            pd.setText(newValue.replaceAll("[^\\d]", ""));
            });
       cmt=new TextField();cmt.setMinWidth(480);
    
  
       HBox name_box=new HBox();
       name_box.setBorder(Border.EMPTY);
       name_box.getChildren().addAll(name,nm);
       name_box.setSpacing(50);
       name_box.setAlignment(Pos.CENTER);
       
      // name_box.setStyle("-fx-background-color: darkgrey;");
       
        HBox sz_box=new HBox();
       sz_box.setBorder(Border.EMPTY);
      sz_box.getChildren().addAll(_size,sz);
       sz_box.setSpacing(57);
       sz_box.setAlignment(Pos.CENTER);
       sz_box.setMargin(_size, new Insets(0, 0, 0, 16));
      // sz_box.setStyle("-fx-background-color: darkgrey;");
       
        HBox num_box=new HBox();
       num_box.setBorder(Border.EMPTY);
     num_box.getChildren().addAll(number,num);
       num_box.setSpacing(37);
       num_box.setAlignment(Pos.CENTER);
      // num_box.setStyle("-fx-background-color: darkgrey;");
       
        HBox t_box=new HBox();
       t_box.getChildren().addAll(unit_price,unit_prc);
       t_box.setSpacing(20);
        num_box.setBorder(Border.EMPTY);
       t_box.setAlignment(Pos.CENTER);
      // t_box.setStyle("-fx-background-color: darkgrey;");
        
        HBox p_box=new HBox();
     p_box.setBorder(Border.EMPTY);
       p_box.getChildren().addAll(paid,pd);
       p_box.setSpacing(58);
       p_box.setAlignment(Pos.CENTER);
       p_box.setMargin(pd, new Insets(0, 0, 0, 16));
       //p_box.setStyle("-fx-background-color: darkgrey;");
       
        HBox c_box=new HBox();
       c_box.setBorder(Border.EMPTY);
      c_box.getChildren().addAll(comment,cmt);
       c_box.setSpacing(32);
       c_box.setAlignment(Pos.CENTER);
       c_box.setMargin(cmt, new Insets(0, 0, 0, -1));
      // c_box.setStyle("-fx-background-color: darkgrey;");
       
       HBox btns=new HBox();
       btns.getChildren().addAll(add_details,details);
       btns.setMouseTransparent(false);
       btns.setSpacing(200);
       btns.setBorder(Border.EMPTY);
       btns.setAlignment(Pos.BOTTOM_CENTER);
       btns.setMargin(details, new Insets(0, 0, 0, 100));
       
       HBox Search_b=new HBox();
       Search_b.setSpacing(20);
       Search_b.getChildren().addAll(search_l,search_box,search);
       Search_b.setBorder(Border.EMPTY);
       
       
        //text display onto buttons
        add_details.setText("ADD DETAILS");
        details.setText("VIEW DETAILS");
        
        //methods onto buttons
        add_details.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                try {
                   
                    if(nm.getText().equals("")&&pd.getText().equals("")&&num.getText().equals("")&&sz.getText().equals("")&&unit_prc.getText().equals("")){
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("ALEX PHOTO STUDIO");
                     Image al = new Image("images/caution.jpg");
                   al_info = new ImageView(al);
                   al_info.setFitHeight(200);
                   al_info.setPreserveRatio(true);
                   alert.setGraphic(al_info);
                    alert.setHeaderText("CAUTION");
                    alert.setContentText("FIELD(s) EMPTY PLEASE!");
                     alert.showAndWait();
                    }
        
                    else{//upload data
                     //check if fields not empty

                    String name=nm.getText();
                    String comnt=cmt.getText();
                    int paid=Integer.parseInt(pd.getText());
                    int nums=Integer.parseInt(num.getText());
                    String size=sz.getText();
                    int unit=Integer.parseInt(unit_prc.getText());
                    LocalDate ndate=java.time.LocalDate.now();//date now
                    String date=ndate.toString();
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                //alert image
                Image al = new Image("images/confirm.jpg");
                al_conf = new ImageView(al);
     
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               alert1.setGraphic(al_conf);

                // clicking X also means no
                ButtonType result = alert1.showAndWait().orElse(ButtonType.NO);

                if (ButtonType.NO.equals(result)) {
                    // consume event i.e. ignore close request 
                    alert1.close();
                }else{
                  Record record=new Record(name,size,nums,unit,(unit*nums),paid,comnt,date);
                    
                    EnterRecord(record);
                }
                    
                    //alert
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("ALEX PHOTO STUDIO");
        
                    alert.setHeaderText("Record Upload");
                    alert.setContentText("UpLOADED!");
                     alert.showAndWait();
                    //clear fields
                    ClearFields();
                    
                    
                    
                    System.out.println(date);
                }
                
                } catch (SQLException ex) {
                    Logger.getLogger(ALEX_PHOTO_STUDIO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                  /** Button btnSet = new Button("Set", new ImageView("set.gif")); Button btnClear = new Button("Clear",      **/
                
               
            }
        });
        //scene two detyailSs
        
        
	
                
        search.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
         Stage searchResults=new Stage();
        VBox search_root=new VBox();
        TableView tableView1=new TableView();
        
        search_root.setSpacing(20);
        search_root.setStyle("-fx-font-size: 20;");
        search_root.setAlignment(Pos.CENTER);
        Scene search_Scene = new Scene(search_root);
                //empty alert
            if(search_box.getText().equals("")){
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("ALEX PHOTO STUDIO");
                     Image al = new Image("images/caution.jpg");
                   al_info = new ImageView(al);
                   al_info.setFitHeight(200);
                   al_info.setPreserveRatio(true);
                   alert.setGraphic(al_info);
                    alert.setHeaderText("CAUTION");
                    alert.setContentText("Search FIELD EMPTY PLEASE!");
                     alert.showAndWait();
                     
                    }else{
                
                
                try {
                    
                    String name =search_box.getText();
                    String stmt = "SELECT * FROM records WHERE nm LIKE ? ";
                    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alp?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
                    PreparedStatement preparedStatement = connection.prepareStatement(stmt);
                    preparedStatement.setString(1, name);
                    ResultSet rs = preparedStatement.executeQuery();
                    TableColumn<Record, Integer> column1 =
                            new TableColumn<>("ID");
                    
                    column1.setCellValueFactory(
                            new PropertyValueFactory<>("id"));
                    //column1.setStyle("-fx-background-color: green;");
                    column1.setStyle( "-fx-alignment: CENTER;");
                    column1.setMinWidth(20);
                    
                    
                    TableColumn<Record, String> column2 =
                            new TableColumn<>("Name");
                    
                    column2.setCellValueFactory(
                            new PropertyValueFactory<>("name"));
                    column2.setMinWidth(150);
                    column2.setStyle( "-fx-alignment: CENTER;");
                    
                    
                    TableColumn<Record, String> column3 =
                            new TableColumn<>("Size");
                    
                    column3.setCellValueFactory(
                            new PropertyValueFactory<>("size"));
                    column3.setMinWidth(80);
                    column3.setStyle( "-fx-alignment: CENTER;");
                    
                    TableColumn<Record, Integer> column4 =
                            new TableColumn<>("Number");
                    
                    column4.setCellValueFactory(
                            new PropertyValueFactory<>("number"));
                    column4.setMinWidth(100);
                    column4.setStyle( "-fx-alignment: CENTER;");
                    
                    TableColumn<Record, Integer> column5=
                            new TableColumn<>("Unit price");
                    
                    column5.setCellValueFactory(
                            new PropertyValueFactory<>("unit_price"));
                    column5.setMinWidth(130);
                    column5.setStyle( "-fx-alignment: CENTER;");
                    
                    TableColumn<Record, Integer> column6 =
                            new TableColumn<>("Total Amount");
                    
                    column6.setCellValueFactory(
                            new PropertyValueFactory<>("total_amount"));
                    column6.setMinWidth(150);
                    column6.setStyle( "-fx-alignment: CENTER;");
                    
                    TableColumn<Record, Integer> column7 =
                            new TableColumn<>("Paid");
                    
                    column7.setCellValueFactory(
                            new PropertyValueFactory<>("paid"));
                    column7.setMinWidth(150);
                    column7.setStyle( "-fx-alignment: CENTER;");
                    
                    TableColumn<Record, String> column8 =
                            new TableColumn<>("Comment");
                    
                    column8.setCellValueFactory(
                            new PropertyValueFactory<>("comment"));
                    column8.setMinWidth(150);
                    column8.setStyle( "-fx-alignment: CENTER;");
                    
                    TableColumn<Record, String> column9 =
                            new TableColumn<>("Date");
                    
                    column9.setCellValueFactory(
                            new PropertyValueFactory<>("Ldate"));
                    column9.setMinWidth(150);
                    column9.setStyle( "-fx-alignment: CENTER;");
                    
                    TableColumn<Record,Integer> column10=new TableColumn<>("Balance");
                    column10.setMinWidth(150);
                    column10.setStyle( "-fx-alignment: CENTER;");
                    column10.setCellValueFactory(new PropertyValueFactory<>("balance"));
                    
                    
                    tableView1.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10);
                    search_root.getChildren().add(tableView1);
                  searchResults.setTitle("Search Results");
                  
                     
            while(rs.next()){
                Record record=new Record();
                record.setId(rs.getInt("id"));
                record.setName(rs.getString("nm"));
               
                record.setSize(rs.getString("size"));
                
                record.setNumber(rs.getInt("num"));
               record.setUnit_price(rs.getInt("unit_p"));
                record.setTotal_amount(rs.getInt("t_amount"));
               
                record.setPaid(rs.getInt("paid"));
              
                 //required comments
               if(record.getBalance()==0){
                    record.setComment("CLEARED");
               }else{
                    record.setComment("NOT CLEARED");
               }
               
          
                record.setLdate(rs.getString("d_date"));
               
                int total=rs.getInt("unit_p")*rs.getInt("num");
               
               
              row2.add(record);

                

               
            }
            if(row2.isEmpty()){
                searchResults.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("ALEX PHOTO STUDIO");
                     Image al = new Image("images/caution.jpg");
                   al_info = new ImageView(al);
                   al_info.setFitHeight(200);
                   al_info.setPreserveRatio(true);
                   alert.setGraphic(al_info);
                    alert.setHeaderText("CAUTION");
                    alert.setContentText("Name Does not Exist in the System!");
                     alert.showAndWait();
                     searchResults.close();
                     
                     
            }else{
            
            tableView1.setItems(row2);
            
            // layout2.getChildren().addAll(borderPane,back_home);
            //close connection
            searchResults.setOnCloseRequest(e -> {
               tableView1.getColumns().clear();
                    tableView1.getItems().clear();
                
            });
                 
            connection.close();
                  
                  
                //stage.initModality(Modality.NONE);
                searchResults.setMinWidth(800);
		searchResults.setScene(search_Scene);
		searchResults.showAndWait();
            }
                } catch (SQLException ex) {
                    Logger.getLogger(ALEX_PHOTO_STUDIO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
                
                
            }
            
        
            
        });
 
        Button back_home=new Button();
        back_home.setText("Back Home");
        back_home.setMinHeight(80);
        layout2 =new VBox();
        layout2.setStyle("-fx-background-color: PURPLE;");
        VBox root=new VBox();
        listView = new ListView();
        tableView=new TableView();
        row1= FXCollections.observableArrayList();
        row2= FXCollections.observableArrayList();
        tableView.prefHeightProperty().bind(primaryStage.heightProperty());
        tableView.prefWidthProperty().bind(primaryStage.widthProperty());
        tableView.setStyle("-fx-font-size: 20;-fx-border-color: red;-fx-background-color: MediumPURPLE; ");
       
     
        //creating scene twov
        view_details=new Scene(layout2,Color.PURPLE);
        //method on back_hombe
        back_home.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               // listView.getItems().clear();
                tableView.getColumns().clear();
                tableView.getItems().clear();
                //tableView.refresh();
               
                primaryStage.setScene(home);
                 primaryStage.setFullScreen(true);//full screen scene
                 primaryStage.setFullScreenExitHint("");
                primaryStage.setResizable(false);
            }
            
        });
        //hod go to scene two
        details.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                //primaryStage.setResizable(false);
                primaryStage.setScene(view_details);
                primaryStage.setFullScreen(true);//full screen scene
                primaryStage.setFullScreenExitHint("");
                  /**Table to show records **/
         String selectStmt = "SELECT * FROM records;";
        
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alp?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
         PreparedStatement preparedStatement = connection.prepareStatement(selectStmt);
                ResultSet rs = preparedStatement.executeQuery()) {
            TableColumn<Record, Integer> column1 = 
                new TableColumn<>("ID");

                column1.setCellValueFactory(
                    new PropertyValueFactory<>("id"));
               //column1.setStyle("-fx-background-color: green;");
               column1.setStyle( "-fx-alignment: CENTER;");
               
                
            
            TableColumn<Record, String> column2 = 
                new TableColumn<>("Name");

                column2.setCellValueFactory(
                    new PropertyValueFactory<>("name"));
                column2.setMinWidth(150);
                column2.setStyle( "-fx-alignment: CENTER;");


                TableColumn<Record, String> column3 = 
                new TableColumn<>("Size");

                column3.setCellValueFactory(
                    new PropertyValueFactory<>("size"));
                column3.setMinWidth(80);
                column3.setStyle( "-fx-alignment: CENTER;");
                
                TableColumn<Record, Integer> column4 = 
                new TableColumn<>("Number");

                column4.setCellValueFactory(
                    new PropertyValueFactory<>("number"));
                column4.setMinWidth(100);
                column4.setStyle( "-fx-alignment: CENTER;");
                
                TableColumn<Record, Integer> column5= 
                new TableColumn<>("Unit price");

                column5.setCellValueFactory(
                    new PropertyValueFactory<>("unit_price"));
                column5.setMinWidth(130);
                column5.setStyle( "-fx-alignment: CENTER;");
                
                TableColumn<Record, Integer> column6 = 
                new TableColumn<>("Total Amount");

                column6.setCellValueFactory(
                    new PropertyValueFactory<>("total_amount"));
                column6.setMinWidth(150);
                column6.setStyle( "-fx-alignment: CENTER;");
                
                TableColumn<Record, Integer> column7 = 
                new TableColumn<>("Paid");

                column7.setCellValueFactory(
                    new PropertyValueFactory<>("paid"));
                column7.setMinWidth(150);
                column7.setStyle( "-fx-alignment: CENTER;");
                
                TableColumn<Record, String> column8 = 
                new TableColumn<>("Comment");

                column8.setCellValueFactory(
                    new PropertyValueFactory<>("comment"));
                column8.setMinWidth(150);
                column8.setStyle( "-fx-alignment: CENTER;");
                
                TableColumn<Record, String> column9 = 
                new TableColumn<>("Date");

                column9.setCellValueFactory(
                    new PropertyValueFactory<>("Ldate"));
                column9.setMinWidth(150);
                column9.setStyle( "-fx-alignment: CENTER;");
                
                TableColumn<Record,Integer> column10=new TableColumn<>("Balance");
                column10.setMinWidth(150);
                column10.setStyle( "-fx-alignment: CENTER;");
                column10.setCellValueFactory(new PropertyValueFactory<>("balance"));
  

                tableView.getColumns().addAll(column9,column2,column3,column4,column5,column6,column7,column10,column8);
             
                // addButtonToTable(new Stage(),tableView);
                //method start
                        
                         TableColumn<Record, Void> column11 = new TableColumn("Options");
                         column11.setStyle( "-fx-alignment: CENTER;");
                         Stage stage=new Stage();

        Callback<TableColumn<Record, Void>, TableCell<Record, Void>> cellFactory = new Callback<TableColumn<Record, Void>, TableCell<Record, Void>>() {
            @Override
            public TableCell<Record, Void> call(final TableColumn<Record, Void> param) {
                final TableCell<Record, Void> cell = new TableCell<Record, Void>() {

                    private final Button btn = new Button("Options");
                        

                    {
                        btn.setTextFill(Color.CORNFLOWERBLUE);
                        btn.setOnAction((ActionEvent event) -> {
                            Record data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            //alert for edit and delete
                          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //alert image
                Image al = new Image("images/confirm.jpg");
                al_conf = new ImageView(al);
   
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               alert.setGraphic(al_conf);
               alert.setTitle("Select");
               alert.setHeaderText("Update or Delete");

		ButtonType edit = new ButtonType("EDIT");
		ButtonType delete = new ButtonType("DELETE");
		ButtonType cancle = new ButtonType("CANCLE");
                ButtonType p_balance = new ButtonType("PAY BALANCE");
                

		// Remove default ButtonTypes
		alert.getButtonTypes().clear();
                if(data.getBalance()==0){
                    alert.getButtonTypes().addAll(edit,delete,cancle);
                }else
                                {
                                    alert.getButtonTypes().addAll(p_balance,edit,delete,cancle);
                                }
                

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();
	
		if (option.get() == edit) {
			//eidt method
                        alert.close(); 
                        //record update starts
                        Alert updt = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to Update?"+" "+data.getName(), ButtonType.YES, ButtonType.NO);
                //alert image
                Image al2 = new Image("images/confirm.jpg");
                al_conf = new ImageView(al2);
   
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               updt.setGraphic(al_conf);

        // clicking X also means no
        ButtonType result = updt.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.NO.equals(result)) {
            // consume event i.e. ignore close request 
            updt.close();
        }else{
          //delting record
          System.out.println(data.getName());
          int id =data.getId();
          //show data in fields
           //LABELS
           
         name=new Label("NAME");name.setTextFill(Color.CORNFLOWERBLUE);
         _size=new Label("SIZE");_size.setTextFill(Color.CORNFLOWERBLUE);
         number=new Label("NUMBER");number.setTextFill(Color.CORNFLOWERBLUE);
         unit_price=new Label("UNIT PRICE");unit_price.setTextFill(Color.CORNFLOWERBLUE);//unitprice
         paid=new Label("PAID");paid.setTextFill(Color.CORNFLOWERBLUE);
         comment=new Label("COMMENT");comment.setTextFill(Color.CORNFLOWERBLUE);
         Button update=new Button("Update");
         update.setTextFill(Color.CORNFLOWERBLUE);
         
        //TEXTEDIT BOXS
      TextField name_=new TextField();name_.setMinWidth(500);
      TextField siz=new TextField();siz.setMinWidth(505);
      
       TextField nums=new TextField();nums.setMinWidth(480);
       nums.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            nums.setText(newValue.replaceAll("[^\\d]", ""));
            });
       TextField unit_price_=new TextField();unit_price_.setMinWidth(480);
       unit_price_.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            unit_price_.setText(newValue.replaceAll("[^\\d]", ""));
            });
      TextField  paid_=new TextField();paid_.setMinWidth(500);
      paid_.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            paid_.setText(newValue.replaceAll("[^\\d]", ""));
            });
       TextField comment_=new TextField();comment_.setMinWidth(470);
    
 
       HBox name_box=new HBox();
      
       name_box.getChildren().addAll(name,name_);
       name_box.setSpacing(50);
       name_box.setAlignment(Pos.CENTER);
       
        HBox sz_box=new HBox();
       
      sz_box.getChildren().addAll(_size,siz);
       sz_box.setSpacing(57);
       sz_box.setAlignment(Pos.CENTER);
       
        HBox num_box=new HBox();
       
     num_box.getChildren().addAll(number,nums);
       num_box.setSpacing(37);
       num_box.setAlignment(Pos.CENTER);
       
        HBox t_box=new HBox();
       t_box.getChildren().addAll(unit_price,unit_price_);
       t_box.setSpacing(20);
       t_box.setAlignment(Pos.CENTER);
        
        HBox p_box=new HBox();
     
       p_box.getChildren().addAll(paid,paid_);
       p_box.setSpacing(58);
       p_box.setAlignment(Pos.CENTER);
       
        HBox c_box=new HBox();
      
      c_box.getChildren().addAll(comment,comment_);
       c_box.setSpacing(32);
       c_box.setAlignment(Pos.CENTER);
          
         name_.setText(data.getName());
         siz.setText(data.getSize());
         nums.setText(String.valueOf(data.getNumber()));
         unit_price_.setText(String.valueOf(data.getUnit_price()));
         paid_.setText(String.valueOf(data.getPaid()));
         comment_.setText(data.getComment());
         
           update.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                  
                  try {//update recored whre id ==id
                                    
                    LocalDate ndate=java.time.LocalDate.now();//date now
                    String date=ndate.toString();
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                //alert image
                Image al = new Image("images/confirm.jpg");
                al_conf = new ImageView(al);
     
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               alert1.setGraphic(al_conf);

                // clicking X also means no
                ButtonType result = alert1.showAndWait().orElse(ButtonType.NO);

                if (ButtonType.NO.equals(result)) {
                    // consume event i.e. ignore close request 
                    alert1.close();
                }else{
                //connect to db
                Connection  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alp?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");

                int id =data.getId();
                PreparedStatement pst = con.prepareStatement("UPDATE records set nm =?,size =?,num =?,unit_p =?,t_amount =?,paid =?,cmt =?,d_date=?  where id=?");

                 
                 pst.setString(1, name_.getText());
                 pst.setString(2,siz.getText());
                 pst.setInt(3,Integer.parseInt(nums.getText()));
                 pst.setInt(4,Integer.parseInt(unit_price_.getText()));
                 pst.setInt(5, Integer.parseInt(unit_price_.getText())*Integer.parseInt(nums.getText()));
                 pst.setInt(6, Integer.parseInt(paid_.getText()));
                 pst.setString(7, comment_.getText());
                 pst.setString(8, date);
                 pst.setInt(9,id);
                 //update
                 
                 pst.executeUpdate(); 
                 tableView.getItems().clear();
                  refresh();
                  
                   stage.setOnCloseRequest(e -> {
                //tableView.getColumns().clear();
                //tableView.getItems().clear();
                //tableView.setItems(row1);
               // refresh();
                
            });
                    stage.close();
                  //alert
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("ALEX PHOTO STUDIO");
        
                    alert.setHeaderText("Updating Record");
                    alert.setContentText("Record Updated successfully!");
                    ButtonType btn = alert.showAndWait().orElse(ButtonType.NO);
                   if (ButtonType.OK.equals(result)) {
                     primaryStage.setScene(view_details);
                   
                        primaryStage.show();
                        alert1.close();
                    
                }
                    
                }
                      
                        } catch (SQLException ex) {
                            Logger.getLogger(ALEX_PHOTO_STUDIO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
          updt.close();
          
                  
              }
                    
                });
                 VBox up_root=new VBox();
        up_root.getChildren().addAll(name_box,sz_box,num_box,t_box,p_box,c_box,update);
        up_root.setSpacing(20);
        up_root.setStyle("-fx-font-size: 20;-fx-background-color: purple ;");
       
        up_root.setAlignment(Pos.CENTER);
        Scene update_Scene = new Scene(up_root,Color.PURPLE);
        
	stage.setTitle("Updating Record)");
        //stage.initModality(Modality.NONE);
        stage.setMinWidth(800);
		stage.setScene(update_Scene);
		stage.showAndWait();
    
        }
            
                        //record update ends
                        
		}else if(option.get()==p_balance){
                    Stage p_stage=new Stage();
                     System.out.println(data.getName());
          int id =data.getId();
          //show data in fields
           //LABELS
           
         Label paid_amount=new Label("Previously Paid Amount: "+String.valueOf(data.getPaid()));paid_amount.setTextFill(Color.GREEN);
         Label balance=new Label("Balance:  "+String.valueOf(data.getBalance()));balance.setTextFill(Color.RED);
        
         Button pay=new Button("Pay");
         pay.setTextFill(Color.CORNFLOWERBLUE);
   
      TextField  paid_balance=new TextField();paid_balance.setMinWidth(500);
      paid_balance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            paid_balance.setText(newValue.replaceAll("[^\\d]", ""));
            });
    
       HBox name_box=new HBox();
       name_box.getChildren().addAll(paid_amount,balance);
       name_box.setSpacing(50);
       name_box.setAlignment(Pos.CENTER);
       
     
        HBox p_box=new HBox();
       p_box.getChildren().addAll(paid_amount,paid_balance);
       p_box.setSpacing(58);
       p_box.setAlignment(Pos.CENTER);
       pay.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event) {
                            //pay balance method
                            //txtfield empty
                            if(paid_balance.getText().equals("")){
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("ALEX PHOTO STUDIO");
                     Image al = new Image("images/caution.jpg");
                   al_info = new ImageView(al);
                   al_info.setFitHeight(200);
                   al_info.setPreserveRatio(true);
                   alert.setGraphic(al_info);
                    alert.setHeaderText("CAUTION");
                    alert.setContentText("AMOUNT FIELD EMPTY PLEASE!!");
                   
                     alert.showAndWait();
                     
                    }else{
                                try {
                                    //pick value connect to database and update the balnce and amount paid
                                    
                                    //connect to db
                                    Connection  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alp?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
                                    
                                    int id =data.getId();
                                    PreparedStatement pst = con.prepareStatement("UPDATE records set paid =? where id=?");
                                    //new amount old + paid balance
                                    int amount=data.getPaid()+Integer.parseInt(paid_balance.getText());
                                   
                                    pst.setInt(1,amount );
                                   
                                    pst.setInt(2,id);
                                    //update
                                    
                                    pst.executeUpdate();
                                    tableView.getItems().clear();
                                    refresh();
                                   
                                    p_stage.close();
                                    //alert
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("ALEX PHOTO STUDIO");
                                    
                                    alert.setHeaderText("Paying Balance");
                                    alert.setContentText("Balance Paid successfully!");
                                    ButtonType btn = alert.showAndWait().orElse(ButtonType.NO);
                                    
                                    if (ButtonType.OK.equals(btn)) {
                                       
                                        alert.close();
                                        
                                    }               } catch (SQLException ex) {
                                    Logger.getLogger(ALEX_PHOTO_STUDIO.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            
                            
                            
                            
                            
                            
                            }
                            
                        }
           
       });
        VBox pay_root=new VBox();
        pay_root.getChildren().addAll(name_box,p_box,pay);
        pay_root.setSpacing(20);
        pay_root.setStyle("-fx-font-size: 20;");
        pay_root.setAlignment(Pos.CENTER);
        pay_root.setStyle("-fx-background-color: purple ;");
        Scene pay_Scene = new Scene(pay_root,Color.PURPLE);
        
	p_stage.setTitle("Balance Update)");
        //stage.initModality(Modality.NONE);
        p_stage.setMinWidth(800);
		p_stage.setScene(pay_Scene);
		p_stage.showAndWait();
    
        // paid_.setText(String.valueOf(data.getPaid()));
        
                    
                    
                }
              /**  else if(option.get()==delete){
                    //delte method
                    alert.close();
                    //open delte alert
                     //on power click
                Alert del = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to delete?"+" "+data.getName(), ButtonType.YES, ButtonType.NO);
                //alert image
                Image al2 = new Image("images/confirm.jpg");
                al_conf = new ImageView(al2);
   
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               del.setGraphic(al_conf);

        // clicking X also means no
        ButtonType result = del.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.NO.equals(result)) {
            // consume event i.e. ignore close request 
            del.close();
        }else{
          //delting record
          System.out.println(data.getName());
          int id =data.getId();
          
                        try {
                          Connection  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alp?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
                          PreparedStatement  pst = con.prepareStatement("delete from records where id = ? ");
                            pst.setInt(1, id);
                            pst.executeUpdate();
                            
                            getTableView().getItems().clear();//refreshes table
                            refresh();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(ALEX_PHOTO_STUDIO.class.getName()).log(Level.SEVERE, null, ex);
                        }
          del.close();
        }
            
      
                    //end delete alert
                }**/
                else  {
			//cancle operation
                        alert.close();
                        
		} 
                            
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        column11.setCellFactory(cellFactory);

        tableView.getColumns().add(column11);
            
                
            while(rs.next()){
                record=new Record();
                record.setId(rs.getInt("id"));
                record.setName(rs.getString("nm"));
               
                record.setSize(rs.getString("size"));
                
                record.setNumber(rs.getInt("num"));
               record.setUnit_price(rs.getInt("unit_p"));
                record.setTotal_amount(rs.getInt("t_amount"));
               
                record.setPaid(rs.getInt("paid"));
                int total=rs.getInt("unit_p")*rs.getInt("num");
                //required comments
               if(record.getBalance()==0){
                    record.setComment("CLEARED");
               }else{
                    record.setComment("NOT CLEARED");
               }
               // record.setComment(rs.getString("cmt"));
          
                record.setLdate(rs.getString("d_date"));
               
                
               
                //buttons
                edit=new Button();edit.setText("Edit");
                delete=new Button();delete.setText("Delete");
                 //adding row to listview
               // VBox row=new VBox();
              /** TableView **/
              row1.add(record);

             
            }
            tableView.setItems(row1);
            
            // layout2.getChildren().addAll(borderPane,back_home);
            //close connection
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        //end of table
                
            }
            
        });
         /**End of Table to show records **/
         //power alert message
         power.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setOnCloseRequest(evt->{
                    // allow user to decide between yes and no on clicking on x
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?", ButtonType.YES, ButtonType.NO);
//alert image
                Image al = new Image("images/confirm.jpg");
                al_conf = new ImageView(al);
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               alert.setGraphic(al_conf);
        // clicking X also means no
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.NO.equals(result)) {
            // consume event i.e. ignore close request 
            evt.consume();
        }
                });
                //on power click
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close ALP?", ButtonType.YES, ButtonType.NO);
                //alert image
                Image al = new Image("images/confirm.jpg");
                al_conf = new ImageView(al);
     
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               alert.setGraphic(al_conf);

        // clicking X also means no
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.NO.equals(result)) {
            // consume event i.e. ignore close request 
            alert.close();
        }else{
          primaryStage.close();
        }
                
            }
             
         });
         Image pimg = new Image("images/power.jpg");
      ImageView pview = new ImageView(pimg);
      pview.setFitHeight(80);
      pview.setPreserveRatio(true);
        Button pwr=new Button();
      
        pwr.setGraphic(pview);
        pwr.setMinHeight(80);
        back_h_power=new HBox();
         back_h_power.getChildren().addAll(back_home,pwr);
       back_h_power.setSpacing(200);
       back_h_power.setAlignment(Pos.BOTTOM_CENTER);
       pwr.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                 //on power click
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close ALP?", ButtonType.YES, ButtonType.NO);
                //alert image
                Image al = new Image("images/confirm.jpg");
                al_conf = new ImageView(al);
   
               al_conf.setFitHeight(200);
               al_conf.setPreserveRatio(true);
               alert.setGraphic(al_conf);

        // clicking X also means no
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.NO.equals(result)) {
            // consume event i.e. ignore close request 
            alert.close();
        }else{
          primaryStage.close();
        }
            }
           
       });
       
       layout2.getChildren().addAll(tableView,Search_b,back_h_power);
       
        //StackPane root = new StackPane();
        root.getChildren().addAll(logImg,name_box,sz_box,num_box,t_box,p_box,c_box,btns,power);
        root.setSpacing(20);
        //GaussianBlur blur = new GaussianBlur();
       // root.setEffect(blur);
        root.setStyle("-fx-font-size: 20;-fx-background-color: purple ;-fx-background-image: url(\"images/blur_A.png\");-fx-background-size: 1024 768;-fx-background-repeat: stretch;-fx-background-position: center;");
       
        root.setAlignment(Pos.CENTER);
       // root.setStyle("-fx-background-color: blue;");
       
         home = new Scene(root);
//         view_details=new Scene(root,950,600);
       
        //primaryStage.setResizable(false);
       // primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("ALEX PHOTO STUDIO");
         primaryStage.setFullScreen(true);//full screen scene
         primaryStage.setFullScreenExitHint("");//removes full screen mode toast message
        primaryStage.setScene(home);
        primaryStage.show();
    }
    
    private void addButtonToTable(Stage stage,TableView t) {
       

    }
    public void refresh(){
        try {
            String selectStmt = "SELECT * FROM records;";
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alp?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            PreparedStatement preparedStatement = connection.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                record=new Record();
                record.setId(rs.getInt("id"));
                record.setName(rs.getString("nm"));
               
                record.setSize(rs.getString("size"));
                
                record.setNumber(rs.getInt("num"));
                record.setUnit_price(rs.getInt("unit_p"));
                record.setTotal_amount(rs.getInt("t_amount"));
               
                record.setPaid(rs.getInt("paid"));
                int total=rs.getInt("unit_p")*rs.getInt("num");
               //required comments
               if(record.getBalance()==0){
                    record.setComment("CLEARED");
               }else{
                    record.setComment("NOT CLEARED");
               }
               
          
                record.setLdate(rs.getString("d_date"));
               
                
               
                //buttons
                edit=new Button();edit.setText("Edit");
                delete=new Button();delete.setText("Delete");
                //adding row to listview
                // VBox row=new VBox();
                /** TableView **/
                row1.add(record);
                
                
            }
            tableView.setItems(row1);
             connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ALEX_PHOTO_STUDIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void ClearFields(){
        nm.setText("");
        sz.setText("");
        num.setText("");
        unit_prc.setText("");
        pd.setText("");
        cmt.setText("");
    }
   
    public int EnterRecord(Record record) throws SQLException{
        int result=0;
        String INSERT_USERS_SQL = "INSERT INTO records" +
            "  (nm,size,num,unit_p,t_amount,paid,cmt,d_date) VALUES " +
            " ( ?, ?, ?,?, ?,?,?,?);";
        
       try(Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alp?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
           // preparedStatement.setInt(1, 1);
            preparedStatement.setString(1, record.getName());
            preparedStatement.setString(2, record.getSize());
            preparedStatement.setInt(3, record.getNumber());
            preparedStatement.setInt(4, record.getUnit_price());
            preparedStatement.setInt(5, record.getTotal_amount());
            preparedStatement.setInt(6, record.getPaid());
            preparedStatement.setString(7, record.getComment());
            preparedStatement.setString(8,record.getLdate().toString());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            //close connection
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return result;
        
    }
    //fetch all records
  
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
