package pdf;

import com.lowagie.text.Document;
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

    public PDFGenerator(List<StandardQuestion> standardQuestions,
                        List<SpecialistQuestion> specialistQuestions,
                        int userPoint,
                        int maxPosiblePoints) {

        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        this.userPoints = userPoint;
        this.maxPosiblePoints = maxPosiblePoints;
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

            PdfPTable table = new PdfPTable(new float[]{2f, 15f, 4f, 4f});

            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,
                    BaseFont.CP1250, BaseFont.EMBEDDED);
            Font fNormal = new Font(bf, 12, Font.NORMAL);
            Font fBold = new Font(bf, 12, Font.BOLD);

            table.addCell(new Paragraph("Nr", fBold));
            table.addCell(new Paragraph("Pytanie", fBold));
            table.addCell(new Paragraph("Odpowiedź użytkownika", fBold));
            table.addCell(new Paragraph("Prawidłowa odpowiedź", fBold));


            int i = 1;
            for (StandardQuestion standardQuestion : standardQuestions) {
                Paragraph questionNumber = new Paragraph(i + ".", fNormal);
                Paragraph question = new Paragraph(standardQuestion.getQuestion(), fNormal);
                Paragraph correctAnswer = new Paragraph(standardQuestion.getCorrectAnswer().toString(), fNormal);
                Paragraph userAnswer = null;

                if (standardQuestion.getUserAnswer() != null) {
                    userAnswer = new Paragraph(standardQuestion.getUserAnswer().toString(), fNormal);
                } else {
                    userAnswer = new Paragraph("Brak odp.", fNormal);
                }

                table.addCell(questionNumber);
                table.addCell(question);
                table.addCell(userAnswer);
                table.addCell(correctAnswer);

                i++;
            }

            i = 1;
            for (SpecialistQuestion specialistQuestion : specialistQuestions) {
                Paragraph questionNumber = new Paragraph(i + ".", fNormal);
                Paragraph question = new Paragraph(specialistQuestion.getQuestion(), fNormal);
                Paragraph correctAnswer = new Paragraph(specialistQuestion.getCorrectAnswer().toString(), fNormal);
                Paragraph userAnswer = null;

                if (specialistQuestion.getUserAnswer() != null) {
                    userAnswer = new Paragraph(specialistQuestion.getUserAnswer().toString(), fNormal);
                } else {
                    userAnswer = new Paragraph("Brak odp.", fNormal);
                }

                table.addCell(questionNumber);
                table.addCell(question);
                table.addCell(userAnswer);
                table.addCell(correctAnswer);

                i++;
            }

            document.add(new Paragraph("Imię: " + imie, fNormal));
            document.add(new Paragraph("Nazwisko: " + nazwisko, fNormal));
            document.add(new Paragraph("PESEL: " + pesel, fNormal));
            document.add(new Paragraph("Liczba uzyskanych punktów: " + userPoints + " z " + maxPosiblePoints, fNormal));
            document.add(new Paragraph(" "));

            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null,
                    "Pomyślnie zapisano plik PDF",
                    "Informacja",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
