public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        System.out.println("PDF: " + describe(pdf, req));
        System.out.println("CSV: " + describe(csv, req));
        System.out.println("JSON: " + describe(json, req));
    }

    private static String describe(Exporter e, ExportRequest r) {
        ExportResult out = e.export(r);
        if (!out.success) return "ERROR: " + out.errorMsg;
        return "OK bytes=" + out.bytes.length;
    }
}
