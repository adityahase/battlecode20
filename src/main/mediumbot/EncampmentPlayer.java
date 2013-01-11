package mediumbot;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class EncampmentPlayer extends BasePlayer {
	public EncampmentPlayer(RobotController rc) {
		super(rc);
	}
	public void run() throws GameActionException {
		if(myType!=RobotType.ARTILLERY)
			return;
		
		if(!rc.isActive())
			return;

		RobotInfo nearest = Util.nearestEnemy(rc, 63);
		if(nearest!=null) {
			MapLocation loc = nearest.location;
			if(rc.canAttackSquare(loc))
				rc.attackSquare(loc);
		}
		
	}
}

