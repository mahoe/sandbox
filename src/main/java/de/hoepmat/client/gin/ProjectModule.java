package de.hoepmat.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;

import de.hoepmat.client.ui.HomeDisplay;

/**
 * Created by hoepmat on 6/25/14.
 */
public class ProjectModule extends AbstractGinModule
{
    @Override
    protected void configure()
    {
        bind(HomeDisplay.class).asEagerSingleton();
    }
}
