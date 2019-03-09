import React, { Component } from 'react';
import SPECS from 'bhse19/specs';
import styled from 'styled-components';

const He = styled.h5`
  font-weight: bold;
  font-size:1.3em;
`;

const Hee = styled.h5`
  text-decoration:underline;
  font-size:1.2em;
`;

class RobotTable extends Component {
    render() {
        return (
            <table className="table">
                <thead><tr>
                    <th scope="col"></th>
                    <th scope="col">Pilgrim</th>
                    <th scope="col">Crusader</th>
                    <th scope="col">Prophet</th>
                    <th scope="col">Preacher</th>
                </tr></thead>
                <tbody>
                <tr>
                    <th scope="row">Construction Karbonite</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.CONSTRUCTION_KARBONITE}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Construction Fuel</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.CONSTRUCTION_FUEL}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Karbonite Carrying Capacity</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.KARBONITE_CAPACITY}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Fuel Carrying Capacity</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.FUEL_CAPACITY}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Movement Speed (r^2)</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.SPEED}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Movement Fuel Cost (per r^2)</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.FUEL_PER_MOVE}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Starting Health</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.STARTING_HP}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Vision Radius (r^2)</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return (
                            <td>{unit.VISION_RADIUS}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Attack Damage</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return unit.ATTACK_DAMAGE===null?(<td>N/A</td>):(
                            <td>{unit.ATTACK_DAMAGE}HP {unit.DAMAGE_SPREAD!==0?"for " + unit.DAMAGE_SPREAD + " r^2":""}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Attack Range (r^2)</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return unit.ATTACK_RADIUS===null?(<td>N/A</td>):(
                            <td>{unit.ATTACK_RADIUS[0]}-{unit.ATTACK_RADIUS[1]}</td>
                        );
                    }) }
                </tr>
                <tr>
                    <th scope="row">Attack Fuel Cost</th>
                    { SPECS.UNITS.slice(2).map(function(unit) {
                        return unit.ATTACK_FUEL_COST===null?(<td>N/A</td>):(
                            <td>{unit.ATTACK_FUEL_COST}</td>
                        );
                    }) }
                </tr>
                
                
                </tbody>
            </table>
        );
    }
}

