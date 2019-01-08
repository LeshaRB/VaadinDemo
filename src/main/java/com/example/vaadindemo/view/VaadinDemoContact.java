package com.example.vaadindemo.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view of the application
 */
@Route(value = "contact", layout = VaadinDemoMainLayout.class)
public class VaadinDemoContact extends VerticalLayout {

    public VaadinDemoContact() {
        H1 h1 = new H1("Contact");
        add(h1);
    }
}