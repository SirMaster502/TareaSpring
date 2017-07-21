package com.umg.TareaSpring.visual;

import com.umg.TareaSpring.clases.Persona;
import com.umg.TareaSpring.clases.PersonaRepository;
import com.umg.TareaSpring.clases.TrabajoRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class App extends UI {
    @Autowired
    PersonaRepository personaRepository;

    protected void agregarPersonas (){
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout hlayout = new HorizontalLayout();

        TextField nombre = new TextField("Nombre Completo");
        DateField fecha = new DateField("Fecha de Ingreso");
        TextField domicilio = new TextField("Domicilio");

        Grid<Persona> grid = new Grid<>();
        grid.addColumn(Persona::getId).setCaption("ID");
        grid.addColumn(Persona::getNombre).setCaption("Nombre");
        grid.addColumn(Persona::getFecha).setCaption("Fecha de Ingreso");
        grid.addColumn(Persona::getDomicilio).setCaption("Domicilio");

        Button add = new Button("Agregar");
        add.addClickListener(new Button.ClickListener(){
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

        Button regresar = new Button("Regresar");
        regresar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Principal();
            }
        });

        layout.addComponents(nombre, fecha, domicilio);
        hlayout.addComponents(add);
        hlayout.addComponent(regresar);
        layout.addComponent(hlayout);
        layout.addComponent(grid);
        setContent(layout);
    }

    protected void revisarPersonas(){
        VerticalLayout layout = new VerticalLayout();

        Grid<Persona> grid = new Grid<>();
        grid.addColumn(Persona::getId).setCaption("ID");
        grid.addColumn(Persona::getNombre).setCaption("Nombre");
        grid.addColumn(Persona::getFecha).setCaption("Fecha de Ingreso");
        grid.addColumn(Persona::getDomicilio).setCaption("Domicilio");

        grid.setItems(personaRepository.findAll());

        Button regresar = new Button("Regresar");
        regresar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Principal();
            }
        });

        layout.addComponent(grid);
        layout.addComponent(regresar);
        setContent(layout);
    }

    protected void Principal() {
        VerticalLayout layout = new VerticalLayout();

        Button pantalla1 = new Button("Agregar Datos");
        pantalla1.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                agregarPersonas();
            }
        });

        Button pantalla2 = new Button("Revisar Personas");
        pantalla2.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                revisarPersonas();
            }
        });
        layout.addComponent(pantalla1);
        layout.addComponent(pantalla2);
        setContent(layout);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Principal();
    }
}
