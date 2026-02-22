import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    protected ExportResult doExport(ExportRequest req) {
        String body = req.body == null ? "" : req.body;
        if (body.length() > 20) {
            return new ExportResult("PDF cannot handle content > 20 chars");
        }
        String pdf = "PDF(" + req.title + "):" + body;
        return new ExportResult("application/pdf", pdf.getBytes(StandardCharsets.UTF_8));
    }
}
