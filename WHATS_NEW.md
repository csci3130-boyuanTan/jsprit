WHATS NEW
==========

------------------------------

<b>2014-09-?</b> new release **v1.4.0**

<b> SKILLS </b>

Skills can now be included easily (see for example https://github.com/jsprit/jsprit/blob/master/jsprit-examples/src/main/java/jsprit/examples/SolomonWithSkillsExample.java).
It lets you assign hard requirements to jobs and vehicles/drivers. For example, your vehicle requires a loading bridge to unload freight at customer A or 
a technician requires a screwdriver, a hammer and an electric drill to serve customer A. 
You assign latter skills to your customer as follows:

<pre><code>Service service = Service.Builder.newInstance(serviceId).addRequiredSkill("screwdriver")
    .addRequiredSkill("hammer").addRequiredSkill("electric-drill"). ... .build();
</code></pre>

Assign these skills to your technician as well:
 
<pre><code>VehicleImpl skilled_technician = VehicleImpl.Builder.newInstance(technicianId).addSkill("screwdriver")
    .addSkill("hammer").addSkill("electric-drill"). ... .build();
</code></pre> 


Note that you can add an arbitrary number of skills to jobs and vehicles.

To enable the algorithm to consider skills, you need to use <code>core.algorithm.VehicleRoutingAlgorithmBuilder</code> as follows:

<pre><code>
VehicleRoutingAlgorithmBuilder vraBuilder = new VehicleRoutingAlgorithmBuilder(skillProblem,yourConfig);
vraBuilder.addCoreConstraints();
vraBuilder.addDefaultCostCalculators();
           
StateManager stateManager = new StateManager(skillProblem);
<b>stateManager.updateSkillStates();</b>

ConstraintManager constraintManager = new ConstraintManager(skillProblem,stateManager);
<b>constraintManager.addSkillsConstraint();</b>

VehicleRoutingAlgorithm vra = vraBuilder.build();
</code></pre>



<b> UNASSIGNED JOB LIST</b>

A solution can now consists of assigned and unassigned jobs. There are various reasons for unassigned jobs, e.g. 
demand exceeds available capacity, the job cannot be served within driver's operation time or the job is just too costly to 
serve it with your own fleet.
 
Note that jsprit uses a "soft" approach to deal with unassigned jobs, i.e. each unassigned job will be penalyzed in the objective function 
(see default objective https://github.com/jsprit/jsprit/blob/master/jsprit-core/src/main/java/jsprit/core/algorithm/VariablePlusFixedSolutionCostCalculatorFactory.java [line 55]). 
If you omit penalyzing them, you probably end up with a solution consisting solely of unassigned jobs (the less the better in terms of total costs). 
This, however, easily enables you to define objective functions that maximizes profits.

<b>Thus, if you already use your own custom objective function, you need to manually adapt it and add penalties for unassigned jobs.</b>

------------------------------

<b>2014-08-20</b> jsprit has a mailing list (https://groups.google.com/group/jsprit-mailing-list)

------------------------------

<b>2014-08-15</b> [YourKit](http://www.yourkit.com/home/) supports jsprit with its full-featured Java Profiler. 

------------------------------

<b>2014-08-10</b> jsprit uses Travis for continuous integration (https://travis-ci.org/jsprit/jsprit) 
