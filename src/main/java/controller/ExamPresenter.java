package controller;

import database.QuestionsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import model.enums.YesOrNoAnswer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import util.Const;
import view.exam_view.ExamPointsRightPanel;
import view.exam_view.ExamQuestionsLeftPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPresenter {
    private List<StandardQuestion> standardQuestions;
    private Map<Integer, YesOrNoAnswer> standardAnswers;

    private List<SpecialistQuestion> specialistQuestions;
    private Map<Integer, YesOrNoAnswer> specialistAnswers;

    private int actualStandardQuestion;
    private int actualSpecialistQuestion;

    private ExamQuestionsLeftPanel examQuestionsLeftPanel;
    private ExamPointsRightPanel.BasicPartPanel basicPartPanel;
    private ExamPointsRightPanel.SpecjalistPartPanel specjalistPartPanel;
    private ExamPointsRightPanel.TimeAndBtnConfirmPanel timeAndBtnConfirmPanel;

    private JLabel timerLbl;
    private JButton yesBtn;
    private JButton noBtn;
    private JLabel howManyPoints;

    private Timer timer;
    private boolean isStandardPartCompleted;

    public ExamPresenter() {
        try {
            this.standardQuestions = QuestionsDao.get20StandardQuestion();
            this.standardAnswers = new HashMap<Integer, YesOrNoAnswer>();
            this.specialistQuestions = QuestionsDao.get12SpecialistQuestion();
            this.specialistAnswers = new HashMap<Integer, YesOrNoAnswer>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        actualStandardQuestion = 1;
        actualSpecialistQuestion = 1;

        timer = new Timer();
        isStandardPartCompleted = false;
    }

    public void nextQuestion() {
        TimerCountdown countDown = new TimerCountdown(this, timerLbl, 5);
        timer.schedule(countDown, 0, 100);

        if (!isStandardPartCompleted) {
            if (actualStandardQuestion <= 20) {
                if (actualStandardQuestion == 20) {
                    isStandardPartCompleted = true;
                }
                setNumberOfStandardQuestion(actualStandardQuestion);

                actualStandardQuestion++;
            }
        } else {
            if (actualSpecialistQuestion <= 12) {
                changePanelFromStandardToSpecial();
                setNumberOfSpecialistQuestion(actualSpecialistQuestion);

                actualSpecialistQuestion++;
            } else {
                timer.cancel();
                System.out.println("Koniec");
            }
        }

    }

    private void changePanelFromStandardToSpecial() {
        examQuestionsLeftPanel.changePanelFromStandarToSpecial();
    }

    public void setNumberOfStandardQuestion(int questionNumber) {
        basicPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(standardQuestions.get(questionNumber-1).getQuestion());
        howManyPoints.setText(standardQuestions.get(questionNumber-1).getPoints() + " pkt");
    }

    public void setNumberOfSpecialistQuestion(int questionNumber) {
        specjalistPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(specialistQuestions.get(questionNumber-1).getQuestion());
        howManyPoints.setText(specialistQuestions.get(questionNumber-1).getPoints() + " pkt");
    }

    class YesBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(yesBtn);
        }
    }

    class NoBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(noBtn);
        }
    }

    class ConfirmBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (yesBtn.isSelected()) {
                System.out.println("TAK");
            } else if (noBtn.isSelected()) {
                System.out.println("NIE");
            } else {
                System.out.println("żaden");
            }
        }
    }

    private void markBtn(JButton whichButtonToMark) {
        unmarkBtns();
        whichButtonToMark.setBackground(Const.Colors.markedBtnBgColor);
        whichButtonToMark.setSelected(true);
    }

    private void unmarkBtns() {
        yesBtn.setBackground(Const.Colors.unmarkedBtnBgColor);
        noBtn.setBackground(Const.Colors.unmarkedBtnBgColor);

        yesBtn.setSelected(false);
        noBtn.setSelected(false);
    }

    public void setTimerLbl(JLabel timerLbl) {
        this.timerLbl = timerLbl;
    }

    public void setYesBtn(JButton yesBtn) {
        this.yesBtn = yesBtn;
        //this.yesBtn.addActionListener(new YesBtnListener());
    }

    public void setNoBtn(JButton noBtn) {
        this.noBtn = noBtn;
        //this.noBtn.addActionListener(new NoBtnListener());
    }

    public void setConfirmBtn(JButton confirmBtn) {
        confirmBtn.addActionListener(new ConfirmBtnListener());
    }

    public void setHowManyPoints(JLabel howManyPoints) {
        this.howManyPoints = howManyPoints;
    }

    public void setExamQuestionsLeftPanel(ExamQuestionsLeftPanel examQuestionsLeftPanel) {
        this.examQuestionsLeftPanel = examQuestionsLeftPanel;
    }

    public void setBasicPartPanel(ExamPointsRightPanel.BasicPartPanel basicPartPanel) {
        this.basicPartPanel = basicPartPanel;
    }

    public void setSpecjalistPartPanel(ExamPointsRightPanel.SpecjalistPartPanel specjalistPartPanel) {
        this.specjalistPartPanel = specjalistPartPanel;
    }

    public void setTimeAndBtnConfirmPanel(ExamPointsRightPanel.TimeAndBtnConfirmPanel timeAndBtnConfirmPanel) {
        this.timeAndBtnConfirmPanel = timeAndBtnConfirmPanel;
    }

}
