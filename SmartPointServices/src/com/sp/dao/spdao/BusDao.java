package com.sp.dao.spdao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sp.model.spmodels.BusBean;;

public class BusDao extends BaseDao {
	/**
	 * @author dan
	 */
	
	public boolean insert(BusBean ponto){
		//POJO, BEAN, VO ()Value Object), TO, DTO
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn =  getDBConnection();
			pstmt = conn.prepareStatement("INSERT INTO ONIBUS ( idONIBUS, idAPP) VALUES ( ?, ?)");
			pstmt.setInt(1, ponto.getIdOnibus());
			pstmt.setInt(2, ponto.getIdApp());
			

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
	
	public List<BusBean> listAll(BusBean pt) {
		List<BusBean> result = new ArrayList<BusBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getDBConnection();
			pstmt = conn.prepareStatement("SELECT idONIBUS	, idAPP FROM ONIBUS");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BusBean bus = new BusBean();
				bus.setIdOnibus(rs.getInt(1));
				bus.setIdApp(rs.getInt(2));
				

				result.add(bus);
			} 
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
					closeConnection(conn, pstmt, rs);
				}		
		return result;
		}
}



	


