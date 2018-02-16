package com.ttpsc.otapowiczp.springApp.converters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.ttpsc.otapowiczp.springApp.converters.DateToYearConverter;

import java.time.LocalDate;
import java.util.Date;

public class YearConverter implements Converter {


    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
        LocalDate date = (LocalDate) o;
        writer.setValue(String.valueOf(DateToYearConverter.getYearFromDate(date)));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext unmarshallingContext) {
        return DateToYearConverter.parseStringToDate(reader.getValue());
    }

    @Override
    public boolean canConvert(Class aClass) {
        return Date.class.isAssignableFrom(aClass);
    }
}