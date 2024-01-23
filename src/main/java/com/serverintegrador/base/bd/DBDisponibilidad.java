package com.serverintegrador.base.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.serverintegrador.base.ArxiuLogs;

public class DBDisponibilidad {
	public String getNumHabitacionesLibresInDB() {
		int totalReservas = 0;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT SUM(habsDisponibles) AS total FROM disponibilidad" + ";";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				totalReservas = rs.getInt("total");
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return Integer.toString(totalReservas);
	}
	
	public String getDisponibilidad(int idHotel, String codigo) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM disponibilidad WHERE idHotel = '" + idHotel + "' AND codigo = '" + codigo + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				res = rs.getString("idHotel");
				res = res + " " + rs.getString("codigo");
				res = res + " " + rs.getString("habsDisponibles");
				res = res + " " + rs.getString("precio");
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return res;
	}
	
	public String getDisponibilidadByHotel(int idHotel) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM disponibilidad"
			+ " JOIN tipohabitacion ON disponibilidad.codigo = tipohabitacion.codigo "
			+ "WHERE idHotel = '" + idHotel + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			res = "[";
			while (rs.next()) {
				res = res + "{\"idHotel\": " + rs.getString("idHotel") + ", ";
				res = res + "\"codigo\": \"" + rs.getString("codigo") + "\", ";
				res = res + "\"descripcion\": \"" + rs.getString("descripcion") + "\", ";
				res = res + "\"habsDisponibles\": " + rs.getString("habsDisponibles") + ", ";
				res = res + "\"precio\": " + rs.getString("precio") + ", ";
				res = res + "\"adultos\": " + rs.getString("adultos") + ", ";
				res = res + "\"menores\": " + rs.getString("menores") + "}";
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
	
	public String addDisponibilidad(Disponibilidad d) {
		String res = "";
		int ind;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "INSERT INTO disponibilidad (idHotel, codigo, descripcion, precio) "
						 + "VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.getConection().prepareStatement(sql);
			ind = 1;
			preparedStatement.setString(ind++, Integer.toString(d.getIdHotel()));
			preparedStatement.setString(ind++, d.getCodigo());
			preparedStatement.setString(ind++, Integer.toString(d.getHabsDisponibles()));
			preparedStatement.setString(ind++, Float.toString(d.getPrecio()));
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
