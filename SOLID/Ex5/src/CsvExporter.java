import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    protected ExportResult doExport(ExportRequest req) {
        String body = req.body == null ? "" : req.body;
        String title = req.title == null ? "" : req.title;
        // proper csv escaping instead of replacing chars
        String escapedBody = "\"" + body.replace("\"", "\"\"") + "\"";
        String escapedTitle = "\"" + title.replace("\"", "\"\"") + "\"";
        String csv = "title,body\n" + escapedTitle + "," + escapedBody + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
