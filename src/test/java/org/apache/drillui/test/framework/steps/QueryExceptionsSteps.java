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
package org.apache.drillui.test.framework.steps;

import org.apache.drillui.test.framework.initial.TestProperties;
import org.apache.drillui.test.framework.initial.WebBrowser;
import org.apache.drillui.test.framework.pages.BasePage;
import org.apache.drillui.test.framework.pages.QueryExceptionPage;

public final class QueryExceptionsSteps {

  private QueryExceptionsSteps() {
  }

  public static boolean hasException() {
    WebBrowser.waitSeconds(TestProperties.defaultTimeout);
    String exception = BasePage.getPage(QueryExceptionPage.class).getFullStackTrace();
    return !exception.isEmpty();
  }

  public static String getFullStackTrace() {
    WebBrowser.waitSeconds(TestProperties.defaultTimeout);
    return BasePage.getPage(QueryExceptionPage.class).getFullStackTrace();
  }
}
