import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

class Loader {
    String srcPath = "resources/";
    ArrayList<String> filesListMonthly = new ArrayList<>();
    ArrayList<String> filesListYearly = new ArrayList<>();


    void findFiles() {
        //поиск файлов в папке path
        filesListMonthly.clear();
        filesListYearly.clear();

        File folder = new File("resources/");
        for (File file : folder.listFiles()) {
            String fileName = file.getName();
            String[] fileNameSplit = fileName.split("\\.");
            if ((fileNameSplit[0].equals("m")) || (fileNameSplit[0].equals("M"))) {
                filesListMonthly.add(file.getName());
            } else if ((fileNameSplit[0].equals("y")) || (fileNameSplit[0].equals("Y"))) {
                filesListYearly.add(file.getName());
            }
        }
    }

    List<String> readFileContents(String path) {
        //получение списка из файла
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

    ReportStorage monthlyLoader(ReportStorage reportStorage) {
        //передача данных в reportStorage по месячным отчетам
        findFiles();

        for (String file : filesListMonthly) {
            String path = srcPath + file;
            List<String> readLinesFromFile = new ArrayList<>(readFileContents(path));
            ArrayList<MonthlyLines> storageExpense = new ArrayList<>();
            ArrayList<MonthlyLines> storageIncome = new ArrayList<>();

            for (int number = 1; number < readLinesFromFile.size(); number++) {
                String[] readLinesFromFileMassive = readLinesFromFile.get(number).split(",");
                String itemName = readLinesFromFileMassive[0];
                Boolean isExpense = Boolean.parseBoolean(readLinesFromFileMassive[1]);
                Double quantity = Double.parseDouble(readLinesFromFileMassive[2]);
                Integer sumOfOne = Integer.parseInt(readLinesFromFileMassive[3]);
                MonthlyLines monthlyLines = new MonthlyLines(itemName, isExpense, quantity, sumOfOne);

                if (isExpense) {
                    storageExpense.add(monthlyLines);
                } else {
                    storageIncome.add(monthlyLines);
                }
            }
            String month = file.substring(6, 8);
            reportStorage.hashMapWriterMonth(month, storageExpense, storageIncome);
        }
        return reportStorage;
    }

    ReportStorage yearlyReportLoader(ReportStorage reportStorage) {
        //передача данных в reportStorage по годовому отчету
        findFiles();

        for (String file : filesListYearly) {
            String path = srcPath + file;
            List<String> readLinesFromFile = new ArrayList<>(readFileContents(path));
            reportStorage.year = file.substring(2, 6);
            for (int number = 1; number < readLinesFromFile.size(); number++) {

                String[] readLinesFromFileMassive = readLinesFromFile.get(number).split(",");

                String month = readLinesFromFileMassive[0];
                Double quantity = Double.parseDouble(readLinesFromFileMassive[1]);
                Boolean isExpense = Boolean.parseBoolean(readLinesFromFileMassive[2]);

                if (isExpense) {
                    reportStorage.hashMapWriterExpenseYearly(month, quantity);
                } else {
                    reportStorage.hashMapWriterIncomeYearly(month, quantity);
                }
            }
        }
        return reportStorage;
    }
}
