package hardplayer;

import static hardplayer.goals.BroadcastEnemyUnitLocationsGoal.*;
import static hardplayer.UnitBalance.*;
import hardplayer.goals.ArchonExploreGoal;
import hardplayer.goals.ArchonFindEnemyGoal;
import hardplayer.goals.BroadcastGoInThisDirectionGoal;
import hardplayer.goals.FindEnemyGoal;
import hardplayer.goals.Goal;
import hardplayer.goals.GoodVisionBroadcastEnemyUnitLocationsGoal;
import hardplayer.goals.StayTogetherGoal;
import hardplayer.message.SuperMessageStack;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.Message;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotLevel;
import battlecode.common.RobotType;
import battlecode.common.Team;
import battlecode.common.TerrainTile;

import static battlecode.common.GameConstants.ENERGON_TO_FLUX_CONVERSION;

public class ArchonPlayer extends BasePlayer {
	
	public static final double MAX_RESERVE = 10.;
	public static final double MIN_ENERGON = 41.;
	public static final double MAX_ENERGON = 74.;
	public static final double MAX_FREE_ENERGON = MAX_ENERGON-MIN_ENERGON;

	public static final double SUPPORT_RATIO = .7;

	FindEnemyGoal findEnemyGoal;
	ArchonExploreGoal exploreGoal;

	public ArchonPlayer(RobotController RC) {
		super(RC);
	}

	public void setGoals() {
		findEnemyGoal = new ArchonFindEnemyGoal(this);
		exploreGoal = new ArchonExploreGoal(this);

		movementGoals = new Goal [] {
			exploreGoal,
			findEnemyGoal,
			new StayTogetherGoal(this)
		};

		broadcastGoals = new Goal [] {
			new BroadcastGoInThisDirectionGoal(this, exploreGoal, findEnemyGoal),
			new GoodVisionBroadcastEnemyUnitLocationsGoal(this, findEnemyGoal)
		};
	}

	public void run() {
		setGoals();
		while(true) {
			runLoop();
		}
	}

	public void runLoop() {
		try {
			myLoc = myRC.getLocation();
			senseNearbyRobots();
			transferEnergon();
			transferFlux();
			sortMessages();
			findEnemyGoal.read();
			checkForEnemy();
			moving=myRC.isMovementActive();
			actions:
			{
				//if(myRC.hasActionSet())
				//	break actions;
				if((!moving)&&queued!=null) {
					queued.doAction();
					queued=null;
				}
				if(myRC.hasActionSet())
					break actions;
				//burn();
				if(myRC.hasActionSet())
					break actions;
				if((!moving)&&flee())
					break actions;
				makeArmy();
				if(myRC.hasActionSet())
					break actions;
				if(!moving) {
					tryBestGoal(movementGoals);
					debug_setIndicatorStringObject(0,lastGoal);
				}
			}
			if(!myRC.hasBroadcastMessage()) {
				tryBestGoal(broadcastGoals);
			}
			findEnemyGoal.decay();
		}  catch(Exception e) {
			debug_stackTrace(e);
		}
		myRC.yield();
	}

