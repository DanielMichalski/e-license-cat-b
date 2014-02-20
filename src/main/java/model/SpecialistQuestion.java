package model;

/**
 * Author: Daniel
 */
public class SpecialistQuestion {
    private int id;
    private int points;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private ABCAnswer userAnswer;
    private ABCAnswer correctAnswer;
    private String module;
    private String mediaPath;
    private MediaType mediaType;

    public SpecialistQuestion(int id,
                              int points,
                              String question,
                              String answerA,
                              String answerB,
                              String answerC,
                              ABCAnswer userAnswer,
                              ABCAnswer correctAnswer,
                              String module,
                              String mediaPath,
                              MediaType mediaType) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
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

    public void setCorrectAnswer(ABCAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpecialistQuestion{");
        sb.append("id=").append(id);
        sb.append(", points=").append(points);
        sb.append(", question='").append(question).append('\'');
        sb.append(", answerA='").append(answerA).append('\'');
        sb.append(", answerB='").append(answerB).append('\'');
        sb.append(", answerC='").append(answerC).append('\'');
        sb.append(", userAnswer=").append(userAnswer);
        sb.append(", correctAnswer=").append(correctAnswer);
        sb.append(", module='").append(module).append('\'');
        sb.append(", mediaPath='").append(mediaPath).append('\'');
        sb.append(", mediaType=").append(mediaType);
        sb.append('}');
        return sb.toString();
    }
}
