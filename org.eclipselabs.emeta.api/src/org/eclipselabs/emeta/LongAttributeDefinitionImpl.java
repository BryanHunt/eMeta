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

import org.osgi.service.metatype.AttributeDefinition;

/**
 * @author bhunt
 * 
 */
public class LongAttributeDefinitionImpl extends AttributeDefinitionImpl
{
	private Long min;
	private Long max;

	public LongAttributeDefinitionImpl(String id, String name, Long min, Long max)
	{
		super(id, name, AttributeDefinition.LONG);
		this.min = min;
		this.max = max;
	}

	@Override
	public String validate(String value)
	{
    try
    {
      long v = Long.parseLong(value);

      if (min != null && v < min)
        return "Value must be a number >= " + min;
      
      if(max != null && v > max)
        return "Value must be a number <= " + max;
    }
    catch (NumberFormatException e)
    {
      return "Value must be a number";
    }

    return "";
  }
}
