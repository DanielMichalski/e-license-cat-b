package model;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class StandardQuestion {
    private int points;
    private String question;
    private YesNoAnswer userAnswer;
    private YesNoAnswer correctAnswer;
    private Module module;
    private String mediaPath;
    private MediaType mediaType;

    public StandardQuestion(int points,
                            String question,
                            YesNoAnswer userAnswer,
                            YesNoAnswer correctAnswer,
                            Module module,
                            String mediaPath,
                            MediaType mediaType) {

        this.points = points;
        this.question = question;
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

    public YesNoAnswer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(YesNoAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public YesNoAnswer getCorrectAnswer() {
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
        final StringBuilder sb = new StringBuilder("StandardQuestion{");
        sb.append("points=").append(points);
        sb.append(", question='").append(question).append('\'');
        sb.append(", userAnswer=").append(userAnswer);
        sb.append(", correctAnswer=").append(correctAnswer);
        sb.append(", module=").append(module);
        sb.append(", mediaPath='").append(mediaPath).append('\'');
        sb.append(", mediaType=").append(mediaType);
        sb.append('}');
        return sb.toString();
    }
}
