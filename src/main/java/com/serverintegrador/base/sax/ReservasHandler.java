package com.serverintegrador.base.sax;

import org.xml.sax.helpers.DefaultHandler;

import com.serverintegrador.base.bd.Reserva;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ReservasHandler extends DefaultHandler implements ObjectHandler{

    private Reserva reserva;
    private boolean bolReservas = false;
    private boolean bolReserva = false;
    private boolean bolidReserva = false;
    private boolean bolFechaInicio = false;
    private boolean bolFechaFin = false;
    private boolean bolAdultos = false;
    private boolean bolMenores = false;
    private boolean bolidCliente = false;
    private boolean bolCodigo = false;
    private boolean bolidHotel = false;

    public ReservasHandler(Reserva reserva) {
        super();
        this.reserva = reserva;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("reservas")) {
            bolReservas = true;
        } else if (qName.equalsIgnoreCase("reserva")) {
            bolReserva = true;
        } else if (qName.equalsIgnoreCase("idReserva")) {
            bolidReserva = true;
        } else if (qName.equalsIgnoreCase("fechainicio")) {
            bolFechaInicio = true;
        } else if (qName.equalsIgnoreCase("fechafin")) {
            bolFechaFin = true;
        } else if (qName.equalsIgnoreCase("adultos")) {
            bolAdultos = true;
        } else if (qName.equalsIgnoreCase("menores")) {
            bolMenores = true;
        } else if (qName.equalsIgnoreCase("idCliente")) {
            bolidCliente = true;
        } else if (qName.equalsIgnoreCase("codigo")) {
            bolCodigo = true;
        } else if (qName.equalsIgnoreCase("idHotel")) {
            bolidHotel = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("reservas")) {
            bolReservas = false;
        } else if (qName.equalsIgnoreCase("reserva")) {
            bolReserva = false;
        } else if (qName.equalsIgnoreCase("idReserva")) {
            bolidReserva = false;
        } else if (qName.equalsIgnoreCase("fechainicio")) {
            bolFechaInicio = false;
        } else if (qName.equalsIgnoreCase("fechafin")) {
            bolFechaFin = false;
        } else if (qName.equalsIgnoreCase("adultos")) {
            bolAdultos = false;
        } else if (qName.equalsIgnoreCase("menores")) {
            bolMenores = false;
        } else if (qName.equalsIgnoreCase("idCliente")) {
            bolidCliente = false;
        } else if (qName.equalsIgnoreCase("codigo")) {
            bolCodigo = false;
        } else if (qName.equalsIgnoreCase("idHotel")) {
            bolidHotel = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String pal = new String(ch, start, length);
        pal = pal.trim();
        if (bolReservas) {
            if (bolReserva) {
                if (bolidReserva) {
                    reserva.setIdReserva(Integer.parseInt(pal));
                } else if (bolFechaInicio) {
                    reserva.setFechaInicio(pal);
                } else if (bolFechaFin) {
                    reserva.setFechaFin(pal);
                } else if (bolAdultos) {
                    reserva.setAdultos(Integer.parseInt(pal));
                } else if (bolMenores) {
                    reserva.setMenores(Integer.parseInt(pal));
                } else if (bolidCliente) {
                    reserva.setIdCliente(Integer.parseInt(pal));
                } else if (bolCodigo) {
                    reserva.setCodigo(pal);
                } else if (bolidHotel) {
                    reserva.setIdHotel(Integer.parseInt(pal));
                }
            }
        }
    }

    public Reserva getInfo() {
        return reserva;
    }
}

