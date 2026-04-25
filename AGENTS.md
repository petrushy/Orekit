# AI Coding Agent Guidelines for Orekit

## Overview
Orekit is a low-level Java library for space flight dynamics, providing orbital mechanics, propagation, attitude modeling, and orbit determination. It emphasizes robustness, maintainability, efficiency, and high test coverage.

**Note**: This repository is a fork of the upstream Orekit project, adapted for the Orekit Python Wrapper using JCC. It includes additional classes prefixed with "Python.." for Python integration, but otherwise remains verbatim 1:1 with the upstream project.

## Architecture
- **Modular Design**: Organized into packages like `orbits`, `propagation`, `frames`, `attitudes`, `forces`, `estimation`, `data`, `time`, `utils`. Each handles specific concerns with clear boundaries.
- **Unified Interfaces**: Propagators, orbits, and frames use consistent interfaces for easy switching between implementations (e.g., Keplerian vs. numerical propagation).
- **Extensibility**: User-extensible through plugin mechanisms (e.g., custom force models, data loaders, event detectors).
- **Immutability**: Core classes like `Orbit`, `AbsoluteDate`, `Vector3D`, `Rotation` are immutable to ensure thread-safety and prevent side effects.
- **Data Context**: Uses `DataContext` for loading external data (Earth orientation, leap seconds) via crawlers (`DirectoryCrawler`, `NetworkCrawler`) and filters (`GzipFilter`, `TruncatingFilter`).

## Development Workflows
- **Build System**: Maven-based. Use `mvn package` for releases, `mvn install` for local repo. For development versions, build Hipparchus first (`git clone https://github.com/Hipparchus-Math/hipparchus.git; cd hipparchus; mvn install`).
- **Version Control**: Git with git-flow: develop on `develop` branch, create feature branches, merge with `--no-ff`. Release branches from `develop`, bugfix branches from `main`.
- **Testing**: Extensive unit tests with JUnit 5 and Mockito. Aim for >80% line coverage (enforced via Jacoco). Tests include validation against references and non-regression checks with tight tolerances.
- **Code Quality**: Run `mvn checkstyle:check` (no star imports, final parameters, 4-space indent, no trailing whitespace, no `System.out.println`). Run `mvn spotbugs:check` to fix all warnings. All code must pass these checks.
- **CI/CD**: GitLab CI with parallel test execution. High coverage requirements: 100% class, 95% method, 90% instruction, 85% branch/line.

## Coding Conventions
- **Style**: Follow Sun Java conventions with Checkstyle. Apache license header on all files. No tabs, UTF-8 encoding. Curly braces at end of line.
- **Naming**: Classes UpperCamelCase, methods/fields lowerCamelCase, constants UPPER_SNAKE_CASE.
- **Javadoc**: Complete for all public/protected elements, including private fields/methods (rare exceptions for translated Fortran code).
- **Imports**: No star imports, no static imports.
- **Parameters**: Mark as `final` wherever possible.
- **Robustness**: No runtime assumptions (no console output, handle embedded environments). Use immutable objects. Switch statements have default cases.
- **Efficiency**: Balance with maintainability; avoid premature optimization.

## Key Patterns
- **Orbit Representations**: Multiple types (`KeplerianOrbit`, `CartesianOrbit`, `CircularOrbit`, `EquinoctialOrbit`) with conversions via `OrbitType`.
- **Propagation**: Choose from analytical (`KeplerianPropagator`), numerical (`NumericalPropagator` with force models), semi-analytical (`DSSTPropagator`). Unified via `Propagator` interface.
- **Event Detection**: Use `EventDetector` for eclipses, node crossings, etc. Can combine with boolean operators.
- **Data Loading**: Implement `DataProvider` with crawlers for local/network/zip files. Use `DataContext.getDefault()` to load Orekit data.
- **Frames**: Extensive hierarchy (`FramesFactory.getGCRF()`, `FramesFactory.getEME2000()`). Handle IERS data transparently.
- **Attitude**: Laws like `NadirPointing`, `YawCompensation`. Extensible via `AttitudeProvider`.
- **Estimation**: Batch least squares, Kalman filters. Measurements like `Range`, `AzimuthElevation`.

## Dependencies
- **Hipparchus**: Core math library (geometry, ODE solvers, optimization). Hidden from users but used extensively.
- **Test**: JUnit 5, Mockito, Hamcrest.
- **External Data**: Download Orekit data archive or clone `https://gitlab.orekit.org/orekit/orekit-data`. Load via `DirectoryCrawler`.

## Common Tasks
- Check that there are python wrapper classes for all Abstract and Interface java classes. If not, create them and add wrapper template code
- Verify that the JCC template code is correct for each PythonXXX wrapper class

## References
- [Overview](src/main/java/org/orekit/overview.html)
- [Building](src/site/markdown/building.md)
- [Contributing](src/site/markdown/contributing.md)
- [Guidelines](src/site/markdown/guidelines.md)
- [API Docs](https://www.orekit.org/site-orekit-development/apidocs/)
