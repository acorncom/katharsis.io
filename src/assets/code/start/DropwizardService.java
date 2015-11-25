package io.katharsis.example.dropwizardSimple;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.katharsis.locator.SampleJsonServiceLocator;
import io.katharsis.rs.KatharsisFeature;

import static io.katharsis.rs.KatharsisProperties.*;

public class DropwizardService extends Application<DropwizardConfiguration> {

    @Override
    public void run(DropwizardConfiguration dropwizardConfiguration, Environment environment)
        throws Exception {

        environment.jersey()
            .property(RESOURCE_DEFAULT_DOMAIN, dropwizardConfiguration.katharsis.host);
        environment.jersey()
            .property(RESOURCE_SEARCH_PACKAGE, dropwizardConfiguration.katharsis.searchPackage);

        KatharsisFeature katharsisFeature = new KatharsisFeature(environment.getObjectMapper(), new
                SampleJsonServiceLocator());
        environment.jersey().register(katharsisFeature);
    }

    public static void main(String[] args) throws Exception {
        new DropwizardService().run(args);
    }
}
