package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import utils.ParseFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class UploadUtilAction extends ActionSupport {

    private static final long serialVersionUID = 578658940629495427L;
    private File fileupload;
    private String fileuploadFileName; // 上传来的文件的名字，Struts2拦截器获得的文件名,命名规则，File的名字+FileName，如此处为 'fileupload' + 'FileName' = 'fileuploadFileName'

    public String uploadFile() throws Exception {

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
        PrintWriter out = response.getWriter();
        String msg = null;
        try {
            String[] cardInfos = ParseFileUtils.parseFile(fileupload);
            out.print("<table>\r\n" + "  <tr>\r\n" + "    <td>卡号</td>\r\n" + "    <td>密码</td>\r\n" + "    <td>磁道1</td>\r\n" + "    <td>磁道2</td>\r\n" + "    <td>磁道3</td>\r\n" + "    <td>保留用户名</td>\r\n" + "    <td>生效年月</td>\r\n" + "    <td>失效年月</td>\r\n" + "    <td>初始金额</td>\r\n" + "    <td>CVN2</td>\r\n" + "  </tr>");
            out.print("<table>\r\n" + "  <tr>\r\n" + "    <td>" + cardInfos[0] + "</td>\r\n" + "    <td>" + cardInfos[1] + "</td>\r\n" + "    <td>" + cardInfos[2] + "</td>\r\n" + "    <td>" + cardInfos[3] + "</td>\r\n" + "    <td>" + cardInfos[4] + "</td>\r\n" + "    <td>" + cardInfos[5] + "</td>\r\n" + "    <td>" + cardInfos[6] + "</td>\r\n" + "    <td>" + cardInfos[7] + "</td>\r\n" + "    <td>" + cardInfos[8] + "</td>\r\n" + "    <td>" + cardInfos[9] + "</td>\r\n" + "  </tr></table>");

        } catch (Exception e) {
            msg = "导入数据格式错误。";
            out.print("<div style='color:red'>" + msg + "</div>");
        }
        out.flush();
        out.close();
        fileupload.deleteOnExit();
        return null; // 这里不需要页面转向，所以返回空就可以了
    }

    public File getFileupload() {
        return fileupload;
    }

    public void setFileupload(File fileupload) {
        this.fileupload = fileupload;
    }

    public String getFileuploadFileName() {
        return fileuploadFileName;
    }

    public void setFileuploadFileName(String fileuploadFileName) {
        this.fileuploadFileName = fileuploadFileName;
    }

}
