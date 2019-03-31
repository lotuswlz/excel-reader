package tech.cathywu.excelreader.sdk.processor;

import org.apache.poi.ss.usermodel.Workbook;

public interface Processor {

    void process(Workbook workbook);
}
