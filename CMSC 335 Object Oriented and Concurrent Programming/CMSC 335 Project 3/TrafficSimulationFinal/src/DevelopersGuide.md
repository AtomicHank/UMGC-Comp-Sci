# Developer's Guide

## 1. Overview
This guide explains how to compile, execute, and test the CMSC 335 Traffic Simulation application

## 2. Prerequisites
- Java Development Kit (JDK) 17 or newer (JDK 11 also works for this codebase)
- Windows PowerShell

## 3. Compile Instructions
From the project root (`TrafficSimulationFinal`):

```powershell
javac -d out src\\*.java
```

Expected result:
- `.class` files are generated under `out`
- No compile errors are displayed

## 4. Execute Instructions
From the project root (`TrafficSimulationFinal`):

```powershell
java -cp out TrafficSimulationApp
```

Expected result:
- The Swing window opens
- The dashboard shows clock, elapsed time, traffic light panel, car table, and control buttons (`Start`, `Pause`, `Continue`, `Stop`)

## 5. Functional Components to Verify
- Menu/control component: `Start` button
- Menu/control component: `Pause` button
- Menu/control component: `Continue` button
- Menu/control component: `Stop` button
- Clock update thread
- Traffic light cycle thread
- Car movement thread

## 6. Test Plan and Evidence
- Use `docs/TestPlan.md`
- For each test case, include:
  - `Actual Result` with Pass/Fail and one sentence
  - `Screenshot File` (for example, `screenshots/test01_start.png`)
  - `Evidence Description` describing what in the screenshot proves expected behavior

## 7. Packaging Checklist for Submission
- Source folder: `src`
- Documentation folder: `docs`
- UML diagram: `docs/UML.md`
- Assumptions/limitations: `docs/Assumptions.md`
- Completed test plan with screenshots: `docs/TestPlan.md`
- Lessons learned: section below

## 8. Lessons Learned
1. Thread coordination is easier to reason about when run/pause/resume logic is centralized (`SimulationControl`) instead of duplicated in each worker
2. Swing UI updates must happen on the Event Dispatch Thread; using `SwingUtilities.invokeLater` prevents random UI behavior
3. A simulation can remain understandable while still demonstrating concurrency if each responsibility is isolated
4. Defining assumptions early (no turns, no collision model, fixed stop line) keeps scope aligned with assignment goals
5. Small, repeatable tests for each control button make debugging multithreaded behavior significantly faster
