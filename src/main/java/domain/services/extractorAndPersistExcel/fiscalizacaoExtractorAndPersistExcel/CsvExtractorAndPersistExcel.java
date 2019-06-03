package domain.services.extractorAndPersistExcel.fiscalizacaoExtractorAndPersistExcel;
import domain.services.extractorAndPersistExcel.ExtractorAndPersistExcel;
import domain.services.extractorAndPersistExcel.PersistExcelLine;
import infra.JPAUtil;

import javax.persistence.EntityManager;
import java.io.*;

public class CsvExtractorAndPersistExcel implements ExtractorAndPersistExcel {

    private String filePath;
    private PersistExcelLine persistExcelLine;

    public CsvExtractorAndPersistExcel(String filePath, PersistExcelLine persistExcelLine){
        this.filePath = filePath;
        this.persistExcelLine = persistExcelLine;
    }

    @Override
    public void Run() {
        EntityManager em = JPAUtil.getEntityManager();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] excelLine = line.split(";");
                persistExcelLine.PersistLines(excelLine, em);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        em.close();
        System.exit(0);
    }
}