	public void transferEnergon() {
		RobotController myRC=this.myRC;
		int i;
		RobotInfo r;
		double transferAmount;
		double freeEnergon=myRC.getEnergonLevel()-MIN_ENERGON;
		MapLocation myLoc=this.myLoc;
		if(freeEnergon<=.1)
			return;
		try {
			RobotInfo [] robots=alliedArchonInfos;
			for(i=alliedArchons.size-1;i>=0;i--) {
				r=robots[i];
				if(myLoc.isAdjacentTo(r.location)&&
				   (r.eventualEnergon<MIN_ENERGON||freeEnergon>MAX_FREE_ENERGON)) {
					transferAmount=Math.min(10.-r.energonReserve,freeEnergon);
					//if(transferAmount>0) {
						myRC.transferUnitEnergon(transferAmount,r.location,RobotLevel.IN_AIR);
						freeEnergon-=transferAmount;
						if(freeEnergon<=.1) return;
					//}
				}
			}
			// Java's lack of macros makes me sad :(
			robots=alliedTurretInfos;
			for(i=alliedTurrets.size-1;i>=0;i--) {
				r=robots[i];
				if(myLoc.distanceSquaredTo(r.location)<=2&&r.energonReserve<MAX_RESERVE) {
					transferAmount=Math.min(10.-r.energonReserve,freeEnergon);
					//if(transferAmount>0) {
						myRC.transferUnitEnergon(transferAmount,r.location,RobotLevel.ON_GROUND);
						freeEnergon-=transferAmount;
						if(freeEnergon<=.1) return;
					//}
				}
			}	
			robots=alliedChainerInfos;
			for(i=alliedChainers.size-1;i>=0;i--) {
				r=robots[i];
				if(myLoc.distanceSquaredTo(r.location)<=2&&r.energonReserve<MAX_RESERVE) {
					transferAmount=Math.min(10.-r.energonReserve,freeEnergon);
					//if(transferAmount>0) {
						myRC.transferUnitEnergon(transferAmount,r.location,RobotLevel.ON_GROUND);
						freeEnergon-=transferAmount;
						if(freeEnergon<=.1) return;
					//}
				}
			}
			robots=alliedSoldierInfos;
			for(i=alliedSoldiers.size-1;i>=0;i--) {
				r=robots[i];
				if(myLoc.distanceSquaredTo(r.location)<=2&&r.energonReserve<MAX_RESERVE) {
					transferAmount=Math.min(10.-r.energonReserve,freeEnergon);
					//if(transferAmount>0) {
						myRC.transferUnitEnergon(transferAmount,r.location,RobotLevel.ON_GROUND);
						freeEnergon-=transferAmount;
						if(freeEnergon<=.1) return;
					//}
				}
			}
			robots=alliedWoutInfos;
			for(i=alliedWouts.size-1;i>=0;i--) {
				r=robots[i];
				if(myLoc.isAdjacentTo(r.location)&&r.energonReserve<MAX_RESERVE) {
					transferAmount=Math.min(10.-r.energonReserve,freeEnergon);
					//if(transferAmount>0) {
						myRC.transferUnitEnergon(transferAmount,r.location,RobotLevel.ON_GROUND);
						freeEnergon-=transferAmount;
						if(freeEnergon<=.1) return;
					//}
				}
			}
			double transferToSelfAmount=Math.min(myRC.getEnergonLevel()-MAX_ENERGON,10.-myRC.getEnergonReserve());
			if(transferToSelfAmount>0)
				myRC.transferUnitEnergon(transferToSelfAmount,myLoc,RobotLevel.IN_AIR);
		} catch(Exception e) {
			debug_stackTrace(e);
		}
	}

	public void transferFlux() {
		double myFlux = myRC.getFlux();
		double transferAmount;
		if(myFlux<=0) return;
		try {
			RobotInfo info;
			int i;
			RobotInfo [] infos = alliedAuraInfos;
			for(i=alliedAuras.size-1;i>=0;i--) {
				info = infos[i];
				if(info.location.isAdjacentTo(myLoc)) {
					transferAmount = Math.min(myFlux,ENERGON_TO_FLUX_CONVERSION*(MAX_RESERVE-info.energonReserve));
					if(transferAmount<=0) continue;
					myRC.transferFlux(transferAmount,info.location,RobotLevel.ON_GROUND);
					myFlux-=transferAmount;
					if(myFlux<=0) return;
				}
			}
			infos = alliedCommInfos;
			for(i=alliedComms.size-1;i>=0;i--) {
				info = infos[i];
				if(info.location.isAdjacentTo(myLoc)&&info.energonLevel<45.) {
					transferAmount = Math.min(myFlux,ENERGON_TO_FLUX_CONVERSION*(MAX_RESERVE-info.energonReserve));
					if(transferAmount<=0) continue;
					myRC.transferFlux(transferAmount,info.location,RobotLevel.ON_GROUND);
					myFlux-=transferAmount;
					if(myFlux<=0) return;
				}
			}
			infos = alliedArchonInfos;
			for(i=alliedArchons.size-1;i>=0;i--) {
				info = infos[i];
				if(info.location.isAdjacentTo(myLoc)&&info.id<myID) {
					transferAmount = Math.min(myFlux,info.type.maxFlux()-info.flux);
					if(transferAmount<=0) continue;
					myRC.transferFlux(transferAmount,info.location,RobotLevel.IN_AIR);
					myFlux-=transferAmount;
					if(myFlux<=0) return;
				}
			}
		} catch(Exception e) {
			BasePlayer.debug_stackTrace(e);
		}
	}

