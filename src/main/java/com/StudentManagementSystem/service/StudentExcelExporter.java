package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Student;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class StudentExcelExporter {

    private List<Student> students;

    public StudentExcelExporter(List<Student> students) {
        this.students = students;
    }

    public void export(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        Row header = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        String[] headers = {"ID", "First Name", "Last Name", "Email"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }

        int rowCount = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getFirstname());
            row.createCell(2).setCellValue(student.getLastname());
            row.createCell(3).setCellValue(student.getEmail());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
