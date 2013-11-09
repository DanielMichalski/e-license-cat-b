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
    private ABCAnswer correctAnswer;
    private Module module;
    private String mediaPath;
    private MediaType mediaType;

    public SpecialistQuestion(int points,
                              String question,
                              String answerA,
                              String answerB,
                              String answerC,
                              ABCAnswer userAnswer,
                              ABCAnswer correctAnswer,
                              Module module,
                              String mediaPath,
                              MediaType mediaType) {

        this.points = points;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.module = module;
        this.mediaPath = mediaPath;
        this.mediaType = mediaType;
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
        return correctAnswer;
    }

    public Module getModule() {
        return module;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public MediaType getMediaType() {
        return mediaType;
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
        sb.append(", correctAnswer=").append(correctAnswer);
        sb.append(", module=").append(module);
        sb.append(", mediaPath='").append(mediaPath).append('\'');
        sb.append(", mediaType=").append(mediaType);
        sb.append('}');
        return sb.toString();
    }
}
