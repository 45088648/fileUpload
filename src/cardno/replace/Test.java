package cardno.replace;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * 将txt里的卡号替换为excel里的卡号
 * 
 * @author Administrator
 * 
 */
public class Test {

    public static void main(String[] args) throws Exception {
        
        String excelPath = "src/cardno/replace/卡库存信息.xls";
        List<String[]> excelDatas = getSheetMap(excelPath);
//        for (String[] data : excelDatas) {
//            System.out.println(data[0]);
//        }
        
        String txtPath = "src/cardno/replace/制卡文件样例.txt";
        List<String[]> txtDatas = getTxtContent(txtPath);
        for (int i = 0; i < txtDatas.size(); i++) {
            txtDatas.get(i)[0] = excelDatas.get(i+1)[0];
        }
        
        for (String[] data : txtDatas) {
            for (int i = 0; i < data.length; i++) {
                if(i != data.length-1) {
                    System.out.print(data[i]+"|");
                }else {
                    System.out.print(data[i]);
                    System.out.println();
                }
            }
        }

    }

    public static List<String[]> getTxtContent(String filePath) throws IOException {
        List<String[]> datas = new ArrayList<String[]>();// NEW一个单元格集合
        List<String> lines = null;
        lines = Files.readLines(new File(filePath), Charsets.UTF_8);
        for (String line : lines) {
            datas.add(line.split("\\|"));
        }
        return datas;
    }

    public static List<String[]> getSheetMap(String filePath) throws BiffException, IOException, ServletException {

        Workbook book = Workbook.getWorkbook(new File(filePath));
        Sheet[] sheetList = book.getSheets();// 获得工作表对象数组

        String[] mes = null;
        Sheet sheets = sheetList[0];
        int cols = sheets.getColumns();// col列数
        int rows = sheets.getRows();// 行数
        int nullcols = 0;
        List<String[]> cellList = new ArrayList<String[]>();// NEW一个单元格集合
        for (int i = 0; i < rows; i++) {
            mes = new String[cols];// 每一行NEW一个新的列数组，cols是列的长度
            nullcols = 0;
            for (int j = 0; j < cols; j++) {
                String cur = sheets.getCell(j, i).getContents();// 从EXCEL取得每个单元格中的数据
                if (null == cur || "" == cur) {
                    nullcols++;
                }
                mes[j] = cur;// 将单元格的数据存入列数组中
            }
            if (cols != nullcols)
                cellList.add(mes);// 将每列的数组存入每个页的集合中
        }

        book.close();
        return cellList;
    }
}
