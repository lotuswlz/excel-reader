package tech.cathywu.excelreader.sdk.annotation;

import tech.cathywu.excelreader.sdk.processor.Processor;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

@Documented
@Inherited
public @interface ExcelProcessor {

    Class<? extends Processor> processor();
}
