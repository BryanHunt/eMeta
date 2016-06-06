/*******************************************************************************
 * Copyright (c) 2013 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *******************************************************************************/

package org.eclipselabs.emeta;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import org.osgi.service.metatype.AttributeDefinition;

/**
 * @author bhunt
 * 
 */
public class ServiceChoiceAttributeDefinitionImpl extends AttributeDefinitionImpl
{
  private String propertyName;
  private CopyOnWriteArraySet<String> choices = new CopyOnWriteArraySet<>();
  
	public ServiceChoiceAttributeDefinitionImpl(String id, String name, String propertyName)
	{
		super(id + ".target", name, AttributeDefinition.STRING);
		this.propertyName = propertyName;
	}

	public void addService(Map<String, Object> properties)
	{
	  choices.add((String) properties.get(propertyName));
	  updateChoices();
	}
	
  public void removeService(Map<String, Object> properties)
  {
    choices.remove((String) properties.get(propertyName));
    updateChoices();
  }
	
  
  private synchronized void updateChoices()
  {
    String[] targets = new String[choices.size()];
    String[] filters = new String[choices.size()];

    choices.toArray(targets);

    for (int i = 0; i < targets.length; i++)
      filters[i] = "(" + propertyName + "=" + targets[i] + ")";

    setOptionLabels(targets);
    setOptionValues(filters);

    if (!choices.isEmpty())
      setDefaultValue(new String[] { choices.iterator().next() });
  }
}
