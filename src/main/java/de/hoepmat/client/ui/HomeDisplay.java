package de.hoepmat.client.ui;

import com.google.gwt.user.client.ui.Label;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created by hoepmat on 6/25/14.
 */
public class HomeDisplay extends ContentPanel
{
    public HomeDisplay(){
        createUi();

    }

    private void createUi()
    {
        this.setHeadingText("Rechne!");
        this.setSize("800px", "600px");

        Label operandLeft = new Label("132");
        Label operandRight = new Label("132");
        Label operator = new Label("+");

        TextField result = new TextField();
        TextButton btnOk = new TextButton("FERTIG!");

        VerticalLayoutContainer con = new VerticalLayoutContainer();

        HorizontalLayoutContainer aufgabe = new HorizontalLayoutContainer();
        aufgabe.add(operandLeft,new HorizontalLayoutContainer.HorizontalLayoutData(.4,1));
        aufgabe.add(operator,new HorizontalLayoutContainer.HorizontalLayoutData(.2,1));
        aufgabe.add(operandRight,new HorizontalLayoutContainer.HorizontalLayoutData(.4,1));

        con.add(aufgabe, new VerticalLayoutContainer.VerticalLayoutData(1,.6));
        con.add(result, new VerticalLayoutContainer.VerticalLayoutData(1,.2));
        con.add(btnOk, new VerticalLayoutContainer.VerticalLayoutData(1,.2));

        this.setWidget(con);
    }
}
