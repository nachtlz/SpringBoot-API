package com.serverintegrador.base.sax;

import org.xml.sax.helpers.DefaultHandler;

import com.serverintegrador.base.bd.Disponibilidad;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DisponibilidadHandler extends DefaultHandler implements ObjectHandler{

    private Disponibilidad disponibilidad;
    private boolean bolDisponibilidades = false;
    private boolean bolDisponibilidad = false;
    private boolean bolidHotel = false;
    private boolean bolCodigo = false;
    private boolean bolHabsDisponibles = false;
    private boolean bolPrecio = false;

    public DisponibilidadHandler(Disponibilidad disponibilidad) {
        super();
        this.disponibilidad = disponibilidad;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (qName.equalsIgnoreCase("disponibilidades")) {
    		bolDisponibilidades = true;
        } else if (qName.equalsIgnoreCase("disponibilidad")) {
            bolDisponibilidad = true;
        } else if (qName.equalsIgnoreCase("idHotel")) {
            bolidHotel = true;
        } else if (qName.equalsIgnoreCase("codigo")) {
            bolCodigo = true;
        } else if (qName.equalsIgnoreCase("habsDisponibles")) {
            bolHabsDisponibles = true;
        } else if (qName.equalsIgnoreCase("precio")) {
            bolPrecio = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	if (qName.equalsIgnoreCase("disponibilidades")) {
    		bolDisponibilidades = false;
        } else if (qName.equalsIgnoreCase("disponibilidad")) {
            bolDisponibilidad = false;
        } else if (qName.equalsIgnoreCase("idHotel")) {
            bolidHotel = false;
        } else if (qName.equalsIgnoreCase("codigo")) {
            bolCodigo = false;
        } else if (qName.equalsIgnoreCase("habsDisponibles")) {
            bolHabsDisponibles = false;
        } else if (qName.equalsIgnoreCase("precio")) {
            bolPrecio = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String pal = new String(ch, start, length);
        pal = pal.trim();
        if (bolDisponibilidades) {
        	if (bolDisponibilidad) {
                if (bolidHotel) {
                    disponibilidad.setIdHotel(Integer.parseInt(pal));
                } else if (bolCodigo) {
                    disponibilidad.setCodigo(pal);
                } else if (bolHabsDisponibles) {
                    disponibilidad.setHabsDisponibles(Integer.parseInt(pal));
                } else if (bolPrecio) {
                    disponibilidad.setPrecio(Float.parseFloat(pal));
                }
            }
        }
    }

    public Disponibilidad getInfo() {
        return disponibilidad;
    }
}

