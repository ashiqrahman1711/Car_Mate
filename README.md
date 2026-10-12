# CarMate: Car Rental Management System

A console-based car rental application built in **Java**, with role-based access for administrators and customers and file-based data persistence through object serialization. It is a compact example of object-oriented design, file I/O, and menu-driven application flow.

**Author:** Ashikur Rahman

---

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Data Persistence](#data-persistence)
- [Roadmap](#roadmap)
- [Author](#author)

---

## Overview

CarMate lets a rental business manage its fleet and lets customers book cars by location. Admins maintain the inventory and review customers and revenue. Customers search for available cars in their area, get an instant cost estimate, and book or return vehicles. All state is saved between runs, with no external database required.

## Key Features

**Admin Panel (authenticated)**
- Secure login with username and password
- View the full car inventory
- Add and remove cars (registration number, brand, model, daily rate, location)
- View customer records
- Track earnings per booking

**Customer Portal**
- Rent a car by location, with only available vehicles shown
- Automatic total cost calculation (daily rate x number of days) with a confirm/cancel step
- Return rented cars, which makes them available again

**System Behavior**
- Default admin account bootstrapped on first launch
- Phone number validation using regular expressions (`01` followed by 9 digits)
- Case-insensitive matching for locations and registration numbers
- Persistent storage across sessions

## Tech Stack

| Area           | Technology                              |
|----------------|-----------------------------------------|
| Language       | Java 23 (arrow-style `switch`, lambdas) |
| Persistence    | Java Serialization (`ObjectInputStream` / `ObjectOutputStream`) |
| Collections    | `List`, `ArrayList`, Streams-style `removeIf` |
| Build / IDE    | Apache Ant, Apache NetBeans            |

## Architecture

The project is organized into model classes, a persistence utility, and a controller class that drives the menus.

```
CarMate/
├── build.xml
├── manifest.mf
├── nbproject/                # NetBeans/Ant project configuration
└── src/carmate/
    ├── CarMate.java          # Entry point; menu flow and business logic
    ├── FileHandler.java      # Generic, reusable serialization read/write
    ├── Admin.java            # Model: administrator credentials
    ├── Car.java              # Model: vehicle details and availability
    ├── Customer.java         # Model: customer details
    └── Earning.java          # Model: revenue record per booking
```

**Design highlights**
- **Encapsulation:** all model classes use private fields with accessors.
- **Generic utility:** `FileHandler` uses generics (`<T> List<T>`) so one class handles every entity type.
- **Separation of concerns:** data models, persistence, and UI/flow logic are in separate classes.
- **Serializable models:** every entity implements `Serializable` for straightforward persistence.

## Getting Started

### Prerequisites
- JDK 23 (JDK 14+ is the minimum for the `switch` syntax used)
- Apache NetBeans (optional)

### Run with NetBeans
1. Choose **File > Open Project** and select the `CarMate` folder.
2. Create a `data/` folder in the project root (see the note below).
3. Click **Run Project** (main class: `carmate.CarMate`).

### Run from the command line
From inside the `CarMate` directory:

```bash
mkdir -p data build/classes
javac -d build/classes src/carmate/*.java
java -cp build/classes carmate.CarMate
```

> **Note:** Data files are written to a `data/` directory relative to the working directory. Create it before the first run.

### Default admin credentials

| Username | Password |
|----------|----------|
| `ashiq`  | `1711`   |

These are for development only. Change them in `initializeDefaultAdmin()` before any real deployment.

## Usage

```
=== CarMate Main Menu ===
1. Admin
2. Customer
3. Exit
```

**Suggested walkthrough**
1. Log in as Admin and add a few cars (*Admin Menu > Add New Car*).
2. Return to the main menu and choose *Customer > Rent a Car*.
3. Enter your name, phone number, and location, pick an available car, set the number of days, and confirm with `Y`.
4. Return the car via *Customer > Return a Car*.
5. Log in as Admin again to review customers and earnings.

## Data Persistence

State is stored as serialized Java lists in the `data/` directory:

| File            | Contents               |
|-----------------|------------------------|
| `admins.dat`    | Administrator accounts |
| `cars.dat`      | Fleet and availability |
| `customers.dat` | Customer records       |
| `earnings.dat`  | Booking revenue        |

Delete these files to reset the application.

## Roadmap

Planned improvements to move the project toward production quality:

- [ ] Formatted tabular output for car listings (`toString()` or `printf` table)
- [ ] Robust input validation and exception handling for all menus
- [ ] Password hashing (e.g., BCrypt or SHA-256 with salt)
- [ ] Rental history linking customers, cars, and dates, plus late-return charges
- [ ] Migration from serialization to a database (SQLite/JDBC)
- [ ] Unit tests (JUnit) for core logic
- [ ] GUI front end (JavaFX) or REST API layer

## Author

**Ashikur Rahman**
Developer of CarMate

Feedback and suggestions are welcome.
