package com.sp.dao.spdao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sp.model.spmodels.PontoBean;;

public class PontoDao extends BaseDao {
	/**
	 * @author dan
	 */
	public boolean insert(PontoBean ponto){
		//POJO, BEAN, VO ()Value Object), TO, DTO
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn =  getDBConnection();
			pstmt = conn.prepareStatement("INSERT INTO VEICULO ( ID, LATITUDE, LONGITUDE) VALUES ( ?, ?, ?, ?) ");
			pstmt.setInt(1, ponto.getId());
			pstmt.setString(2, ponto.getLatitude());
			pstmt.setString(3, ponto.getLongitude());

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
	
	public List<PontoBean> listAll(PontoBean pt) {
		List<PontoBean> result = new ArrayList<PontoBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getDBConnection();
			pstmt = conn.prepareStatement("SELECT idPONTO, Latitude, Longitude, Referencia FROM PONTO");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PontoBean pto = new PontoBean();
				pto.setId(rs.getInt(1));
				pto.setLatitude(rs.getString(2));
				pto.setLongitude(rs.getString(3));


				result.add(pto);
			} 
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
					closeConnection(conn, pstmt, rs);
				}		
		return result;
		}
	
	public List<PontoBean> listAll(PontoBean pt, String linha) {
		List<PontoBean> result = new ArrayList<PontoBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getDBConnection();
			pstmt = conn.prepareStatement("SELECT P.idPONTO ,P.Latitude, P.Longitude FROM PONTO P, LINHA L WHERE P.idPONTO = L.idLINHA and L.nomeLINHA =" +"'"+ linha +"'"+ "");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PontoBean pto = new PontoBean();
				pto.setId(rs.getInt(1));
				pto.setLatitude(rs.getString(2));
				pto.setLongitude(rs.getString(3));


				result.add(pto);
			} 
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
					closeConnection(conn, pstmt, rs);
				}		
		return result;
		}
}



	


