package de.hoepmat.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import de.hoepmat.client.ui.HomeDisplay;

/**
 * Created by hoepmat on 6/25/14.
 */
@GinModules(ProjectModule.class)
public interface ProjectGinjector extends Ginjector
{
    HomeDisplay getHomeDisplay();
}
