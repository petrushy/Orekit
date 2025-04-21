"""
This script validates the mapping between Orekitpublic interfaces and their corresponding 
implementations as Python wrapper classes. Specifically, it checks if each public interface 
defined in the Java source files has a corresponding implementation class prefixed with "Python".

The script performs the following steps:
1. Scans the source directory for all public interfaces.
2. Scans the source directory for all classes prefixed with "Python".
3. Compares the interfaces against the implementations to identify missing mappings.
4. Outputs a summary of the total interfaces, implemented interfaces, and missing implementations.
5. Lists all interfaces that do not have a corresponding "Python"-prefixed implementation.

Usage:
- cd python_wrapper_validation
- python interfaces_validation.py


This script was created by Petrus Hyvönen 2025, with heavy use of copilot.
This script is part of the Orekit project, which is licensed under the Apache License 2.0.
"""

import os
import re

# Directory to search
SRC_DIR = "../src"

def find_interfaces(src_dir):
    """Find all public interfaces in the source directory."""
    interfaces = set()
    for root, _, files in os.walk(src_dir):
        for file in files:
            if file.endswith(".java"):
                filepath = os.path.join(root, file)
                with open(filepath, "r", encoding="utf-8") as f:
                    for line in f:
                        match = re.match(r"public interface (\w+)", line)
                        if match:
                            interfaces.add(match.group(1))
    return interfaces

def find_python_implementations(src_dir):
    """Find all classes prefixed with 'Python' in the source directory."""
    implementations = set()
    for root, _, files in os.walk(src_dir):
        for file in files:
            if file.endswith(".java"):
                filepath = os.path.join(root, file)
                with open(filepath, "r", encoding="utf-8") as f:
                    for line in f:
                        match = re.match(r"public class Python(\w+)", line)
                        if match:
                            implementations.add(match.group(1))
    return implementations

def main():
    interfaces = find_interfaces(SRC_DIR)
    implementations = find_python_implementations(SRC_DIR)

    print("Checking interface-implementation mapping...\n")

    missing_implementations = []

    for interface in sorted(interfaces):
        if interface in implementations:
            print(f"Found implementation for interface: {interface}")
        else:
            print(f"Missing implementation for interface: {interface}")
            missing_implementations.append(interface)

    print("\nSummary:")
    print(f"Total interfaces: {len(interfaces)}")
    print(f"Implemented interfaces: {len(interfaces) - len(missing_implementations)}")
    print(f"Missing implementations: {len(missing_implementations)}")

    if missing_implementations:
        print("\nList of missing interface implementations:")
        for missing in missing_implementations:
            print(f"- {missing}")

if __name__ == "__main__":
    main()