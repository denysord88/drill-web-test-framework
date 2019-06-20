/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.drill_web_test_framework.web_ui.tests.options;

import org.apache.drill_web_test_framework.properties.PropertiesConst;
import org.apache.drill_web_test_framework.web_ui.steps.AuthSteps;
import org.apache.drill_web_test_framework.web_ui.steps.BaseSteps;
import org.apache.drill_web_test_framework.web_ui.steps.OptionsSteps;
import org.apache.drill_web_test_framework.web_ui.tests.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class OptionsTest extends BaseTest {
  private final AuthSteps authSteps = BaseSteps.getSteps(AuthSteps.class);
  private final OptionsSteps optionsSteps = BaseSteps.getSteps(OptionsSteps.class);

  // It is possible to sort options by "OPTION", "VALUE", "DESCRIPTION"
  @Test(groups = {"functional"})
  public void sortingOptionsTable(){
    List<List<String>> firstTable = authSteps.login(PropertiesConst.ADMIN_1_NAME, PropertiesConst.ADMIN_1_PASSWORD).navigateOptions().getOptionsTable();
    optionsSteps.sortByOption();
    List<List<String>> secondTable = optionsSteps.getOptionsTable();
    assertNotEquals(firstTable,secondTable);
    optionsSteps.selectPlannerFilter();
    firstTable = optionsSteps.getOptionsTable();
    assertNotEquals(firstTable,secondTable);
    optionsSteps.selectStoreFilter();
    secondTable = optionsSteps.getOptionsTable();
    assertNotEquals(firstTable,secondTable);
    optionsSteps.selectParquetFilter();
    firstTable = optionsSteps.getOptionsTable();
    assertNotEquals(firstTable,secondTable);
    optionsSteps.selectHashAggFilter();
    secondTable = optionsSteps.getOptionsTable();
    assertNotEquals(firstTable,secondTable);
    optionsSteps.selectHashJoinFilter();
    firstTable = optionsSteps.getOptionsTable();
    assertNotEquals(firstTable,secondTable);
    authSteps.logOut();
  }

  @Test(groups = {"functional"})
  public void updateOptions() throws InterruptedException {
    authSteps.login(PropertiesConst.ADMIN_1_NAME, PropertiesConst.ADMIN_1_PASSWORD).navigateOptions().searchFieldSendKeys("drill.exec");
    List<List<String>> table = optionsSteps.getOptionsTable();
    for (List<String> x : table) {
      if (x != table.get(0)) {
        assertTrue(x.get(0).contains("drill.exec"));
      }
    }
    optionsSteps.areAllOptionsOnTheScreen();

    optionsSteps.updateBooleanOption();
    assertTrue(optionsSteps.isValueEqualsTrue());
    optionsSteps.setBooleanToDefault();
    assertTrue(optionsSteps.isValueEqualsFalse());
    optionsSteps.updateNumericOption("16777217");
    assertTrue(optionsSteps.isValueOfNumericUpdated());
    optionsSteps.setNumericToDefault();
    assertTrue(optionsSteps.isValueOfNumericDefault());
    optionsSteps.updateStringOption("test");
    assertTrue(optionsSteps.isValueOfStringUpdated());
    optionsSteps.setStringToDefault();
    assertTrue(optionsSteps.isValueOfStringDefault());
    optionsSteps.clearSearchField();
    optionsSteps.areAllOptionsOnTheScreen();
  }

}
