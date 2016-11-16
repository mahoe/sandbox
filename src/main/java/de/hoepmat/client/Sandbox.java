package de.hoepmat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.RootPanel;

import de.hoepmat.client.gin.ProjectGinjector;

import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sandbox implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  private final Messages messages = GWT.create(Messages.class);

    ProjectGinjector projectGinjector = GWT.create(ProjectGinjector.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    Viewport viewport = new Viewport();
//      RootPanel.get().add(projectGinjector.getHomeDisplay());
      RootPanel.get().add(viewport);
    projectGinjector.getAppPresenter().showInContainer(viewport);
  }
}
