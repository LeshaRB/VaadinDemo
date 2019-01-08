package com.example.vaadindemo.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "menu", layout = VaadinDemoMainLayout.class)
public class VaadinDemoMenu extends VerticalLayout {

    public VaadinDemoMenu() {
        H1 h1 = new H1("Menu");
        add(h1);
    }
}