package com.serverintegrador.base.sax;

import org.xml.sax.helpers.DefaultHandler;

import com.serverintegrador.base.bd.Hotel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class HotelesHandler extends DefaultHandler implements ObjectHandler {

    private Hotel hotel;
    private boolean bolHoteles = false;
    private boolean bolHotel = false;
    private boolean bolidHotel = false;
    private boolean bolNombre = false;
    private boolean bolDescripcion = false;
    private boolean bolURL = false;
    private boolean bolCategoria = false;
    private boolean bolTelefono = false;
    private boolean bolImagen = false;

    public HotelesHandler(Hotel hotel) {
        super();
        this.hotel = hotel;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (qName.equalsIgnoreCase("hoteles")) {
    		bolHoteles = true;
        } else if (qName.equalsIgnoreCase("hotel")) {
            bolHotel = true;
        } else if (qName.equalsIgnoreCase("idHotel")) {
            bolidHotel = true;
        } else if (qName.equalsIgnoreCase("nombre")) {
            bolNombre = true;
        } else if (qName.equalsIgnoreCase("descripcion")) {
            bolDescripcion = true;
        } else if (qName.equalsIgnoreCase("url")) {
            bolURL = true;
        } else if (qName.equalsIgnoreCase("categoria")) {
            bolCategoria = true;
        } else if (qName.equalsIgnoreCase("telefono")) {
            bolTelefono = true;
        } else if (qName.equalsIgnoreCase("imagen")) {
            bolImagen = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	if (qName.equalsIgnoreCase("hoteles")) {
    		bolHoteles = false;
        } else if (qName.equalsIgnoreCase("hotel")) {
            bolHotel = false;
        } else if (qName.equalsIgnoreCase("idHotel")) {
            bolidHotel = false;
        } else if (qName.equalsIgnoreCase("nombre")) {
            bolNombre = false;
        } else if (qName.equalsIgnoreCase("descripcion")) {
            bolDescripcion = false;
        } else if (qName.equalsIgnoreCase("url")) {
            bolURL = false;
        } else if (qName.equalsIgnoreCase("categoria")) {
            bolCategoria = false;
        } else if (qName.equalsIgnoreCase("telefono")) {
            bolTelefono = false;
        } else if (qName.equalsIgnoreCase("imagen")) {
            bolImagen = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String pal = new String(ch, start, length);
        pal = pal.trim();
        if(bolHoteles) {
        	if (bolHotel) {
                if (bolidHotel) {
                    hotel.setIdHotel(Integer.parseInt(pal));
                } else if (bolNombre) {
                    hotel.setNombre(pal);
                } else if (bolDescripcion) {
                    hotel.setDescripcion(pal);
                } else if (bolURL) {
                    hotel.setUrl(pal);
                } else if (bolCategoria) {
                    hotel.setCategoria(Integer.parseInt(pal));
                } else if (bolTelefono) {
                    hotel.setTelefono(pal);
                } else if (bolImagen) {
                    hotel.setImagen(pal);
                }
            }
        }
    }

    public Hotel getInfo() {
        return hotel;
    }
}

