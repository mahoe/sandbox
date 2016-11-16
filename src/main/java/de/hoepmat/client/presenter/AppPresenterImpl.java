package de.hoepmat.client.presenter;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

/**
 * Created by mahoe on 11/14/2016.
 */
public class AppPresenterImpl implements AppPresenter
{
    private AppDisplay display;

    @Inject
    public AppPresenterImpl(AppPresenter.AppDisplay display )
    {
        this.display = display;
    }

    public void showInContainer(HasOneWidget viewport)
    {
        viewport.setWidget(display.asWidget());
//        viewport.setWidget(new TextButton("sdfsdfsdf"));

        display.getAddButton().addSelectHandler(new SelectEvent.SelectHandler()
        {
            public void onSelect(SelectEvent event)
            {
                ToolButton t = new ToolButton(ToolButton.PLUS);
                display.addAToolButton(t);
            }
        });
    }
}
