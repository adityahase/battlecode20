package nathantestplayer;

import battlecode.common.Direction;
import battlecode.common.GameConstants;
import battlecode.common.RobotController;
import battlecode.common.RobotType;
import battlecode.common.*;
import java.util.*;

public class RobotPlayer {
	static Random rand;
	
	public static void run(RobotController rc) {
		rand = new Random();
		Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};

        MapLocation enemyHQLocation = rc.senseEnemyHQLocation();
        Direction lastDirection = null;

		while(true) {
            try {
                rc.setIndicatorString(0, "" + rc.senseOre(rc.getLocation()));
                rc.setIndicatorString(1, "" + rc.getLocation());
                rc.setIndicatorString(2, "my supply level: " + rc.getSupplyLevel());
                if (enemyHQLocation == null) {
                    int test = rc.readBroadcast(0);
                    if (test != 0) {
                        enemyHQLocation = new MapLocation(rc.readBroadcast(1), rc.readBroadcast(2));
                    }
                }
            } catch (Exception e) {
                System.out.println("Early exception");
            }

			if (rc.getType() == RobotType.HQ) {
				try {					
					//Check if a robot is spawnable and spawn one if it is
					if (rc.canMove()) {
                        Direction moveDirection = enemyHQLocation == null ? directions[rand.nextInt(8)] : rc.getLocation().directionTo(enemyHQLocation);
						if (rc.canMove(moveDirection) && rc.getTeamOre() >= 500) {
							rc.spawn(moveDirection, RobotType.FURBY);
						}
					}
				} catch (Exception e) {
					System.out.println("HQ Exception");
                    e.printStackTrace();
				}
			}
			if (rc.getType() == RobotType.BARRACKS) {
				try {					
					//Check if a robot is spawnable and spawn one if it is
					if (rc.canMove()) {
                        Direction moveDirection = enemyHQLocation == null ? directions[rand.nextInt(8)] : rc.getLocation().directionTo(enemyHQLocation);
						if (rc.canMove(moveDirection) && rc.getTeamOre() >= 500 && rand.nextInt(100) < 1) {
							rc.spawn(moveDirection, RobotType.BASHER);
						}
					}
				} catch (Exception e) {
					System.out.println("HQ Exception");
                    e.printStackTrace();
				}
			}

			if (rc.getType() == RobotType.SUPPLYDEPOT) {
				try {					
                    rc.transferSuppliesToHQ();
				} catch (Exception e) {
					System.out.println("supplydepot exception");
                    e.printStackTrace();
				}
			}

            if (rc.getType() == RobotType.TRAININGFIELD) {
                try {					
					if (rc.canMove()) {
                        Direction moveDirection = enemyHQLocation == null ? directions[rand.nextInt(8)] : rc.getLocation().directionTo(enemyHQLocation);
						if (rc.canMove(moveDirection) && !rc.hasCommander() && rc.getTeamOre() >= 100) {
							rc.spawn(moveDirection, RobotType.COMMANDER);
                            System.out.println("COMMANDER SPAWNED");
						}
					}
				} catch (Exception e) {
					System.out.println("trainingfield Exception");
                    e.printStackTrace();
				}
            }

            if (rc.getType() == RobotType.MINER) {
                try {
                    if (rc.isActive()) {
                        int action = (rc.getRobot().getID()*rand.nextInt(101) + 50)%101;
                        if (action < 50) {
                            rc.mine();
						} else if (action < 100) {
                            Direction moveDirection = directions[rand.nextInt(8)];
                            if (rc.canMove(moveDirection)) {
                                rc.move(moveDirection);
                            }
                        }
                    }
				} catch (Exception e) {
					System.out.println("miner Exception");
                    e.printStackTrace();
				}
            }

            if (rc.getType() == RobotType.SOLDIER || rc.getType() == RobotType.DRONE || rc.getType() == RobotType.COMMANDER) {
                try {
                    int action = (rc.getRobot().getID()*rand.nextInt(101) + 50)%101;
                    //Attack a random nearby enemy
                    if (action < 50) {
                        Robot[] nearbyEnemies = rc.senseNearbyGameObjects(Robot.class,rc.getType().attackRadiusSquared,rc.getTeam().opponent());
                        if (nearbyEnemies.length > 0) {
                            RobotInfo robotInfo = rc.senseRobotInfo(nearbyEnemies[0]);
                            rc.attackSquare(robotInfo.location);
                        }

                        for (Robot r : nearbyEnemies) {
                            RobotInfo rr = rc.senseRobotInfo(r);
                            if (rr.type == RobotType.HQ) {
                                rc.broadcast(0, 1);
                                rc.broadcast(1, rr.location.x);
                                rc.broadcast(2, rr.location.y);
                            }
                        }
                    //Move towards enemy headquarters
                    } else if (action < 100) {
                        Direction moveDirection = rc.getLocation().directionTo(enemyHQLocation);
                        if (rc.canMove(moveDirection)) {
                            rc.move(moveDirection);
                        } else {
                            moveDirection = moveDirection.rotateLeft();
                            if (rc.canMove(moveDirection)) {
                                rc.move(moveDirection);
                            } else {
                                moveDirection = moveDirection.rotateRight().rotateRight();
                                if (rc.canMove(moveDirection)) {
                                    rc.move(moveDirection);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
			
			if (rc.getType() == RobotType.FURBY || rc.getType() == RobotType.BUILDER) {
				try {

					if (rc.isActive()) {
						int action = (rc.getRobot().getID()*rand.nextInt(101) + 50)%101;
                        boolean shouldBuild = rc.getLocation().distanceSquaredTo(rc.senseHQLocation()) > 50 && rc.getTeamOre() > 500;
                        Direction dir = directions[rand.nextInt(8)];
						//Mine
						if (action < 20 && rc.getType() == RobotType.FURBY) {
                            rc.mine();
                        // build
                        } else if (action < 30 && shouldBuild) {
                            if (rc.canBuild(dir, RobotType.SUPPLYDEPOT)) {
                                rc.build(dir, RobotType.SUPPLYDEPOT);
                            }
                        } else if (action < 40 && shouldBuild) {
                            if (rc.canBuild(dir, RobotType.BARRACKS)) {
                                rc.build(dir, RobotType.BARRACKS);
                            }
                        } else if (action < 80 && shouldBuild) {
                            if (rc.canBuild(dir, RobotType.TRAININGFIELD)) {
                                rc.build(dir, RobotType.TRAININGFIELD);
                            }
                        //Move towards enemy headquarters
						} else if (action < 100) {
							Direction moveDirection = rc.getLocation().directionTo(enemyHQLocation);
							if (rc.canMove(moveDirection)) {
								rc.move(moveDirection);
							} else {
                                moveDirection = moveDirection.rotateLeft();
                                if (rc.canMove(moveDirection)) {
                                    rc.move(moveDirection);
                                } else {
                                    moveDirection = moveDirection.rotateRight().rotateRight();
                                    if (rc.canMove(moveDirection)) {
                                        rc.move(moveDirection);
                                    }
                                }
                            }
                        }
					}
				} catch (Exception e) {
					System.out.println("Soldier Exception");
                    e.printStackTrace();
				}
			}

            if (rc.getType() == RobotType.BARRACKS) {
				try {					
					//Check if a robot is spawnable and spawn one if it is
                    /*
					if (rc.isActive()) {
                        Direction moveDirection = enemyHQLocation == null ? directions[rand.nextInt(8)] : rc.getLocation().directionTo(enemyHQLocation);
						if (rc.canMove(moveDirection) && rc.getTeamOre() > RobotType.SOLDIER.oreCost) {
							rc.spawn(moveDirection, RobotType.SOLDIER);
						}
					}
                    */
				} catch (Exception e) {
					System.out.println("Barracks Exception");
                    e.printStackTrace();
				}
            }

			rc.yield();
		}
	}
}
