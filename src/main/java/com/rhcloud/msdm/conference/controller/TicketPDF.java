package com.rhcloud.msdm.conference.controller;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "ticketPDF")
public class TicketPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        Conference conference = (Conference) request.getSession().getAttribute("conference");

        Map<String, String> data = new HashMap<>();

        BaseFont helvetica =
                BaseFont.createFont(
                        BaseFont.HELVETICA,
                        BaseFont.CP1252,
                        BaseFont.NOT_EMBEDDED);
        Font font = new Font(helvetica, 12);

        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(0);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        Image image = Image.getInstance(new URL("http://pickaface.net/includes/themes/clean/img/slide4.png"));
        image.setWidthPercentage(10);
        PdfPCell cell0 = new PdfPCell(image, true);

        Font font1 = new Font(Font.HELVETICA , 25, Font.BOLD);

        PdfPCell participantCell = new PdfPCell(new Paragraph("Participant", font1));

        PdfPCell cell1 = new PdfPCell(new Paragraph("Conference name: "));
        PdfPCell cell2 = new PdfPCell(new Paragraph(conference.getName(), font));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Date: "));
        PdfPCell cell4 = new PdfPCell(new Paragraph("07.07.2015"));
        PdfPCell cell5 = new PdfPCell(new Paragraph("Participant"));
        PdfPCell cell6 = new PdfPCell(new Paragraph(user.getFirstName() + " " + user.getLastName(), font));

        cell0.setBorder(Rectangle.NO_BORDER);
        cell1.setBorder(Rectangle.NO_BORDER);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell3.setBorder(Rectangle.NO_BORDER);
        cell4.setBorder(Rectangle.NO_BORDER);
        cell5.setBorder(Rectangle.NO_BORDER);
        cell6.setBorder(Rectangle.NO_BORDER);
        participantCell.setBorder(Rectangle.NO_BORDER);

        participantCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        participantCell.setVerticalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell0);
        table.addCell(participantCell);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);

        document.add(table);
    }
}