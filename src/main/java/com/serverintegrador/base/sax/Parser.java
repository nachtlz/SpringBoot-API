package com.serverintegrador.base.sax;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.serverintegrador.base.ArxiuLogs;
import com.serverintegrador.base.bd.Cliente;
import com.serverintegrador.base.bd.Disponibilidad;
import com.serverintegrador.base.bd.Hotel;
import com.serverintegrador.base.bd.Reserva;
import com.serverintegrador.base.bd.TipoHabitacion;

public class Parser {
    public Object parseXml(String ent, Class<?> clazz) {
        Object res = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            InputSource in = new InputSource(new StringReader(ent));
            
            if (clazz.equals(Cliente.class)) {
            	Cliente c = new Cliente();
            	ClientesHandler handler = new ClientesHandler(c);
            	saxParser.parse(in, handler);
                res = handler.getInfo();
            } else if (clazz.equals(Hotel.class)) {
            	Hotel h = new Hotel();
            	HotelesHandler handler = new HotelesHandler(h);
            	saxParser.parse(in, handler);
                res = handler.getInfo();
            } else if (clazz.equals(Disponibilidad.class)) {
            	Disponibilidad d = new Disponibilidad();
            	DisponibilidadHandler handler = new DisponibilidadHandler(d);
            	saxParser.parse(in, handler);
                res = handler.getInfo();
            } else if (clazz.equals(TipoHabitacion.class)) {
            	TipoHabitacion th = new TipoHabitacion();
            	TipoHabitacionHandler handler = new TipoHabitacionHandler(th);
            	saxParser.parse(in, handler);
                res = handler.getInfo();
            } else if (clazz.equals(Reserva.class)) {
            	Reserva r = new Reserva();
            	ReservasHandler handler = new ReservasHandler(r);
            	saxParser.parse(in, handler);
                res = handler.getInfo();
            }
            
        } catch (Exception e) {
            ArxiuLogs.guardarExcepcio(e);
        }
        return res;
    }
}
