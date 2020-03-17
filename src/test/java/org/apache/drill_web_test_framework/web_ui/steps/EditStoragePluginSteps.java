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
import org.apache.drill_web_test_framework.web_ui.pages.EditStoragePluginPage;

public class EditStoragePluginSteps extends BaseSteps {

  private EditStoragePluginPage getPage() {
    return BasePage.getPage(EditStoragePluginPage.class);
  }

  public EditStoragePluginSteps setPluginConfig(String pluginConfig) {
    getPage()
        .setPluginConfig(pluginConfig);
    return this;
  }

  public StorageSteps back() {
    getPage()
        .back();
    return getSteps(StorageSteps.class);
  }

  public boolean enabled() {
    setImplicitWait(0);
    boolean result = getPage().disableButtonPresented() &&
        !getPage().enableButtonPresented();
    resetImplicitWait();
    return result;
  }

  public boolean disabled() {
    setImplicitWait(0);
    boolean result = !getPage().disableButtonPresented() &&
        getPage().enableButtonPresented();
    resetImplicitWait();
    return result;
  }

  public boolean waitForEnabled() {
    getPage().waitForEnabled();
    return enabled();
  }

  public boolean waitForDisabled() {
    getPage().waitForDisabled();
    return disabled();
  }

  public String getPluginConfig() {
    return getPage().getPluginConfig();
  }

  public EditStoragePluginSteps update() {
    getPage().update();
    return this;
  }

  public EditStoragePluginSteps enable() {
    getPage().enable();
    return this;
  }

  public ConfirmDialogSteps disable() {
    getPage().disable();
    return getSteps(ConfirmDialogSteps.class);
  }

  public ConfirmDialogSteps delete() {
    getPage().delete();
    return getSteps(ConfirmDialogSteps.class);
  }

  public String getMessage() {
    return getPage().getMessage();
  }
}
