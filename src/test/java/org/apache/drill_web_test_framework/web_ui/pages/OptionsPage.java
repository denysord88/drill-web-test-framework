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
  @FindBy (css = "a[href='/options']")
  WebElement optionsTab;

  @FindBy (css = "input[id='searchBox']")
  WebElement searchField;

  @FindBy (css = "button[title='Clear search']")
  WebElement clearSearchButton;

  @FindBy (xpath = "//button[text()='planner']")
  WebElement plannerFilter;

  @FindBy (xpath = "//button[text()='store']")
  WebElement storeFilter;

  @FindBy (xpath = "//button[text()='parquet']")
  WebElement parquetFilter;

  @FindBy (xpath = "//button[text()='hashagg']")
  WebElement hashaggFilter;

  @FindBy (xpath = "//button[text()='hashjoin']")
  WebElement hashjoinFilter;

  @FindBy (xpath = "//th[text()='OPTION']")
  WebElement optionSortBy;

  @FindBy (xpath = "//th[text()='VALUE']")
  WebElement valueSortBy;

  @FindBy (xpath = "//th[text()='DESCRIPTION']")
  WebElement descriptionSortBy;
}
