    package com.example.app;

    import javafx.animation.PauseTransition;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextField;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.Pane;
    import javafx.scene.text.Text;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.util.Duration;

    import java.io.File;
    import java.io.IOException;
    import java.net.URL;
    import java.util.*;

    import javafx.animation.KeyFrame;
    import javafx.animation.Timeline;
    import javafx.animation.KeyFrame;
    import javafx.animation.Timeline;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Label;
    import javafx.util.Duration;
    import java.net.URL;
    import java.util.ResourceBundle;
    import javafx.event.ActionEvent;
    import javafx.util.Duration;
    import javafx.animation.PauseTransition;
    import javafx.util.Duration;
    import com.example.cmd.Dictionary;
    import com.example.cmd.Word;
    public class GameController implements Initializable {

        private Stage stage ;
        private Scene scene;
        private Parent root;
        @FXML
        private Pane myPane;

        @FXML
        private ImageView hinhanh ;

        @FXML
        private Button textForWord1;
        @FXML
        private Button textForWord2;
        @FXML
        private Button textForWord3;
        @FXML
        private Button textForWord4;

        @FXML

        private int num_live ;
        private ArrayList<String>VietNam =new ArrayList<>();
        private  ArrayList<String>English = new ArrayList<>();

        private String VietAnswer ;
        private String EngAnswer;
        @FXML
        private Text tuTiengViet;

        @FXML
        private Text diemso ;


        private int mark;
        private String guessWord1;
        private String guessWord2;
        private String guessWord3;
        private String guessWord4;
        private Timeline gameTimer;
        private int gameTimeInSeconds = 90; // Thời gian chơi trong giây

        @FXML
        private ImageView gameOverImage;

        @FXML
        private ArrayList<ImageView> heart = new ArrayList<>();

        @FXML
        private ImageView heart1;
        @FXML
        private ImageView heart2;
        @FXML
        private ImageView heart3;
        @FXML
        private ImageView heart4;
        @FXML
        private ImageView heart5;
        @FXML
        private ImageView heart6;
        private int maxMark ;
        ArrayList<Button> textList = new ArrayList<>();
        @FXML
        private Label timerLabel;

        @FXML
        private Text dapan;

        @FXML
        private AnchorPane gameAnchorPane;

        private String[] imagePaths = {
                String.valueOf(Main.class.getResource("images/dra.png")),
                String.valueOf(Main.class.getResource("images/incorrect.png")),
                String.valueOf(Main.class.getResource("images/correct.jpg")),
        };
        private int currentImageIndex;

        private boolean answer = false;
        private boolean gameOverShowed = false;
        private boolean winGameShowed = false;

        public void menuGame(ActionEvent event) throws IOException {
            new SceneSwitch(gameAnchorPane,"helloGameView.fxml");
        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Dictionary dictionary = Main.dictionary;
            for (Word x : dictionary.getWords()) {
                if(x.getWord_explain().length()<=68) {
                    this.VietNam.add(x.getWord_explain());
                    this.English.add(x.getWord_target());
                }
            }
            currentImageIndex =0 ;
            hinhanh.setImage(new Image(imagePaths[currentImageIndex]));
            gameOverShowed = false;
            winGameShowed = false;
            heart.add(heart1);
            heart.add(heart2);
            heart.add(heart3);
            heart.add(heart4);
            heart.add(heart5);
            heart.add(heart6);
                textList.add(textForWord1);
                textList.add(textForWord2);
                textList.add(textForWord3);
                textList.add(textForWord4);
            if (tuTiengViet != null) {
                Random random = new Random();
                int randomPos = random.nextInt(VietNam.size());
                VietAnswer = VietNam.get(randomPos);
                EngAnswer = English.get(randomPos);
                num_live = 6;
                tuTiengViet.setText(VietAnswer);
                setDiem();
                int randomText = random.nextInt(textList.size());
                textList.get(randomText).setText(EngAnswer);
                for (int i = 0 ;i < textList.size(); i ++) {
                    if(i != randomText) {
                        int randomNumber = random.nextInt(VietNam.size());
                        String tiengAnh = English.get(randomNumber);
                        textList.get(i).setText(tiengAnh);
                    }
                }
                mark = 0;
                maxMark = 0 ;
            }
            startTimer();
        }

        // them mới 27/11
        private void startTimer() {
            gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
            gameTimer.setCycleCount(gameTimeInSeconds);
            gameTimer.setOnFinished(this::handleGameTimerFinished);
            gameTimer.play();
        }
        private void updateTimer(ActionEvent event) {
            gameTimeInSeconds--;
            timerLabel.setText(gameTimeInSeconds + "s");
        }
        private void handleGameTimerFinished(ActionEvent event)  {
            if(gameOverShowed == false && mark <10) {
                try {
                    if (mark >= gameScore.getIns().getMaxScore()) {
                        gameScore.getIns().setMaxScore(mark);
                    }
                    gameScore.getIns().setDiemCuaBan(mark);
                    showGameOverScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (winGameShowed == false && mark >=10) {
                try {
                    if (mark >= gameScore.getIns().getMaxScore()) {
                        gameScore.getIns().setMaxScore(mark);
                    }
                    gameScore.getIns().setDiemCuaBan(mark);
                    showWinGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setDiem() {
            diemso.setText(mark+"");
        }

        public void getTextInPut1() throws IOException {
//            answer = true;
            if(!answer) {
                guessWord1 = textForWord1.getText();

                if (!guessWord1.equalsIgnoreCase(EngAnswer)) {
                    hinhanh.setImage(new Image(imagePaths[1]));
                    dapan.setText("Đáp án là: " + EngAnswer);
                    num_live--;
                    if(num_live>=0) {
                        heart.get(num_live).setVisible(false);
                    }
                    if(num_live<0 ) {
                        if(mark >= gameScore.getIns().getMaxScore()) {
                            gameScore.getIns().setMaxScore(mark);
                        }
                        gameScore.getIns().setDiemCuaBan(mark);
                        if(mark<=9) {
                            showGameOverScreen();
                            gameOverShowed = true ;
                            winGameShowed = true;
                        } else {
                            showWinGame();
                            gameOverShowed = true;
                            winGameShowed = true;
                        }
                    }
                    answer = true;
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        textForWord1.setText("");
                        guessWord1 = "";

                        if (num_live >= 0) {
                            hinhanh.setImage(new Image(imagePaths[0]));
                            Random random = new Random();
                            int randomPos = random.nextInt(VietNam.size());
                            VietAnswer = VietNam.get(randomPos);
                            EngAnswer = English.get(randomPos);
                            VietNam.remove(randomPos);
                            English.remove(randomPos);
                            dapan.setText("");
                            tuTiengViet.setText(VietAnswer);
                            int randomText = random.nextInt(textList.size());
                            textList.get(randomText).setText(EngAnswer);
                            for (int i = 0 ;i < textList.size(); i ++) {
                                if(i != randomText) {
                                    int randomNumber = random.nextInt(VietNam.size());
                                    String tiengAnh = English.get(randomNumber);
                                    textList.get(i).setText(tiengAnh);
                                }
                            }
                        }
                    });
                    canceled.play();
//                    answer = false;
                } else {
                    hinhanh.setImage(new Image(imagePaths[2]));
                    dapan.setText("chính xác!");
                    textForWord1.setText("");
                    guessWord1 = "";
                    answer = true;
                    mark ++ ;
                    setDiem();
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        hinhanh.setImage(new Image(imagePaths[0]));
                        Random random = new Random();
                        int randomPos = random.nextInt(VietNam.size());
                        VietAnswer = VietNam.get(randomPos);
                        EngAnswer = English.get(randomPos);
                        VietNam.remove(randomPos);
                        English.remove(randomPos);
                        tuTiengViet.setText(VietAnswer);
                        int randomText = random.nextInt(textList.size());
                        textList.get(randomText).setText(EngAnswer);
                        for (int i = 0 ;i < textList.size(); i ++) {
                            if(i != randomText) {
                                int randomNumber = random.nextInt(VietNam.size());
                                String tiengAnh = English.get(randomNumber);
                                textList.get(i).setText(tiengAnh);
                            }
                        }
                        dapan.setText("");
                    });
                    canceled.play();
//                    answer = false;
                }
            }
            PauseTransition cancel = new PauseTransition(Duration.seconds(1));
            cancel.setOnFinished(event -> {
                answer = false;
            });
            cancel.play();
        }

        public void getTextInPut2() throws IOException {
            if(!answer) {
                guessWord2 = textForWord2.getText();

                if (!guessWord2.equalsIgnoreCase(EngAnswer)) {
                    hinhanh.setImage(new Image(imagePaths[1]));
                    dapan.setText("Đáp án là: " + EngAnswer);
                    num_live--;
                    if(num_live>=0) {
                        heart.get(num_live).setVisible(false);
                    }
                    if(num_live<0 ) {
                        if(mark >= gameScore.getIns().getMaxScore()) {
                            gameScore.getIns().setMaxScore(mark);
                        }
                        gameScore.getIns().setDiemCuaBan(mark);
                        if(mark<=9) {
                            showGameOverScreen();
                            gameOverShowed = true ;
                            winGameShowed = true;
                        } else {
                            showWinGame();
                            gameOverShowed = true;
                            winGameShowed = true;
                        }
                    }
                    answer = true;
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        textForWord1.setText("");
                        guessWord2 = "";

                        if (num_live >= 0) {
                            hinhanh.setImage(new Image(imagePaths[0]));
                            Random random = new Random();
                            int randomPos = random.nextInt(VietNam.size());
                            VietAnswer = VietNam.get(randomPos);
                            EngAnswer = English.get(randomPos);
                            VietNam.remove(randomPos);
                            English.remove(randomPos);
                            dapan.setText("");
                            tuTiengViet.setText(VietAnswer);
                            int randomText = random.nextInt(textList.size());
                            textList.get(randomText).setText(EngAnswer);
                            for (int i = 0 ;i < textList.size(); i ++) {
                                if(i != randomText) {
                                    int randomNumber = random.nextInt(VietNam.size());
                                    String tiengAnh = English.get(randomNumber);
                                    textList.get(i).setText(tiengAnh);
                                }
                            }
                        }
                    });
                    canceled.play();
                } else {
                    hinhanh.setImage(new Image(imagePaths[2]));
                    dapan.setText("chính xác!");
                    textForWord2.setText("");
                    guessWord2 = "";
                    answer = true;
                    mark ++ ;
                    setDiem();
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        hinhanh.setImage(new Image(imagePaths[0]));
                        Random random = new Random();
                        int randomPos = random.nextInt(VietNam.size());
                        VietAnswer = VietNam.get(randomPos);
                        EngAnswer = English.get(randomPos);
                        VietNam.remove(randomPos);
                        English.remove(randomPos);
                        tuTiengViet.setText(VietAnswer);
                        int randomText = random.nextInt(textList.size());
                        textList.get(randomText).setText(EngAnswer);
                        for (int i = 0 ;i < textList.size(); i ++) {
                            if(i != randomText) {
                                int randomNumber = random.nextInt(VietNam.size());
                                String tiengAnh = English.get(randomNumber);
                                textList.get(i).setText(tiengAnh);
                            }
                        }
                        dapan.setText("");
                    });
                    canceled.play();
                }
            }
            PauseTransition cancel = new PauseTransition(Duration.seconds(1));
            cancel.setOnFinished(event -> {
                answer = false;
            });
            cancel.play();
        }
        public void getTextInPut3() throws IOException {
            if(!answer) {
                guessWord3 = textForWord3.getText();

                if (!guessWord3.equalsIgnoreCase(EngAnswer)) {
                    hinhanh.setImage(new Image(imagePaths[1]));
                    dapan.setText("Đáp án là: " + EngAnswer);
                    num_live--;
                    if(num_live>=0) {
                        heart.get(num_live).setVisible(false);
                    }
                    if(num_live<0 ) {
                        if(mark >= gameScore.getIns().getMaxScore()) {
                            gameScore.getIns().setMaxScore(mark);
                        }
                        gameScore.getIns().setDiemCuaBan(mark);
                        if(mark<=9) {
                            showGameOverScreen();
                            gameOverShowed = true ;
                            winGameShowed = true;
                        } else {
                            showWinGame();
                            gameOverShowed = true;
                            winGameShowed = true;
                        }
                    }
                    answer = true;
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        textForWord3.setText("");
                        guessWord3 = "";

                        if (num_live >= 0) {
                            hinhanh.setImage(new Image(imagePaths[0]));
                            Random random = new Random();
                            int randomPos = random.nextInt(VietNam.size());
                            VietAnswer = VietNam.get(randomPos);
                            EngAnswer = English.get(randomPos);
                            VietNam.remove(randomPos);
                            English.remove(randomPos);
                            dapan.setText("");
                            tuTiengViet.setText(VietAnswer);
                            int randomText = random.nextInt(textList.size());
                            textList.get(randomText).setText(EngAnswer);
                            for (int i = 0 ;i < textList.size(); i ++) {
                                if(i != randomText) {
                                    int randomNumber = random.nextInt(VietNam.size());
                                    String tiengAnh = English.get(randomNumber);
                                    textList.get(i).setText(tiengAnh);
                                }
                            }
                        }
                    });
                    canceled.play();
                } else {
                    hinhanh.setImage(new Image(imagePaths[2]));
                    dapan.setText("chính xác!");
                    textForWord3.setText("");
                    guessWord3 = "";
                    answer = true ;
                    mark ++ ;
                    setDiem();
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        hinhanh.setImage(new Image(imagePaths[0]));
                        Random random = new Random();
                        int randomPos = random.nextInt(VietNam.size());
                        VietAnswer = VietNam.get(randomPos);
                        EngAnswer = English.get(randomPos);
                        VietNam.remove(randomPos);
                        English.remove(randomPos);
                        tuTiengViet.setText(VietAnswer);
                        int randomText = random.nextInt(textList.size());
                        textList.get(randomText).setText(EngAnswer);
                        for (int i = 0 ;i < textList.size(); i ++) {
                            if(i != randomText) {
                                int randomNumber = random.nextInt(VietNam.size());
                                String tiengAnh = English.get(randomNumber);
                                textList.get(i).setText(tiengAnh);
                            }
                        }
                        dapan.setText("");
                    });
                    canceled.play();
                }
            }
            PauseTransition cancel = new PauseTransition(Duration.seconds(1));
            cancel.setOnFinished(event -> {
                answer = false;
            });
            cancel.play();
        }
        public void getTextInPut4() throws IOException {
            if(!answer) {
                guessWord4 = textForWord4.getText();

                if (!guessWord4.equalsIgnoreCase(EngAnswer)) {
                    hinhanh.setImage(new Image(imagePaths[1]));
                    dapan.setText("Đáp án là: " + EngAnswer);
                    num_live--;
                    if(num_live>=0) {
                        heart.get(num_live).setVisible(false);
                    }
                    if(num_live<0 ) {
                        if(mark >= gameScore.getIns().getMaxScore()) {
                            gameScore.getIns().setMaxScore(mark);
                        }
                        gameScore.getIns().setDiemCuaBan(mark);
                        if(mark<=9) {
                            showGameOverScreen();
                            gameOverShowed = true ;
                            winGameShowed = true;
                        } else {
                            showWinGame();
                            gameOverShowed = true;
                            winGameShowed = true;
                        }
                    }
                    answer = true ;
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        textForWord4.setText("");
                        guessWord1 = "";

                        if (num_live >= 0) {
                            hinhanh.setImage(new Image(imagePaths[0]));
                            Random random = new Random();
                            int randomPos = random.nextInt(VietNam.size());
                            VietAnswer = VietNam.get(randomPos);
                            EngAnswer = English.get(randomPos);
                            VietNam.remove(randomPos);
                            English.remove(randomPos);
                            dapan.setText("");
                            tuTiengViet.setText(VietAnswer);
                            int randomText = random.nextInt(textList.size());
                            textList.get(randomText).setText(EngAnswer);
                            for (int i = 0 ;i < textList.size(); i ++) {
                                if(i != randomText) {
                                    int randomNumber = random.nextInt(VietNam.size());
                                    String tiengAnh = English.get(randomNumber);
                                    textList.get(i).setText(tiengAnh);
                                }
                            }
                        }
                    });
                    canceled.play();
                } else {
                    hinhanh.setImage(new Image(imagePaths[2]));
                    dapan.setText("chính xác!");
                    textForWord4.setText("");
                    guessWord4 = "";
                    answer = true;
                    mark ++ ;
                    setDiem();
                    PauseTransition canceled = new PauseTransition(Duration.seconds(1));
                    canceled.setOnFinished(event -> {
                        hinhanh.setImage(new Image(imagePaths[0]));
                        Random random = new Random();
                        int randomPos = random.nextInt(VietNam.size());
                        VietAnswer = VietNam.get(randomPos);
                        EngAnswer = English.get(randomPos);
                        VietNam.remove(randomPos);
                        English.remove(randomPos);
                        tuTiengViet.setText(VietAnswer);
                        int randomText = random.nextInt(textList.size());
                        textList.get(randomText).setText(EngAnswer);
                        for (int i = 0 ;i < textList.size(); i ++) {
                            if(i != randomText) {
                                int randomNumber = random.nextInt(VietNam.size());
                                String tiengAnh = English.get(randomNumber);
                                textList.get(i).setText(tiengAnh);
                            }
                        }
                        dapan.setText("");
                    });
                    canceled.play();
                }
            }
            PauseTransition cancel = new PauseTransition(Duration.seconds(1));
            cancel.setOnFinished(event -> {
                answer = false;
            });
            cancel.play();
        }

        public void showGameOverScreen() throws IOException {
            new SceneSwitch(gameAnchorPane,"gameOverView.fxml");
        }
        public int getmaxMark() {
            return this.maxMark;
        }
        public void showWinGame() throws IOException {
            new SceneSwitch(gameAnchorPane,"winGameView.fxml");
        }

    }

