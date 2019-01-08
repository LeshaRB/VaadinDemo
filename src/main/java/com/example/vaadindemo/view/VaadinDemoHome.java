package com.example.vaadindemo.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = VaadinDemoMainLayout.class)
public class VaadinDemoHome extends VerticalLayout {

    public VaadinDemoHome() {
        setClassName("app-view");

        Label hello = new Label("Hello Vaadin Demo!");
        add(hello);

        Button button = new Button("Click me", event -> {
            hello.setText("Clicked!");
            hello.setClassName("clicked");
        });
        add(button);
    }
}