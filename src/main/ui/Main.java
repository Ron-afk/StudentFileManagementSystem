package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        new SplashScreenUI();
        new MainPageUI();
//        try {
//            new ApplicationStarter();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run the application: file not found");
//        }
    }
}
