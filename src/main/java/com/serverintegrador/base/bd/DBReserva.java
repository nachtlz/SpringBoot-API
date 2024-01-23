package com.serverintegrador.base.bd;

import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.serverintegrador.base.ArxiuLogs;

public class DBReserva {
	public String getNumReservasInDB() {
		int totalReservas = 0;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT COUNT(*) AS total FROM reserva" + ";";
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
	
	public String getReserva(int idReserva) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM reserva WHERE idReserva = '" + idReserva + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				res = rs.getString("idReserva");
				res = res + " " + rs.getString("fechaInicio");
				res = res + " " + rs.getString("fechaFin");
				res = res + " " + rs.getString("adultos");
				res = res + " " + rs.getString("menores");
				res = res + " " + rs.getString("idCliente");
				res = res + " " + rs.getString("codigo");
				res = res + " " + rs.getString("idHotel");
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return res;
	}
	
	public String getReservasByCliente(int idCliente) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT reserva.idReserva, reserva.fechainicio, reserva.fechafin, reserva.adultos, reserva.menores, disponibilidad.precio, hotel.nombre, hotel.url, hotel.telefono, hotel.imagen, tipohabitacion.descripcion"
					+ " FROM reserva"
					+ " JOIN hotel ON reserva.idHotel = hotel.idHotel"
					+ " JOIN tipohabitacion ON tipohabitacion.codigo = reserva.codigo"
					+ " JOIN disponibilidad ON disponibilidad.idHotel = reserva.idHotel AND disponibilidad.codigo = reserva.codigo"
					+ " WHERE reserva.idCliente = '" + idCliente + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			res = "[";
			while (rs.next()) {
				res = res + "{\"idReserva\": " + rs.getString("idReserva") + ", ";
				res = res + "\"fechaInicio\": \"" + rs.getString("fechaInicio") + "\", ";
				res = res + "\"fechaFin\": \"" + rs.getString("fechaFin") + "\", ";
				res = res + "\"adultos\": " + rs.getString("adultos") + ", ";
				res = res + "\"menores\": " + rs.getString("menores") + ", ";
				res = res + "\"precio\": " + rs.getString("precio") + ", ";
				res = res + "\"nombre\": \"" + rs.getString("nombre") + "\", ";
				res = res + "\"telefono\": \"" + rs.getString("telefono") + "\", ";
				res = res + "\"imagen\": \"" + rs.getString("imagen") + "\", ";
				res = res + "\"descripcion\": \"" + rs.getString("descripcion") + "\"}";
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
	
	public String getReservasByDni(String dni) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT hotel.nombre, reserva.fechainicio, reserva.fechafin, reserva.adultos, reserva.menores, tipohabitacion.descripcion"
					+ " FROM reserva"
					+ " JOIN cliente ON cliente.idCliente = reserva.idCliente"
					+ " JOIN hotel ON hotel.idHotel = reserva.idHotel"
					+ " JOIN tipohabitacion ON tipohabitacion.codigo = reserva.codigo"
					+ " WHERE cliente.id = '" + dni + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			res = "[";
			while (rs.next()) {
				res = res + "{\"nombre\": \"" + rs.getString("nombre") + "\", ";
				res = res + "\"fechaInicio\": \"" + rs.getString("fechaInicio") + "\", ";
				res = res + "\"fechaFin\": \"" + rs.getString("fechaFin") + "\", ";
				res = res + "\"adultos\": " + rs.getString("adultos") + ", ";
				res = res + "\"menores\": " + rs.getString("menores") + ", ";
				res = res + "\"descripcion\": \"" + rs.getString("descripcion") + "\"}";
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
	
	public String addReserva(Reserva r) {
		String res = "";
		int ind;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "INSERT INTO reserva (fechaInicio, fechaFin, adultos, menores, idCliente, codigo, idHotel) "
						 + "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.getConection().prepareStatement(sql);
			ind = 1;
			preparedStatement.setString(ind++, r.getFechaInicio());
			preparedStatement.setString(ind++, r.getFechaFin());
			preparedStatement.setString(ind++, Integer.toString(r.getAdultos()));
			preparedStatement.setString(ind++, Integer.toString(r.getMenores()));
			preparedStatement.setString(ind++, Integer.toString(r.getIdCliente()));
			preparedStatement.setString(ind++, r.getCodigo());
			preparedStatement.setString(ind++, Integer.toString(r.getIdHotel()));
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
	
	public String getHabitacionesDisponiblesByReserva(int idHotel, String fechaInicio, String fechaFin) {
		String res = "--none--";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT d.idHotel, hotel.nombre, d.habsDisponibles, d.precio, th.*, GREATEST(d.habsDisponibles - IFNULL(nr.ocupadas, 0), 0) AS disponibles"
					+ " FROM disponibilidad d"
					+ " LEFT JOIN "
					+ "(SELECT r.codigo, COUNT(r.idReserva) AS ocupadas"
					+ " FROM reserva r"
					+ " WHERE r.idHotel = " + idHotel
					+ " AND ((r.fechainicio >= '" + fechaInicio +"' AND r.fechafin <= '" + fechaFin +"') OR"
					+ " (r.fechainicio < '" + fechaInicio +"' AND r.fechafin > '" + fechaFin +"') OR"
					+ " (r.fechainicio < '" + fechaInicio +"' AND r.fechafin > '" + fechaFin +"'))"
					+ " GROUP BY r.codigo) AS nr"
					+ " ON d.codigo = nr.codigo"
					+ " JOIN tipohabitacion th ON th.codigo = d.codigo"
					+ " JOIN hotel ON d.idHotel = hotel.idHotel"
					+ " WHERE d.idHotel = " + idHotel + ";";
			res = sql;
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			res = "[";
			while (rs.next()) {
				res = res + "{\"idHotel\": " + rs.getString("idHotel") + ", ";
				res = res + "\"nombre\": \"" + rs.getString("nombre") + "\", ";
				res = res + "\"habsDisponibles\": " + rs.getString("habsDisponibles") + ", ";
				res = res + "\"precio\": " + rs.getString("precio") + ", ";
				res = res + "\"codigo\": \"" + rs.getString("codigo") + "\", ";
				res = res + "\"descripcion\": \"" + rs.getString("descripcion") + "\", ";
				res = res + "\"adultos\": " + rs.getString("adultos") + ", ";
				res = res + "\"menores\": " + rs.getString("menores") + ", ";
				res = res + "\"disponibles\": " + rs.getString("disponibles") + "}";
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
}
