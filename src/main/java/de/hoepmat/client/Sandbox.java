package de.hoepmat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.RootPanel;

import de.hoepmat.client.gin.ProjectGinjector;

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

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  private final Messages messages = GWT.create(Messages.class);

    ProjectGinjector projectGinjector = GWT.create(ProjectGinjector.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
      RootPanel.get().add(projectGinjector.getHomeDisplay());
  }
}
