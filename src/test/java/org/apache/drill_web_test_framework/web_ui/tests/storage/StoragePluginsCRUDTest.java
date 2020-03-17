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
package org.apache.drill_web_test_framework.web_ui.tests.storage;

import org.apache.drill_web_test_framework.rest_api.data.RestStorageSteps;
import org.apache.drill_web_test_framework.web_ui.steps.BaseSteps;
import org.apache.drill_web_test_framework.web_ui.steps.EditStoragePluginSteps;
import org.apache.drill_web_test_framework.web_ui.steps.NavigationSteps;
import org.apache.drill_web_test_framework.web_ui.steps.StorageSteps;
import org.apache.drill_web_test_framework.web_ui.tests.FunctionalTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class StoragePluginsCRUDTest extends FunctionalTest {
  private final StorageSteps storageSteps = BaseSteps.getSteps(StorageSteps.class);
  private final EditStoragePluginSteps editStoragePluginSteps = BaseSteps.getSteps(EditStoragePluginSteps.class);
  private final NavigationSteps navigationSteps = BaseSteps.getSteps(NavigationSteps.class);

  private String testPluginConfig =
      "{\n" +
      "  \"type\": \"kafka\",\n" +
      "  \"kafkaConsumerProps\": {\n" +
      "    \"bootstrap.servers\": \"localhost:9092\",\n" +
      "    \"group.id\": \"drill-consumer\"\n" +
      "  },\n" +
      "  \"enabled\": false\n" +
      "}";

  private String testPluginName = "kafka_2";

  @BeforeClass
  public void setupStoragePlugins() {
    RestStorageSteps.setupStoragePlugins();
  }

  @BeforeMethod
  public void beforeMethod() {
    navigationSteps.navigateStorage();
    if (storageSteps.exists(testPluginName)) {
      storageSteps.update(testPluginName)
          .delete()
          .confirmAction();
      BaseSteps.waitForURL("/storage");
    }
  }

  @Test(groups = {"functional"})
  public void addPluginCancel() {
    storageSteps.openCreatePluginDialog();
    assertTrue(storageSteps.addPluginMode());
    storageSteps.fillNewPluginData(testPluginName, testPluginConfig)
        .closeNewPluginForm();
    assertEquals(BaseSteps.getURL(), "/storage");
    assertFalse(storageSteps.exists(testPluginName));
  }

  @Test(groups = {"functional"})
  public void addPluginCreate() {
    storageSteps.openCreatePluginDialog();
    assertTrue(storageSteps.addPluginMode());
    storageSteps.fillNewPluginData(testPluginName, testPluginConfig)
            .submitNewPluginForm();
    assertEquals(BaseSteps.getURL(), "/storage");
    assertTrue(storageSteps.exists(testPluginName));
    assertFalse(storageSteps.enabled(testPluginName));
  }

  @Test(groups = {"functional"})
  public void addPluginExisting() {
    storageSteps.openCreatePluginDialog()
        .fillNewPluginData(testPluginName, testPluginConfig.replace("\"enabled\": false", "\"enabled\": true"))
        .submitNewPluginForm();
    assertTrue(storageSteps.enabled(testPluginName));
  }

  @Test(groups = {"functional"})
  public void updatePluginUnableToParse() {
    String modifiedConfig = storageSteps.create(testPluginName, testPluginConfig)
        .update(testPluginName)
        .getPluginConfig()
        .toUpperCase();
    editStoragePluginSteps.setPluginConfig(modifiedConfig)
        .update();
    assertEquals(editStoragePluginSteps.getMessage(), "Please retry: Invalid JSON");
    editStoragePluginSteps.back()
        .update(testPluginName);
    assertEquals(editStoragePluginSteps.getPluginConfig(), testPluginConfig);
  }

  @Test(groups = {"functional"})
  public void updatePluginUnableInvalidMapping() {
    String modifiedConfig = storageSteps.create(testPluginName, testPluginConfig)
        .update(testPluginName)
        .getPluginConfig()
        .replace("kafka", "KAFKA");
    editStoragePluginSteps.setPluginConfig(modifiedConfig)
        .update();
    assertEquals(editStoragePluginSteps.getMessage(), "Please retry: Invalid JSON");
    editStoragePluginSteps.back()
        .update(testPluginName);
    assertEquals(editStoragePluginSteps.getPluginConfig(), testPluginConfig);
  }

  @Test(groups = {"functional"})
  public void updatePluginSuccess() {
    String modifiedConfig = storageSteps.create(testPluginName, testPluginConfig)
        .update(testPluginName)
        .getPluginConfig()
        .replace("9092", "1111");
    editStoragePluginSteps.setPluginConfig(modifiedConfig)
        .update();
    assertEquals(editStoragePluginSteps.getMessage(), "Success");
    BaseSteps.waitForURL("/storage");
    storageSteps.update(testPluginName);
    assertEquals(editStoragePluginSteps.getPluginConfig(), modifiedConfig);
  }

  @Test(groups = {"functional"})
  public void updatePluginEnableDisable() {
    String modifiedConfig = storageSteps.create(testPluginName, testPluginConfig)
        .update(testPluginName)
        .getPluginConfig()
        .replace("\"enabled\": false", "\"enabled\": true");
    editStoragePluginSteps.setPluginConfig(modifiedConfig)
        .update();
    assertEquals(editStoragePluginSteps.getMessage(), "Success");
    BaseSteps.waitForURL("/storage");
    assertTrue(storageSteps.enabled(testPluginName));
    storageSteps.update(testPluginName)
        .setPluginConfig(testPluginConfig)
        .update();
    assertEquals(editStoragePluginSteps.getMessage(), "Success");
    BaseSteps.waitForURL("/storage");
    assertFalse(storageSteps.enabled(testPluginName));
  }

  @Test(groups = {"functional"})
  public void enableDisablePlugin() {
    storageSteps.create(testPluginName, testPluginConfig)
        .update(testPluginName)
        .enable();
    assertEquals(editStoragePluginSteps.getMessage(), "Success");
    assertTrue(editStoragePluginSteps.waitForEnabled());
    editStoragePluginSteps.back();
    assertTrue(storageSteps.enabled(testPluginName));
    storageSteps.update(testPluginName)
        .disable()
        .confirmAction();
    assertEquals(editStoragePluginSteps.getMessage(), "Success");
    assertTrue(editStoragePluginSteps.waitForDisabled());
    editStoragePluginSteps.back();
    assertFalse(storageSteps.enabled(testPluginName));
  }

  @Test(groups = {"functional"})
  public void deletePlugin() {
    storageSteps.create(testPluginName, testPluginConfig)
        .update(testPluginName)
        .delete()
        .cancelAction();
    assertEquals(BaseSteps.getURL(), "/storage/" + testPluginName);
    editStoragePluginSteps.back();
    assertTrue(storageSteps.exists(testPluginName));
    storageSteps.update(testPluginName)
        .delete()
        .confirmAction();
    BaseSteps.waitForURL("/storage");
    assertFalse(storageSteps.exists(testPluginName));
  }
}
