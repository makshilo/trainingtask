package com.qulix.shilomy.trainingtask.wicket;

import com.qulix.shilomy.trainingtask.wicket.page.MainPage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import java.nio.charset.StandardCharsets;

public class TrainingtaskWebApplication extends WebApplication {
    @Override
    public Class< ? extends Page> getHomePage() {
        getMarkupSettings().setDefaultMarkupEncoding(StandardCharsets.UTF_8.name());
        return MainPage.class;
    }
}
