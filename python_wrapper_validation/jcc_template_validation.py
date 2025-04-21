import os
import re

# Directory to search
SRC_DIR = "../src"

def find_python_classes(src_dir):
    """Find all classes prefixed with 'Python' in the source directory."""
    python_classes = []
    for root, _, files in os.walk(src_dir):
        for file in files:
            if file.endswith(".java"):
                filepath = os.path.join(root, file)
                with open(filepath, "r", encoding="utf-8") as f:
                    for line in f:
                        match = re.match(r"public class (Python\w+)", line)
                        if match:
                            python_classes.append((match.group(1), filepath))
    return python_classes

def check_python_extension_method(filepath):
    """Check if the file contains the 'pythonExtension' method."""
    with open(filepath, "r", encoding="utf-8") as f:
        content = f.read()
        return "public void pythonExtension(long pythonObject)" in content

def main():
    python_classes = find_python_classes(SRC_DIR)
    missing_extension_method = []

    print("Checking 'pythonExtension' method in Python-prefixed classes...\n")

    for class_name, filepath in python_classes:
        if check_python_extension_method(filepath):
            print(f"Class '{class_name}' contains 'pythonExtension' method.")
        else:
            print(f"Class '{class_name}' is missing 'pythonExtension' method.")
            missing_extension_method.append((class_name, filepath))

    print("\nSummary:")
    print(f"Total Python-prefixed classes: {len(python_classes)}")
    print(f"Classes with 'pythonExtension' method: {len(python_classes) - len(missing_extension_method)}")
    print(f"Classes missing 'pythonExtension' method: {len(missing_extension_method)}")

    if missing_extension_method:
        print("\nList of classes missing 'pythonExtension' method:")
        for class_name, filepath in missing_extension_method:
            print(f"- {class_name} (File: {filepath})")

if __name__ == "__main__":
    main()