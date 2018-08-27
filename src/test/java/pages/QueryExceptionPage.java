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
package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;

public class QueryExceptionPage extends BasePage {
  @FindBy(xpath = "/html/body/div[2]")
  private WebElement exceptionElement;

  public String getFullStackTrace() {
    try{
      exceptionElement.getText();
    } catch (NoSuchElementException e) {
      return "Selenium WebDriver error!\n" + e.getMessage();
    }
    return exceptionElement.getText().replace("back", "").trim();
  }

  public boolean hasQueryExceptionResult() {
    try{
      exceptionElement.getText();
    } catch (NoSuchElementException e) {
      return false;
    }
    return exceptionElement.getText().replace("back", "").trim().startsWith("Query Failed: ");
  }
}
