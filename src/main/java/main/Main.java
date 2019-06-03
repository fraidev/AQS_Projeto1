package main;
import domain.services.extractorAndPersistExcel.fiscalizacaoExtractorAndPersistExcel.CsvExtractorAndPersistExcel;
import domain.services.extractorAndPersistExcel.fiscalizacaoExtractorAndPersistExcel.FiscalizacaoPersistExcelLines;
import domain.services.extractorAndPersistExcel.ExtractorAndPersistExcel;
import infra.JPAUtil;

import javax.persistence.EntityManager;


public class Main {
	
	public static void main(String[] args) {
		FiscalizacaoPersistExcelLines fiscalizacaoPersistExcelLines = new FiscalizacaoPersistExcelLines();
		ExtractorAndPersistExcel csvExtractorAndPersistExcel = new CsvExtractorAndPersistExcel("C:/Empresas - São Paulo.csv", fiscalizacaoPersistExcelLines);
		csvExtractorAndPersistExcel.Run();
	}
}