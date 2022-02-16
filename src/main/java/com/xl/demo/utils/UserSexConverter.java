package com.xl.demo.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

/**
 * @author XL
 * @description
 * @date 2022/1/24 17:22
 */

public class UserSexConverter implements Converter<String> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 这里读的时候会调用
     *
     * @param context
     * @return
     */
    @Override
    public String convertToJavaData(ReadConverterContext<?> context) {
        String value = context.getReadCellData().getStringValue();
        if("男".equals(value)){
            value = "1";
        }else if ("女".equals(value)){
            value = "0";
        }else{
            value = "-1";
        }
        return value;
    }

    /**
     * 这里是写的时候会调用 不用管
     *
     * @return
     */
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
        if("1".equals(context.getValue())){
            context.setValue("男");
        }else if ("0".equals(context.getValue())){
            context.setValue("女");
        }else{
            context.setValue("未知");
        }
        return new WriteCellData<>(context.getValue());
    }

}
