package tech.cathywu.excelreader.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyProcessor {

    private static MyProcessor instance = null;

    private List<String> filePaths;

    private MyProcessor() {
    }

    public synchronized static MyProcessor getInstance() {
        if (instance == null) {
            instance = new MyProcessor();
        }
        return instance;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public List<Model> read() {
        if (filePaths == null || filePaths.size() == 0) {
            return null;
        }

        List<Workbook> workbooks = filePaths.stream().map(this::convertFrom).collect(Collectors.toList());
        List<Sheet> sheets = mergeFiles(workbooks);

        return null;
    }

    private List<Sheet> mergeFiles(List<Workbook> workbooks) {
        List<List<Sheet>> allSheets = workbooks.stream().map(workbook -> {
            List<Sheet> sheets = new ArrayList<>();
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                if (workbook.isSheetVeryHidden(i)) {
                    sheets.add(workbook.getSheetAt(i));
                }
            }
            return sheets;
        }).collect(Collectors.toList());
        return allSheets.stream().flatMap((Function<List<Sheet>, Stream<Sheet>>) Collection::stream).collect(Collectors.toList());
    }

    private Workbook convertFrom(String filePath) {
        try {
            if (filePath.toLowerCase().endsWith("." + XSSFWorkbookType.XLSX)
                    || filePath.endsWith("." + XSSFWorkbookType.XLSM)) {
                return new XSSFWorkbook(filePath);
            } else {
                return new HSSFWorkbook(new FileInputStream(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
