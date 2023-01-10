import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.*;

public class Main {
    public static void main(String[] args) {
        // read data from ikea.csv file and create a List of IKEA objects
        List<IKEA> entities = readFromCSV("ikea.csv");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. List randomly selected 20 entities");
            System.out.println("2. List top 20 entities");
            System.out.println("3. List bottom 20 entities");
            System.out.println("4. Sort entities");
            System.out.println("5. Search entities");
            System.out.println("6. List column names");
            System.out.println("7. Filter entities");
            System.out.println("8. Exit");

            int option = scanner.nextInt();
            if (option == 1) {
                IKEA.listEntities(entities, "random");
            } else if (option == 2) {
                IKEA.listEntities(entities, "top");
             } else if (option == 3) {
                IKEA.listEntities(entities, "bottom");
             } else if (option == 4) {
                System.out.println("Enter the field to sort by (id,item_id,name,category,price,oldPrice):");
                String field = scanner.next();
                System.out.println("Enter the order (asc or desc):");
                String order = scanner.next();
                IKEA.sortEntities(entities,field,order);
                System.out.println("Do you want to export the report to a .csv file? (y/n)");
                String export = scanner.next();
                if (export.equals("y")) {
                    exportToCSV(entities, field + "_" + order + "_sorted_entities_report");
                }
            } else if (option == 5) {
                System.out.println("Enter the field to search by (id,item_id,name,category,price,oldPrice):");
                String field = scanner.next();
                System.out.println("Enter the value to search for(id;item_id;name;category;price;oldPrice):");
                String value = scanner.next();
                IKEA.searchEntities(entities, field, value);
                System.out.println("Do you want to export the report to a .csv file? (y/n)");
                String export = scanner.next();
                if (export.equals("y")) {
                    exportToCSV(entities, "Search_report");
                }
            }else if (option == 6) {
                System.out.println("Enter the name of the file:");
                String fileName = scanner.next();
                IKEA.listColumnNames(fileName);
            } else if (option == 7) {
                System.out.println("Enter the field to filter by (id,item_id,name,category,price,oldPrice):");
                String field = scanner.next();

                System.out.println("Enter the rule you want to apply (contains, eq, gt, lt, ge, le, bt, null):");
                String rule = scanner.next();

                System.out.println("Enter the value you want to filter by (id,item_id,name,category,price,oldPrice):");
                String value = scanner.next();

                // filter the entities and store the result in a new list
                List<IKEA> filteredEntities = IKEA.filterEntities(entities, field, rule, value);

                // print the number of entities in the filtered list
                System.out.println("Number of entities: " + filteredEntities.size());

                // prompt user for which fields to display
                System.out.println("Enter the fields you want to display, separated by a comma:");
                String fieldsString = scanner.next();
                String[] fields = fieldsString.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");

                // display the values of the filtered entities
                for (IKEA entity : filteredEntities) {
                    entity.displayFields(fields);
                }
                System.out.println("Do you want to export the report to a CSV file? (y/n)");
                String export = scanner.next();
                if (export.equals("y")) {
                    // generate a file name based on the request info
                    String fileName = field + "_" + rule + "_" + value;
                    // export the report to a CSV file
                    exportToCSV(filteredEntities, fileName);
                }

            }else if (option == 8) {
                break;
            } else {
                System.out.println("Invalid option. Try again.");
                continue;
            }

            System.out.println("Enter 1 to display all fields or 2 to select fields:");
            int displayOption = scanner.nextInt();

            if (displayOption == 1) {
                for (IKEA entity : entities) {
                    entity.displayFields();
                }
            } else if (displayOption == 2) {
                System.out.println("Enter the fields you want to display, separated by a comma:");
                String fieldsInput = scanner.nextLine();
                String[] fields = fieldsInput.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
                for (IKEA entity : entities) {
                    entity.displayFields(fields);
                }
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static List<IKEA> readFromCSV(String fileName) {
        List<IKEA> entities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
                IKEA entity = new IKEA();
                try {
                    entity.setId(Integer.parseInt(fields[0]));
                } catch (NumberFormatException e) {
                    
                }
                try {
                    entity.setItemId(Integer.parseInt(fields[1]));
                } catch (NumberFormatException e) {
                    
                }
                entity.setName(fields[2]);
                entity.setCategory(fields[3]);
                try {
                    entity.setPrice(Double.parseDouble(fields[4]));
                } catch (NumberFormatException e) {
                   
                }
                try {
                    entity.setOldPrice(Double.parseDouble(fields[5]));
                } catch (NumberFormatException e) {
                    
                }
                entity.setSellableOnline(fields[6].equals("Yes"));
                entity.setLink(fields[7]);
                entity.setOtherColors(fields[8]);
                entity.setShortDescription(fields[9]);
                entity.setDesigner(fields[10]);
                entities.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public static void exportToCSV(List<IKEA> entities, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName + ".csv");

            // write the headers
            writer.append("ID");
            writer.append(",");
            writer.append("Item ID");
            writer.append(",");
            writer.append("Name");
            writer.append(",");
            writer.append("Category");
            writer.append(",");
            writer.append("Price");
            writer.append(",");
            writer.append("Old_Price");
            writer.append(",");
            writer.append("Sellable_Online");
            writer.append(",");
            writer.append("Link");
            writer.append(",");
            writer.append("Other_Colors");
            writer.append(",");
            writer.append("Short_Description");
            writer.append(",");
            writer.append("Designer");
            writer.append(",");
            // other headers go here

            writer.append("\n");

            // write the data
            for (IKEA entity : entities) {
                writer.append(String.valueOf(entity.getId()));
                writer.append(",");
                writer.append(String.valueOf(entity.getItemId()));
                writer.append(",");
                writer.append(entity.getName());
                writer.append(",");
                writer.append(entity.getName());
                writer.append(",");
                writer.append(entity.getCategory());
                writer.append(",");
                writer.append(entity.getName());
                writer.append(",");
                writer.append(String.valueOf(entity.getPrice()));
                writer.append(",");
                writer.append(entity.getName());
                writer.append(",");
                writer.append(String.valueOf(entity.getOldPrice()));
                writer.append(",");
                writer.append(String.valueOf(entity.isSellableOnline()));
                writer.append(",");
                writer.append(String.valueOf(entity.getLink()));
                writer.append(",");
                writer.append(entity.getOtherColors());
                writer.append(",");
                writer.append(entity.getShortDescription());
                writer.append(",");
                writer.append(entity.getDesigner());
                writer.append(",");
                // other fields go here

                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
