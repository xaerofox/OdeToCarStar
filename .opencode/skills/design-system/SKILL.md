---
name: design-system
description: Provides strict styling tokens, typography constraints, and layout rules for the High-Octane Velocity design system.
compatibility: opencode
---
## When to use me
Use this skill whenever analyzing, reviewing, or writing code that involves user interfaces, color implementations, button styles, custom input fields, spacing/layout definitions, or component design compliance.

## High-Octane Velocity Design System Specifications

### 1. Colors & Token Implementation
- Background base: #131314 (Midnight Carbon/Obsidian Base)
- Primary Accent: #00dbe9 (Electric Blue - used for primary actions, active states, and ignition)
- Secondary Accent: #ff525c (Racing Red - reserved for alerts, errors, and redline performance data)
- Tertiary Accent: #bbea00 (Acid Green - used for success, ready states, and metrics)

### 2. Typography Rules
- Headings/Labels demanding attention must map to 'Anton' font styles, using heavy weights, and must ALWAYS be implemented as UPPERCASE in display scenarios.
- Body and technical copy must use 'Geist' or 'JetBrains Mono' (for raw data, numeric spec sheets, or telemetry stats) to preserve the data-rich engineering aesthetic.

### 3. Shapes & Layout Constraints
- Sharp (0px) roundedness strategy: Flags any implementation using rounded corners, border-radii, or circular geometries for buttons, input fields, or layout cards.
- Look out for clipped corners (45-degree chamfers) on top-right and bottom-left edges of primary components.
- Layouts must respect a strict 4px micro-rhythm base unit. Spacing between major disparate content blocks must be aggressive (48px+) to honor the design system's structural breathing room.

### 4. Component Definitions
- Buttons: Must be sharp-edged. Look out for hover state glow effects or ghost styles with 2px borders for secondary components.
- Inputs: Bottom-border styling only. Standard text inputs with wrapping box outlines violate the spec.
- Data Cards & Progress Bars: Linear telemetry ranges mapping from Primary Blue to Secondary Red (tachometer layout).

---
## Enforcement Protocol
If any analyzed code or feature layout violates these principles, reject the implementation, specify exactly which rule (e.g., "Shape Aggression Rule: Border radius found") was broken, and provide the exact fix.