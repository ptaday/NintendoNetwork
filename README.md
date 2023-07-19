# NintendoNetwork

> A java application network management software for the massively multiplayer Nintendo 64 Donkey Kong league setup by the Stony Brook Basement Club.

## Table of Contents

- [Description](#description)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Required Classes](#required-classes)
  - [NetworkNode](#networknode)
  - [NetworkTree](#networktree)
  - [NintendoNetwork](#nintendonetwork)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Description

The NintendoNetwork project is a VB.Net network management software designed to help the Stony Brook Basement Club manage their massively multiplayer Nintendo 64 Donkey Kong league. The software models the network as a tree with two types of nodes: Nintendo Nodes (representing Donkey Kong players with Nintendo64 devices) and Raspberry Nodes (used as routers). The network management software allows the user to view the network topology, load and save it from a file, add nodes, remove subtrees, and identify the root of the minimal subtree containing all the failures for a given scenario.

## Prerequisites

To run the NintendoNetwork project, ensure you have the following installed on your computer:

- Java Development Kit (JDK) 1.8 or later
- Git (optional, for cloning the repository)

## Installation

1. Clone the NintendoNetwork repository to your local machine (if you haven't already).

```bash
$ git clone https://github.com/yourusername/NintendoNetwork.git
$ cd NintendoNetwork
```
## Usage
To compile 
```bash
$ javac NintendoNetwork.java
```
Run the DeliveryListManager application from the command line.
```bash
$ java NintendoNetwork
```
Keep in my mind to create a package name and include the package name in all the classes.

## Required Classes
### NetworkNode
A fully documented class named NetworkNode which holds the type of component being represented, an array of children (null if this will be a Nintendo), and a string for the text. Be sure to include all getters and setters.

### NetworkTree
A fully documented class called NetworkTree which will serve as the tree manager for your NetworkTree. This must hold references to a tree (the root and cursor) and be able to generate and save the tree to and from a file.

### NintendoNetwork
A a fully-documented class named NintendoNetwork that takes in a text file, generates a NetworkTree, and provides an interface for a user to manipulate the tree.

## Contributing
Contributions to NintendoNetwork are welcome! If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
If you have any questions or feedback, you can contact the project maintainer at:

Email: pmtaday@gmail.com
GitHub: @ptaday
