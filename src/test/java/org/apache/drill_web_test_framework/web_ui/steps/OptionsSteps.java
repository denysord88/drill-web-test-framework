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
package org.apache.drill_web_test_framework.web_ui.steps;

import org.apache.drill_web_test_framework.web_ui.pages.BasePage;
import org.apache.drill_web_test_framework.web_ui.pages.OptionsPage;

import java.util.List;

public final class OptionsSteps extends BaseSteps {

  private OptionsPage getOptionsPage() {
    return BasePage.getPage(OptionsPage.class);
  }

  public void searchFieldSendKeys(String string) {
    getOptionsPage().searchFieldSendKeys(string);
  }

  public void clearSearchField() {
    getOptionsPage().clearSearchField();
  }

  public void updateBooleanOption() {
    getOptionsPage().updateBooleanOption();
  }

  public void setBooleanToDefault() {
    getOptionsPage().setBooleanToDefault();
  }

  public void updateNumericOption(String string) {
    getOptionsPage().updateNumericOption(string);
  }

  public void setNumericToDefault() {
    getOptionsPage().setNumericToDefault();
  }

  public void updateStringOption(String string) {
    getOptionsPage().updateStringOption(string);
  }

  public void setStringToDefault() {
    getOptionsPage().setStringToDefault();
  }

  public void selectPlannerFilter() {
    getOptionsPage().selectPlannerFilter();
  }

  public void selectStoreFilter() {
    getOptionsPage().selectStoreFilter();
  }

  public void selectParquetFilter() {
    getOptionsPage().selectParquetFilter();
  }

  public void selectHashAggFilter() {
    getOptionsPage().selectHashAggFilter();
  }

  public void selectHashJoinFilter() {
    getOptionsPage().selectHashJoinFilter();
  }

  public List<List<String>> getOptionsTable() {
    return getOptionsPage().getOptionsTable();
  }

  public void sortByOption() {
    getOptionsPage().sortByOption();
  }

  public void sortByValue() {
    getOptionsPage().sortByValue();
  }

  public void sortByDescription() {
    getOptionsPage().sortByDescription();
  }

  public boolean isValueEqualsTrue() {
    return getOptionsPage().isValueEqualsTrue();
  }

  public boolean isValueEqualsFalse() {
    return getOptionsPage().isValueEqualsFalse();
  }

  public boolean isValueOfNumericUpdated() {
    return getOptionsPage().isValueOfNumericUpdated();
  }

  public boolean isValueOfNumericDefault() {
    return getOptionsPage().isValueOfNumericDefault();
  }

  public boolean isValueOfStringUpdated(){
    return getOptionsPage().isValueOfStringUpdated();
  }

  public boolean isValueOfStringDefault(){
    return getOptionsPage().isValueOfStringDefault();
  }

  public boolean areAllOptionsOnTheScreen(){
    return getOptionsPage().areAllOptionsOnTheScreen();
  }
}
