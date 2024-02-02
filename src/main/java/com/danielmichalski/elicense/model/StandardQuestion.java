package com.danielmichalski.elicense.model;

/**
 * Author: Daniel
 */
public class StandardQuestion {
    private int id;
    private int points;
    private String question;
    private YesNoAnswer userAnswer;
    private YesNoAnswer correctAnswer;
    private String module;
    private String mediaPath;
    private MediaType mediaType;

    public StandardQuestion(int id, int points, String question, YesNoAnswer userAnswer, YesNoAnswer correctAnswer, String module, String mediaPath, MediaType mediaType) {
        this.id = id;
        this.points = points;
        this.question = question;
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

    public void setPoints(int points) {
        this.points = points;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(YesNoAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public void setMediaType(MediaType mediaType) {
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

    public String getModule() {
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
        sb.append("id=").append(id);
        sb.append(", points=").append(points);
        sb.append(", question='").append(question).append('\'');
        sb.append(", userAnswer=").append(userAnswer);
        sb.append(", correctAnswer=").append(correctAnswer);
        sb.append(", module='").append(module).append('\'');
        sb.append(", mediaPath='").append(mediaPath).append('\'');
        sb.append(", mediaType=").append(mediaType);
        sb.append('}');
        return sb.toString();
    }
}
