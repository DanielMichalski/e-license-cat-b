package model;

import model.enums.YesOrNoAnswer;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class StandardQuestion {
    private int points;
    private String question;
    private YesOrNoAnswer userAnswer;
    private YesOrNoAnswer correctAnswer;
    private Module module;
    private String picturePath;
    private String videoPath;

    public StandardQuestion(int points,
                            String question,
                            YesOrNoAnswer userAnswer,
                            YesOrNoAnswer correctAnswer,
                            Module module,
                            String picturePath,
                            String videoPath) {

        this.points = points;
        this.question = question;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.module = module;
        this.picturePath = picturePath;
        this.videoPath = videoPath;
    }

    public int getPoints() {
        return points;
    }

    public String getQuestion() {
        return question;
    }

    public YesOrNoAnswer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(YesOrNoAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public YesOrNoAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public Module getModule() {
        return module;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StandardQuestion{");
        sb.append("points=").append(points);
        sb.append(", question='").append(question).append('\'');
        sb.append(", userAnswer=").append(userAnswer);
        sb.append(", correctAnswer=").append(correctAnswer);
        sb.append(", module=").append(module);
        sb.append(", picturePath='").append(picturePath).append('\'');
        sb.append(", videoPath='").append(videoPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
