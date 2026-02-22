// base class for all exporters
// null check is handled here so subclasses don't need to worry about it
public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {
        if (req == null) return new ExportResult("request is null");
        return doExport(req);
    }

    protected abstract ExportResult doExport(ExportRequest req);
}
