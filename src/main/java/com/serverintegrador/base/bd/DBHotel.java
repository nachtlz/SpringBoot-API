package com.serverintegrador.base.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.serverintegrador.base.ArxiuLogs;

public class DBHotel {
	public String getNumHotelesInDB() {
		int totalHoteles = 0;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT COUNT(*) AS total FROM hotel" + ";";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				totalHoteles = rs.getInt("total");
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return Integer.toString(totalHoteles);
	}
	
	public String getHotel(int idHotel) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM hotel WHERE idHotel = '" + idHotel + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			res = "[";
			if (rs.next()) {
				res = res + "{\"idHotel\": " + rs.getString("idHotel") + ", ";
				res = res + "\"nombre\": \"" + rs.getString("nombre") + "\", ";
				res = res + "\"descripcion\": \"" + rs.getString("descripcion") + "\", ";
				res = res + "\"url\": \"" + rs.getString("url") + "\", ";
				res = res + "\"categoria\": " + rs.getString("categoria") + ", ";
				res = res + "\"telefono\": \"" + rs.getString("telefono") + "\", ";
				res = res + "\"imagen\": \"" + rs.getString("imagen") + "\"}";
				res = res + ", ";
			}
			res = res.substring(0, res.length() - 2);
			res = res + "]";
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return res;
	}
	
	public String getAllHoteles() {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM hotel";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			res = "[";
			while (rs.next()) {
				res = res + "{\"idHotel\": " + rs.getString("idHotel") + ", ";
				res = res + "\"nombre\": \"" + rs.getString("nombre") + "\", ";
				res = res + "\"descripcion\": \"" + rs.getString("descripcion") + "\", ";
				res = res + "\"url\": \"" + rs.getString("url") + "\", ";
				res = res + "\"categoria\": " + rs.getString("categoria") + ", ";
				res = res + "\"telefono\": \"" + rs.getString("telefono") + "\", ";
				res = res + "\"imagen\": \"" + rs.getString("imagen") + "\"}";
				res = res + ", ";
			}
			res = res.substring(0, res.length() - 2);
			res = res + "]";
			
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return res;
	}
	
	public String addHotel(Hotel h) {
		String res = "";
		int ind;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "INSERT INTO hotel (nombre, descripcion, url, categoria, telefono, imagen) "
						 + "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.getConection().prepareStatement(sql);
			ind = 1;
			preparedStatement.setString(ind++, h.getNombre());
			preparedStatement.setString(ind++, h.getDescripcion());
			preparedStatement.setString(ind++, h.getUrl());
			preparedStatement.setString(ind++, Integer.toString(h.getCategoria()));
			preparedStatement.setString(ind++, h.getTelefono());
			preparedStatement.setString(ind++, h.getImagen());
			int statement = preparedStatement.executeUpdate();
			if (statement > 0) {
				res = res + "Row inserted";
			} else {
				res = res + "ERRORBD: row not inserted";
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
			res = res + "ERRORBD: " + ex.toString();
		} finally {
			con.close();
		}
		return res;
	}
}
