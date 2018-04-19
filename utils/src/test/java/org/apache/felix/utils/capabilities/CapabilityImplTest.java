/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.utils.capabilities;

import junit.framework.TestCase;

import org.mockito.Mockito;
import org.osgi.resource.Resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CapabilityImplTest extends TestCase {
    public void testCapability() {
        Map<String, Object> attrs = Collections.<String,Object>singletonMap("foo", "bar");
        Map<String, String> dirs = Collections.emptyMap();
        CapabilityImpl c = new CapabilityImpl("org.foo.bar", attrs, dirs);

        assertEquals("org.foo.bar", c.getNamespace());
        assertEquals(attrs, c.getAttributes());
        assertEquals(dirs, c.getDirectives());
        assertNull(c.getResource());
    }

    public void testCapabilityEqualsHashcode() {
        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("ding", "dong");
        attrs.put("la", "la");
        Map<String, String> dirs = Collections.singletonMap("a", "b");
        Resource res = Mockito.mock(Resource.class);
        CapabilityImpl c1 = new CapabilityImpl("org.foo.bar", attrs, dirs, res);
        assertEquals(res, c1.getResource());

        CapabilityImpl c2 = new CapabilityImpl("org.foo.bar", attrs, dirs);
        c2.setResource(res);
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());

        CapabilityImpl c3 = new CapabilityImpl("org.foo.bar2", attrs, dirs, res);
        assertFalse(c1.equals(c3));
        assertFalse(c1.hashCode() == c3.hashCode());
    }
}
