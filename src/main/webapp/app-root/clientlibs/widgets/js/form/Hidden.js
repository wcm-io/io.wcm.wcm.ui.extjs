/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2014 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
/**
 * Hiden form field.
 * <p>Enhancements over CQ5 version:</p>
 * <ul>
 *   <li>Fix problem with default value in CQ54 </li>
 * </ul>
 */
io.wcm.wcm.ui.form.Hidden = CQ.Ext.extend(CQ.Ext.form.Hidden, {

  /**
   * Creates a new component.
   * @param config configuration
   */
  constructor : function(config) {
    config = config || {};

    // set value to defaultValue to fix problem in CQ54 with applying default values
    if (config.value===undefined && config.defaultValue!==undefined) {
      config.value = config.defaultValue;
    }

    io.wcm.wcm.ui.form.Hidden.superclass.constructor.call(this, config);
  }

});

CQ.Ext.reg("io.wcm.wcm.ui.hidden", io.wcm.wcm.ui.form.Hidden);
