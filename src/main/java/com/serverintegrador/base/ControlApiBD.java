package com.serverintegrador.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serverintegrador.base.bd.Cliente;
import com.serverintegrador.base.bd.DBClientes;
import com.serverintegrador.base.bd.DBDisponibilidad;
import com.serverintegrador.base.bd.DBHotel;
import com.serverintegrador.base.bd.DBReserva;
import com.serverintegrador.base.bd.DBTipoHabitacion;
import com.serverintegrador.base.bd.Disponibilidad;
import com.serverintegrador.base.bd.Hotel;
import com.serverintegrador.base.bd.Reserva;
import com.serverintegrador.base.bd.TipoHabitacion;
import com.serverintegrador.base.sax.Parser;

@RestController
public class ControlApiBD {
	@RequestMapping({ "/apibd" })
	public String apiBD(@RequestParam(required = false) String comanda, @RequestParam(required = false) String dades) {
		String res = "";
		if (comanda == null) {
			res = errorAPI();
		} else {
			if (comanda.startsWith("cliente-")) {
				res = crudCliente(comanda, dades);
			} else if (comanda.startsWith("reserva-")) {
				res = crudReserva(comanda, dades);
			} else if (comanda.startsWith("hotel-")) {
				res = crudHotel(comanda, dades);
			} else if (comanda.startsWith("tipohabitacion-")) {
				res = crudTipoHabitacion(comanda, dades);
			} else if (comanda.startsWith("disponibilidad-")) {
				res = crudDisponibilidad(comanda, dades);
			}
		}
		return res;
	}
	
	private String errorAPI() {
		String res = "Fallo en la comanda";
		return res;
	}

	private String crudCliente(String c, String d) {
		String res = "Error a la comanda";
		if (d == null) {
			res = "Error a les dades de la comanda";
		} else {
			Cliente cli = new Cliente();
			Parser p = new Parser();
			cli = (Cliente) p.parseXml(d, Cliente.class);
			if (c.endsWith("-getClienteByDni")) {
				DBClientes dbc = new DBClientes();
				cli = dbc.getCliente(cli.getId());
				res = cli.toJSON();
				//http://localhost:8080/apibd?comanda=cliente-getNomByDni&dades=%3Cclientes%3E%3Ccliente%3E%3Cid%3E43234421M%3C/id%3E%3C/cliente%3E%3C/clientes%3E
			} else if (c.endsWith("-addCliente")) {
				DBClientes dbc = new DBClientes();
				res = dbc.addCliente(cli);
				res = dbc.getClienteByEmail(cli.getEmail());
				//http://localhost:8080/apibd?comanda=cliente-addCliente&dades=%3Cclientes%3E%3Ccliente%3E%3Cnombre%3EMarcos%3C/nombre%3E%3Capellido%3EPalotes%20Dulces%3C/apellido%3E%3Ctelefono%3E84939843%3C/telefono%3E%3Cid%3EDNI%3C/id%3E%3Ctipo%3EDNI%3C/tipoid%3E%3Cnacionalidad%3EArgentino%3C/nacionalidad%3E%3Cedad%3E18%3C/edad%3E%3C/cliente%3E%3C/clientes%3E
			} else if (c.endsWith("-getClienteByEmail")) {
				DBClientes dbc = new DBClientes();
				res = dbc.getClienteByEmail(cli.getEmail());
				//http://localhost:8080/apibd?comanda=cliente-getClienteByEmail&dades=%3Cclientes%3E%3Ccliente%3E%3Cemail%3Ejuanperez@gmail.com%3C/email%3E%3C/cliente%3E%3C/clientes%3E
			} else if (c.endsWith("-getReservasByDni")) {
				DBReserva dbr = new DBReserva();
				res = dbr.getReservasByDni(cli.getId());
				//http://localhost:8080/apibd?comanda=cliente-getReservasByDni&dades=%3Cclientes%3E%3Ccliente%3E%3Cemail%3Ejuanperez@gmail.com%3C/email%3E%3C/cliente%3E%3C/clientes%3E
			}
		}
		
		return res;
	}
	
