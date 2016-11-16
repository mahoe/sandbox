package de.hoepmat.client.presenter;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.LayoutCommand;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.SplitButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * Created by mahoe on 11/14/2016.
 */
public class AppDisplayImpl extends ContentPanel implements AppPresenter.AppDisplay
{

    private final TextButton addButton;

    private HBoxLayoutContainer toolBar;
    private ToolBar leftToolBar;
    private ToolBar middleToolbar;
    private ToolBar rightToolbar;

    public AppDisplayImpl()
    {
        this.getHeader().setHTML(SafeHtmlUtils.fromSafeConstant("<img src='https://www.hoepfnersoftware.de/administrator/templates/isis/images/logo.png'/>"));

        addButton = new TextButton("ADD Tool");
        this.addButton(addButton);

        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        this.add(vlc);

        MenuBar menuBar = new MenuBar();
        MenuItem itemA = new MenuItem("A");

        Menu menuA = new Menu();
        menuA.setTitle("AAA");
        menuA.add(itemA);
        menuBar.add( new MenuBarItem("Das A Menu", menuA));
        vlc.add(menuBar, new VerticalLayoutContainer.VerticalLayoutData(1,-1));

        HBoxLayoutContainer layoutleft = new HBoxLayoutContainer();
        layoutleft.setPack(BoxLayoutContainer.BoxLayoutPack.START);

        BoxLayoutContainer.BoxLayoutData flex0 = new BoxLayoutContainer.BoxLayoutData(new Margins(0));
        BoxLayoutContainer.BoxLayoutData flex1 = new BoxLayoutContainer.BoxLayoutData(new Margins(0));
        flex1.setFlex(1);

        leftToolBar = new ToolBar();
        middleToolbar = new ToolBar();
        layoutleft.add(leftToolBar, flex0);
        layoutleft.add(middleToolbar, flex0);

        rightToolbar = new ToolBar();
        rightToolbar.setPack(BoxLayoutContainer.BoxLayoutPack.END);

        toolBar = new HBoxLayoutContainer();
        toolBar.add(layoutleft, flex0);
        toolBar.add(rightToolbar, flex1);
        vlc.add(toolBar, new VerticalLayoutContainer.VerticalLayoutData(1,-1));
    }

    public Widget asWidget()
    {
        return this;
    }

    public TextButton getAddButton(){
        return addButton;
    }

    public void addAToolButton(ToolButton t)
    {
        this.getHeader().addTool( t );
        leftToolBar.add( getSplitButton("A text"));
        middleToolbar.add( getSplitButton("B text"));
        rightToolbar.add( getSplitButton("C text"));

//        leftToolBar.forceLayout();
//        middleToolbar.forceLayout();
//        rightToolbar.forceLayout();
        Scheduler.get().scheduleFinally(new Scheduler.ScheduledCommand()
        {
            public void execute()
            {
                AppDisplayImpl.this.onResize();
                AppDisplayImpl.this.forceLayout();
            }
        });
    }

    private SplitButton getSplitButton(String s)
    {
        Menu menu2 = new Menu();
        MenuItem item1 = new MenuItem();
        item1.setHTML(SafeHtmlUtils.fromSafeConstant("<b>Bold</b>"));

        MenuItem item2 = new MenuItem();
        item2.setHTML(SafeHtmlUtils.fromSafeConstant("<i>Italic</i>"));

        MenuItem item3 = new MenuItem();
        item3.setHTML(SafeHtmlUtils.fromSafeConstant("<u>Underline</u>"));

        menu2.add(item1);
        menu2.add(item2);
        menu2.add(item3);

        SplitButton splitItem = new SplitButton(s);
        splitItem.setMenu(menu2);
        return splitItem;
    }

}
