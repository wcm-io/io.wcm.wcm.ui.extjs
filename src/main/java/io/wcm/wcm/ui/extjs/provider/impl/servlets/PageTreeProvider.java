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
package io.wcm.wcm.ui.extjs.provider.impl.servlets;

import io.wcm.wcm.commons.contenttype.FileExtension;
import io.wcm.wcm.ui.extjs.provider.AbstractPageTreeProvider;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.servlets.HttpConstants;

import com.day.cq.commons.predicate.PredicateProvider;

/**
 * Exports the resource tree at the addressed resource in JSON format to the response.
 * This can be used by the <code>io.wcm.wcm.ui.form.BrowseField</code> widget.
 */
@SlingServlet(
    extensions = FileExtension.JSON,
    selectors = "wcm-io-wcm-ui-extjs-pagetree",
    resourceTypes = "sling/servlet/default",
    methods = HttpConstants.METHOD_GET)
public final class PageTreeProvider extends AbstractPageTreeProvider {
  private static final long serialVersionUID = 1L;

  @Reference(cardinality = ReferenceCardinality.OPTIONAL_UNARY)
  private PredicateProvider predicateProvider;

  @Override
  protected PredicateProvider getPredicateProvider() {
    return predicateProvider;
  }

}
