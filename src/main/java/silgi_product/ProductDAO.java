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
	
	
}
