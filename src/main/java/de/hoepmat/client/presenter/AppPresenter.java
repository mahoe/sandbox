package de.hoepmat.client.presenter;

import de.hoepmat.client.ui.HomeDisplay;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * Created by mahoe on 11/14/2016.
 */
public interface AppPresenter
{
    void showInContainer(HasOneWidget viewport);

    interface AppDisplay extends Display {

        TextButton getAddButton();

        void addAToolButton(ToolButton t);
    }
}
