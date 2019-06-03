package domain.services.extractorAndPersistExcel;

import javax.persistence.EntityManager;

public interface PersistExcelLine {
    void PersistLines(String[] excelLine, EntityManager em) throws Exception;
}
