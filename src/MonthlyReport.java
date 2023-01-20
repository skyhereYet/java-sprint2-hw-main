import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class MonthlyReport {
    String month;
    HashMap<String, ArrayList<MonthlyLines>> storageExpenseMonthly = new HashMap<>();
    HashMap<String, ArrayList<MonthlyLines>> storageIncomeMonthly = new HashMap<>();

    void printReport (HashMap<String, ArrayList<MonthlyLines>> storageExpenseMonthly, HashMap<String, ArrayList<MonthlyLines>> storageIncomeMonthly){
        this.storageExpenseMonthly = storageExpenseMonthly;
        this.storageIncomeMonthly = storageIncomeMonthly;
            MonthlyLines tempMonthlyLines = new MonthlyLines("Temp",false,0.0,0);
            //нужен список ключей
            String[] keyArray = storageExpenseMonthly.keySet().toString().substring(1).split(", ");
            String temp = keyArray[keyArray.length-1];
            keyArray[keyArray.length-1]=temp.substring(0,2);

            for (String numberMassive : keyArray) {
                System.out.println("--------------------------------------------------------------------------------");
                tempMonthlyLines = maxValue(storageExpenseMonthly.get(numberMassive), tempMonthlyLines);
                System.out.println("Отчет по месяцу - " + numberMassive);
                System.out.println(" - самые большие расходы на товар - " + tempMonthlyLines.itemName);
                System.out.println(" - стоимость товара - " + tempMonthlyLines.quantity*(double) tempMonthlyLines.sumOfOne);

                tempMonthlyLines = maxValue(storageIncomeMonthly.get(numberMassive), tempMonthlyLines);
                System.out.println(" - самый прибыльный товар - " +tempMonthlyLines.itemName);
                System.out.println(" - стоимость товара - " + tempMonthlyLines.quantity*(double) tempMonthlyLines.sumOfOne);
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println();
            }
        }


    MonthlyLines maxValue (ArrayList<MonthlyLines> storageExpenseIncomeMonthly, MonthlyLines tempMonthlyLines){
        Double maxValue = 0.0;

        for (MonthlyLines monthObject : storageExpenseIncomeMonthly){
            Double expense = monthObject.quantity*(double) monthObject.sumOfOne;
            if (maxValue< expense){
                maxValue = expense;
                tempMonthlyLines = monthObject;
            }
        }
        return tempMonthlyLines;
    }
}