	private String crudReserva(String c, String d) {
		String res = "Error a la comanda";
		if (d == null) {
			res = "Error a les dades de la comanda";
		} else {
			Reserva r = new Reserva();
			Parser p = new Parser();
			r = (Reserva) p.parseXml(d, Reserva.class);
			
			if (c.endsWith("-getReservaById")) {
				DBReserva dbr = new DBReserva();
				res = dbr.getReserva(r.getIdReserva()) + "<br/>";
				res = res + "--------------------<br/>";
				res = res + r.toJSON();
			} else if (c.endsWith("-addReserva")) {
				DBReserva dbr = new DBReserva();
				res = dbr.addReserva(r) + "<br/>";
				res = res + "--------------------<br/>";
				res = res + r.toJSON();
			} else if (c.endsWith("-getHabitacionesDisponiblesByReserva")) {
				DBReserva dbr = new DBReserva();
				res = dbr.getHabitacionesDisponiblesByReserva(r.getIdHotel(), r.getFechaInicio(), r.getFechaFin());
			} else if (c.endsWith("-getReservasByCliente")) {
				DBReserva dbr = new DBReserva();
				res = dbr.getReservasByCliente(r.getIdCliente());
			}
		}
		
		return res;
	}
	
	private String crudHotel(String c, String d) {
		String res = "Error a la comanda";
		if (d == null) {
			res = "Error a les dades de la comanda";
		} else {
			Hotel h = new Hotel();
			Parser p = new Parser();
			h = (Hotel) p.parseXml(d, Hotel.class);
			if (c.endsWith("-getHotelById")) {
				DBHotel dbh = new DBHotel();
				res = dbh.getHotel(h.getIdHotel());
			} else if (c.endsWith("-addHotel")) {
				DBHotel dbh = new DBHotel();
				res = dbh.addHotel(h) + "<br/>";
				res = res + "--------------------<br/>";
				res = res + h.toJSON();
			} else if (c.endsWith("-getAll")) {
				DBHotel dbh = new DBHotel();
				res = dbh.getAllHoteles();
				//http://localhost:8080/apibd?comanda=hotel-getAll&dades=%3Call%3E
			}
		}
		
		return res;
	}
	
	private String crudTipoHabitacion(String c, String d) {
		String res = "Error a la comanda";
		if (d == null) {
			res = "Error a les dades de la comanda";
		} else {
			TipoHabitacion th = new TipoHabitacion();
			Parser p = new Parser();
			th = (TipoHabitacion) p.parseXml(d, TipoHabitacion.class);
			if (c.endsWith("-getTipoHabitacionByCode")) {
				DBTipoHabitacion dbth = new DBTipoHabitacion();
				res = dbth.getTipoHabitacion(th.getCodigo()) + "<br/>";
				res = res + "--------------------<br/>";
				res = res + th.toJSON();
			} else if (c.endsWith("-addTipoHabitacion")) {
				DBTipoHabitacion dbth = new DBTipoHabitacion();
				res = dbth.addTipoHabitacion(th) + "<br/>";
				res = res + "--------------------<br/>";
				res = res + th.toJSON();
			}
		}
		
		return res;
	}
	
	private String crudDisponibilidad(String c, String d) {
		String res = "Error a la comanda";
		if (d == null) {
			res = "Error a les dades de la comanda";
		} else {
			Disponibilidad dis = new Disponibilidad();
			Parser p = new Parser();
			dis = (Disponibilidad) p.parseXml(d, Disponibilidad.class);
			if (c.endsWith("-getDisponibilidadByIdAndCode")) {
				DBDisponibilidad dbd = new DBDisponibilidad();
				res = dbd.getDisponibilidad(dis.getIdHotel(), dis.getCodigo()) + "<br/>";
				res = res + "--------------------<br/>";
				res = res + dis.toJSON();
			} else if (c.endsWith("-addDisponibilidad")) {
				DBDisponibilidad dbd = new DBDisponibilidad();
				res = dbd.addDisponibilidad(dis) + "<br/>";
				res = res + "--------------------<br/>";
				res = res + dis.toJSON();
			} else if (c.endsWith("-getDisponibilidadByHotel")) {
				DBDisponibilidad dbd = new DBDisponibilidad();
				res = dbd.getDisponibilidadByHotel(dis.getIdHotel());
				//http://localhost:8080/apibd?comanda=disponibilidad-getDisponibilidadByHotel&dades=%3Cdisponibilidades%3E%3Cdisponibilidad%3E%3CidHotel%3E1%3C/idHotel%3E%3C/disponibilidad%3E%3C/disponibilidades%3E
			}
		}
		
		return res;
	}
}

