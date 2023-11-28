package ru.clevertec.util.impl;

import com.google.gson.Gson;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.clevertec.Font;
import ru.clevertec.util.WriterInPdf;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class WriterInPdfImpl implements WriterInPdf {

    Gson gson;

    @Override
    public void write(String str, String filePath, String outputFilePath) {
        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(filePath),
                    new PdfWriter(outputFilePath));
            Document document = docPdf(pdfDocument);
            document.add(new Paragraph(str));

            pdfDocument.close();
            log.info("PDF-документ успешно изменен.");
        } catch (IOException e) {
            log.error("Ошибка при изменении PDF-документа: " + e.getMessage());
        }
    }

    @Override
    public <T> void write(T t, String filePath, String outputFilePath) {

        try {
            String json = gson.toJson(t);
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(filePath),
                    new PdfWriter(outputFilePath));
            Document document = docPdf(pdfDocument);
            document.add(new Paragraph(json));

            pdfDocument.close();
            log.info("PDF-документ успешно изменен.");
        } catch (IOException e) {
            log.error("Ошибка при изменении PDF-документа: " + e.getMessage());
        }
    }

    @Override
    public <T> void write(java.util.List<T> data, String filePath, String outputFilePath) {

        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(filePath),
                    new PdfWriter(outputFilePath));
            Document document = docPdf(pdfDocument);

            List list = new List();

            data.stream()
                    .map(o -> gson.toJson(o))
                    .forEach(list::add);
            document.add(list);

            PdfDocument backgroundDocument = new PdfDocument(new PdfReader(filePath));
            PdfFormXObject backgroundXObject = backgroundDocument.getFirstPage().copyAsFormXObject(pdfDocument);

            for (int i = 1; i <= pdfDocument.getNumberOfPages(); i++) {
                PdfPage page = pdfDocument.getPage(i);
                PdfStream stream = page.newContentStreamBefore();
                new PdfCanvas(stream, page.getResources(), pdfDocument).addXObjectAt(backgroundXObject, 0, 0);
            }

            pdfDocument.close();
            log.info("PDF-документ успешно изменен.");
        } catch (IOException e) {
            log.error("Ошибка при изменении PDF-документа: " + e.getMessage());
        }
    }

    private Document docPdf(PdfDocument pdfDocument) throws IOException {
        PdfFont font = PdfFontFactory.createFont(Font.BKANT.getPath());
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        document.setFont(font)
                .setFontColor(ColorConstants.BLUE)
                .setTopMargin(200);

        return document;
    }
}
