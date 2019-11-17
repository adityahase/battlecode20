// automatically generated by the FlatBuffers compiler, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * A single time-step in a Game.
 * The bulk of the data in the file is stored in tables like this.
 * Note that a struct-of-arrays format is more space efficient than an array-
 * of-structs.
 */
public final class Round extends Table {
  public static Round getRootAsRound(ByteBuffer _bb) { return getRootAsRound(_bb, new Round()); }
  public static Round getRootAsRound(ByteBuffer _bb, Round obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public Round __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * The IDs of teams in the Game.
   */
  public int teamIDs(int j) { int o = __offset(4); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int teamIDsLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer teamIDsAsByteBuffer() { return __vector_as_bytebuffer(4, 4); }
  public ByteBuffer teamIDsInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 4); }
  /**
   * The soup counts of the teams.
   */
  public int teamSoups(int j) { int o = __offset(6); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int teamSoupsLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer teamSoupsAsByteBuffer() { return __vector_as_bytebuffer(6, 4); }
  public ByteBuffer teamSoupsInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 4); }
  /**
   * The refined soup counts of the teams.
   */
  public int teamSoups(int j) { int o = __offset(6); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int teamSoupsLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer teamSoupsAsByteBuffer() { return __vector_as_bytebuffer(6, 4); }
  /**
   * The IDs of bodies that moved.
   */
  public int movedIDs(int j) { int o = __offset(8); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int movedIDsLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer movedIDsAsByteBuffer() { return __vector_as_bytebuffer(8, 4); }
  /**
   * The new locations of bodies that have moved.
   */
  public VecTable movedLocs() { return movedLocs(new VecTable()); }
  public VecTable movedLocs(VecTable obj) { int o = __offset(10); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * New bodies.
   */
  public SpawnedBodyTable spawnedBodies() { return spawnedBodies(new SpawnedBodyTable()); }
  public SpawnedBodyTable spawnedBodies(SpawnedBodyTable obj) { int o = __offset(12); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The IDs of bodies that died (drowned units and shot drones).
   */
  public int diedIDs(int j) { int o = __offset(14); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int diedIDsLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer diedIDsAsByteBuffer() { return __vector_as_bytebuffer(14, 4); }
  /**
   * The IDs of robots that performed actions.
   * IDs may repeat.
   */
  public int actionIDs(int j) { int o = __offset(16); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int actionIDsLength() { int o = __offset(16); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer actionIDsAsByteBuffer() { return __vector_as_bytebuffer(16, 4); }
  /**
   * The actions performed. These actions allow us to track how much soup or dirt a body carries.
   */
  public byte actions(int j) { int o = __offset(18); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int actionsLength() { int o = __offset(18); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer actionsAsByteBuffer() { return __vector_as_bytebuffer(18, 1); }
  /**
   * The 'targets' of the performed actions. Actions without targets may have
   * any target (typically 0).
   */
  public int actionTargets(int j) { int o = __offset(20); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int actionTargetsLength() { int o = __offset(20); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer actionTargetsAsByteBuffer() { return __vector_as_bytebuffer(20, 4); }
  /**
   * The indexes of the locations whose dirt amount changed.
   */
  public VecTable dirtChangedLocs() { return dirtChangedLocs(new VecTable()); }
  public VecTable dirtChangedLocs(VecTable obj) { int o = __offset(22); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The amount the dirt changed by.
   */
  public int dirtChanges(int j) { int o = __offset(24); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int dirtChangesLength() { int o = __offset(24); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer dirtChangesAsByteBuffer() { return __vector_as_bytebuffer(24, 4); }
  /**
   * The indexes of the locations whose water amount changed.
   */
  public VecTable waterChangedLocs() { return waterChangedLocs(new VecTable()); }
  public VecTable waterChangedLocs(VecTable obj) { int o = __offset(26); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The amount the water changed by.
   */
  public int waterChanges(int j) { int o = __offset(28); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int waterChangesLength() { int o = __offset(28); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer waterChangesAsByteBuffer() { return __vector_as_bytebuffer(28, 4); }
  /**
   * The indexes of the locations whose pollution amount changed.
   */
  public VecTable pollutionChangedLocs() { return pollutionChangedLocs(new VecTable()); }
  public VecTable pollutionChangedLocs(VecTable obj) { int o = __offset(30); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The amount the pollution changed by.
   */
  public int pollutionChanges(int j) { int o = __offset(32); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int pollutionChangesLength() { int o = __offset(32); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer pollutionChangesAsByteBuffer() { return __vector_as_bytebuffer(32, 4); }
  /**
   * The indexes of the locations whose soup amount changed.
   */
  public VecTable soupChangedLocs() { return soupChangedLocs(new VecTable()); }
  public VecTable soupChangedLocs(VecTable obj) { int o = __offset(34); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The amount the soup changed by.
   */
  public int soupChanges(int j) { int o = __offset(36); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int soupChangesLength() { int o = __offset(36); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer soupChangesAsByteBuffer() { return __vector_as_bytebuffer(36, 4); }
  /**
   * Costs of new message requests.
   */
  public int newMessagesCosts(int j) { int o = __offset(38); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int newMessagesCostsLength() { int o = __offset(38); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer newMessagesCostsAsByteBuffer() { return __vector_as_bytebuffer(38, 4); }
  /**
   * New message requests.
   */
  public String newMessages(int j) { int o = __offset(40); return o != 0 ? __string(__vector(o) + j * 4) : null; }
  public int newMessagesLength() { int o = __offset(40); return o != 0 ? __vector_len(o) : 0; }
  /**
   * Costs of broadcasted messages.
   */
  public int broadcastedMessagesCosts(int j) { int o = __offset(42); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int broadcastedMessagesCostsLength() { int o = __offset(42); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer broadcastedMessagesCostsAsByteBuffer() { return __vector_as_bytebuffer(42, 4); }
  /**
   * Broadcasted messages.
   */
  public String broadcastedMessages(int j) { int o = __offset(44); return o != 0 ? __string(__vector(o) + j * 4) : null; }
  public int broadcastedMessagesLength() { int o = __offset(44); return o != 0 ? __vector_len(o) : 0; }
  /**
   * The IDs of bodies that set indicator dots
   */
  public int indicatorDotIDs(int j) { int o = __offset(46); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int indicatorDotIDsLength() { int o = __offset(46); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer indicatorDotIDsAsByteBuffer() { return __vector_as_bytebuffer(46, 4); }
  /**
   * The location of the indicator dots
   */
  public VecTable indicatorDotLocs() { return indicatorDotLocs(new VecTable()); }
  public VecTable indicatorDotLocs(VecTable obj) { int o = __offset(48); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The RGB values of the indicator dots
   */
  public RGBTable indicatorDotRGBs() { return indicatorDotRGBs(new RGBTable()); }
  public RGBTable indicatorDotRGBs(RGBTable obj) { int o = __offset(50); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The IDs of bodies that set indicator lines
   */
  public int indicatorLineIDs(int j) { int o = __offset(52); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int indicatorLineIDsLength() { int o = __offset(52); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer indicatorLineIDsAsByteBuffer() { return __vector_as_bytebuffer(52, 4); }
  /**
   * The start location of the indicator lines
   */
  public VecTable indicatorLineStartLocs() { return indicatorLineStartLocs(new VecTable()); }
  public VecTable indicatorLineStartLocs(VecTable obj) { int o = __offset(54); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The end location of the indicator lines
   */
  public VecTable indicatorLineEndLocs() { return indicatorLineEndLocs(new VecTable()); }
  public VecTable indicatorLineEndLocs(VecTable obj) { int o = __offset(56); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The RGB values of the indicator lines
   */
  public RGBTable indicatorLineRGBs() { return indicatorLineRGBs(new RGBTable()); }
  public RGBTable indicatorLineRGBs(RGBTable obj) { int o = __offset(58); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * All logs sent this round.
   * Messages from a particular robot in this round start on a new line, and
   * have a header:
   * '[' $TEAM ':' $ROBOTTYPE '#' $ID '@' $ROUND '] '
   * $TEAM = 'A' | 'B'
   * $ROBOTTYPE = 'ARCHON' | 'GARDENER' | 'LUMBERJACK' 
   *            | 'SOLDIER' | 'TANK' | 'SCOUT' | other names...
   * $ID = a number
   * $ROUND = a number
   * The header is not necessarily followed by a newline.
   * This header should only be sent once per robot per round (although
   * players may forge it, so don't crash if you get strange input.)
   *
   * You should try to only read this value once, and cache it. Reading
   * strings from a flatbuffer is much less efficient than reading other
   * buffers, because they need to be copied into an environment-provided
   * buffer and validated.
   *
   * (haha i guess you can never really escape string parsing can you)
   */
  public String logs() { int o = __offset(60); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer logsAsByteBuffer() { return __vector_as_bytebuffer(60, 1); }
  /**
   * The first sent Round in a match should have index 1. (The starting state,
   * created by the MatchHeader, can be thought to have index 0.)
   * It should increase by one for each following round.
   */
  public int roundID() { int o = __offset(62); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  /**
   * The IDs of player bodies.
   */
  public int bytecodeIDs(int j) { int o = __offset(64); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int bytecodeIDsLength() { int o = __offset(64); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer bytecodeIDsAsByteBuffer() { return __vector_as_bytebuffer(64, 4); }
  /**
   * The bytecodes used by the player bodies.
   */
  public int bytecodesUsed(int j) { int o = __offset(66); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int bytecodesUsedLength() { int o = __offset(66); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer bytecodesUsedAsByteBuffer() { return __vector_as_bytebuffer(66, 4); }

  public static int createRound(FlatBufferBuilder builder,
      int teamIDsOffset,
      int teamSoupsOffset,
      int movedIDsOffset,
      int movedLocsOffset,
      int spawnedBodiesOffset,
      int diedIDsOffset,
      int actionIDsOffset,
      int actionsOffset,
      int actionTargetsOffset,
      int dirtChangedLocsOffset,
      int dirtChangesOffset,
      int waterChangedLocsOffset,
      int waterChangesOffset,
      int pollutionChangedLocsOffset,
      int pollutionChangesOffset,
      int soupChangedLocsOffset,
      int soupChangesOffset,
      int newMessagesCostsOffset,
      int newMessagesOffset,
      int broadcastedMessagesCostsOffset,
      int broadcastedMessagesOffset,
      int indicatorDotIDsOffset,
      int indicatorDotLocsOffset,
      int indicatorDotRGBsOffset,
      int indicatorLineIDsOffset,
      int indicatorLineStartLocsOffset,
      int indicatorLineEndLocsOffset,
      int indicatorLineRGBsOffset,
      int logsOffset,
      int roundID,
      int bytecodeIDsOffset,
      int bytecodesUsedOffset) {
    builder.startObject(32);
    Round.addBytecodesUsed(builder, bytecodesUsedOffset);
    Round.addBytecodeIDs(builder, bytecodeIDsOffset);
    Round.addRoundID(builder, roundID);
    Round.addLogs(builder, logsOffset);
    Round.addIndicatorLineRGBs(builder, indicatorLineRGBsOffset);
    Round.addIndicatorLineEndLocs(builder, indicatorLineEndLocsOffset);
    Round.addIndicatorLineStartLocs(builder, indicatorLineStartLocsOffset);
    Round.addIndicatorLineIDs(builder, indicatorLineIDsOffset);
    Round.addIndicatorDotRGBs(builder, indicatorDotRGBsOffset);
    Round.addIndicatorDotLocs(builder, indicatorDotLocsOffset);
    Round.addIndicatorDotIDs(builder, indicatorDotIDsOffset);
    Round.addBroadcastedMessages(builder, broadcastedMessagesOffset);
    Round.addBroadcastedMessagesCosts(builder, broadcastedMessagesCostsOffset);
    Round.addNewMessages(builder, newMessagesOffset);
    Round.addNewMessagesCosts(builder, newMessagesCostsOffset);
    Round.addSoupChanges(builder, soupChangesOffset);
    Round.addSoupChangedLocs(builder, soupChangedLocsOffset);
    Round.addPollutionChanges(builder, pollutionChangesOffset);
    Round.addPollutionChangedLocs(builder, pollutionChangedLocsOffset);
    Round.addWaterChanges(builder, waterChangesOffset);
    Round.addWaterChangedLocs(builder, waterChangedLocsOffset);
    Round.addDirtChanges(builder, dirtChangesOffset);
    Round.addDirtChangedLocs(builder, dirtChangedLocsOffset);
    Round.addActionTargets(builder, actionTargetsOffset);
    Round.addActions(builder, actionsOffset);
    Round.addActionIDs(builder, actionIDsOffset);
    Round.addDiedIDs(builder, diedIDsOffset);
    Round.addSpawnedBodies(builder, spawnedBodiesOffset);
    Round.addMovedLocs(builder, movedLocsOffset);
    Round.addMovedIDs(builder, movedIDsOffset);
    Round.addTeamSoups(builder, teamSoupsOffset);
    Round.addTeamIDs(builder, teamIDsOffset);
    return Round.endRound(builder);
  }

  public static void startRound(FlatBufferBuilder builder) { builder.startObject(32); }
  public static void addTeamIDs(FlatBufferBuilder builder, int teamIDsOffset) { builder.addOffset(0, teamIDsOffset, 0); }
  public static int createTeamIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startTeamIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addTeamSoups(FlatBufferBuilder builder, int teamSoupsOffset) { builder.addOffset(1, teamSoupsOffset, 0); }
  public static int createTeamSoupsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startTeamSoupsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addMovedIDs(FlatBufferBuilder builder, int movedIDsOffset) { builder.addOffset(2, movedIDsOffset, 0); }
  public static int createMovedIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startMovedIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addMovedLocs(FlatBufferBuilder builder, int movedLocsOffset) { builder.addOffset(3, movedLocsOffset, 0); }
  public static void addSpawnedBodies(FlatBufferBuilder builder, int spawnedBodiesOffset) { builder.addOffset(4, spawnedBodiesOffset, 0); }
  public static void addDiedIDs(FlatBufferBuilder builder, int diedIDsOffset) { builder.addOffset(5, diedIDsOffset, 0); }
  public static int createDiedIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startDiedIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addActionIDs(FlatBufferBuilder builder, int actionIDsOffset) { builder.addOffset(6, actionIDsOffset, 0); }
  public static int createActionIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startActionIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addActions(FlatBufferBuilder builder, int actionsOffset) { builder.addOffset(7, actionsOffset, 0); }
  public static int createActionsVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startActionsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addActionTargets(FlatBufferBuilder builder, int actionTargetsOffset) { builder.addOffset(8, actionTargetsOffset, 0); }
  public static int createActionTargetsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startActionTargetsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addDirtChangedLocs(FlatBufferBuilder builder, int dirtChangedLocsOffset) { builder.addOffset(9, dirtChangedLocsOffset, 0); }
  public static void addDirtChanges(FlatBufferBuilder builder, int dirtChangesOffset) { builder.addOffset(10, dirtChangesOffset, 0); }
  public static int createDirtChangesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startDirtChangesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addWaterChangedLocs(FlatBufferBuilder builder, int waterChangedLocsOffset) { builder.addOffset(11, waterChangedLocsOffset, 0); }
  public static void addWaterChanges(FlatBufferBuilder builder, int waterChangesOffset) { builder.addOffset(12, waterChangesOffset, 0); }
  public static int createWaterChangesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startWaterChangesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addPollutionChangedLocs(FlatBufferBuilder builder, int pollutionChangedLocsOffset) { builder.addOffset(13, pollutionChangedLocsOffset, 0); }
  public static void addPollutionChanges(FlatBufferBuilder builder, int pollutionChangesOffset) { builder.addOffset(14, pollutionChangesOffset, 0); }
  public static int createPollutionChangesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startPollutionChangesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addSoupChangedLocs(FlatBufferBuilder builder, int soupChangedLocsOffset) { builder.addOffset(15, soupChangedLocsOffset, 0); }
  public static void addSoupChanges(FlatBufferBuilder builder, int soupChangesOffset) { builder.addOffset(16, soupChangesOffset, 0); }
  public static int createSoupChangesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startSoupChangesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addNewMessagesCosts(FlatBufferBuilder builder, int newMessagesCostsOffset) { builder.addOffset(17, newMessagesCostsOffset, 0); }
  public static int createNewMessagesCostsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startNewMessagesCostsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addNewMessages(FlatBufferBuilder builder, int newMessagesOffset) { builder.addOffset(18, newMessagesOffset, 0); }
  public static int createNewMessagesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startNewMessagesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addBroadcastedMessagesCosts(FlatBufferBuilder builder, int broadcastedMessagesCostsOffset) { builder.addOffset(19, broadcastedMessagesCostsOffset, 0); }
  public static int createBroadcastedMessagesCostsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startBroadcastedMessagesCostsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addBroadcastedMessages(FlatBufferBuilder builder, int broadcastedMessagesOffset) { builder.addOffset(20, broadcastedMessagesOffset, 0); }
  public static int createBroadcastedMessagesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startBroadcastedMessagesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addIndicatorDotIDs(FlatBufferBuilder builder, int indicatorDotIDsOffset) { builder.addOffset(21, indicatorDotIDsOffset, 0); }
  public static int createIndicatorDotIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startIndicatorDotIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addIndicatorDotLocs(FlatBufferBuilder builder, int indicatorDotLocsOffset) { builder.addOffset(22, indicatorDotLocsOffset, 0); }
  public static void addIndicatorDotRGBs(FlatBufferBuilder builder, int indicatorDotRGBsOffset) { builder.addOffset(23, indicatorDotRGBsOffset, 0); }
  public static void addIndicatorLineIDs(FlatBufferBuilder builder, int indicatorLineIDsOffset) { builder.addOffset(24, indicatorLineIDsOffset, 0); }
  public static int createIndicatorLineIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startIndicatorLineIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addIndicatorLineStartLocs(FlatBufferBuilder builder, int indicatorLineStartLocsOffset) { builder.addOffset(25, indicatorLineStartLocsOffset, 0); }
  public static void addIndicatorLineEndLocs(FlatBufferBuilder builder, int indicatorLineEndLocsOffset) { builder.addOffset(26, indicatorLineEndLocsOffset, 0); }
  public static void addIndicatorLineRGBs(FlatBufferBuilder builder, int indicatorLineRGBsOffset) { builder.addOffset(27, indicatorLineRGBsOffset, 0); }
  public static void addLogs(FlatBufferBuilder builder, int logsOffset) { builder.addOffset(28, logsOffset, 0); }
  public static void addRoundID(FlatBufferBuilder builder, int roundID) { builder.addInt(29, roundID, 0); }
  public static void addBytecodeIDs(FlatBufferBuilder builder, int bytecodeIDsOffset) { builder.addOffset(30, bytecodeIDsOffset, 0); }
  public static int createBytecodeIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startBytecodeIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addBytecodesUsed(FlatBufferBuilder builder, int bytecodesUsedOffset) { builder.addOffset(31, bytecodesUsedOffset, 0); }
  public static int createBytecodesUsedVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startBytecodesUsedVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endRound(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}