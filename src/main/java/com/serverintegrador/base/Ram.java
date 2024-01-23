package com.serverintegrador.base;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Ram {
	
	public static Object getApplicationAttribute(HttpServletRequest req, String atr) {
		return req.getServletContext().getAttribute(atr);
	}	
	
	public static void setApplicationAttribute(HttpServletRequest req, String atr, Object obj) {
		req.getServletContext().setAttribute(atr, obj);
	}
	
	public static Object getSessionAttribute(HttpSession session, String atr) {
		return session.getAttribute(atr);
	}

	public static void setSessionAttribute(HttpSession session, String atr, Object obj) {
		session.setAttribute(atr, obj);
	}

	public static void tancarSessio(HttpSession session) {
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
		    session.removeAttribute(e.nextElement());
		}
	}
}
