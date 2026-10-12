# ☕ Coffee Machine Simulator (JavaFX)

An interactive 2D specialty coffee brewing simulation built with **JavaFX**. The project simulates the real-world coffee preparation process—from selecting coffee beans, grinding, and tamping in the portafilter, to extracting the final espresso shot.

---

## 🎮 Game Mechanics & Features

- **Coffee Selection:** Pick bean types and dosage, which spawns a movable coffee bowl (`entities.CoffeeBowl`).
- **Grinding Station (`entities.stations.GrinderStation`):** Smooth *Drag-and-Drop* bowl mechanics onto the grinder with time-based grinding.
- **entities.Portafilter Mechanics (`entities.Portafilter`):** Lock the portafilter into the grinder to collect fresh ground coffee.
- **Espresso Extraction (`entities.stations.EspressoMachine`):**
  - Insert the prepped portafilter and place a coffee cup (`entities.CupState`).
  - Interactive GUI Pop-up menu to specify the exact yield in `ml`.
  - Asynchronous brewing powered by JavaFX animations (`PauseTransition`) ensuring a smooth, non-freezing UI.

---

## 🛠️ Built With

* **Language:** Java 17+
* **GUI Framework:** JavaFX / OpenJFX
* **Architecture:** Object-Oriented Programming (OOP), Custom GUI Wrapper pattern (`entities.GUIObject`), Singleton & Event-Driven Architecture
* **Build System / IDE:** IntelliJ IDEA, Maven / Gradle

---

## 🚀 Getting Started

### Prerequisites:
- **JDK 17** or higher installed
- **JavaFX SDK** configured (if not using Maven/Gradle dependencies)

### Installation & Running:

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/coffee-machine-simulator.git](https://github.com/your-username/coffee-machine-simulator.git)
   cd coffee-machine-simulator
