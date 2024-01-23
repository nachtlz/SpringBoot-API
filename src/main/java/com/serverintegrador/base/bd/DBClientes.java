package com.serverintegrador.base.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.serverintegrador.base.ArxiuLogs;

public class DBClientes {
	public String getNumClientesInBD() {
		int totalRegistros = 0;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT COUNT(*) AS total FROM Clientes" + ";";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				totalRegistros = rs.getInt("total");
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return Integer.toString(totalRegistros);
	}

	public Cliente getCliente(String id) {
		Cliente res = new Cliente();
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM cliente WHERE id = '" + id + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {	
				res.setIdCliente(Integer.parseInt(rs.getString("idCliente")));
				res.setNombre(rs.getString("nombre"));
				res.setApellido(rs.getString("apellido"));
				res.setTelefono(rs.getString("telefono"));
				res.setId(rs.getString("id"));
				res.setTipoId(rs.getString("tipoId"));
				res.setNacionalidad(rs.getString("nacionalidad"));
				res.setEdad(Integer.parseInt(rs.getString("edad")));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return res;
	}
	
	public String getClienteByEmail(String email) {
		String res = "[{\"email\": \"none\"}]";
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "SELECT * FROM cliente WHERE email = '" + email + "';";
			Statement st = con.getConection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				res = "[";
				res = res + "{\"idCliente\": " + rs.getString("idCliente") + ", ";
				res = res + "\"nombre\": \"" + rs.getString("nombre") + "\", ";
				res = res + "\"apellido\": \"" + rs.getString("apellido") + "\", ";
				res = res + "\"telefono\": \"" + rs.getString("telefono") + "\", ";
				res = res + "\"id\": \"" + rs.getString("id") + "\", ";
				res = res + "\"tipoId\": \"" + rs.getString("tipoId") + "\", ";
				res = res + "\"nacionalidad\": \"" + rs.getString("nacionalidad") + "\", ";
				res = res + "\"edad\": " + rs.getString("edad") + ", ";
				res = res + "\"email\": \"" + rs.getString("email") + "\", ";
				res = res + "\"password\": \"" + rs.getString("password") + "\"}";
				res = res + "]";
			}
		} catch (Exception ex) {
			ArxiuLogs.guardarExcepcio(ex);
		} finally {
			con.close();
		}
		return res;
	}

	public String addCliente(Cliente cli) {
		String res = "";
		int ind;
		DBConnection con = new DBConnection();
		try {
			con.open();
			String sql = "INSERT INTO cliente (nombre, apellido, telefono, id, tipoId, nacionalidad, edad, email, password) "
						 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.getConection().prepareStatement(sql);
			ind = 1;
			preparedStatement.setString(ind++, cli.getNombre());
			preparedStatement.setString(ind++, cli.getApellido());
			preparedStatement.setString(ind++, cli.getTelefono());
			preparedStatement.setString(ind++, cli.getId());
			preparedStatement.setString(ind++, cli.getTipoId());
			preparedStatement.setString(ind++, cli.getNacionalidad());
			preparedStatement.setString(ind++, Integer.toString(cli.getEdad()));
			preparedStatement.setString(ind++, cli.getEmail());
			preparedStatement.setString(ind++, cli.getPassword());
			int filesresultat = preparedStatement.executeUpdate();
			if (filesresultat > 0) {
				res = res + "Ok, inserted";
			} else {
				res = res + "ERRORBD: not inserted";
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
