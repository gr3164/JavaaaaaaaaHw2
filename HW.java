
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 * HW
 */
public class HW {
    
    public static void main(String[] args) {
        bubbleSort();
        //filterString();
        // derivationOfGrades();
    }

    /**
     * Дана строка sql-запроса "select * from students where ".
     * Сформируйте часть WHERE этого запроса, используя StringBuilder.
     * Данные для фильтрации приведены ниже в виде json строки
     */
    public static void filterString() {
        JSONObject student = new JSONObject("{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}");
        String name = (String) student.get("name");
        String country = (String) student.get("country");
        String city = (String) student.get("city");
        System.out.println("select * from students where name = " + name + " and country = " + country + "and city = " + city);
    }

    /**
    * Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл
    */
    public static void bubbleSort() {
        Logger log = Logger.getLogger(HW.class.getName());
        FileHandler fh = null;

        try {
            fh = new FileHandler("log.txt");
            log.addHandler(fh);
            SimpleFormatter sFormat = new SimpleFormatter();
            fh.setFormatter(sFormat); 
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
     
        int[] arr = new int[5];
        for(int i=0; i < arr.length; i++){
            arr[i] = (int)randomNumber();
        }
        
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            } 
            log.info(Arrays.toString(arr)); 
        }
        System.out.println(Arrays.toString(arr));    
    }

    private static double randomNumber(){
        int max = 100;
        int min = 1;
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }

    /**
    * Дана json строка (можно сохранить в файл и читать из файла)
    * Написать метод(ы), который распарсит json и, используя StringBuilder,
    * создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет]
    */
    public static void derivationOfGrades() {
        JSONArray students = new JSONArray("[" +
                "{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]");
        for (int i = 0; i < students.length(); i++) {
            StringBuilder lastName = new StringBuilder((String) students.getJSONObject(i).get("фамилия"));
            StringBuilder grade = new StringBuilder((String) students.getJSONObject(i).get("оценка"));
            StringBuilder subject = new StringBuilder((String) students.getJSONObject(i).get("предмет"));
            System.out.println("Студент(ка) " + lastName + " получил(а) " + grade + " по предмету " + subject);
        }
    }

}