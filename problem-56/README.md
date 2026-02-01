# P56 of Problem Combination 27 Group Project
- Description
Implementation of a packet reassembly buffer for a video streaming client that handles out-of-order packet arrival with timeout-based loss detection.
# Features
   - Min-Heap used to store incoming packets
   - Circular Queue for playback output  
   - Timeout system to detect missing packets
   - Releases packets in order
   - Command-line interface
   - Tracks basic metrics such as packet loss and buffer size
# How to Compile and Run
- cd problem-56
- javac -d bin src/*.java
- java -cp bin Main, or use the Run/Debug button in Main.java using IDE .

# Commands
- SET_TIMEOUT <t> // Set timeout value (in ticks)
- RECEIVE <seq> <data> // Receive a packet with sequence number and data.
- TICK // Advance time by one tick and check for timeouts.
- PLAY // play all packets ready in the playback buffer.
- METRICS // Display packet loss rate and buffer statistics.
- EXIT // Exit the program.

# Example

- SET_TIMEOUT 3
- RECEIVE 1 "FrameA"
- RECEIVE 4 "FrameD"  // Packets 2 and 3 are missing
- TICK
Released for playback: Seq 1: FrameA
- TICK
- TICK
- TICK  //After total 4 ticks
Timeout for Seq 2 Marked as LOST.
Timeout for Seq 3 Marked as LOST.
Released for playback: Seq 4: FrameD

# METRICS
-  Metrics 
Packets Received: 2
Packets Lost: 2
Loss Rate: 25.0%
Avg Buffer Size: 1.4




