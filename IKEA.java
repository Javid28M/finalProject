import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IKEA {
    private int id;
    private int itemId;
    private String name;
    private String category;
    private double price;
    private double oldPrice;
    private boolean sellableOnline;
    private String link;
    private String otherColors;
    private String shortDescription;
    private String designer;
    private String depth;
    private String height;

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public boolean isSellableOnline() {
        return sellableOnline;
    }

    public void setSellableOnline(boolean sellableOnline) {
        this.sellableOnline = sellableOnline;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOtherColors() {
        return otherColors;
    }

    public void setOtherColors(String otherColors) {
        this.otherColors = otherColors;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }



    public static void sortEntities(List<IKEA> entities, String field, String order) {
        entities.sort((a, b) -> {
            if (order.equals("asc")) {
                if (field.equals("id")) {
                    return Integer.compare(b.getId(), a.getId());
                }
                else if (field.equals("item_id")) {
                    return Integer.compare(b.getItemId(), a.getItemId());
                }
                else if (field.equals("name")) {
                    return a.getName().compareTo(b.getName());
                } else if (field.equals("category")) {
                    return a.getCategory().compareTo(b.getCategory());
                } else if (field.equals("price")) {
                    return Double.compare(a.getPrice(), b.getPrice());
                } else if (field.equals("oldPrice")) {
                    return Double.compare(a.getOldPrice(), b.getOldPrice());
                }
            } else if (order.equals("desc")) {
                if (field.equals("id")) {
                    return Integer.compare(b.getId(), a.getId());
                }
                else if (field.equals("item_id")) {
                    return Integer.compare(b.getItemId(), a.getItemId());
                }
                else if (field.equals("name")) {
                    return b.getName().compareTo(a.getName());
                } else if (field.equals("category")) {
                    return b.getCategory().compareTo(a.getCategory());
                } else if (field.equals("price")) {
                    return Double.compare(b.getPrice(), a.getPrice());
                } else if (field.equals("oldPrice")) {
                    return Double.compare(b.getOldPrice(), a.getOldPrice());
                }
            }
            return 0;
        });
    }

    public static void listEntities(List<IKEA> entities, String listType) {
        if (listType.equals("random")) {
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                int index = random.nextInt(entities.size());
                System.out.println(entities.get(index).toString());
            }
        } else if (listType.equals("top")) {
            for (int i = 1; i < 22; i++) {
                System.out.println(entities.get(i).toString());
            }
        } else if (listType.equals("bottom")) {
            entities.sort((e1, e2) -> Double.compare(e1.getPrice(), e2.getPrice()));
            for (int i = 0; i < 20; i++) {
                System.out.println(entities.get(i).toString());
            }
        }
    }
    public static void searchEntities(List<IKEA> entities, String field, String value) {
        for (IKEA entity : entities) {
            if (field.equals("id")) {
                if (entity.getId() == Integer.parseInt(value)) {
                    System.out.println(entity.toString());
                }
            }
            else if (field.equals("item_id")) {
                if (entity.getItemId() == Integer.parseInt(value)) {
                    System.out.println(entity.toString());
                }
            }
            if (field.equals("name")) {
                if (entity.getName().contains(value)) {
                    System.out.println(entity.toString());
                }
            } else if (field.equals("category")) {
                if (entity.getCategory().contains(value)) {
                    System.out.println(entity.toString());
                }
            } else if (field.equals("price")) {
                if (entity.getPrice() == Double.parseDouble(value)) {
                    System.out.println(entity.toString());
                }
            } else if (field.equals("oldPrice")) {
                if (entity.getOldPrice() == Double.parseDouble(value)) {
                    System.out.println(entity.toString());
                }
            }
        }
    }

    public static void listColumnNames(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "IKEA{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", sellableOnline=" + sellableOnline +
                ", link='" + link + '\'' +
                ", otherColors='" + otherColors + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", designer='" + designer + '\'' +
                '}';
    }
    public static List<IKEA> filterEntities(List<IKEA> entities, String field, String rule, String value) {
        List<IKEA> filteredEntities = new ArrayList<>();
        if (rule.equals("contains")) {
            if (field.equals("name")) {
                for (IKEA entity : entities) {
                    if (entity.getName().contains(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("category")) {
                for (IKEA entity : entities) {
                    if (entity.getCategory().contains(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("otherColors")) {
                for (IKEA entity : entities) {
                    if (entity.getOtherColors() != null && entity.getOtherColors().contains(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("shortDescription")) {
                for (IKEA entity : entities) {
                    if (entity.getShortDescription() != null && entity.getShortDescription().contains(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("link")) {
                for (IKEA entity : entities) {
                    if (entity.getLink() != null && entity.getLink().contains(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("designer")) {
                for (IKEA entity : entities) {
                    if (entity.getDesigner() != null && entity.getDesigner().contains(value)) {
                        filteredEntities.add(entity);
                    }
                }
            }
        } else if (rule.equals("eq")) {
            if (field.equals("price")) {
                for (IKEA entity : entities) {
                    if (entity.getPrice() == Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("oldPrice")) {
                for (IKEA entity : entities) {
                    if (entity.getOldPrice() == Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            }
        } else if (rule.equals("gt")) {
            if (field.equals("price")) {
                for (IKEA entity : entities) {
                    if (entity.getPrice() > Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("oldPrice")) {
                for (IKEA entity : entities) {
                    if (entity.getOldPrice() > Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            }
        } else if (rule.equals("lt")) {
            if (field.equals("price")) {
                for (IKEA entity : entities) {
                    if (entity.getPrice() < Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("oldPrice")) {
                for (IKEA entity : entities) {
                    if (entity.getOldPrice() < Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            }
        } else if (rule.equals("ge")) {
            if (field.equals("price")) {
                for (IKEA entity : entities) {
                    if (entity.getPrice() >= Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("oldPrice")) {
                for (IKEA entity : entities) {
                    if (entity.getOldPrice() >= Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            }
        } else if (rule.equals("le")) {
            if (field.equals("price")) {
                for (IKEA entity : entities) {
                    if (entity.getPrice() <= Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("oldPrice")) {
                for (IKEA entity : entities) {
                    if (entity.getOldPrice() <= Double.parseDouble(value)) {
                        filteredEntities.add(entity);
                    }
                }
            }
        } else if (rule.equals("bt")) {
            String[] values = value.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
            if (field.equals("price")) {
                for (IKEA entity : entities) {
                    if (entity.getPrice() >= Double.parseDouble(values[0]) && entity.getPrice() <= Double.parseDouble(values[1])) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("oldPrice")) {
                for (IKEA entity : entities) {
                    if (entity.getOldPrice() >= Double.parseDouble(values[0]) && entity.getOldPrice() <= Double.parseDouble(values[1])) {
                        filteredEntities.add(entity);
                    }
                }
            }
        } else if (rule.equals("null")) {
            if (field.equals("name")) {
                for (IKEA entity : entities) {
                    if (entity.getName() == null) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("category")) {
                for (IKEA entity : entities) {
                    if (entity.getCategory() == null) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("price")) {
                for (IKEA entity : entities) {
                    if (entity.price  == 0) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("oldPrice")) {
                for (IKEA entity : entities) {
                    if (entity.oldPrice == 0) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("sellableOnline")) {
                for (IKEA entity : entities) {
                    if (entity.isSellableOnline()) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("link")) {
                for (IKEA entity : entities) {
                    if (entity.getLink() == null) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("otherColors")) {
                for (IKEA entity : entities) {
                    if (entity.getOtherColors() == null) {
                        filteredEntities.add(entity);
                    }
                }
            } else if (field.equals("shortDescription")) {
                for (IKEA entity : entities) {
                    if (entity.getShortDescription() == null) {
                        filteredEntities.add(entity);
                    }
                }
            }
        }
        return filteredEntities;
    }

        public void displayFields() {
        System.out.println("Id: " + this.id);
        System.out.println("Item_Id: " + this.itemId);
        System.out.println("Name: " + this.name);
        System.out.println("Category: " + this.category);
        System.out.println("Price: " + this.price);
        System.out.println("Old Price: " + this.oldPrice);
        System.out.println("Sellable Online: " + this.sellableOnline);
        System.out.println("Link: " + this.link);
        System.out.println("Other Colors: " + this.otherColors);
        System.out.println("Short Description: " + this.shortDescription);
        System.out.println("Designer: " + this.designer);

    }
    public void displayFields(String[] fields) {
        for (String field : fields) {
            if (field.equals("id")) {
                System.out.println("Id: " + this.id);
            } else if (field.equals("item_id")) {
                System.out.println("Item_id: " + this.itemId);
            }
            else if (field.equals("name")) {
                System.out.println("Name: " + this.name);
            } else if (field.equals("category")) {
                System.out.println("Category: " + this.category);
            } else if (field.equals("price")) {
                System.out.println("Price: " + this.price);
            } else if (field.equals("oldPrice")) {
                System.out.println("Old Price: " + this.oldPrice);
            } else if (field.equals("sellableOnline")) {
                System.out.println("Sellable Online: " + this.sellableOnline);
            } else if (field.equals("link")) {
                System.out.println("Link: " + this.link);
            } else if (field.equals("otherColors")) {
                System.out.println("Other Colors: " + this.otherColors);
            } else if (field.equals("shortDescription")) {
                System.out.println("Short Description: " + this.shortDescription);
            }
            else if (field.equals("designer")) {
                System.out.println("Designer: " + this.designer);
            }
            else if (field.equals("height")) {
                System.out.println("Height: " + this.height);
            }
        }
    }

}
