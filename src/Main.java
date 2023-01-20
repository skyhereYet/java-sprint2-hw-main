import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loader loader = new Loader();
        ReportStorage reportStorage = new ReportStorage();
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        while (true) {
            printMenu(); //вывод меню

            int numberMenu = scanner.nextInt();
            if (numberMenu == 1) {
                reportStorage = loader.monthlyLoader(reportStorage);
                System.out.println("***Загрузка месячных отчётов выполнена***");

            } else if (numberMenu == 2) {
                reportStorage = loader.yearlyReportLoader(reportStorage);
                System.out.println("***Загрузка годовых отчетов выполнена***");

            } else if (numberMenu == 3) {
                Boolean checkReportStorage = reportStorage.checkReportStorage();
                if (checkReportStorage){
                    System.out.println("***Выполняется сверка отчётов***");
                    reportStorage.checker();
                } else {
                    System.out.println("***Отчеты не загружены, выполнение команды невозможно***");
                }

            } else if (numberMenu == 4) {
                Boolean checkReportStorage = reportStorage.checkReportStorage();
                if (checkReportStorage){
                    System.out.println("***Вывод информации о всех месячных отчётах***");
                    monthlyReport.printReport(reportStorage.storageExpenseMonthly, reportStorage.storageIncomeMonthly);
                } else {
                    System.out.println("***Отчеты не загружены, выполнение команды невозможно***");
                }

            } else if (numberMenu == 5) {
                Boolean checkReportStorage = reportStorage.checkReportStorage();
                if (checkReportStorage){
                    System.out.println("***Вывод информации о годовом отчёте***");
                    yearlyReport.printReport(reportStorage);
            } else {
                System.out.println("***Отчеты не загружены, выполнение команды невозможно***");
            }

            } else if (numberMenu == 0) {
                //Выход
                System.out.println("Пока!");
                scanner.close();
                return;
            } else {
                System.out.println("Такой команды нет");
            }
        }
    }

    static void printMenu() {
        // Вывод доступных команд
        System.out.println("Введите команду");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из приложения");
    }


}

