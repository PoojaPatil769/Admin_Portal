package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtil {

    public static Object[][] getData(String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(
                new File(System.getProperty("user.dir") + "\\testdata\\excel.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Safety check
        if (sheet == null) {
            workbook.close();
            fis.close();
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        DataFormatter df = new DataFormatter();

        // temporary storage
        Object[][] temp = new Object[rows - 1][cols];
        int actualRow = 0;

        for (int i = 1; i < rows; i++) {

            XSSFRow row = sheet.getRow(i);
            if (row == null) continue;

            boolean isEmpty = true;

            for (int j = 0; j < cols; j++) {

                String value = df.formatCellValue(row.getCell(j));
                temp[actualRow][j] = value;

                if (!value.trim().isEmpty()) {
                    isEmpty = false;
                }
            }

            // Only keep row if not empty
            if (!isEmpty) {
                actualRow++;
            }
        }

        // final array without empty rows
        Object[][] data = new Object[actualRow][cols];

        for (int i = 0; i < actualRow; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = temp[i][j];
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}