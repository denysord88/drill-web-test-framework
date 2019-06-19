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
package org.apache.drill_web_test_framework.web_ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OptionsPage extends BasePage {

  @FindBy(css = "input[id='searchBox']")
  private WebElement searchField;

  @FindBy(css = "button[title='Clear search']")
  private WebElement clearSearchButton;

  @FindBy(xpath = "//button[text()='planner']")
  private WebElement plannerFilter;

  @FindBy(xpath = "//button[text()='store']")
  private WebElement storeFilter;

  @FindBy(xpath = "//button[text()='parquet']")
  private WebElement parquetFilter;

  @FindBy(xpath = "//button[text()='hashagg']")
  private WebElement hashaggFilter;

  @FindBy(xpath = "//button[text()='hashjoin']")
  private WebElement hashjoinFilter;

  @FindBy(xpath = "//th[text()='OPTION']")
  private WebElement optionSortBy;

  @FindBy(xpath = "//th[text()='VALUE']")
  private WebElement valueSortBy;

  @FindBy(xpath = "//th[text()='DESCRIPTION']")
  private WebElement descriptionSortBy;

  @FindBy(css = "button[onclick=\"alterSysOptionUsingId('drill.exec.functions.cast_empty_string_to_null')\"]")
  private WebElement updateBooleanButton;

  @FindBy(css = "form[id='drill.exec.functions.cast_empty_string_to_null']>div>div>select")
  private WebElement booleanDropdown;

  @FindBy(css = "form[id='drill.exec.functions.cast_empty_string_to_null']>div>div>select>option[value=\"true\"]")
  private WebElement booleanDropdownTrue;

  @FindBy(css = "button[onclick=\"alterSysOption('drill.exec.functions.cast_empty_string_to_null','false', 'BOOLEAN')\"]")
  private WebElement booleanDefaultButton;

  @FindBy(css = "form[id='drill.exec.memory.operator.output_batch_size']>div>div>input")
  private WebElement numericField;

  @FindBy(css = "button[onclick=\"alterSysOptionUsingId('drill.exec.memory.operator.output_batch_size')\"]")
  private WebElement numericUpdateButton;

  @FindBy(css = "button[onclick=\"alterSysOption('drill.exec.memory.operator.output_batch_size','16777216', 'LONG')\"]")
  private WebElement numericDefaultButton;

  @FindBy(css = "form[id='drill.exec.storage.file.partition.column.label']>div>div>input")
  private WebElement stringField;

  @FindBy(css = "button[onclick=\"alterSysOptionUsingId('drill.exec.storage.file.partition.column.label')\"]")
  private WebElement stringUpdateButton;

  @FindBy(css = "button[onclick=\"alterSysOption('drill.exec.storage.file.partition.column.label','dir', 'STRING')\"]")
  private WebElement stringDefaultButton;

  public void searchFieldSendKeys(String string) {
    searchField.clear();
    searchField.sendKeys(string);
  }

  public void clearSearchField() {
    clearSearchButton.click();
  }

  public void updateBooleanOption() {
    booleanDropdown.click();
    booleanDropdownTrue.click();
    updateBooleanButton.click();
  }

  public void setBooleanToDefault() {
    booleanDefaultButton.click();
  }

  public void updateNumericOption(String string) {
    numericField.clear();
    numericField.sendKeys(string);
    numericUpdateButton.click();
  }

  public void setNumericToDefault() {
    numericDefaultButton.click();
  }

  public void updateStringOption(String string) {
    stringField.clear();
    stringField.sendKeys(string);
    stringUpdateButton.click();
  }

  public void setStringToDefault() {
    stringDefaultButton.click();
  }

  public void selectPlannerFilter() {
    plannerFilter.click();
  }

  public void selectStoreFilter() {
    storeFilter.click();
  }

  public void selectParquetFilter() {
    parquetFilter.click();
  }

  public void selectHashAggFilter() {
    hashaggFilter.click();
  }

  public void selectHashJoinFilter() {
    hashjoinFilter.click();
  }

  public void sortByOption() {
    optionSortBy.click();
  }

  public void sortByValue() {
    valueSortBy.click();
  }

  public void sortByDescription() {
    descriptionSortBy.click();
  }

}
