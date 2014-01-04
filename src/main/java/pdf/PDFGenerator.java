package pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import model.SpecialistQuestion;
import model.StandardQuestion;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author: Daniel
 * Date: 25.11.13.
 */
public class PDFGenerator {
    private int userPoints;
    private int maxPosiblePoints;

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    String imie = System.getProperty("firstName");
    String nazwisko = System.getProperty("lastName");
    String pesel = System.getProperty("pesel");

    private Font fNormal;
    private Font fBold;

    public PDFGenerator(List<StandardQuestion> standardQuestions,
                        List<SpecialistQuestion> specialistQuestions,
                        int userPoint,
                        int maxPosiblePoints) {

        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        this.userPoints = userPoint;
        this.maxPosiblePoints = maxPosiblePoints;

        try {
            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
            fNormal = new Font(bf, 8, Font.NORMAL);
            fBold = new Font(bf, 8, Font.BOLD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void generatePDFFile() {
        try {
            savePDF();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(null,
                "Wystąpił błąd podczas zapisu pliku PDF: " + e,
                "Informacja",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void savePDF() throws Exception {

        Document document = new Document();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Plik PDF", ".pdf"));

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            PdfPTable table1 = new PdfPTable(new float[]{1f, 17f, 2f, 3f, 3f});
            addToTable(table1, true);

            PdfPTable table2 = new PdfPTable(new float[]{1f, 17f, 2f, 3f, 3f});
            addToTable(table2, false);

            document.add(new Paragraph("Imię i nazwisko: " + imie + " " + nazwisko, fNormal));
            document.add(new Paragraph("PESEL: " + pesel, fNormal));
            document.add(new Paragraph("Liczba uzyskanych punktów: " + userPoints + " z " + maxPosiblePoints, fNormal));
            document.add(new Paragraph("Data egzaminu: " + getDateInString(), fNormal));
            document.add(new Paragraph(" "));

            document.add(table1);

            document.add(new Paragraph(" "));

            document.add(table2);

            document.close();

            JOptionPane.showMessageDialog(null,
                    "Pomyślnie zapisano plik PDF",
                    "Informacja",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String getDateInString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();

        return sdf.format(date);
    }

    private void addToTable(PdfPTable table, boolean standardQuestions) throws IOException, DocumentException {

        table.addCell(new Paragraph("Nr", fBold));

        if (standardQuestions) {
            table.addCell(new Paragraph("Pytanie standardowe", fBold));
        } else {
            table.addCell(new Paragraph("Pytanie specjalistyczne", fBold));
        }

        table.addCell(new Paragraph("Waga pytania", fBold));
        table.addCell(new Paragraph("Odpowiedź użytkownika", fBold));
        table.addCell(new Paragraph("Prawidłowa odpowiedź", fBold));


        if (standardQuestions) {
            addStandardQuestionsToTable(table);
        } else {
            addSpecialistQuestionsToTable(table);
        }
    }

    private void addStandardQuestionsToTable(PdfPTable table) {
        int i = 1;
        for (StandardQuestion standardQuestion : standardQuestions) {
            Paragraph questionNumber = new Paragraph(i + ".", fNormal);
            Paragraph question = new Paragraph(standardQuestion.getQuestion(), fNormal);
            Paragraph points = new Paragraph(String.valueOf(standardQuestion.getPoints()), fNormal);
            Paragraph correctAnswer = new Paragraph(standardQuestion.getCorrectAnswer().toString(), fNormal);
            Paragraph userAnswer = null;

            if (standardQuestion.getUserAnswer() != null) {
                userAnswer = new Paragraph(standardQuestion.getUserAnswer().toString(), fNormal);
            } else {
                userAnswer = new Paragraph("Brak odp.", fNormal);
            }

            table.addCell(questionNumber);
            table.addCell(question);
            table.addCell(points);
            table.addCell(userAnswer);
            table.addCell(correctAnswer);

            i++;
        }
    }

    private void addSpecialistQuestionsToTable(PdfPTable table) {
        int i = 1;
        for (SpecialistQuestion specialistQuestion : specialistQuestions) {
            Paragraph questionNumber = new Paragraph(i + ".", fNormal);
            Paragraph question = new Paragraph(specialistQuestion.getQuestion(), fNormal);
            Paragraph points = new Paragraph(String.valueOf(specialistQuestion.getPoints()), fNormal);
            Paragraph correctAnswer = new Paragraph(specialistQuestion.getCorrectAnswer().toString(), fNormal);
            Paragraph userAnswer = null;

            if (specialistQuestion.getUserAnswer() != null) {
                userAnswer = new Paragraph(specialistQuestion.getUserAnswer().toString(), fNormal);
            } else {
                userAnswer = new Paragraph("Brak odp.", fNormal);
            }

            table.addCell(questionNumber);
            table.addCell(question);
            table.addCell(points);
            table.addCell(userAnswer);
            table.addCell(correctAnswer);

            i++;
        }
    }
}
