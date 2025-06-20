package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Teacher;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class TeacherExcelExporter {

    private List<Teacher> teachers;

    public TeacherExcelExporter(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void export(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Teachers");

        Row header = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        String[] headers = {"ID", "Name", "Email", "Employee ID"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }

        int rowCount = 1;
        for (Teacher teacher : teachers) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(teacher.getId());
            row.createCell(1).setCellValue(teacher.getUsername());
            row.createCell(2).setCellValue(teacher.getEmail());
            row.createCell(3).setCellValue(teacher.getEmployeeId());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