	public RobotType chooseTypeToSpawn() {
		myRC.setIndicatorString(1,Boolean.toString(atWar));
		myRC.setIndicatorString(2,Double.toString(myRC.getFlux()));
		building:
		{
			int i;
			for(i=alliedAuras.size-1;i>=0;i--) {
				if(myLoc.distanceSquaredTo(alliedAuraInfos[i].location)<9)
					break building;
			}
			for(i=alliedComms.size-1;i>=0;i--) {
				if(myLoc.distanceSquaredTo(alliedCommInfos[i].location)<9)
					break building;
			}
			if(atWar) {
				if(myRC.getFlux()>=RobotType.AURA.spawnFluxCost()&&
				   2*(enemyTurrets.size+enemyChainers.size)+enemySoldiers.size>=2
				   &&3*(alliedChainers.size+alliedTurrets.size)+2*alliedSoldiers.size>=17) {
					return RobotType.AURA;
				}
				if(myRC.getFlux()>=RobotType.COMM.spawnFluxCost())
					return RobotType.COMM;
			}
			else {
				// save some flux for the battle
				if(myRC.getFlux()>=2*RobotType.COMM.spawnFluxCost())
					return RobotType.COMM;
			}
		}
		if(3*alliedChainers.size+2*alliedSoldiers.size+3*alliedTurrets.size>12*alliedWouts.size+6)
			return RobotType.WOUT;
		else if(3*alliedChainers.size+4*alliedTurrets.size>4*alliedSoldiers.size&&atWar/*3*enemyArchons.size>=4*enemyTurrets.size+2*enemySoldiers.size+3*enemyChainers.size*/)
			return RobotType.SOLDIER;
		/*
		else if(2*alliedChainers.size>=alliedTurrets.size+12)
			return RobotType.TURRET;
		else
			return RobotType.CHAINER;
		*/
		else if(myTeam==Team.A)
			return RobotType.TURRET;
		else
			return RobotType.CHAINER;
		/*
		else if(alliedChainers.size<=2*alliedTurrets.size)
			return RobotType.CHAINER;
		else
			return RobotType.TURRET;
		*/
	}

	public void makeArmy() {
		RobotType typeToSpawn=chooseTypeToSpawn();
		if(typeToSpawn==null||myRC.getEnergonLevel()<MIN_ENERGON+typeToSpawn.spawnCost()||myRC.getFlux()<typeToSpawn.spawnFluxCost())
			return;
		if((!typeToSpawn.isBuilding())&&!canSupportAnother(typeToSpawn))
			return;
		try {
			/* We want to spawn closer to the fighting.  But we do try to be
			   facing the fighting already so maybe this isn't needed.
			if(typeToSpawn==RobotType.AURA_OFF||typeToSpawn==RobotType.AURA_DEF) {
				Direction [] enemyDirs = findEnemyGoal.getEnemyDirs();
				if(canSpawnGround(enemyDirs[0])) {
					if(myRC.getDirection()==enemyDirs[0])
						myRC.spawn(typeToSpawn);
					else if(!myRC.isMovementActive())
						myRC.setDirection(enemyDirs[0]);
				}
			}
			else {
			*/
			if(canSpawnGround(myRC.getDirection())) {
				myRC.spawn(typeToSpawn);
			}
			else if(!myRC.isMovementActive()) {
				int i;
				for(i=7;i>=0;i--) {
					if(canSpawnGround(directions[i])) {
						myRC.setDirection(directions[i]);
						return;
					}
				}
			}
			//}
		} catch(Exception e) {
			debug_stackTrace(e);
		}
	}

	static public final int TURRET_FEAR = 3;
	static public final int SOLDIER_FEAR = 7;
	static public final int CHAINER_FEAR = 4;

