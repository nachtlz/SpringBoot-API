package com.serverintegrador.base.bd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverintegrador.base.ArxiuLogs;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String id;
    private String tipoId;
    private String nacionalidad;
    private int edad;
    private String email;
    private String password;

    // Constructor vacío
    public Cliente() {
    }
	
	public Cliente(int idCliente, String nombre, String apellido, String telefono, String id, String tipoId,
			String nacionalidad, int edad) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.id = id;
		this.tipoId = tipoId;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String contraseña) {
		this.password = contraseña;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono="
				+ telefono + ", id=" + id + ", tipoId=" + tipoId + ", nacionalidad=" + nacionalidad + ", edad=" + edad
				+ ", email=" + email + ", password=" + password + "]";
	}

	public String toJSON() {
		String res = "";
		ObjectMapper op = new ObjectMapper();
		try {
			res = op.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			ArxiuLogs.guardarExcepcio(e);
		}
		return res;
	}
}