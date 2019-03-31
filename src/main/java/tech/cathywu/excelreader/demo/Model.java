package tech.cathywu.excelreader.demo;

import lombok.Data;
import tech.cathywu.excelreader.demo.processor.ReadProcessor;
import tech.cathywu.excelreader.sdk.annotation.ExcelProcessor;
import tech.cathywu.excelreader.sdk.annotation.SourceColumn;

@Data
@ExcelProcessor(processor = ReadProcessor.class)
public class Model {

    @SourceColumn("Profile.Name")
    private String name;

    @SourceColumn("Profile.Gender")
    private String gender;

    @SourceColumn("Profile.Age")
    private int age;

    @SourceColumn("Other Information.Grade")
    private String grade;

    @SourceColumn("Other Information.Title")
    private String title;

}
