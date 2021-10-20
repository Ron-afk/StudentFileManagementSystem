package ui;

import model.Student;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationStarter {

    // EFFECTS: show greet and ending text, run the program
    public ApplicationStarter() throws FileNotFoundException {
        System.out.println("Welcome!");
        new MainPage();
        System.out.println("See you next time!");
    }

}
