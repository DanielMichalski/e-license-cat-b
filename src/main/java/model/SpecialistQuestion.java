package model;

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
    private ABCAnswer userAnswer;
    private ABCAnswer correctAnser;
    private Module module;
    private String picturePath;
    private String videoPath;

    public SpecialistQuestion(
            int points,
            String question,
            String answerA,
            String answerB,
            String answerC,
            ABCAnswer userAnswer,
            ABCAnswer correctAnser,
            Module module,
            String picturePath,
            String videoPath) {

        this.points = points;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.userAnswer = userAnswer;
        this.correctAnser = correctAnser;
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

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public ABCAnswer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(ABCAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public ABCAnswer getCorrectAnswer() {
        return correctAnser;
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
        final StringBuilder sb = new StringBuilder("SpecialistQuestion{");
        sb.append("points=").append(points);
        sb.append(", question='").append(question).append('\'');
        sb.append(", answerA='").append(answerA).append('\'');
        sb.append(", answerB='").append(answerB).append('\'');
        sb.append(", answerC='").append(answerC).append('\'');
        sb.append(", userAnswer=").append(userAnswer);
        sb.append(", correctAnser=").append(correctAnser);
        sb.append(", module=").append(module);
        sb.append(", picturePath='").append(picturePath).append('\'');
        sb.append(", videoPath='").append(videoPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
