package tech.cathywu.excelreader.demo.processor;

import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import tech.cathywu.excelreader.sdk.annotation.SourceColumn;
import tech.cathywu.excelreader.sdk.processor.Processor;

import java.lang.reflect.Field;
import java.util.*;

public class ReadProcessor implements Processor {

    private Object source;
    private List<Sheet> sheets;

    public ReadProcessor(Object source) {
        this.source = source;
    }

    @Override
    public void process(Workbook workbook) {
        this.sheets = getAllSheets(workbook);

        Class<?> clazz = source.getClass();
        Map<Field, String> fieldMap = readFieldsFrom(clazz);

//        try {
//            clazz.getField("name").set(source, "aaaa");
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }

    private Map<Field, String> readFieldsFrom(Class<?> clazz) {
        Map<Field, String> fieldsMap = new HashMap<>();

        Arrays.stream(clazz.getFields()).map(field -> {
            SourceColumn sourceColumn = field.getDeclaredAnnotation(SourceColumn.class);
            return Pair.create(field, sourceColumn.value());
        }).forEach(pair -> fieldsMap.put(pair.getKey(), pair.getValue()));

        return fieldsMap;
    }

    private List<Sheet> getAllSheets(Workbook workbook) {
        List<Sheet> sheets = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            if (workbook.isSheetVeryHidden(i)) {
                sheets.add(workbook.getSheetAt(i));
            }
        }
        return sheets;
    }
}
