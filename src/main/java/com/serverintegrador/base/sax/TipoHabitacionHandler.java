package com.serverintegrador.base.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.serverintegrador.base.bd.TipoHabitacion;

public class TipoHabitacionHandler extends DefaultHandler implements ObjectHandler{

    private TipoHabitacion tipoHabitacion;
    private boolean bolTipoHabitaciones = false;
    private boolean bolTipoHabitacion = false;
    private boolean bolCodigo = false;
    private boolean bolDescripcion = false;
    private boolean bolAdultos = false;
    private boolean bolMenores = false;

    public TipoHabitacionHandler(TipoHabitacion tipoHabitacion) {
        super();
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (qName.equalsIgnoreCase("tipoHabitaciones")) {
    		bolTipoHabitaciones = true;
        } else if (qName.equalsIgnoreCase("tipoHabitacion")) {
            bolTipoHabitacion = true;
        } else if (qName.equalsIgnoreCase("codigo")) {
            bolCodigo = true;
        } else if (qName.equalsIgnoreCase("descripcion")) {
            bolDescripcion = true;
        } else if (qName.equalsIgnoreCase("adultos")) {
            bolAdultos = true;
        } else if (qName.equalsIgnoreCase("menores")) {
            bolMenores = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	if (qName.equalsIgnoreCase("tipoHabitaciones")) {
    		bolTipoHabitaciones = false;
        } else if (qName.equalsIgnoreCase("tipoHabitacion")) {
            bolTipoHabitacion = false;
        } else if (qName.equalsIgnoreCase("codigo")) {
            bolCodigo = false;
        } else if (qName.equalsIgnoreCase("descripcion")) {
            bolDescripcion = false;
        } else if (qName.equalsIgnoreCase("adultos")) {
            bolAdultos = false;
        } else if (qName.equalsIgnoreCase("menores")) {
            bolMenores = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String pal = new String(ch, start, length);
        pal = pal.trim();
        if (bolTipoHabitaciones) {
        	if (bolTipoHabitacion) {
                if (bolCodigo) {
                    tipoHabitacion.setCodigo(pal);
                } else if (bolDescripcion) {
                    tipoHabitacion.setDescripcion(pal);
                } else if (bolAdultos) {
                    tipoHabitacion.setAdultos(Integer.parseInt(pal));
                } else if (bolMenores) {
                    tipoHabitacion.setMenores(Integer.parseInt(pal));
                }
            }
        }
    }

    public TipoHabitacion getInfo() {
        return tipoHabitacion;
    }
}
