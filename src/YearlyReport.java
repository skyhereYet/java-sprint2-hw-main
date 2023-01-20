import java.util.ArrayList;
import java.util.HashMap;

class YearlyReport {
    HashMap<String, Double> storageExpenseYearly = new HashMap<>();//isExpense = true
    HashMap<String, Double> storageIncomeYearly = new HashMap<>();//isExpense = false
    String year;

    void printReport (ReportStorage reportStorage){
        this.storageExpenseYearly = reportStorage.storageExpenseYearly;
        this.storageIncomeYearly = reportStorage.storageIncomeYearly;
        this.year=reportStorage.year;
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Информация по году - " + year);
        System.out.println("Прибыль по месяцам:");
        profitReport(reportStorage);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Средний расход за все месяцы в году: " + averageReport(storageExpenseYearly));
        System.out.println("Средний расход за все месяцы в году: "+ averageReport(storageIncomeYearly));
        System.out.println("--------------------------------------------------------------------------------");
    }

    void profitReport(ReportStorage reportStorage){
        Double profit = 0.0;
        String[] keyArray = keyArrayFind(storageExpenseYearly);

        for (String numberMassive : keyArray){
            profit = storageIncomeYearly.get(numberMassive) - storageExpenseYearly.get(numberMassive);
            System.out.println(" - за месяц " + numberMassive + " прибыль составила: " + profit);
        }
    }

    Double averageReport (HashMap<String, Double> yearlyReportHash){
        Double average = 0.0;
        String[] keyArray = keyArrayFind(yearlyReportHash);

        for (String numberMassive : keyArray){
            average += yearlyReportHash.get(numberMassive);
        }
        return (average/ keyArray.length);
    }

    String[] keyArrayFind(HashMap<String, Double> storageTempHash){
        String[] keyArray = storageTempHash.keySet().toString().substring(1).split(", ");
        String temp = keyArray[keyArray.length-1];
        keyArray[keyArray.length-1] = temp.substring(0,2);
        return keyArray;
    }
}
