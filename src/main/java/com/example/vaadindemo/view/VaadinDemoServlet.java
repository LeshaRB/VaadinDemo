package com.example.vaadindemo.view;

import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletConfiguration;
import javax.servlet.annotation.WebServlet;


@WebServlet(urlPatterns = "/*", name = "VaadinDemoServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = VaadinDemoUI.class, productionMode = false)
public class VaadinDemoServlet extends VaadinServlet {

}