import java.util.HashMap;

public class MonthlyLines {
    String itemName;
    Boolean isExpense;
    Double quantity;
    Integer sumOfOne;


    //конструктор
    MonthlyLines (String itemName, Boolean isExpense, Double quantity, Integer sumOfOne){
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }


}
