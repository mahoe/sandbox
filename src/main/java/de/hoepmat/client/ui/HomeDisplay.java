package de.hoepmat.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.inject.Inject;

import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created by hoepmat on 6/25/14.
 */
public class HomeDisplay extends ContentPanel
{

    interface DisplayTemplate extends XTemplates{

        @XTemplate(source = "DisplayTemplate.html")
        public SafeHtml get(String operandA, String operator, String opernandB);
    }

    private DisplayTemplate template;

    @Inject
    public HomeDisplay(DisplayTemplate template){
        this.template=template;
        createUi();
    }

    private void createUi()
    {
        this.setHeadingText("Rechne!");
        this.setSize("800px", "600px");

        String operandLeft = "132";
        String operandRight = "132";
        String operator = "+";

        TextField result = new TextField();
        TextButton btnOk = new TextButton("FERTIG!");

        HtmlLayoutContainer con = new HtmlLayoutContainer(template.get(operandLeft,operator,operandRight));
        con.add(new TextField(), new AbstractHtmlLayoutContainer.HtmlData(".inputResult"));
        con.add(new TextButton("PR\u00dcFEN"), new AbstractHtmlLayoutContainer.HtmlData(".okButton"));

        this.setWidget(con);
    }
}
