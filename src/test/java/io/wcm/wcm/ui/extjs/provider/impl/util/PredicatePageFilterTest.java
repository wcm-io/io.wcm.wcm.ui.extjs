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
package io.wcm.wcm.ui.extjs.provider.impl.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.apache.commons.collections.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.day.cq.wcm.api.Page;

@RunWith(MockitoJUnitRunner.class)
public class PredicatePageFilterTest {

  @Mock
  private Predicate predicate;
  @Mock
  private Page page;

  private PredicatePageFilter underTest;

  @Before
  public void setUp() throws Exception {
    underTest = new PredicatePageFilter(predicate);
    when(page.isValid()).thenReturn(true);
    when(page.isHideInNav()).thenReturn(false);
  }

  @Test
  public void testIncludesAccept() {
    when(predicate.evaluate(page)).thenReturn(true);
    assertTrue(underTest.includes(page));
  }

  @Test
  public void testIncludesDeny() {
    when(predicate.evaluate(page)).thenReturn(false);
    assertFalse(underTest.includes(page));
  }

  @Test
  public void testIncludesAccept_InvalidPage() {
    when(predicate.evaluate(page)).thenReturn(true);
    when(page.isValid()).thenReturn(false);
    assertFalse(underTest.includes(page));
  }

}
