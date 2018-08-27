/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.drillui.test.framework.testng;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.drillui.test.framework.initial.TestProperties;
import org.apache.drillui.test.framework.initial.WebBrowser;
import org.apache.drillui.test.framework.pages.BasePage;
import org.apache.drillui.test.framework.pages.ErrorPage;
import org.apache.drillui.test.framework.pages.QueryExceptionPage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.LinkedList;

public class EventsListener implements ITestListener {
  private static final String LOG_LINES_SEPARATOR = "-------------------------------------";

  @Override
  public void onTestStart(ITestResult iTestResult) {

  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {

  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    String error = getError();
    if (error.isEmpty()) {
      return;
    }

    System.out.println("\n==============\n" + error + "\n==============\n");

    Throwable throwable = iTestResult.getThrowable();
    String originalMessage = throwable.getMessage();
    String newMessage = "\n" + LOG_LINES_SEPARATOR + "\nDrill error:\n\n" + error + "\n\n" +
        LOG_LINES_SEPARATOR + "\nFramework error:\n\n" + originalMessage;
    System.out.println("\n" + newMessage.length() + ">>>>>>>>>>>>>>>>>>>>>>>\n" + newMessage + "\n>>>>>>>>>>>>>>>>>>>>>>>\n");
    try {
      FieldUtils.writeField(throwable, "detailMessage", newMessage, true);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("++++++++++++++++++++++++++++++++++++\n" + iTestResult.getThrowable().getMessage() + "\n++++++++++++++++++++++++++++++++++++");
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {

  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

  }

  @Override
  public void onStart(ITestContext iTestContext) {

  }

  @Override
  public void onFinish(ITestContext iTestContext) {

  }

  public static String getError() {
    if (BasePage.getPage(ErrorPage.class).hasErrorOnPage()) {
      return BasePage.getPage(ErrorPage.class).getFullStackTrace();
    } else if (BasePage.getPage(QueryExceptionPage.class).hasQueryExceptionResult()) {
      return BasePage.getPage(QueryExceptionPage.class).getFullStackTrace();
    }
    return "";
  }
}
