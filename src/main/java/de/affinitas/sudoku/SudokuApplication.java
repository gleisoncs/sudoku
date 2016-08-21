package de.affinitas.sudoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Class support the initialization of application.
 * 
 * @author  Gleison Caetano
 * @see ApplicationContext
 * @see SpringApplication
 * 
 * */
@SpringBootApplication
public class SudokuApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SudokuApplication.class, args);
    }
}