	public boolean flee() {
		int i, j, k;
		long xsum = 0;
		long ysum = 0;
		long n=0;
		int d;
		MapLocation loc;
		MapLocation myLoc=this.myLoc;
		Message m;
		int [] ints;
		MapLocation [] locs;
		Message [] messages;
		for(k=SuperMessageStack.KEEP_TIME-1;k>=0;k--) {
			messages=enemyUnitMessages.messages[k];
			for(j=enemyUnitMessages.lengths[k]-1;j>=0;j--) {
				ints=messages[j].ints;
				locs=messages[j].locations;
				for(i=locs.length-2;i>0&&(ints[i]&TYPE_MASK)==TURRET_TYPE;i--) {
					loc=locs[i];
					d=myLoc.distanceSquaredTo(loc);
					if(d<=41) {
						xsum+=2*TURRET_FEAR*loc.getX();
						ysum+=2*TURRET_FEAR*loc.getY();
						n+=2*TURRET_FEAR;
					}
				}
			}
		}
		RobotInfo [] robots=enemyTurretInfos;
		for(i=enemyTurrets.size-1;i>=0;i--) {
			loc = robots[i].location;
			d = myLoc.distanceSquaredTo(loc);
			if(d>25) {
				xsum+=2*TURRET_FEAR*loc.getX();
				ysum+=2*TURRET_FEAR*loc.getY();
				n+=2*TURRET_FEAR;
			}
			else if(d>=16) {
				xsum+=6*TURRET_FEAR*loc.getX();
				ysum+=6*TURRET_FEAR*loc.getY();
				n+=6*TURRET_FEAR;
			}
			else {
				xsum+=3*TURRET_FEAR*loc.getX();
				ysum+=3*TURRET_FEAR*loc.getY();
				n+=3*TURRET_FEAR;
				
			}
		}
		robots=enemyChainerInfos;
		for(i=enemyChainers.size-1;i>=0;i--) {
			loc = robots[i].location;
			d = myLoc.distanceSquaredTo(loc);
			if(d>16) {
				if(d<=26) {
					xsum+=2*CHAINER_FEAR*loc.getX();
					ysum+=2*CHAINER_FEAR*loc.getY();
					n+=2*CHAINER_FEAR;
				}
			}
			else {
				if(d>=2) {
					xsum+=6*CHAINER_FEAR*loc.getX();
					ysum+=6*CHAINER_FEAR*loc.getY();
					n+=6*CHAINER_FEAR;
				}
				else {
					xsum+=3*CHAINER_FEAR*loc.getX();
					ysum+=3*CHAINER_FEAR*loc.getY();
					n+=3*CHAINER_FEAR;
				}
			}
		}
		robots=enemySoldierInfos;
		for(i=enemySoldiers.size-1;i>=0;i--) {
			loc = robots[i].location;
			d = myLoc.distanceSquaredTo(loc);
			if(d<=8) {
				if(d<=2) {
					xsum+=6*SOLDIER_FEAR*loc.getX();
					ysum+=6*SOLDIER_FEAR*loc.getY();
					n+=6*SOLDIER_FEAR;
				}
				else {
					xsum+=2*SOLDIER_FEAR*loc.getX();
					ysum+=2*SOLDIER_FEAR*loc.getY();
					n+=2*SOLDIER_FEAR;
				}
			}
			else if(d<=18) {
				xsum+=SOLDIER_FEAR*loc.getX();
				ysum+=SOLDIER_FEAR*loc.getY();
				n+=SOLDIER_FEAR;
			}
			d = myLoc.distanceSquaredTo(loc);
			
		}
		if(n>=2*SOLDIER_FEAR) {
			retreat(myLoc.getX()-(double)xsum/n, myLoc.getY()-(double)ysum/n);
			return true;
		}
		else {
			return false;
		}
	}

	public static final double RETREAT_STAYPUT_VALUE = -.127;
	public static final double RETREAT_EDGE_PENALTY = .06;
	public static final double RETREAT_CORNER_PENALTY = .176;
	public static final double RETREAT_WAIT_FOR_ARCHON_PENALTY = .172;

	public static final double [] dX = new double [] { 0, 1, 1, 1, 0, -1, -1, -1, 0, 0 };
	public static final double [] dY = new double [] { -1, -1, 0, 1, 1, 1, 0, -1, 0, 0 };

	public static final int AVOID_EDGE_TIME = 30;

	int [] retreatForbiddenTimeout = new int [8];

