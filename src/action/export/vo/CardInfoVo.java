package action.export.vo;

public class CardInfoVo {
//    序号  订单号 卡面值 姓名  地址  邮编  电话  卡号  物流单号                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            

    private int no;
    private String ddh;
    private int kmz;
    private String name;
    private String address;
    private int zipCode;
    private int phoneNo;
    private String cardNo;
    private String wldh;
    
    public CardInfoVo(int no, String ddh, int kmz, String name, String address, int zipCode, int phoneNo, String cardNo, String wldh) {
        this.no = no;
        this.ddh = ddh;
        this.kmz = kmz;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNo = phoneNo;
        this.cardNo = cardNo;
        this.wldh = wldh;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getDdh() {
        return ddh;
    }
    public void setDdh(String ddh) {
        this.ddh = ddh;
    }
    public int getKmz() {
        return kmz;
    }
    public void setKmz(int kmz) {
        this.kmz = kmz;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    public int getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getWldh() {
        return wldh;
    }
    public void setWldh(String wldh) {
        this.wldh = wldh;
    }
}