class Docs extends Component {
    render() {
        return (
            <div className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-12">
                        
                        {/*
                            <div className="card">
                                <div className="header">
                                    <h4 className="title">Battlecode: Crusade Official Game Specs</h4>
                                    <p className="category">Updated 1/7/19 7:00PM EST</p>
                                </div>
                                <div className="content">
                                    <p>The planet of Mars is a house divided.  Only ten short years after the great war for the red planet, two opposing religious dogmas have emerged from the chaos.  The Religious Exploratory Doctrinists (RED) believe that the only route to peace in the galaxy is by spreading the robot way of peace, while the Believers of Lasting Unity Everywhere (BLUE) claim that only by non-aggression can robotkind remain.  The only possible resolution?  Total war.</p>
                                    <He>Game Format</He>
                                    <p>Battlecode: Crusade is a turn based game, where robots on a tiled grid are each controlled by individual computer programs.  Robots include Castles, Churches, Pilgrims, Crusaders, Prophets, and Preachers.  The objective of the game is to destroy the enemy team Castles.  If by { SPECS.MAX_ROUNDS } rounds both blue and red Castles remain, the winner is determined by the team with more castles, followed by the team with more total health, followed by a coin flip.</p>
                                    <He>Map and Resources Overview</He>
                                    <p>Game maps are procedurally generated, and are square 2d grids ranging between 32x32 and 64x64 tiles.  Every map is either horizontally or vertically symmetric, and the top left corner has the coordinates (0,0).   Each tile in the map is either passable or impassable rocky terrain, and each team starts with 1-3 Castles on the map, { SPECS.INITIAL_KARBONITE } Karbonite, and { SPECS.INITIAL_FUEL } Fuel.</p>
                                    <p>Passable tiles can have resource points on them which when mined by Pilgrims provide either Karbonite, which is used to construct units, or Fuel, which is used to run them.  Once mined, these resources can be transferred between units and deposited for global usage at Castles or Churches.  Before being deposited at a Castle or Church, resources are unrefined, and cannot be utilized.  Almost any action in Battlecode Crusade consumes either Karbonite or Fuel, all from the global refined stores.  Note that rather than being distributed evenly, Karbonite and Fuel depots are usually found in small discrete clumps on the map.  In addition to the resources teams start with and mine, at every round each team receives { SPECS.TRICKLE_FUEL } fuel.</p>
                                    <p>Robots have knowledge of the full map at the beginning of the game (including resource depots), and can only see robots within their vision radius.</p>
                                    <He>Units Overview</He>
                                    <p>Unlike last year’s Battlecode game, each unit is controlled by its own process.  Each unit is initialized with a { SPECS.CHESS_INITIAL }ms chess clock, and receives { SPECS.CHESS_EXTRA }ms of additional computation each round.  Each turn is additionally capped at {SPECS.TURN_MAX_TIME}ms, after which code will be stopped.  If a robot exceeds its chess clock, it cannot move until it has > 0 time in its clock.</p>
                                    <p>When a unit is spawned, it is assigned a unique 32 bit integer ID, and always occupies a single tile. When the health of a unit is reduced to 0, the unit is immediately removed from the game.</p>
                                    <p>There are two types of units: robots and structures. Robots are mobile units that fight, move, build factories, carry resources, or mine fuel and karbonite from the map. There are two types of structures: Castles and Churches.  Castles are like Churches that cannot be created and carry special abilities.  Churches produce robots, and provide a depot for Pilgrims to deposit resources into the global economy.</p>
                                    <Hee>Castles</Hee>
                                    <p>Each team starts with 1-3 castles on the map, each with initial health { SPECS.UNITS[SPECS.CASTLE].STARTING_HP } and vision radius { SPECS.UNITS[SPECS.CASTLE].VISION_RADIUS}.  Castles have all the abilities of Churches, but cannot be built, and have greater health.  Castles can also attack units within a {SPECS.UNITS[SPECS.CASTLE].ATTACK_RADIUS[1]} r^2 distance at {SPECS.UNITS[SPECS.CASTLE].ATTACK_DAMAGE} damage for {SPECS.UNITS[SPECS.CASTLE].ATTACK_FUEL_COST} fuel.  Castles also have unique communication abilities; not only can all units send messages to Castles for free (discussed in the Communication section), but Castles can also trade Karbonite and Fuel with opposing team castles.</p>
                                    <p>Each turn, a castle can offer a Barter to a castle of the opposing team.  Barters are offers to trade X Karbonite for Y Fuel (or vice versa).  Players can use this functionality to collaborate with the opposing team for mutual benefit.</p>
                                    <p>When all of a team’s castles are destroyed, the team is considered defeated.</p>
                                    <Hee>Churches</Hee>
                                    <p>Churches are structures with the ability to produce robots for their Karbonite and Fuel cost.  In any given turn a church or castle can spawn a robot in any adjacent square (where adjacent is defined to include diagonals), with that robot added to the end of the turn queue.  Robots adjacent to churches and castles in their turn can deposit Fuel and Karbonite, adding those resources to the team’s global stores.</p>
                                    <p>Churches can be constructed by Pilgrims for { SPECS.UNITS[SPECS.CHURCH].CONSTRUCTION_KARBONITE } Karbonite and { SPECS.UNITS[SPECS.CHURCH].CONSTRUCTION_FUEL } Fuel, and have an initial starting health of { SPECS.UNITS[SPECS.CHURCH].STARTING_HP } and a vision radius of { SPECS.UNITS[SPECS.CHURCH].VISION_RADIUS}.</p>
                                    <Hee>Robots</Hee>
                                    <p>There are four classes of robots: Pilgrims, Crusaders, Prophets, and Preachers.  Pilgrims are scouting, mining, and building robots, while the other robots are only capable of combat and resource transportation.   Below is a summary of the robot types, with more description following.</p>
                                    <RobotTable />
                                    <p>Pilgrims are non-combat robots that can mine unrefined Karbonite or Fuel and deliver them to Castles and Churches.  For each turn a Pilgrim mines a Karbonite depot, they receive { SPECS.KARBONITE_YIELD } unrefined Karbonite.  Similarly, for each turn a Pilgrim mines a Fuel depot they receive { SPECS.FUEL_YIELD } unrefined Fuel.  Pilgrims can also construct Churches.</p>
                                    <p>Crusaders are capable of shorter-range combat, Prophets are longer range, and Preachers deal AOE damage.</p>
                                    <p>Robots can move to or attack any square within their speed or attack radius, even if that terrain is technically unreachable using a smaller step size.  In each turn, a unit can only perform one physical action, including moving, attacking, depositing/giving, mining, trading, and building.</p>
                                    <He>Reclaim</He>
                                    <p>When units are destroyed, the robot that destroyed them receives half of the Karbonite required to build the destroyed unit, in addition to any resources they may have been carrying, all divided by the <code>r^2</code> between the attacker and the target.  So, if a Pilgrim were destroyed by a Crusader with <code>dx,dy=(1,1)</code> and was carrying 10 Fuel and 3 Karbonite, the attacker would now have an additional 5 Fuel and {Math.floor((3+SPECS.UNITS[SPECS.PILGRIM].CONSTRUCTION_KARBONITE/2)/2)} Karbonite.</p>
                                    <He>Communication</He>
                                    <p>Each unit on the board has its own process, and is sandboxed from other units.  To facilitate communication and global planning, each unit has two possible methods of communication.</p>
                                    <p>Radio is the primary method of communication usable by unit.  In any given turn, a unit can broadcast a {SPECS.COMMUNICATION_BITS} bit message to all units within squared radius X^2, consuming X Fuel.  For example, a unit with id 1984 that wanted to broadcast a message with a squared radius of 10 squares would need to expend <code>Math.ceil(Math.sqrt(10)) = 4</code> Fuel.  On the next round, all units within that radius will see that the a unit with ID 1984 broadcasted the given message (from its new position, if the unit moved).  Units can radio broadcast simultaneously with all other actions.  Note that robots can see the unit ID and x,y location that produced a broadcast, but not which team the unit belongs to.</p>
                                    <p>Units also have a direct channel to communicate an {SPECS.CASTLE_TALK_BITS} bit value to all their team’s Castles for free from any distance.  This can also be combined with any other action, including general radio communications.</p>
                                    <He>Turn Queue</He>
                                    <p>Battlecode Crusade games consist of up to {SPECS.MAX_ROUNDS} rounds, and each round consists of a turn for every unit on the board at that time.  This is acheived by cycling each round through a queue that consists of all units on the map.  This queue is initialized with each team’s Castles in alternating Red, Blue order.  Then, whenever a unit produces a new unit, that unit is added to the end of the turn queue as soon as the constructor unit’s turn ends.  To rephrase, units built in a round will get a turn in the same round.  A round consists of a full pass through the turn queue.</p>
                                </div>
                            </div>
                        */}
                            <div className="card">
                                <div className="header">
                                    <h4 className="title">Installation and CLI usage</h4>
                                    <p className="category">Updated 1/7/19 7:00PM EST</p>
                                </div>
                                <div className="content">
                                    <p>This year, Battlecode will be run through the Node Package Manager (npm). Installation for npm varies from operating system to operating system, but generally achieved through the <a href='https://nodejs.org/en/'>Node Website</a>. If you are on a Mac, download Homebrew and install from there using <code>brew install node npm</code>.</p>
                                    <ol>
                                        <li>Install npm.</li>
                                        <li><code>npm install -g bc19</code>.</li>
                                        <li>Run or compile your code using <code>bc19run</code> or <code>bc19compile</code>. Note that the bot code needs to be in its own directory.  Example (using the <a href="https://github.com/npfoss/examplefuncsplayer"> examplefuncsplayer </a>): <code>bc19run -b bots/exampy -r bots/example_js --chi 1000</code>.</li>
                                        <li>Upload compiled code using <code>bc19upload</code>.  Make sure you've defined environment variables <code>BC_USERNAME</code> and <code>BC_PASSWORD</code>, which should be the credentials you use to access this site.</li>
                                    </ol>

                                    You must have internet access to compile Python and Java code.  Additionally, be sure to frequently update by running <code>npm install -g bc19</code>.  If you are not running the most recent distribution, replays will not render correctly.
                                </div>
                            </div>
                            <div className="card">
                                <div className="header">
                                    <h4 className="title">Javascript Bot Reference</h4>
                                    <p className="category">Updated 1/7/19 7:00PM EST</p>
                                </div>
                                <div className="content">
                                    <p>Below is an example of a simple bot. It it is a Voyager, it moves randomly, and if it is a Planet,
                                        it tries to build as soon as it can.
                                    </p>
                                    <pre>{`import {BCAbstractRobot, SPECS} from 'battlecode';

class MyRobot extends BCAbstractRobot {
    turn() {

        if (this.me.unit === SPECS.VOYAGER) {
            const choices = [[0,-1], [1, -1], [1, 0], [1, 1], [0, 1], [-1, 1], [-1, 0], [-1, -1]];
            const choice = choices[Math.floor(Math.random()*choices.length)]
            return this.move(choice[0], choice[1]);
        }

        else if (this.me.unit === SPECS.PLANET) {
            if (this.orbs >= SPECS.UNITS[SPECS.VOYAGER].CONSTRUCTION_KARBONITE) {
                return this.buildUnit(0, 1)
            }
        }

    }
}

var robot = new MyRobot();`}</pre>
                                    <p>The main container of your bot code is the <code>MyRobot</code> class, 
                                    which must be a subclass of <code>BCAbstractRobot</code>.</p>
                                    <p>When your bot is spawned, a <code>MyRobot</code> object is 
                                    created in its own global scope (meaning that you can use global variables, but that they will not be shared between bots).
                                    For every turn, the <code>turn()</code> method of your class is called.  
                                    This is where the heart of your robot code lives. 
                                    If you want the robot to perform an action, the <code>turn()</code> method should return it.</p>
                                    <p>Note that the same <code>MyRobot</code> class is used 
                                    for all units. Some API methods will only be available for some units, 
                                    and will throw an error if called by unallowed units.</p>
                                    <p>You can change the name of the 
                                        <code>MyRobot</code> class, as long as you update the <code>var robot = new MyRobot();</code> line.</p>
                                    <hr /><h6>State Information</h6><hr />
                                    <ul>
                                        <li><code>this.me</code>: The robot object (see below) for this robot.</li>
                                        <li><code>this.map</code>: The full map. Boolean grid where <code>true</code> indicates passable and <code>false</code> indicates impassable. Indexed <code>[y][x]</code>, like all 2D arrays in Battlecode.</li>
                                        <li><code>this.orbs_map</code>: The Orbs map. Grid with integer values indicating how much orbs are present at each position. </li>
                                        <li><code>this.orbs</code>: The global amount of Orbs that the team possesses.</li>
                                        <li><code>this.robots</code>: All units that exist (including <code>this.me</code>), in random order.</li>
                                    </ul>
                                    <hr /><h6>The Robot Object</h6><hr />
                                    <p style={{fontSize: '14px'}}>Let <code>r</code> be any robot object (e.g., <code>r = this.me</code> or <code>r = this.robots[1]</code>).</p>
                                    <p style={{fontSize: '14px'}}>The following properties are available for all robots:</p>
                                    <ul>
                                        <li><code>r.id</code>: The id of the robot, which is an integer between 1 and {SPECS.MAX_ID}..</li>
                                        <li><code>r.unit</code>: The robot's unit type, where { SPECS.PLANET } stands for Planet and { SPECS.VOYAGER } stands for Voyager.</li>
                                        <li><code>r.signal</code>: The current signal of the robot.</li>
                                    </ul>
                                    <p style={{fontSize: '14px'}}>The following properties are available if the robot is visible (that is, if <code>isVisible(r)</code> is <code>true</code>).</p>
                                    <ul>
                                        <li><code>r.team</code>: The team of the robot, where {SPECS.RED} stands for RED and {SPECS.BLUE} stands for BLUE.</li>
                                        <li><code>r.x</code>: The x position of the robot.</li>
                                        <li><code>r.y</code>: The y position of the robot. </li>
                                    </ul>
                                    <p style={{fontSize: '14px'}}> In addition, the following properties are available if <code>r = this.me</code>.</p>
                                    <ul>
                                        <li><code>r.turn</code>: The turn count of the robot (initialiazed to 0, and incremented just before <code>turn()</code>).</li>
                                        <li><code>r.time</code>: The chess clock's value at the start of the turn, in ms.</li>

                                    </ul>
                                    <hr /><h6>Actions</h6><hr />
                                    <p style={{fontSize: '14px'}}>The following is a list of methods that can be returned in <code>turn()</code>, to perform an action. Note that the action will only be performed if it is returned. </p>
                                    <ul>
                                        <li><code>this.move(dx, dy)</code>: Move <code>dx</code> steps in the x direction, and <code>dy</code> steps in the y direction. Only Voyagers can move.</li>
                                        <li><code>this.buildUnit(dx, dy)</code>: Build a Voyager in the tile that is <code>dx</code> steps in the x direction and <code>dy</code> steps in the y direction from <code>this.me</code>. Can only build in adjacent, empty and passable tiles.
                                        Uses <code>{SPECS.UNITS[1].CONSTRUCTION_KARBONITE}</code> Orbs. Only Planets can build.</li>
                                    </ul>
                                    <hr /><h6>Communication</h6><hr />
                                    <ul>
                                        <li><code>this.signal(value)</code>: Broadcast <code>value</code> to all robots. The <code>value</code> should be an integer between <code>0</code> and <code>2^{SPECS.COMMUNICATION_BITS}-1</code> (inclusive). Can be called multiple times in one <code>turn()</code>; however, only the most recent signal will be used. </li>
                                    </ul>
                                    <hr /><h6>Helper Methods</h6><hr />
                                    <ul>
                                        <li><code>this.log(message)</code>: Print a message to the command line.  You cannot use ordinary <code>console.log</code> in Battlecode for security reasons.</li>
                                        <li><code>this.getVisibleRobotMap()</code>: Returns a 2d grid of integers the size of <code>this.map</code>. All tiles outside <code>this.me</code>'s vision radius will contain <code>-1</code>. All tiles within the vision will be <code>0</code> if empty, and will be a robot id if it contains a robot. </li>
                                        <li><code>this.getRobot(id)</code>: Returns a robot object with the given integer <code>id</code>.  Returns <code>null</code> if such a robot is not in your vision radius.</li>
                                        <li><code>this.isVisible(id)</code>: Returns <code>true</code> if and only if the robot identified by <code>id</code> is within <code>this.me</code>'s vision radius (particularly, <code>this.me</code> is always visible to itself). </li>
                               
                                    </ul>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Docs;
