package com.serverintegrador.base.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.serverintegrador.base.bd.Cliente;

public class ClientesHandler extends DefaultHandler {

    private Cliente cliente;
    private boolean bolclientes = false;
    private boolean bolcliente = false;
    private boolean bolidcliente = false;
    private boolean bolnombre = false;
    private boolean bolapellido = false;
    private boolean boltelefono = false;
    private boolean bolid = false;
    private boolean boltipoid = false;
    private boolean bolnacionalidad = false;
    private boolean boledad = false;
    private boolean bolemail = false;
    private boolean bolpassword = false;

    public ClientesHandler(Cliente od) {
        super();
        cliente = od;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("clientes")) {
            bolclientes = true;
        } else if (qName.equalsIgnoreCase("cliente")) {
            bolcliente = true;
        } else if (qName.equalsIgnoreCase("idCliente")) {
        	bolidcliente = true;
        } else if (qName.equalsIgnoreCase("nombre")) {
            bolnombre = true;
        } else if (qName.equalsIgnoreCase("apellido")) {
        	bolapellido = true;
        } else if (qName.equalsIgnoreCase("telefono")) {
        	boltelefono = true;
        } else if (qName.equalsIgnoreCase("id")) {
        	bolid = true;
        } else if (qName.equalsIgnoreCase("tipoid")) {
        	boltipoid = true;
        } else if (qName.equalsIgnoreCase("nacionalidad")) {
        	bolnacionalidad = true;
        } else if (qName.equalsIgnoreCase("edad")) {
        	boledad = true;
        } else if (qName.equalsIgnoreCase("email")) {
        	bolemail = true;
        } else if (qName.equalsIgnoreCase("password")) {
        	bolpassword = true;
        }
    }

    public void endElement(String uri, String localName,
            String qName) throws SAXException {
    	if (qName.equalsIgnoreCase("clientes")) {
            bolclientes = false;
        } else if (qName.equalsIgnoreCase("cliente")) {
            bolcliente = false;
        } else if (qName.equalsIgnoreCase("idCliente")) {
        	bolidcliente = false;
        } else if (qName.equalsIgnoreCase("nombre")) {
            bolnombre = false;
        } else if (qName.equalsIgnoreCase("apellido")) {
        	bolapellido = false;
        } else if (qName.equalsIgnoreCase("telefono")) {
        	boltelefono = false;
        } else if (qName.equalsIgnoreCase("id")) {
        	bolid = false;
        } else if (qName.equalsIgnoreCase("tipoid")) {
        	boltipoid = false;
        } else if (qName.equalsIgnoreCase("nacionalidad")) {
        	bolnacionalidad = false;
        } else if (qName.equalsIgnoreCase("edad")) {
        	boledad = false;
        } else if (qName.equalsIgnoreCase("email")) {
        	bolemail = false;
        } else if (qName.equalsIgnoreCase("password")) {
        	bolpassword = false;
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String pal = new String(ch, start, length);
        pal = pal.trim();
        if (bolclientes) {
            if (bolcliente) {
            	if (bolidcliente) {
            		cliente.setIdCliente(Integer.parseInt(pal));
            	} else if (bolnombre) {
            		cliente.setNombre(pal);
            	} else if (bolapellido) {
            		cliente.setApellido(pal);
            	} else if (boltelefono) {
            		cliente.setTelefono(pal);
            	} else if (bolid) {
            		cliente.setId(pal);
            	} else if (boltipoid) {
            		cliente.setTipoId(pal);
            	} else if (bolnacionalidad) {
            		cliente.setNacionalidad(pal);
            	} else if (boledad) {
            		cliente.setEdad(Integer.parseInt(pal));
            	} else if (bolemail) {
            		cliente.setEmail(pal);
            	} else if (bolpassword) {
            		cliente.setPassword(pal);
            	}
            }
        }
    }

    public Cliente getInfo() {
        return cliente;
    }
}
