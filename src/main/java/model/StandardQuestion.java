package model;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class StandardQuestion {
    private String question;
    private YesOrNoAnswer anser;

    public StandardQuestion(String question, YesOrNoAnswer anser) {
        this.question = question;
        this.anser = anser;
    }

    public String getQuestion() {
        return question;
    }

    public YesOrNoAnswer getAnser() {
        return anser;
    }
}
