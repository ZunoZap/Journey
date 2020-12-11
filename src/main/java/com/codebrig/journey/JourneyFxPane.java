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

    private JourneyBrowserView view;

    public JourneyFxPane(CefBrowserProxy browser) {
        this(new JourneyBrowserView(browser));
    }

    private JourneyFxPane(JourneyBrowserView view) {
        this.view = view;
        SwingNode swingNode = new SwingNode();

        JComponent browserUi = (JComponent) getCefBrowser().getUIComponent();
        getChildren().add(swingNode);
        AnchorPane.setTopAnchor(swingNode, 0d);
        AnchorPane.setBottomAnchor(swingNode, 0d);
        AnchorPane.setRightAnchor(swingNode, 0d);
        AnchorPane.setLeftAnchor(swingNode, 0d);
        swingNode.setContent(browserUi);
    }

    public JourneyFxPane() {
        this(new JourneyBrowserView());
    }

    public JourneyFxPane(JourneySettings journeySettings, String initialUrl) {
        this(new JourneyBrowserView(journeySettings, initialUrl));
    }

    public JourneyFxPane(String initialUrl) {
        this(new JourneyBrowserView(initialUrl));
    }

    public JourneyFxPane(String[] args, JourneySettings journeySettings, String initialUrl) {
        this(new JourneyBrowserView(args,journeySettings, initialUrl));
    }

    public JourneyBrowserView getBrowserView() {
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
