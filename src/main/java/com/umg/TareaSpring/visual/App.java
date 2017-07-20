package com.umg.TareaSpring.visual;

import com.umg.TareaSpring.clases.Persona;
import com.umg.TareaSpring.clases.PersonaRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
public class App extends UI {
    @Autowired
    PersonaRepository personaRepository;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout hlayout = new HorizontalLayout();

        TextField nombre = new TextField("Nombre Completo");
        DateField fecha = new DateField("Fecha de Ingreso");
        TextField domicilio = new TextField("Domicilio");

        Grid<Persona> grid = new Grid<>();
        grid.addColumn(Persona::getNombre).setCaption("Nombre");
        grid.addColumn(Persona::getFecha).setCaption("Fecha de Ingreso");
        grid.addColumn(Persona::getDomicilio).setCaption("Domicilio");

        Button add = new Button("Agregar");
        add.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Persona p = new Persona();
                p.setNombre(nombre.getValue());
                p.setFecha(fecha.getValue());
                p.setDomicilio(domicilio.getValue());

                personaRepository.save(p);
                grid.setItems(personaRepository.findAll());

                Notification.show("Datos agregados con exito");

                nombre.clear();
                fecha.clear();
                domicilio.clear();
            }
        });

        Button remove = new Button("Borrar");
        remove.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Persona p = new Persona();
            }
        });

        layout.addComponents(nombre,fecha,domicilio);
        hlayout.addComponents(add,remove);
        layout.addComponent(hlayout);
        layout.addComponent(grid);
        setContent(layout);
    }
}
