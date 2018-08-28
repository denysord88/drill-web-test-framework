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
package org.apache.drillui.test.framework.initial;

import java.io.FileInputStream;
import java.util.Properties;

public abstract class TestProperties {
  public static String drillHost;

  public static WebBrowser.DRIVER driverType;

  public static String webdriversPath;

  public static boolean secureDrill;

  public static int defaultTimeout;

  public static String drillUserName;

  public static String drillUserPassword;

  static {
    try (FileInputStream in = new FileInputStream("conf/init.properties")) {
      Properties p = new Properties();
      p.load(in);
      drillHost = p.getProperty("DRILL_HOST");
      driverType = WebBrowser.DRIVER.valueOf(p.getProperty("DRIVER_TYPE"));
      webdriversPath = p.getProperty("WEBDRIVERS_PATH");
      secureDrill = Boolean.parseBoolean(p.getProperty("SECURE_DRILL"));
      defaultTimeout = Integer.parseInt(p.getProperty("DEFAULT_TIMEOUT"));
      drillUserName = p.getProperty("DRILL_USER_NAME");
      drillUserPassword = p.getProperty("DRILL_USER_PASSWORD");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
