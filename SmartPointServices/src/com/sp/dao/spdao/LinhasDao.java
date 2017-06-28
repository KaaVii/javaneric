package com.sp.dao.spdao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sp.model.spmodels.LinhasBean;;

public class LinhasDao extends BaseDao {
	/**
	 * @author dan
	 */
	public boolean insert(LinhasBean linha){
		//POJO, BEAN, VO ()Value Object), TO, DTO
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn =  getDBConnection();
			pstmt = conn.prepareStatement("INSERT INTO LINHA ( idLINHA, idPONTO, idONIBUS) VALUES ( ?, ?, ?)");
			pstmt.setInt(1, linha.getIdLinha());
			pstmt.setInt(2, linha.getIdPonto());
			pstmt.setInt(3, linha.getIdOnibus());
			
			int row = pstmt.executeUpdate();
			if(row > 0){
				result = true;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(conn, pstmt);
			}
		return result; 
	}
	
	public List<LinhasBean> listAll(LinhasBean lna) {
		List<LinhasBean> result = new ArrayList<LinhasBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getDBConnection();
			pstmt = conn.prepareStatement("SELECT idLINHA, idPONTO, idONIBUS FROM LINHA");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LinhasBean ln = new LinhasBean();
				ln.setIdLinha(rs.getInt(1));
				ln.setIdPonto(rs.getInt(2));
				ln.setIdOnibus(rs.getInt(3));
				
				
				result.add(ln);
			} 
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
					closeConnection(conn, pstmt, rs);
				}		
		return result;
		}
}



	


