import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    protected ExportResult doExport(ExportRequest req) {
        String json = "{\"title\":\"" + esc(req.title) + "\",\"body\":\"" + esc(req.body) + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String esc(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}
