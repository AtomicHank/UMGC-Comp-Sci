# Assumptions and Limitations

## Assumptions
- Cars move on a straight line only, so `Y = 0` for all cars at all times.
- Distance between adjacent intersections is fixed at `1000` meters.
- Units are:
  - Position: meters
  - Speed: km/h
  - Time step for car updates: 0.25 seconds
- Traffic lights cycle independently with fixed durations:
  - RED: 12 seconds
  - GREEN: 10 seconds
  - YELLOW: 3 seconds
- Cars stop immediately at red lights (no braking physics), and proceed through yellow/green.

## Limitations
- No turn lanes, lane changes, or collision/vehicle interaction model.
- Speed changes are randomized within a bounded range to emulate variation.
- Intersections and cars can be added through the GUI, but not removed.
- Display is tabular for clarity and grading traceability.
