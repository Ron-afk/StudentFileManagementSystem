package ui;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class ApplicationStarter {

    // EFFECTS: show greet and ending text, run the program
    public ApplicationStarter() {
        System.out.println("Welcome!");
        new MainPage();
        System.out.println("See you next time!");
    }

}
