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
package org.apache.drillui.test.framework.testng.unsecure.query;

import org.apache.drillui.test.framework.initial.TestProperties;
import org.apache.drillui.test.framework.initial.WebBrowser;
import org.apache.drillui.test.framework.steps.ErrorSteps;
import org.testng.annotations.Test;
import org.apache.drillui.test.framework.steps.QuerySteps;
import org.apache.drillui.test.framework.testng.unsecure.BaseUnsecureTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ErrorTest extends BaseUnsecureTest {
  @Test(groups = {"functional"})
  public void queryWithError() {
    WebBrowser.getDriver().get(TestProperties.drillHost + "/djmfhhgkdjs");
    assertTrue(ErrorSteps.hasError());
    if(TestProperties.driverType.name().equals("CHROME") || TestProperties.driverType.name().equals("EDGE")) {
      assertEquals(ErrorSteps.getFullStackTrace(), ("{\n  \"errorMessage\" : \"HTTP 404 Not Found\"\n}"));
    } else if (TestProperties.driverType.name().equals("FIREFOX")) {
      assertEquals(ErrorSteps.getFullStackTrace(), ("errorMessage \"HTTP 404 Not Found\""));
    }
  }

  @Test(groups = {"functional"})
  public void queryWithoutError() {
    WebBrowser.getDriver().get(TestProperties.drillHost);
    QuerySteps.runQuery("SELECT * FROM cp.`employee.json` LIMIT 9");
    assertFalse(ErrorSteps.hasError());
  }

}
