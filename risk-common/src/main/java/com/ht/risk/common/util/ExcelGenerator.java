package com.ht.risk.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelGenerator {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        // TODO Auto-generated method stub

        File file = new File("D:\\user.xlsx");
        String fileName = file.getName();
        String className = "demo";
        File file1 = null;
        FileOutputStream fop = null;
        List<String> types = new ArrayList<String>();
        List<String> attributes = new ArrayList<String>();
        List<String> marks = new ArrayList<String>();
        if (fileName.endsWith("xlsx")) {
            XSSFWorkbook workbooks = new XSSFWorkbook(file);
            XSSFSheet xssfSheet = workbooks.getSheetAt(0);
            int totalRows = xssfSheet.getPhysicalNumberOfRows();
            XSSFRow row = xssfSheet.getRow(0);
            className = row.getCell(0).getStringCellValue();
            System.out.println(className + "total:" + totalRows);
            for (int i = 2; i < totalRows; i++) {
                XSSFRow row2 = xssfSheet.getRow(i);
                attributes.add(row2.getCell(0).getStringCellValue());
                types.add(row2.getCell(1).getStringCellValue());
                marks.add(row2.getCell(2).getStringCellValue());
            }
        } else {
            HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
            //读取默认第一个工作表sheet
            HSSFSheet sheet = workbook.getSheetAt(0);
            int firstRowNum = 0;
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow row = sheet.getRow(firstRowNum);
            className = row.getCell(0).getStringCellValue();
            for (int i = 2; i <= lastRowNum; i++) {
                HSSFRow row1 = sheet.getRow(i);
                attributes.add(row1.getCell(0).getStringCellValue());
                types.add(row1.getCell(1).getStringCellValue());
                marks.add(row1.getCell(2).getStringCellValue());
            }
        }

        StringBuffer sb = new StringBuffer();
        //导包
        sb.append("import io.swagger.annotations.ApiModelProperty;" + "\n");
        sb.append("import io.swagger.annotations.ApiModel;" + "\n");
        sb.append("import lombok.Data;" + "\n\n");
        //生成类
        sb.append("@Data" + "\n");
        sb.append("@ApiModel" + "\n");
        sb.append("public class " + className + "{\n\n");
        sb.append(CreateUtil.appendPrivate(types, attributes, marks));
        sb.append("}");

        file1 = new File("d:/" + className + ".java");
        fop = new FileOutputStream(file1);
        if (!file1.exists()) {
            file1.createNewFile();
        }
        byte[] contentInBytes = sb.toString().getBytes();

        fop.write(contentInBytes);
        fop.flush();
        fop.close();

        System.out.println("Done");
    }


    public String appendPrivate(List<String> types, List<String> attributes, List<String> mark) {
        StringBuffer sb = new StringBuffer();
        if (types.size() == attributes.size() && types.size() == mark.size()) {
            for (int i = 0; i < types.size(); i++) {
                sb.append("\t" + "@ApiModelProperty(value = \"" + mark.get(i) + "\")" + "\n");
                sb.append("\t" + "private " + types.get(i) + " " + attributes.get(i) + ";\n\n");
            }
        }
        return sb.toString();
    }
}