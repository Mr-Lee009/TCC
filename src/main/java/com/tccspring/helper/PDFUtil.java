package com.tccspring.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFUtil {
    public static void main(String[] args) {
        // tạo một document
        Document document = new Document();

        try {
            // khởi tạo một PdfWriter truyền vào document và FileOutputStream
            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld_ABC.pdf"));

            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            document.add(new Paragraph("A Hello World PDF document."));

//            Phrase phrase = new Phrase();
//            for(int i = 0; i < 10; i++) {
//                Chunk chunk = new Chunk("Hello World!!\n");
//                phrase.add(chunk);
//            }
//            document.add(phrase);
            //Show(document);

            //CreatLinkInText(document);

            //Tạo một ordered list
            PaintTable(document);
            //document.add(unorderedList);
            // đóng file
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Show(Document document) throws DocumentException, IOException {
        //Khai báo 2 paragraph
        Paragraph paragraph1 = new Paragraph("This is Paragraph 1");
        Paragraph paragraph2 = new Paragraph("This is Paragraph 2");
        //Định dạng đoạn văn bản thứ nhất
        paragraph1.setIndentationLeft(80);
        paragraph1.setIndentationRight(80);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setSpacingAfter(15);
        //Đinh dạng đoạn văn bản thứ 2
        paragraph2.setSpacingBefore(15);
        paragraph2.setAlignment(Element.ALIGN_LEFT);
        //Thêm nội dung cho cả 2 đoạn văn bản trên
        Phrase phrase = new Phrase("This is a large sentence.");
        for(int i = 0; i < 10; i++)
        {
            paragraph1.add(phrase);
            paragraph2.add(phrase);
        }

        //Thêm 2 đoạn văn bản vào document
        document.add(paragraph1);
        document.add(paragraph2);
    }
    public static void CreatLinkInText(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase("You can download the IText at "));
        //Tạo 1 link
        Anchor anchor = new Anchor("Download");
        anchor.setReference("http://sourceforge.net/projects/itext/");
        //Thêm link vào đoạn văn bản
        paragraph.add(anchor);
        //Thêm đoạn văn bản vào document
        document.add(paragraph);
    }

    public static void PaintTable(Document document) throws IOException, DocumentException {
        //Khởi tạo một table có 3 cột
        PdfPTable table = new PdfPTable(3);
        //Khởi tạo 3 ô header
        PdfPCell header1 = new PdfPCell(new Paragraph("Header 1"));
        PdfPCell header2 = new PdfPCell(new Paragraph("Header 2"));
        PdfPCell header3 = new PdfPCell(new Paragraph("Header 3"));
        //Thêm 3 ô header vào table
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);

        //Khởi tạo 3 ô data: ô số 1 là string, ô số 2 là ảnh, ô số 3 là table
        PdfPCell data1 = new PdfPCell(new Paragraph("Data String"));
        PdfPCell data2 = new PdfPCell(Image.getInstance("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.seekpng.com%2Fipng%2Fu2q8a9q8q8i1r5i1_laughing-reaction-emoji-haha-react%2F&psig=AOvVaw3VRK04drJ0tGdQ090VC5mo&ust=1614337287357000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKCe-ZfxhO8CFQAAAAAdAAAAABAD"), false);

        PdfPTable nestedTable = new PdfPTable(2);
        nestedTable.addCell(new Paragraph("Nested Cell 1"));
        nestedTable.addCell(new Paragraph("Nested Cell 2"));
        PdfPCell data3 = new PdfPCell(nestedTable);
        //Thêm data vào bảng.
        table.addCell(data1);
        table.addCell(data2);
        table.addCell(data3);

        document.add(table);
    }
}
