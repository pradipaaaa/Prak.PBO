package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import model.Transaction;
import model.TransactionDetail;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PDFGenerator {
    public static void generateReceipt(Transaction trx) {
        String filename = "struk_" + trx.getId() + ".pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font normal = new Font(Font.FontFamily.HELVETICA, 10);

            document.add(new Paragraph("STRUK BELANJA", bold));
            document.add(new Paragraph("Kasir Minimarket", normal));
            document.add(new Paragraph("Tanggal: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            document.add(new Paragraph("========================================"));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("Nama Produk");
            table.addCell("Qty");
            table.addCell("Harga");
            table.addCell("Subtotal");

            for (TransactionDetail d : trx.getDetails()) {
                table.addCell(d.getProductName());
                table.addCell(String.valueOf(d.getQuantity()));
                table.addCell(String.format("%.0f", d.getUnitPrice()));
                table.addCell(String.format("%.0f", d.getSubtotal()));
            }

            document.add(table);
            document.add(new Paragraph("========================================"));
            document.add(new Paragraph("Total: Rp " + String.format("%.0f", trx.getTotalAmount())));
            document.add(new Paragraph("Tunai: Rp " + String.format("%.0f", trx.getCashReceived())));
            document.add(new Paragraph("Kembali: Rp " + String.format("%.0f", trx.getChangeAmount())));

            document.close();

            // buka otomatis
            java.awt.Desktop.getDesktop().open(new java.io.File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

