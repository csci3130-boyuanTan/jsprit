/*******************************************************************************
 * Copyright (C) 2013  Stefan Schroeder
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package jsprit.core.problem.solution.route.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jsprit.core.problem.job.Service;
import jsprit.core.problem.solution.route.activity.ServiceActivity;

import org.junit.Before;
import org.junit.Test;


public class ServiceActivityTest {
	
	private Service service;
	
	private ServiceActivity serviceActivity;
	
	@Before
	public void doBefore(){
		service = Service.Builder.newInstance("service").setLocationId("loc").
				setTimeWindow(TimeWindow.newInstance(1., 2.)).
				addSizeDimension(0, 10).addSizeDimension(1, 100).addSizeDimension(2, 1000).build();
		serviceActivity = ServiceActivity.newInstance(service);
	}
	
	@Test
	public void whenCallingCapacity_itShouldReturnCorrectCapacity(){
		assertEquals(10,serviceActivity.getCapacity().get(0));
		assertEquals(100,serviceActivity.getCapacity().get(1));
		assertEquals(1000,serviceActivity.getCapacity().get(2));
	}

	
	@SuppressWarnings("deprecation")
	@Test
	public void whenCallingCapacityDemand_itShouldReturnCapDimWithIndex0(){
		assertEquals(10,serviceActivity.getCapacityDemand());
	}
	
	@Test
	public void whenStartIsIniWithEarliestStart_itShouldBeSetCorrectly(){
		assertEquals(1.,serviceActivity.getTheoreticalEarliestOperationStartTime(),0.01);
	}
	
	@Test
	public void whenStartIsIniWithLatestStart_itShouldBeSetCorrectly(){
		assertEquals(2.,serviceActivity.getTheoreticalLatestOperationStartTime(),0.01);
	}
	
	@Test
	public void whenSettingArrTime_itShouldBeSetCorrectly(){
		serviceActivity.setArrTime(4.0);
		assertEquals(4.,serviceActivity.getArrTime(),0.01);
	}
	
	@Test
	public void whenSettingEndTime_itShouldBeSetCorrectly(){
		serviceActivity.setEndTime(5.0);
		assertEquals(5.,serviceActivity.getEndTime(),0.01);
	}
	
	@Test
	public void whenIniLocationId_itShouldBeSetCorrectly(){
		assertEquals("loc",serviceActivity.getLocationId());
	}
	
	@Test
	public void whenCopyingStart_itShouldBeDoneCorrectly(){
		ServiceActivity copy = (ServiceActivity) serviceActivity.duplicate();
		assertEquals(1.,copy.getTheoreticalEarliestOperationStartTime(),0.01);
		assertEquals(2.,copy.getTheoreticalLatestOperationStartTime(),0.01);
		assertEquals("loc",copy.getLocationId());
		assertTrue(copy!=serviceActivity);
	}

	
	@Test
	public void whenTwoDeliveriesHaveTheSameUnderlyingJob_theyAreEqual(){
		Service s1 = Service.Builder.newInstance("s").setLocationId("loc").build();
		Service s2 = Service.Builder.newInstance("s").setLocationId("loc").build();
		
		ServiceActivity d1 = ServiceActivity.newInstance(s1);
		ServiceActivity d2 = ServiceActivity.newInstance(s2);
		
		assertTrue(d1.equals(d2));
	}
	
	@Test
	public void whenTwoDeliveriesHaveTheDifferentUnderlyingJob_theyAreNotEqual(){
		Service s1 = Service.Builder.newInstance("s").setLocationId("loc").build();
		Service s2 = Service.Builder.newInstance("s1").setLocationId("loc").build();
		
		ServiceActivity d1 = ServiceActivity.newInstance(s1);
		ServiceActivity d2 = ServiceActivity.newInstance(s2);
		
		assertFalse(d1.equals(d2));
	}
}
