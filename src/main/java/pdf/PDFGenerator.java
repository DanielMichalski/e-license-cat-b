package pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import model.SpecialistQuestion;
import model.StandardQuestion;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 25.11.13.
 */
public class PDFGenerator {
    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    String imie = "Tomasz";
    String nazwisko = "Kowalski";
    String pesel = "83737392932";

    public PDFGenerator(List<StandardQuestion> standardQuestions,
                        List<SpecialistQuestion> specialistQuestions) {

        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        try {
            savePDF();
        } catch (Exception e) {

        }
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

            PdfPTable table = new PdfPTable(new float[] { 2f, 15f, 2f, 4f});

            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,
                    BaseFont.CP1250, BaseFont.EMBEDDED);
            Font f = new Font(bf, 14, Font.NORMAL);

            int i = 1;
            for (StandardQuestion standardQuestion : standardQuestions) {
                Paragraph questionNumber = new Paragraph(i + ".", f);
                Paragraph question = new Paragraph(standardQuestion.getQuestion(), f);
                Paragraph correctAnswer = new Paragraph(standardQuestion.getCorrectAnswer().toString(), f);
                Paragraph userAnswer = null;

                if (standardQuestion.getUserAnswer() != null) {
                    userAnswer = new Paragraph(standardQuestion.getUserAnswer().toString(), f);
                } else {
                    userAnswer = new Paragraph("Brak odp.", f);
                }

                table.addCell(questionNumber);
                table.addCell(question);
                table.addCell(correctAnswer);
                table.addCell(userAnswer);

                i++;
            }

            i = 1;
            for (SpecialistQuestion specialistQuestion : specialistQuestions) {
                Paragraph questionNumber = new Paragraph(i + ".", f);
                Paragraph question = new Paragraph(specialistQuestion.getQuestion(), f);
                Paragraph correctAnswer = new Paragraph(specialistQuestion.getCorrectAnswer().toString(), f);
                Paragraph userAnswer = null;

                if (specialistQuestion.getUserAnswer() != null) {
                    userAnswer = new Paragraph(specialistQuestion.getUserAnswer().toString(), f);
                } else {
                    userAnswer = new Paragraph("Brak odp.", f);
                }

                table.addCell(questionNumber);
                table.addCell(question);
                table.addCell(correctAnswer);
                table.addCell(userAnswer);

                i++;
            }

            document.add(new Paragraph("Imię: " + imie, f));
            document.add(new Paragraph("Nazwisko: " + nazwisko, f));
            document.add(new Paragraph("PESEL: " + pesel, f));
            document.add(new Paragraph(" "));

            document.add(table);
            document.close();
        }
    }
}
