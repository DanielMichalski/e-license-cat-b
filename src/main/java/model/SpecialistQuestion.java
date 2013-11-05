package model;

import model.enums.ABCAnswer;

/**
 * Author: Daniel
 * Date: 04.11.13
 */
public class SpecialistQuestion {
    private int points;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private ABCAnswer correctAnser;
    private Module module;
    private String picturePath;
    private String moviePath;

    public SpecialistQuestion(
            int points,
            String question,
            String answerA,
            String answerB,
            String answerC,
            ABCAnswer correctAnser,
            Module module,
            String picturePath,
            String moviePath) {

        this.points = points;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.correctAnser = correctAnser;
        this.module = module;
        this.picturePath = picturePath;
        this.moviePath = moviePath;
    }

    public int getPoints() {
        return points;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public ABCAnswer getCorrectAnser() {
        return correctAnser;
    }

    public Module getModule() {
        return module;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getMoviePath() {
        return moviePath;
    }
}
