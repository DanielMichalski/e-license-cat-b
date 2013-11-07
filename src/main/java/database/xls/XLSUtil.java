package database.xls;

import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import model.enums.ABCAnswer;
import model.enums.YesOrNoAnswer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class XLSUtil {
    public static Module getModuleFromRow(Row row) {
        int id = 0;
        String moduleName = "";

        for (int column = 0; column < 2; column++) {
            Cell tempCell = row.getCell(column);

            switch (column) {
                case 0:
                    id = (int) tempCell.getNumericCellValue();
                    break;
                case 1:
                    moduleName = tempCell.getStringCellValue();
                    break;
            }
        }

        return new Module(id, moduleName);
    }

    public static StandardQuestion getStandardQuestionFromRow(Row row)
            throws IOException, InvalidFormatException {

        int points = 0;
        String question = null;
        YesOrNoAnswer correctAnser = null;
        int moduleId = 0;
        Module module = null;
        String picturePath = null;
        String videoPath = null;

        if (row.getCell(3) != null) {
            points = (int) row.getCell(3).getNumericCellValue();
        }
        if (row.getCell(4) != null) {
            question = row.getCell(4).getStringCellValue();
        }
        if (row.getCell(8) != null) {
            correctAnser = YesOrNoAnswer.valueOf(row.getCell(8).getStringCellValue());
        }
        if (row.getCell(9) != null) {
            moduleId = (int) row.getCell(9).getNumericCellValue();
            module = XLSModuleDataProvider.getModuleFromModuleId(moduleId);
        }
        if (row.getCell(12) != null) {
            picturePath = row.getCell(12).getStringCellValue();
        }
        if (row.getCell(13) != null) {
            videoPath = row.getCell(13).getStringCellValue();
        }

        return new StandardQuestion(
                points,
                question,
                null,
                correctAnser,
                module,
                picturePath,
                videoPath);
    }

    public static SpecialistQuestion getSpecialistQuestionFromRow(Row row)
            throws IOException, InvalidFormatException {

        int points = 0;
        String question = null;
        String answerA = null;
        String answerB = null;
        String answerC = null;
        ABCAnswer correctAnser = null;
        int moduleId = 0;
        Module module = null;
        String picturePath = null;
        String videoPath = null;

        if (row.getCell(3) != null) {
            points = (int) row.getCell(3).getNumericCellValue();
        }
        if (row.getCell(4) != null) {
            question = row.getCell(4).getStringCellValue();
        }
        if (row.getCell(5) != null) {
            answerA = row.getCell(5).getStringCellValue();
        }
        if (row.getCell(6) != null) {
            answerB = row.getCell(6).getStringCellValue();
        }
        if (row.getCell(7) != null) {
            answerC = row.getCell(7).getStringCellValue();
        }
        if (row.getCell(8) != null) {
            correctAnser = ABCAnswer.valueOf(row.getCell(8).getStringCellValue());
        }
        if (row.getCell(9) != null) {
            moduleId = (int) row.getCell(9).getNumericCellValue();
            module = XLSModuleDataProvider.getModuleFromModuleId(moduleId);
        }
        if (row.getCell(12) != null) {
            picturePath = row.getCell(12).getStringCellValue();
        }
        if (row.getCell(13) != null) {
            videoPath = row.getCell(13).getStringCellValue();
        }

        return new SpecialistQuestion(
                points,
                question,
                answerA,
                answerB,
                answerC,
                null,
                correctAnser,
                module,
                picturePath,
                videoPath
        );

    }
}

