package com.serverintegrador.base.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.serverintegrador.base.ArxiuLogs;

public class DBTipoHabitacion {
	public String getNumTipoHabitacionesInDB() {
		int totalTipoHabitaciones = 0;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT COUNT(*) AS total FROM tipohabitacion" + ";";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				totalTipoHabitaciones = rs.getInt("total");
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return Integer.toString(totalTipoHabitaciones);
	}
	
	public String getTipoHabitacion(String codigo) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM tipohabitacion WHERE codigo = '" + codigo + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				res = rs.getString("codigo");
				res = res + " " + rs.getString("descripcion");
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return res;
	}
	
	public String addTipoHabitacion(TipoHabitacion th) {
		String res = "";
		int ind;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "INSERT INTO tipohabitacion (codigo, descripcion) "
						 + "VALUES (?, ?)";
			PreparedStatement preparedStatement = con.getConection().prepareStatement(sql);
			ind = 1;
			preparedStatement.setString(ind++, th.getCodigo());
			preparedStatement.setString(ind++, th.getDescripcion());
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