	public void retreat(double dx, double dy) {
		try {
			double myEnergon=myRC.getEnergonLevel();
			debug_setIndicatorString(0,"retreat called "+Clock.getRoundNum());
			MapLocation myLoc = this.myLoc;
			Robot r;
			RobotInfo info;
			int i;
			double dxn, dyn;
			boolean [] edge = new boolean [9]; // we don't actually use the odd ones
			boolean [] forbidden = new boolean [9];
			Direction dir;
			Direction bestDir = null;
			double bestVal = RETREAT_STAYPUT_VALUE;
			double val;
			double dist = Math.sqrt(dx*dx+dy*dy);
			int t=Clock.getRoundNum();
			int numEdges=0;
			for(i=6;i>=0;i-=2) {
				if(myRC.senseTerrainTile(multipleAddDirection(myLoc,directions[i],6)).getType()==TerrainTile.TerrainType.OFF_MAP) {
					edge[i]=true;
					numEdges++;
				}
				if(t<retreatForbiddenTimeout[i])
					forbidden[i]=true;
			}
			boolean twoEdges=(numEdges>=2);
			edge[8]=edge[0];
			forbidden[8]=forbidden[0];
			for(i=6;i>=0;i-=2) {
				if(forbidden[i]||(twoEdges&&edge[i])) continue;
				dir=directions[i];
				if(myRC.canMove(dir)) {
					dxn=dx+dX[i];
					dyn=dy+dY[i];
					val=(Math.sqrt(dxn*dxn+dyn*dyn)-dist)/6.;
				}
				else {
					r=myRC.senseAirRobotAtLocation(myLoc.add(dir));
					if(r==null) continue;
					info=myRC.senseRobotInfo(r);
					if(info.team!=myTeam) continue;
					dxn=dx+dX[i];
					dyn=dy+dY[i];
					val=(Math.sqrt(dxn*dxn+dyn*dyn)-dist)/6.-RETREAT_WAIT_FOR_ARCHON_PENALTY;
				}
				if(val>bestVal) {
					bestDir=dir;
					bestVal=val;
				}
			}
			for(i=7;i>=0;i-=2) {
				if(forbidden[i-1]||forbidden[i+1]||(edge[i-1]&&edge[i+1])) continue;
				dir=directions[i];
				if(myRC.canMove(dir)) {
					dxn=dx+dX[i];
					dyn=dy+dY[i];
					val=(Math.sqrt(dxn*dxn+dyn*dyn)-dist)/8.;
				}
				else {
					r=myRC.senseAirRobotAtLocation(myLoc.add(dir));
					if(r==null) continue;
					info=myRC.senseRobotInfo(r);
					if(info.team!=myTeam) continue;
					dxn=dx+dX[i];
					dyn=dy+dY[i];
					val=(Math.sqrt(dxn*dxn+dyn*dyn)-dist)/8.-RETREAT_WAIT_FOR_ARCHON_PENALTY;
				}
				if(val>bestVal) {
					bestDir=dir;
					bestVal=val;
				}
			}
			if(bestDir!=null) {
				if(myRC.canMove(bestDir)) {
					myNav.setDirectionAndMoveASAP(bestDir);
					int ord;
					if(bestDir.isDiagonal()) {
						ord=bestDir.opposite().rotateLeft().ordinal();
						if(edge[ord])
							retreatForbiddenTimeout[ord]=t+AVOID_EDGE_TIME;
						ord=bestDir.opposite().rotateRight().ordinal();
						if(edge[ord])
							retreatForbiddenTimeout[ord]=t+AVOID_EDGE_TIME;
					}
					else {
						ord=bestDir.opposite().ordinal();
						if(edge[ord])
							retreatForbiddenTimeout[ord]=t+AVOID_EDGE_TIME;
					}
				}
			}
		} catch(Exception e) {
			debug_stackTrace(e);
		}
	}

	public boolean canSupportAnother(RobotType type) {
		return SUPPORT_RATIO*(alliedArchons.size+1)>=
			RobotType.WOUT.energonUpkeep()*alliedWouts.size+RobotType.CHAINER.energonUpkeep()*alliedChainers.size+RobotType.SOLDIER.energonUpkeep()*alliedSoldiers.size+RobotType.TURRET.energonUpkeep()*alliedTurrets.size+type.energonUpkeep();
	}

}