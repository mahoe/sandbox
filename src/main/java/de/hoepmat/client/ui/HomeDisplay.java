package de.hoepmat.client.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;

import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import de.hoepmat.client.logic.Aufgabe;

/**
 * Created by hoepmat on 6/25/14.
 */
public class HomeDisplay extends ContentPanel {

    private TextField textField;
    private int aufgabenCounter;
    private LinkedHashMap<Aufgabe, Result> aufgaben;
    private Iterator<Aufgabe> iterator;
    private long startTime;
    private TextButton cmdListResults;

    interface DisplayTemplate extends XTemplates {

        @XTemplate(source = "DisplayTemplate.html")
        public SafeHtml get(String operandA, String operator, String opernandB);
    }

    class Result {
        String value;
        boolean correct;
        long consumedTime;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }

        public long getConsumedTime() {
            return consumedTime;
        }

        public void setConsumedTime(long consumedTime) {
            this.consumedTime = consumedTime;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (correct) {
                sb.append("RICHTIG");
            } else {
                sb.append("FALSCH (").append(value).append(")");
            }
            sb.append(" in einer Zeit von:");
            sb.append(consumedTime);
            return sb.toString();
        }
    }

    private DisplayTemplate template;

    @Inject
    public HomeDisplay(DisplayTemplate template) {
        this.template = template;
        createUi();
        bind();
    }

    private void bind()
    {
        cmdListResults.addSelectHandler(new SelectEvent.SelectHandler()
        {
            public void onSelect(SelectEvent event)
            {

            }
        });
    }

    private void createUi()
    {
        this.setHeadingText("Rechne!");
        this.setSize("800px", "600px");

        aufgaben = new LinkedHashMap<Aufgabe, Result>();
        iterator = aufgaben.keySet().iterator();
        aufgabenCounter = 0;


        BorderLayoutContainer blc = new BorderLayoutContainer();
        FileUploadField fileInput = new FileUploadField();
        fileInput.addValueChangeHandler(new ValueChangeHandler<String>()
        {
            public void onValueChange(ValueChangeEvent<String> event)
            {
                Info.display("Value changed!", event.getValue());
            }
        });
        blc.setCenterWidget(fileInput);
        blc.setWidth(200);
        setWidget(blc);

        stelleAufgabe();
    }

    private void stelleAufgabe() {
        if (aufgabenCounter < 25) {
            aufgabenCounter++;

            final PopupPanel p = new PopupPanel(true, true);
            final Aufgabe aufgabe = Aufgabe.get(0, 10, Aufgabe.OPERATOR.PLUS, Aufgabe.OPERATOR.MINUS, Aufgabe.OPERATOR.MUL, Aufgabe.OPERATOR.DIV);

            String operandLeft = aufgabe.getOpA();
            String operandRight = aufgabe.getOpB();
            String operator = aufgabe.getOperator();

            HtmlLayoutContainer con = new HtmlLayoutContainer(template.get(operandLeft, operator, operandRight));
            textField = new TextField();
            con.add(textField, new AbstractHtmlLayoutContainer.HtmlData(".inputResult"));
            textField.addAttachHandler(new AttachEvent.Handler() {
                public void onAttachOrDetach(AttachEvent attachEvent) {
                    textField.focus();
                }
            });
            textField.addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent keyDownEvent) {
                    if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                        checkResult(aufgabe, p);
                    }
                }
            });
            final TextButton button = new TextButton("PR\u00dcFEN");
            con.add(button, new AbstractHtmlLayoutContainer.HtmlData(".okButton"));

            button.addSelectHandler(new SelectEvent.SelectHandler() {
                public void onSelect(SelectEvent selectEvent) {
                    checkResult(aufgabe, p);
                }
            });
            p.setWidget(con);
            p.center();
            p.show();
            textField.focus();
            startTime = System.currentTimeMillis();

        } else {
            zeigeErgebnisse();
        }
    }

    private void checkResult(final Aufgabe aufgabe, final PopupPanel p) {
        Scheduler.get().scheduleFinally(new Scheduler.ScheduledCommand() {
            public void execute() {
                final Result result = new Result();
                final String value = textField.getValue();
                result.setValue(value);
                result.setConsumedTime(System.currentTimeMillis() - startTime);
                final String resultAsString = aufgabe.getResultAsString();
                assert resultAsString != null;
                assert value != null;
                result.setCorrect(resultAsString.equals(value.trim()));
                aufgaben.put(aufgabe, result);
                p.hide();
                stelleAufgabe();

            }
        });
    }

    private void zeigeErgebnisse() {
        PopupPanel p = new PopupPanel(true, true);
        FlexTable table = new FlexTable();
        int row = 0;
        long timeSum = 0;
        int counterTrue = 0;
        int counterFalse = 0;

        for (Aufgabe aufgabe : aufgaben.keySet()) {
            table.setWidget(row, 0, new Label("Aufgabe: " + aufgabe.toString()));
            final Result result = aufgaben.get(aufgabe);
            table.setWidget(row++, 1, new Label("Ergebnis: " + result.toString()));
            timeSum += result.getConsumedTime();
            if (result.isCorrect()) {
                counterTrue++;
            } else {
                counterFalse++;
            }
        }

        table.setWidget(row++, 0, new Label("Richtig: " + counterTrue));
        table.setWidget(row++, 0, new Label("Falsch: " + counterFalse));
        table.setWidget(row++, 0, new Label("Zeit in Summe: " + timeSum));
        table.setWidget(row++, 0, new Label("Zeit je Aufgabe: " + (timeSum / aufgaben.size())));

        p.setWidget(table);
        p.center();
        p.show();

        cmdListResults = new TextButton("Alle Ergebnisse...");
        this.addButton(cmdListResults);
    }
}
