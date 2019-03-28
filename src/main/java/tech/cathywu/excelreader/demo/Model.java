package tech.cathywu.excelreader.demo;

import tech.cathywu.excelreader.annotation.SourceColumn;

public class Model {

    @SourceColumn("name")
    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
