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
package testng.secure.login;

import initial.TestProperties;
import org.testng.annotations.Test;
import steps.AuthSteps;
import testng.secure.BaseSecureTest;

import static org.testng.Assert.*;

public class LoginTest extends BaseSecureTest {
  @Test(groups = {"functional"})
  public void testLogin() {
    assertEquals(
        AuthSteps.login(TestProperties.drillUserName, TestProperties.drillUserPassword).getLogoutText(),
        "Log Out (" + TestProperties.drillUserName + ")", "Login failed");
  }

  @Test(groups = {"functional"})
  public void testLogout() {
    assertEquals(AuthSteps.logOut().getLoginText(), "Log In", "Logout failed");
  }
}
