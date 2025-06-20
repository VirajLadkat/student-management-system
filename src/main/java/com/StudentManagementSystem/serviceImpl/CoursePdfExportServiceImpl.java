package com.StudentManagementSystem.serviceImpl;

import com.StudentManagementSystem.entity.Course;
import com.StudentManagementSystem.entity.Semester;
import com.StudentManagementSystem.entity.Subject;
import com.StudentManagementSystem.service.CoursePdfExportService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class CoursePdfExportServiceImpl implements CoursePdfExportService {

    @Override
    public void exportCourseToPdf(Course course, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        // Sanitize course name to avoid illegal filename characters
        String safeCourseName = course.getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + safeCourseName + "_structure.pdf";
        response.setHeader(headerKey, headerValue);

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Paragraph title = new Paragraph(course.getName() + " Structure", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("Duration: " + course.getDuration() + " semesters"));
        document.add(new Paragraph("Total Fees: ₹" + course.getTotalFees()));
        document.add(Chunk.NEWLINE);

        for (Semester semester : course.getSemesters()) {
            Font semFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.BLUE);
            document.add(new Paragraph("Semester " + semester.getNumber(), semFont));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100f);
            table.setSpacingBefore(10);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            cell.setPadding(5);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(Color.BLACK);

            cell.setPhrase(new Phrase("Subject", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Fee", font));
            table.addCell(cell);

            for (Subject subject : semester.getSubjects()) {
                table.addCell(subject.getName());
                table.addCell("₹" + subject.getFee());
            }

            document.add(table);
            document.add(Chunk.NEWLINE);
        }

        document.close();
    }
}
