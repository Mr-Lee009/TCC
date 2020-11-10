package com.tccspring.Helper;

import com.tccspring.model.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Student> studentList;

    public StudentExcelExporter(List<Student> StudentList) {
        this.studentList = StudentList;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Student Code ", style);
        createCell(row, 2, "Full Name", style);
        createCell(row, 3, "Password", style);
        createCell(row, 4, "Birthday", style);
        createCell(row, 5, "Class Id", style);
        createCell(row, 6, "Is monitor", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Student x : studentList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, x.getId().toString(), style);
            createCell(row, columnCount++, x.getStudent_code(), style);
            createCell(row, columnCount++, x.getFullname(), style);
            createCell(row, columnCount++, x.getPassword(), style);

            Date date =  x.getBirthday();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


            createCell(row, columnCount++,formatter.format(date), style);
            createCell(row, columnCount++, x.getClass_id().toString(), style);
            createCell(row, columnCount++, x.isIs_monitor(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
