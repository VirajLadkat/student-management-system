package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Teacher;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeacherPdfExporter {

    private List<Teacher> teachers;

    public TeacherPdfExporter(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(BaseColor.BLUE);

        Paragraph title = new Paragraph("Teacher List", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 5.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);

        String[] headers = {"ID", "Name", "Email", "Employee ID"};
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }

    private void writeTableData(PdfPTable table) {
        for (Teacher teacher : teachers) {
            table.addCell(String.valueOf(teacher.getId()));
            table.addCell(teacher.getUsername());
            table.addCell(teacher.getEmail());
            table.addCell(teacher.getEmployeeId());
        }
    }
}
