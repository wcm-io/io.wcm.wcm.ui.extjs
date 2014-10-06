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
package io.wcm.wcm.ui.provider;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.jackrabbit.util.Text;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.commons.Filter;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.Template;
import com.day.cq.wcm.api.WCMException;

/**
 * Virtual page implementation for handling sling:Folder and sling:OrderedFolder
 * nodes as pages in {@link PageIterator}
 */
class SlingFolderVirtualPage implements Page {

  private final Resource resource;

  public SlingFolderVirtualPage(Resource resource) {
    this.resource = resource;
  }

  @Override
  public String getDescription() {
    return getProperties().get(JcrConstants.JCR_DESCRIPTION, String.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <AdapterType> AdapterType adaptTo(Class<AdapterType> pType) {
    if (pType == Resource.class) {
      return (AdapterType)resource;
    }
    else {
      return resource.adaptTo(pType);
    }
  }

  @Override
  public boolean canUnlock() {
    return false;
  }

  @Override
  public Page getAbsoluteParent(int level) {
    String parentPath = Text.getAbsoluteParent(resource.getPath(), level);
    Resource parentResource = resource.getResourceResolver().getResource(parentPath);
    if (parentResource != null) {
      return parentResource.adaptTo(Page.class);
    }
    return null;
  }

  @Override
  public Resource getContentResource() {
    return resource.getChild("./" + JcrConstants.JCR_CONTENT);
  }

  @Override
  public Resource getContentResource(String path) {
    Resource contentResource = getContentResource();
    if (contentResource != null) {
      return contentResource.getChild(path);
    }
    return null;
  }

  @Override
  public int getDepth() {
    return StringUtils.split(resource.getPath(), "/").length - 1;
  }

  @Override
  public Locale getLanguage(boolean arg0) {
    return null;
  }

  @Override
  public Calendar getLastModified() {
    return null;
  }

  @Override
  public String getLastModifiedBy() {
    return null;
  }

  @Override
  public String getLockOwner() {
    return null;
  }

  @Override
  public String getName() {
    return resource.getName();
  }

  @Override
  public String getNavigationTitle() {
    return null;
  }

  @Override
  public Calendar getOffTime() {
    return null;
  }

  @Override
  public Calendar getOnTime() {
    return null;
  }

  @Override
  public PageManager getPageManager() {
    return resource.getResourceResolver().adaptTo(PageManager.class);
  }

  @Override
  public String getPageTitle() {
    return null;
  }

  @Override
  public Page getParent() {
    Resource parentResource = resource.getParent();
    return parentResource.adaptTo(Page.class);
  }

  @Override
  public Page getParent(int level) {
    String parentPath = Text.getRelativeParent(resource.getPath(), level);
    Resource parentResource = resource.getResourceResolver().getResource(parentPath);
    if (parentResource != null) {
      return parentResource.adaptTo(Page.class);
    }
    return null;
  }

  @Override
  public String getPath() {
    return resource.getPath();
  }

  @Override
  public ValueMap getProperties() {
    Resource contentResource = getContentResource();
    if (contentResource == null) {
      return ValueMap.EMPTY;
    }
    else {
      return contentResource.getValueMap();
    }
  }

  @Override
  public ValueMap getProperties(String path) {
    Resource contentResource = getContentResource(path);
    if (contentResource == null) {
      return ValueMap.EMPTY;
    }
    else {
      return contentResource.getValueMap();
    }
  }

  @Override
  public Tag[] getTags() {
    return null;
  }

  @Override
  public Template getTemplate() {
    return null;
  }

  @Override
  public String getTitle() {
    return getProperties().get(JcrConstants.JCR_TITLE, String.class);
  }

  @Override
  public String getVanityUrl() {
    return null;
  }

  @Override
  public boolean hasChild(String arg0) {
    return false;
  }

  @Override
  public boolean hasContent() {
    return false;
  }

  @Override
  public boolean isHideInNav() {
    return false;
  }

  @Override
  public boolean isLocked() {
    return false;
  }

  @Override
  public boolean isValid() {
    return false;
  }

  @Override
  public Iterator<Page> listChildren() {
    return listChildren(null);
  }

  @Override
  public Iterator<Page> listChildren(Filter<Page> pageFilter) {
    try {
      Node node = resource.adaptTo(Node.class);
      if (node != null) {
        return new PageIterator(node.getNodes(), resource.getResourceResolver(), null);
      }
      else {
        return null;
      }
    }
    catch (RepositoryException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public Iterator<Page> listChildren(Filter<Page> pageFilter, boolean deep) {
    if (deep) {
      throw new UnsupportedOperationException("Deep children list not supported.");
    }
    else {
      return listChildren(pageFilter);
    }
  }

  @Override
  public void lock() throws WCMException {
    throw new UnsupportedOperationException();
  }

  @Override
  public long timeUntilValid() {
    return 0;
  }

  @Override
  public void unlock() throws WCMException {
    throw new UnsupportedOperationException();
  }

}
