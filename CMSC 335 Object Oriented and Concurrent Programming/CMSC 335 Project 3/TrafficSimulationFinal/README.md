# TrafficSimulationFinal

Java Swing traffic simulation for CMSC 335 Project 3.

## Features
- Current time updated every second.
- Main intersection traffic light cycling through RED, GREEN, and YELLOW.
- Three simulated cars with position and speed updates.
- Button controls: `Start`, `Pause`, `Continue`, `Stop`.
- Concurrent design using separate threads for clock, light, and car simulation.

## Project Structure
- `src/TrafficSimulationApp.java`: program entry point.
- `src/TrafficSimulationFrame.java`: Swing GUI and controls.
- `src/SimulationModel.java`: shared simulation data model.
- `src/SimulationControl.java`: run/pause/resume/stop coordination.
- `src/ClockWorker.java`: 1-second clock updates.
- `src/TrafficLightWorker.java`: traffic light cycle logic.
- `src/CarWorker.java`: car movement updates.
- `src/TrafficLightColor.java`: light state enum.

## Compile and Run (PowerShell)
From `TrafficSimulationFinal`:

```powershell
javac -d out src\\*.java
java -cp out TrafficSimulationApp
```

## Documentation
- `docs/DevelopersGuide.md`: compile/execute instructions, testing guidance, lessons learned.
- `docs/TestPlan.md`: comprehensive test plan with evidence columns.
- `docs/UML.md`: UML class diagram.
- `docs/Assumptions.md`: assumptions and limitations.
