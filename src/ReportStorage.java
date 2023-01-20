import javax.swing.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportStorage {
    String year;
    HashMap<String, ArrayList<MonthlyLines>> storageExpenseMonthly = new HashMap<>();//isExpense = true
    HashMap<String, ArrayList<MonthlyLines>> storageIncomeMonthly = new HashMap<>();//isExpense = false
    HashMap<String, Double> storageExpenseYearly = new HashMap<>();//isExpense = true
    HashMap<String, Double> storageIncomeYearly = new HashMap<>();//isExpense = false

    void hashMapWriterMonth (String month, ArrayList<MonthlyLines> storageExpense, ArrayList<MonthlyLines> storageIncome){
        storageExpenseMonthly.put(month, storageExpense);
        storageIncomeMonthly.put(month, storageIncome);
    }

    void hashMapWriterExpenseYearly (String month, Double quantity){
        storageExpenseYearly.put(month, quantity);
    }
    void hashMapWriterIncomeYearly (String month, Double quantity){
        storageIncomeYearly.put(month, quantity);
    }

    void checker(){
        Boolean error = false;
        String[] keyArray = keyArrayFind(storageExpenseMonthly);
        HashMap<String, Double> storageExpenseMonthCheck = new HashMap<>();
        HashMap<String, Double> storageIncomeMonthCheck = new HashMap<>();
        storageExpenseMonthCheck = sumMonthExpenseIncome(storageExpenseMonthly, storageExpenseMonthCheck, keyArray);
        storageIncomeMonthCheck = sumMonthExpenseIncome(storageIncomeMonthly, storageIncomeMonthCheck, keyArray);

        for (String numberMassive : keyArray){
            Boolean valueCheckMonthExpense = (Double.compare(storageExpenseMonthCheck.get(numberMassive),storageExpenseYearly.get(numberMassive))) ==0;
            Boolean valueCheckMonthIncome = (Double.compare(storageIncomeMonthCheck.get(numberMassive), storageIncomeYearly.get(numberMassive))) ==0;

            if (valueCheckMonthExpense && valueCheckMonthIncome){
                error = false;
            } else {
                error = true;
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Обнаружена ошибка в месяце - "+numberMassive);
            }
        }
        if (error) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("В отчетах обнаружены ошибки");
            System.out.println("--------------------------------------------------------------------------------");
        } else {
            System.out.println("Сверка завершена успешно!");
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    String[] keyArrayFind(HashMap<String, ArrayList<MonthlyLines>> storageTempHash){
        String[] keyArray = storageTempHash.keySet().toString().substring(1).split(", ");
        String temp = keyArray[keyArray.length-1];
        keyArray[keyArray.length-1] = temp.substring(0,2);
        return keyArray;
    }

    HashMap<String, Double> sumMonthExpenseIncome (HashMap<String, ArrayList<MonthlyLines>> workHashMap,
                                                   HashMap<String, Double> sumMonthExpenseIncome, String[] keyArray){
        Double monthIncome = 0.0;
        Double monthExpense = 0.0;
        ArrayList<MonthlyLines> tempMonthlyLines = new ArrayList<>();
        for (String numberMassive : keyArray){
            tempMonthlyLines = workHashMap.get(numberMassive);
            for (MonthlyLines monthObject : tempMonthlyLines){
                monthExpense = monthExpense + monthObject.quantity*(double) monthObject.sumOfOne;
            }
            sumMonthExpenseIncome.put(String.valueOf(numberMassive), monthExpense);
        }
        return sumMonthExpenseIncome;
    }

    Boolean checkReportStorage (){
        if (storageExpenseMonthly.isEmpty()){
           return false;
        } else {
            return true;
        }

    }
}
