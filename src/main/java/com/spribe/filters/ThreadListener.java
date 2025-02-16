package com.spribe.filters;

import com.spribe.ConfigManager;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class ThreadListener implements IAlterSuiteListener {

    @Override
    public void alter(List<XmlSuite> suites) {
        String threadCount = ConfigManager.getThreadCount();
        for (XmlSuite suite : suites) {
            suite.setThreadCount(Integer.parseInt(threadCount));
        }
    }
}
