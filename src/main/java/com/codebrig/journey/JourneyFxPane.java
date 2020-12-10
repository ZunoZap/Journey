package com.codebrig.journey;

import com.codebrig.journey.proxy.CefAppProxy;
import com.codebrig.journey.proxy.CefBrowserProxy;
import com.codebrig.journey.proxy.CefClientProxy;
import com.codebrig.journey.proxy.browser.CefFrameProxy;
import com.codebrig.journey.proxy.handler.CefLifeSpanHandlerProxy;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import static org.joor.Reflect.on;

/**
 * Wraps CefApp/CefClient/CefBrowser and extends JComponent for ease of implementation.
 *
 * @author Isaiah
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class JourneyFxPane extends AnchorPane {

    private BrowserView view;

    public JourneyFxPane(CefBrowserProxy browser) {
        this.view = new BrowserView(browser);
        this();
    }

    public JourneyFxPane() {
        if (null == this.view)
            this.view = new BrowserView();
        SwingNode swingNode = new SwingNode();

        Component browserUi = (Component) getCefBrowser().getUIComponent();
        uis.add(browserUi);
        getChildren().add(swingNode);
        AnchorPane.setTopAnchor(swingNode, 0d);
        AnchorPane.setBottomAnchor(swingNode, 0d);
        AnchorPane.setRightAnchor(swingNode, 0d);
        AnchorPane.setLeftAnchor(swingNode, 0d);
        swingNode.setContent(browserUi);
    }

    public JourneyFxPane(JourneySettings journeySettings, String initialUrl) {
        this.view = new BrowserView(journeySettings, initialUrl);
        this();
    }

    public JourneyFxPane(String initialUrl) {
        this.view = new BrowserView(initialUrl);
        this();
    }

    public JourneyFxPane(String[] args, JourneySettings journeySettings, String initialUrl) {
        this.view = new BrowserView(args,journeySettings, initialUrl);
        this();
    }

    public BrowserView getBrowserView() {
        return this.view;
    }

    public JourneySettings getJourneySettings() {
        return view.getJourneySettings();
    }

    public CefAppProxy getCefApp() {
        return view.getCefApp();
    }

    public CefClientProxy getCefClient() {
        return view.getCefClient();
    }

    public CefBrowserProxy getCefBrowser() {
        return view.getCefBrowser();
    }

}
