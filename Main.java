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
                    // TODO export
                    
                }
            } else if (option == 5) {
                // TODO SEARCH ENTITIES
            }else if (option == 6) {
                System.out.println("Enter the name of the file:");
                //TODO: LIST COLUMN
            } else if (option == 7) {
                //TODO FILTER

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


}

