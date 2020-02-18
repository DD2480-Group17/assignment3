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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.terasology.AdHoc.AdHocBuildPartial;
import static org.junit.jupiter.api.Assertions.*;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

class NetDataTest {
    @AfterAll
    static void printCoverage() {
        AdHocNetData.NetMessage.Builder.MergeFrom.printRes();
        AdHocNetData.NetMessage.Builder.IsInitialized.printRes();
        BranchCoverageNetDataNetMessageBuilderClearMethod.printBranchCoveragePercentage();
    }

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

    /**
     * Test that mergeFrom an empty ByteString message is initialized
     */
    @Test
    public void mergeFromEmptyNetMessage() throws InvalidProtocolBufferException {
        NetData.NetMessage.Builder builder = NetData.NetMessage.newBuilder();
        NetData.NetMessage empty = NetData.NetMessage.parseFrom(ByteString.EMPTY);
        builder.mergeFrom(empty);
        assertTrue(builder.isInitialized());
    }

    /**
     * Test that mergeFrom the same NetMessage is initalized
     */
    @Test
    public void mergeFromSameNetMessage() {
        NetData.NetMessage defaultInstance = NetData.NetMessage.getDefaultInstance();
        NetData.NetMessage.Builder builder = defaultInstance.toBuilder();
        builder.mergeFrom(defaultInstance);
        assertTrue(builder.isInitialized());
    }

    /**
     * Test that mergeFrom merges a JoinMessage
     */
    @Test
    public void mergeFromWithJoinMessage() {
        NetData.JoinMessage.Builder joinMessageBuilder = NetData.JoinMessage.newBuilder();
        joinMessageBuilder.setName("test");

        NetData.NetMessage defaultInstance = NetData.NetMessage.getDefaultInstance();
        NetData.NetMessage.Builder netMessageBuilder = defaultInstance.toBuilder();

        netMessageBuilder.setJoin(joinMessageBuilder);
        defaultInstance = defaultInstance.toBuilder().mergeFrom(netMessageBuilder.build()).build();

        assertEquals("test", defaultInstance.getJoin().getName());
    }

    /**
     * Test that mergeFrom merges a BiomeChangeMessage
     */
    @Test
    public void mergeFromWithBiomeChange() {
        NetData.BiomeChangeMessage.Builder biomeChangeMessageBuilder = NetData.BiomeChangeMessage.newBuilder();
        biomeChangeMessageBuilder.setNewBiome(1);

        NetData.NetMessage defaultInstance = NetData.NetMessage.getDefaultInstance();
        NetData.NetMessage.Builder netMessageBuilder = defaultInstance.toBuilder();

        netMessageBuilder.addBiomeChange(biomeChangeMessageBuilder);
        defaultInstance = defaultInstance.toBuilder().mergeFrom(netMessageBuilder.build()).build();


        assertEquals(1, defaultInstance.getBiomeChange(0).getNewBiome());
    }

    private void assertListCountsEqualToSize(NetData.NetMessage.Builder builder, int size) {
        assertEquals(size, builder.getChunkInfoCount());
        assertEquals(size, builder.getBlockChangeCount());
        assertEquals(size, builder.getCreateEntityCount());
        assertEquals(size, builder.getRemoveEntityCount());
        assertEquals(size, builder.getUpdateEntityCount());
        assertEquals(size, builder.getEventCount());
        assertEquals(size, builder.getBiomeChangeCount());
        assertEquals(size, builder.getExtraDataChangeCount());
        assertEquals(size, builder.getBlockFamilyRegisteredCount());
        assertEquals(size, builder.getModuleRequestCount());
    }

    private void addNewBuilders(NetData.NetMessage.Builder builder) {
        builder.addChunkInfoBuilder();
        builder.addInvalidateChunkBuilder();
        builder.addBlockChangeBuilder();
        builder.addCreateEntityBuilder();
        builder.addRemoveEntityBuilder();
        builder.addUpdateEntityBuilder();
        builder.addEventBuilder();
        builder.addBiomeChangeBuilder();
        builder.addExtraDataChangeBuilder();
        builder.addBlockFamilyRegisteredBuilder();
        builder.addModuleRequestBuilder();
    }

    private void addNewObjects(NetData.NetMessage.Builder builder) {
        builder.addChunkInfo(EntityData.ChunkStore.newBuilder().build());
        builder.addInvalidateChunk(NetData.InvalidateChunkMessage.newBuilder().build());
        builder.addBlockChange(NetData.BlockChangeMessage.newBuilder().build());
        builder.addCreateEntity(NetData.CreateEntityMessage.newBuilder().build());
        builder.addRemoveEntity(NetData.RemoveEntityMessage.newBuilder().build());
        builder.addUpdateEntity(NetData.UpdateEntityMessage.newBuilder().build());
        builder.addEvent(NetData.EventMessage.newBuilder().build());
        builder.addBiomeChange(NetData.BiomeChangeMessage.newBuilder().build());
        builder.addExtraDataChange(NetData.ExtraDataChangeMessage.newBuilder().build());
        builder.addBlockFamilyRegistered(NetData.BlockFamilyRegisteredMessage.newBuilder().build());
        builder.addModuleRequest(NetData.ModuleRequest.newBuilder().build());
    }

    @Test
    void testClearWithoutAddingNewObjects(){
        NetData.NetMessage.Builder builder = NetData.NetMessage.newBuilder();
        assertListCountsEqualToSize(builder, 0);
        builder.clear();
        assertListCountsEqualToSize(builder, 0);
    }

    @Test
    void testClearAddBuilders(){
        NetData.NetMessage.Builder builder = NetData.NetMessage.newBuilder();
        assertListCountsEqualToSize(builder, 0);
        addNewBuilders(builder);
        assertListCountsEqualToSize(builder, 1);
        builder.clear();
        assertListCountsEqualToSize(builder, 0);
    }

    @Test
    void testClearAddBuildersCreatedManually(){
        NetData.NetMessage.Builder builder = NetData.NetMessage.newBuilder();
        assertListCountsEqualToSize(builder, 0);
        addNewObjects(builder);
        assertListCountsEqualToSize(builder, 1);
        builder.clear();
        assertListCountsEqualToSize(builder, 0);
    }
}
