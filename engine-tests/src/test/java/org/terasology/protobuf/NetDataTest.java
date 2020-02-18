/*
 * Copyright 2020 MovingBlocks
 *
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
 */
package org.terasology.protobuf;

import org.junit.jupiter.api.Test;
import org.terasology.AdHoc.AdHocBuildPartial;
import org.terasology.protobuf.NetData;
import static org.junit.jupiter.api.Assertions.*;

class NetDataTest {

    /**
     * Test that some of the attributes for the ServerInfoMessage's object returned by buildPartial() are as
     * expected and that the adhoc tool works.
     *
     * Test case 1:
     * Expected value: An empty list because we haven't added any modules, but not null
     *
     * Test case 2:
     * Expected value: playersAmount_ = 0
     *
     * Test case 3:
     * Expected value: reflectionHeight_ = 0
     */
    @Test
    void testBuildPartial() {
        NetData.ServerInfoMessage serverinfo1 = NetData.ServerInfoMessage.getDefaultInstance();
        NetData.ServerInfoMessage.Builder builder = serverinfo1.newBuilder(serverinfo1);
        NetData.ServerInfoMessage serverinfo2 = builder.buildPartial();

        // returns an empty list
        assertNotNull(serverinfo2.getModuleList());
        // gets the correct value for playersAmount
        assertEquals(0, serverinfo2.getOnlinePlayersAmount());
        // gets the correct value for reflectionHeight
        assertEquals(0.0, serverinfo2.getReflectionHeight());

        boolean[] result = AdHocBuildPartial.getAdHoc();
        for (int i = 0; i < result.length; i++){
            System.out.println("Visited branch " + (i+1) + " " + result[i]);
        }
    }
}