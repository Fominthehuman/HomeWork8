package Account;

import java.io.*;
import java.util.Scanner;

public class FileController {

    public FileController() throws FileNotFoundException {

    }

    public File file = new File("Accounts.txt");

    public void isFileExistChecker() throws IOException {
        if (!file.exists()) {
            System.out.println("File not exists, creating file...");
            boolean created = file.createNewFile();
            startFileFiller();

        } else
            System.out.println("File found");
    }

    public void startFileFiller() throws IOException {
        FileWriter writer = new FileWriter("Accounts.txt");
        int a = 0; // Начальное значение диапазона - "от"
        int b = 10000; // Конечное значение диапазона - "до"
        int randomId;
        int randomAmount;
        for (int i = 0; i < 10; i++) {
            randomId = a + (int) (Math.random() * b);
            randomAmount = (i + 1) * b;
            writer.write(randomId + " " + randomAmount + "\n");
        }
        writer.flush();
    }

    public String findAccountInFile(int accountId) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] cols = line.split(" ");
                if (cols[0].equals(String.valueOf(accountId))) {
                    return line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccountInFile(int accountId, int finalAmount) throws FileNotFoundException, UnknownAccountException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String newLine = (accountId + " " + finalAmount);
            StringBuffer inputBuffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                if (line.contains(String.valueOf(accountId))) {
                    inputBuffer.append(newLine);
                    inputBuffer.append('\n');
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }

            }

            String inputStr = inputBuffer.toString();
            reader.close();
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public File getFileName() {
        return file;
    }


}
