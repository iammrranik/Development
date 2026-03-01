"""
    This is a CLI application to calculate student results.
    And (Possibly) to export them to a .csv file.
    (docString for the module)
"""

#Helper functions


# App functionalities implementation
def add_student(students: list[dict]) -> None:
    sid = 1 # To be continued

# CLI menu printing function
def print_menu() -> None:
    print("=== Student Results CLI ===")
    print("1) Add student + compute results")
    print("2) View students and result summary")
    print("3) Search student by Id")
    print("4) Delete student by Id")
    print("5) Export results to CSV file")
    print("6) Exit")

# Main function
def main() -> None:
    # List to contain students
    students: list[dict] = []
    while True:
        print_menu()
        #variable to store user choice
        choice = input("Choose your choice (1 - 6): ").strip()
        #Match-case is only available in Python 3.10 and above
        match choice:
            case "1":
                print("Adding student...")
            case "2":
                print("Viewing students and results...")
            case "3":
                print("Searching student by Id...")
            case "4":
                print("Deleting student by Id...")
            case "5":
                print("Exporting results to CSV file...")
            case "6":
                print("Exiting the application.")
                break
            case _:
                print("Invalid choice. Please try again.")

# __ magic function(Dunder method) to run the main function when the script is executed
if __name__ == "__main__":
    main()
