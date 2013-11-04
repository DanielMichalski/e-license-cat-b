package model;

/**
 * Author: Daniel
 * Date: 04.11.13
 */
public class SpecialistQuestion {
    private String question;
    private ABCAnswer anser;
    private int points;

    public SpecialistQuestion(String question, ABCAnswer anser, int points) {
        this.question = question;
        this.anser = anser;
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public ABCAnswer getAnser() {
        return anser;
    }
}
