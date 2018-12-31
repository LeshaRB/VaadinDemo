package com.example.vaadindemo;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.Theme;

@HtmlImport("frontend://styles/vaadindemo-theme.html")
@Theme(Lumo.class)
public class VaadinDemoUI extends UI { }
