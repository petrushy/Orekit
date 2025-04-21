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
                            python_classes.append(filepath)
    return python_classes

def check_override_methods(filepath):
    """Check if all @Override methods in the file are of 'public native' type."""
    with open(filepath, "r", encoding="utf-8") as f:
        lines = f.readlines()

    issues = []
    for i, line in enumerate(lines):
        if "@Override" in line:
            # Check the next non-empty line for 'public native'
            j = i + 1
            while j < len(lines) and lines[j].strip() == "":
                j += 1
            if j < len(lines) and not re.match(r"public native", lines[j].strip()):
                issues.append((i + 1, lines[j].strip()))  # Line number and problematic line

    return issues

def main():
    python_classes = find_python_classes(SRC_DIR)
    total_classes = len(python_classes)
    classes_with_issues = 0

    print("Checking @Override methods in Python-prefixed classes...\n")

    for filepath in python_classes:
        issues = check_override_methods(filepath)
        if issues:
            classes_with_issues += 1
            print(f"Issues found in file: {filepath}")
            for line_num, content in issues:
                print(f"  Line {line_num}: {content}")
        else:
            # print(f"No issues found in file: {filepath}")
            pass

    print("\nSummary:")
    print(f"Total Python-prefixed classes checked: {total_classes}")
    print(f"Classes with issues: {classes_with_issues}")

if __name__ == "__main__":
    main()