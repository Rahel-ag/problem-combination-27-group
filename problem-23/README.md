Customer Support Center

A simple Java-based Customer Support Center that manages customer requests using tiered queues and Weighted Round Robin (WRR) processing.

Features:-

Three Support Tiers: Platinum, Gold, Silver

Weighted Round Robin (WRR) Processing:-

Platinum: 3 users per cycle

Gold: 2 users per cycle

Silver: 1 user per cycle

Supported Commands:-

ARRIVE <user> <tier> = Add a user to a specific tier queue

PROCESS_NEXT = Process the next user based on WRR rules

STATUS = Display the current queue status

EXIT = Exit the system

File Structure:-

Main.java          # Entry point for the application

SupportCenter.java # Support center logic and queue management

How to Compile and Run

Compile the Java files:-

javac Main.java

SupportCenter.java


Run the program:-

java Main


Interact with the system using commands:

> ARRIVE Alice Platinum
> ARRIVE Bob Gold
> PROCESS_NEXT
> STATUS
> EXIT

Examples:-
Customer Support Center Loaded. Commands: ARRIVE <user> <tier>, PROCESS_NEXT, STATUS, EXIT
> ARRIVE Alice Platinum
> ARRIVE Bob Gold
> ARRIVE Charlie Silver
> PROCESS_NEXT
Alice (Plat #1)
> PROCESS_NEXT
Alice (Plat #2)
> PROCESS_NEXT
Alice (Plat #3)
> PROCESS_NEXT
Bob (Gold #1)
> STATUS

Current Queues 
Platinum: []
Gold: [Bob]
Silver: [Charlie]
> EXIT

Implementation Details:-

Queues:-

queues[0] = Platinum

queues[1] = Gold

queues[2] = Silver

Weighted Round Robin (WRR) Quotas:

quotas = {3, 2, 1} corresponding to Platinum, Gold, Silver.

The system cycles through tiers, processing up to the quota or skipping empty queues.

Cycle Management:-

currentTier tracks which tier is being processed.

currentCount tracks how many users have been processed in the current tier.

Error Handling:

Only Platinum, Gold, and Silver tiers are allowed. Invalid tier input will display an error.

If all queues are empty, PROCESS_NEXT prints "All queues are empty".

About the system:-

The system is console-based and ideal for learning or demonstration of priority-based queue management.
The STATUS command provides a snapshot of the current queues at any time.
users are automatically processed in order, with higher-priority tiers handled more frequently according to WRR rules.


Users are automatically processed in order, with higher-priority tiers handled more frequently according to WRR rules.

The STATUS command provides a snapshot of the current queues at any time.

