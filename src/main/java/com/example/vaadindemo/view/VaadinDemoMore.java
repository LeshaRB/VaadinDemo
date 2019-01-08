package com.example.vaadindemo.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "more", layout = VaadinDemoMainLayout.class)
public class VaadinDemoMore extends VerticalLayout {

    public VaadinDemoMore() {
        H1 h1 = new H1("More");
        add(h1);
    }
}