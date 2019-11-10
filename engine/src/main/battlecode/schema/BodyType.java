// automatically generated by the FlatBuffers compiler, do not modify

package battlecode.schema;

/**
 * The possible types of things that can exist.
 * Note that bullets are not treated as bodies.
 */
public final class BodyType {
  private BodyType() { }
  /**
   * The hq produces miners, is also a net gun and a refinery.
   */
  public static final byte HQ = 0;
  /**
   * Miners extract crude soup and bring it to the refineries.
   */
  public static final byte MINER = 1;
  /**
   * Refineries turn crude soup into refined soup, and produce pollution.
   */
  public static final byte REFINERY = 2;
  /**
   * Vaporators reduce pollution.
   */
  public static final byte VAPORATOR = 3;
  /**
   * Design schools create landscapers.
   */
  public static final byte DESIGN_SCHOOL = 4;
  /**
   * Fulfillment centers create drones.
   */
  public static final byte FULFILLMENT_CENTER = 5;
  /**
   * Landscapers take dirt from adjacent (decreasing the elevation)
   * squares or deposit dirt onto adjacent squares, including
   * into water (increasing the elevation).
   */
  public static final byte LANDSCAPER = 6;
  /**
   * Drones pick up any unit and drop them somewhere else.
   */
  public static final byte DRONE = 7;
  /**
   * Net guns shoot down drones.
   */
  public static final byte NET_GUN = 8;
  /**
   * Cows produce pollution.
   */
  public static final byte COW = 9;
  /**
   * Delivery drones are slow units that can pick other units up
   */
  public static final byte DELIVERY_DRONE = 10;

  public static final String[] names = { "HQ", "MINER", "REFINERY", "VAPORATOR", "DESIGN_SCHOOL", "FULFILLMENT_CENTER", "LANDSCAPER", "DRONE", "NET_GUN", "COW", "DELIVERY_DRONE"};

  public static String name(int e) { return names[e]; }
}

