package DAO;

import java.io.IOException;
import java.util.List;

public interface DataImportExport<T> {
    //import
    void importData(String fileName) throws IOException;
    //export
    void exportData(String fileName, List<T> data) throws IOException;
}
