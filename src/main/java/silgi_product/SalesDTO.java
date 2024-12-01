package silgi_product;

public class SalesDTO {
	private String p_code;
	private String p_name;
	private int totalSales;
	private int inprice;
	private int outprice;
	private int salesprice;
	
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	public int getInprice() {
		return inprice;
	}
	public void setInprice(int inprice) {
		this.inprice = inprice;
	}
	public int getOutprice() {
		return outprice;
	}
	public void setOutprice(int outprice) {
		this.outprice = outprice;
	}
	public int getSalesprice() {
		return salesprice;
	}
	public void setSalesprice(int salesprice) {
		this.salesprice = salesprice;
	}
}
