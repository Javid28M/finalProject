# finalProject
This is Final Project i.e. a little console-based application which helps user to list, search, sort, and filter items which are located in ".csv" files.

User manual on our application:
The menu: 
Choose an option:
1. List randomly selected 20 entities
2. List top 20 entities
3. List bottom 20 entities
4. Sort entities
5. Search entities
6. List column names
7. Filter entities
8. Exit
Choose 1 if you want to list randomly 20 selected entities from .csv file.
Choose 2 if you want to list top 20 selected entities from .csv file.
Choose 3 if you want to list bottom 20 selected entities from .csv file.
Choose 4 if you want to sort entities from .csv file:
	You can enter fields to sort by (id, item_id, name, category, price, oldPrice)
	For instance, user wants to choose price – He/she should type price.
	Once selecting the field to sort by is done, program offers to choose ascending or descending order of sorting.
	After selecting the order of sorting, program offers to store the report to a separate .csv file with proper indentation, or a person can get back to menu.
The option “Enter 1 to display all fields or 2 to select fields:” offers two choices, either to get back on main menu(enter 2), or display all fields(which may be useful) on console and then get back on main menu.
Choose 5 if you want to search entities from .csv file:
	You can enter fields to search by (id, item_id, name, category, price, oldPrice)
	Then, you can enter the value to search for, let’s say category.
	Let’s say person wants to search items in “Beds” category. He/she should enter the “Beds” to the console. The program will show all the items in Beds category.
	Also, program will offer to make a separate .csv file with proper identation.
Choose 6 to list column names from any .csv file:
	A person can enter any file, for instance “ikea.csv”, and the program will show columns in specified file.
Choose 7 to filter entities from .csv file:
	A person can enter fields to filter by (id, item_id, name, category, price, oldPrice):
	Let’s say one wants to filter by name. He should write “name” in console.
	Next step is to choose which rule to apply to filtering. These options are considered by program:
		Write “contains” – means find string that contains in item.
		Write “eq” – means equals, for instance, to find exact match of price, id, etc.
		Write “gt” – means greater than, for instance to find prices greater than 3000.
		Write “lt” – means less than, for instance to find prices less than 1000.
		Write “ge” – means greater equal, for instance to find prices greater or equal to 3000.
		Write “le” – means less equal, for instance to find prices less or equal to 1000.
Write “bt” – means between, for instance to find prices of items between 2000 and 3000(write values separated by only comma)
Write “null” – to find an empty field.
	After choosing which rule to apply to filtering, user should choose which fields program should display. For instance, person wants to display price and designer name, so he/she should write “price,designer”.
	And again, program will offer to create separate .csv file.
Choose 8 to terminate the program.
