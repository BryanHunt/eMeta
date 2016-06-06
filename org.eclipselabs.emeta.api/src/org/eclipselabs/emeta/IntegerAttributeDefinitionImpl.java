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
public class IntegerAttributeDefinitionImpl extends AttributeDefinitionImpl
{
	private Integer min;
	private Integer max;

	public IntegerAttributeDefinitionImpl(String id, String name)
	{
		this(id, name, null, null);
	}
	
	public IntegerAttributeDefinitionImpl(String id, String name, Integer min, Integer max)
	{
		super(id, name, AttributeDefinition.INTEGER);
		this.min = min;
		this.max = max;
	}

	@Override
	public String validate(String value)
	{
		try
		{
			int v = Integer.parseInt(value);

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
