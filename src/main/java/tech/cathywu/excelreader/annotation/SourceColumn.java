package tech.cathywu.excelreader.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

@Documented
@Inherited
public @interface SourceColumn {
    String value();
}
