package silgi_product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private Connection con;
	private ResultSet rs;
	
	public ProductDAO() {//db connection
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","TEST_USR","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProductDTO> productList(){
		ArrayList<ProductDTO> list=new ArrayList<ProductDTO>();
		String sql="SELECT * FROM tbl_product";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO product=new ProductDTO();
				product.setP_code(rs.getString(1));
				product.setP_name(rs.getString(2));
				product.setP_size(rs.getInt(3));
				product.setP_incost(rs.getInt(4));
				product.setP_outcost(rs.getInt(5));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<CompanyDTO> getCompany() {
		List<CompanyDTO> list=new ArrayList<CompanyDTO>();
		String sql="SELECT c_code, c_name FROM tbl_company";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				CompanyDTO companyDTO=new CompanyDTO();
				companyDTO.setC_code(rs.getString("c_code"));
				companyDTO.setC_name(rs.getString("c_name"));
				list.add(companyDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNext() {
		int num=0;
		String sql="SELECT inout_seq.NEXTVAL AS nextval FROM DUAL";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt("nextval");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public boolean inout(String t_no, String p_code,String t_type,int t_cnt,Date t_date,String c_code) {
		String sql="INSERT INTO tbl_inout VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1,t_no);
			pstmt.setString(2,p_code);
			pstmt.setString(3,t_type);
			pstmt.setInt(4, t_cnt);
			pstmt.setDate(5, t_date);
			pstmt.setString(6, c_code);
			int rowsAffected=pstmt.executeUpdate();
			return rowsAffected>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<InoutDTO> getInout(){
		String sql="SELECT t.t_no, t.p_code, p.p_name, t.t_type, t.t_cnt, t.t_date, c.c_code, c.c_name "+
					"FROM tbl_inout t JOIN tbl_product p ON t.p_code=p.p_code "+
					"JOIN tbl_company c ON t.c_code=c.c_code ORDER BY t.t_no";
		List<InoutDTO> list=new ArrayList<InoutDTO>();
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				InoutDTO inoutDTO=new InoutDTO();
				inoutDTO.setT_no(rs.getString("t_no"));
				inoutDTO.setP_code(rs.getString("p_code"));
				inoutDTO.setP_name(rs.getString("p_name"));
				inoutDTO.setT_type(rs.getString("t_type").equals("I")?"입고":"출고");
				inoutDTO.setT_cnt(rs.getInt("t_cnt"));
				inoutDTO.setT_date(rs.getDate("t_date"));
				inoutDTO.setC_code(rs.getString("c_code"));
				inoutDTO.setC_name(rs.getString("c_name"));
				list.add(inoutDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<SalesDTO> getSales(){
		List<SalesDTO> list=new ArrayList<SalesDTO>();
		String sql="SELECT t.p_code, p.p_name, SUM(t.t_cnt) AS totalSales, p.p_incost, p.p_outcost "+
					"FROM tbl_inout t JOIN tbl_product p ON t.p_code=p.p_code "+
					"WHERE t.t_type='O' GROUP BY t.p_code, p.p_name, p.p_incost, p.p_outcost ORDER BY t.p_code";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SalesDTO salesDTO=new SalesDTO();
				salesDTO.setP_code(rs.getString("p_code"));
				salesDTO.setP_name(rs.getString("p_name"));
				salesDTO.setTotalSales(rs.getInt("totalSales"));
				salesDTO.setInprice(rs.getInt("p_incost"));
				salesDTO.setOutprice(rs.getInt("p_outcost"));
				salesDTO.setSalesprice(salesDTO.getTotalSales() * (salesDTO.getOutprice() - salesDTO.getInprice()));
				list.add(salesDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(list.size());
		return list;
	}
	
}
