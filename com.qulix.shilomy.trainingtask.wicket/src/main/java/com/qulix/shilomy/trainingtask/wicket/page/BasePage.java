package com.qulix.shilomy.trainingtask.wicket.page;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class BasePage extends WebPage {
    public static final String HOME = "home";

    public BasePage() {
        add(new BookmarkablePageLink<>(HOME, MainPage.class));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssHeaderItem.forUrl("css/general.css"));
        response.render(CssHeaderItem.forUrl("css/buttons.css"));
        response.render(CssHeaderItem.forUrl("css/inputs.css"));
        response.render(CssHeaderItem.forUrl("css/labels.css"));
        response.render(CssHeaderItem.forUrl("css/tables.css"));
    }
}
