package action.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import action.export.vo.CardInfoVo;
import com.opensymphony.xwork2.ActionSupport;

public class ExportExcelAction extends ActionSupport {

    private static final long serialVersionUID = -7176159631366218356L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); //时间格式化的格式 
    private InputStream excelFile;
    private String downloadFileName;

    public String export() throws Exception {

        //生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）   
        downloadFileName = "发货单_" + sdf.format(new Date()) + RandomStringUtils.randomNumeric(5) + ".xls";//文件重命名后的名字 
        
        String userAgent = ServletActionContext.getRequest().getHeader("USER-AGENT");
        if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
            downloadFileName = new String(downloadFileName.getBytes("gbk"), "ISO8859-1");
        } else {
            downloadFileName = URLEncoder.encode(downloadFileName , "UTF8");
        }
        
        String path = ServletActionContext.getServletContext().getRealPath("/excel/");
        String templatePath = path + "/" + "发货单.xls"; // excel模板路径
        String exportPath = path + "/" + downloadFileName;

        List<CardInfoVo> data = new ArrayList<CardInfoVo>();
        data.add(new CardInfoVo(1234, "ddh001", 100, "张三", "北京市海淀区厂洼中街66号英泰科技大厦415", 100048, 88177542, "hui10K1231231", "dx233534534"));
        exportExcel(templatePath, exportPath,data );
        
        deleteYesterdayFile(path);
        
        return SUCCESS;
    }

    /**
     * 删除昨天的临时文件
     * @param path
     */
    public void deleteYesterdayFile(String path) {
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("_yyyyMMdd").format(cal.getTime());

        //删除昨天的数据
        for (File file : new File(path).listFiles()) {
            if(StringUtils.contains(file.getName(), yesterday)) {
                FileUtils.deleteQuietly(file);
            }
        }
    }

    /**
     * 导出EXCEL文件
     * @param templatePath
     * @param exportPath
     * @param data
     * @throws IOException
     * @throws BiffException
     * @throws WriteException
     * @throws RowsExceededException
     * @throws FileNotFoundException
     */
    public void exportExcel(String templatePath, String exportPath, List<CardInfoVo> data) throws IOException, BiffException, WriteException, RowsExceededException, FileNotFoundException {
        
        // 创建可写入的Excel工作薄
        Workbook wb = Workbook.getWorkbook(new File(templatePath)); // 获取指定目录下的excel
        File exportFile = new File(exportPath); //导出Excel文件
        WritableWorkbook exportWb = Workbook.createWorkbook(exportFile, wb);// 将指定目录下的excel拷贝一份新的

        //加边框,字体
        WritableFont wf = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.NO_BOLD, true);
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
        wcf.setAlignment(Alignment.CENTRE);// 水平对齐
        wcf.setWrap(false); // 是否换行
        
        WritableSheet sheet = exportWb.getSheet(0); // 获取第一个工作表
        Label label = null;
        for (int i = 0; i < data.size(); i++) {//    序号  订单号 卡面值 姓名  地址  邮编  电话  卡号  物流单号 
            CardInfoVo dataVo = data.get(i);
            label = new Label(0, 3 + i, dataVo.getNo()+"",wcf);
            sheet.addCell(label);
            label = new Label(1, 3 + i, dataVo.getDdh(),wcf);
            sheet.addCell(label);
            label = new Label(2, 3 + i, dataVo.getKmz()+"",wcf);
            sheet.addCell(label);
            label = new Label(3, 3 + i, dataVo.getName(),wcf);
            sheet.addCell(label);
            label = new Label(4, 3 + i, dataVo.getAddress(),wcf);
            sheet.addCell(label);
            label = new Label(5, 3 + i, dataVo.getZipCode()+"",wcf);
            sheet.addCell(label);
            label = new Label(6, 3 + i, dataVo.getPhoneNo()+"",wcf);
            sheet.addCell(label);
            label = new Label(7, 3 + i, dataVo.getCardNo(),wcf);
            sheet.addCell(label);
            label = new Label(8, 3 + i, dataVo.getWldh(),wcf);
            sheet.addCell(label);
        }
        
        
        exportWb.write();
        exportWb.close();
        excelFile = new FileInputStream(exportFile);
    }

    public InputStream getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(InputStream excelFile) {
        this.excelFile = excelFile;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }
}
