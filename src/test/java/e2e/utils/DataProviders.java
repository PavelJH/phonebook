package e2e.utils;

import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> newContact(){ // Бкжнт идти, пока не проверят все записи в методе
        List<Object[]> list = new ArrayList<>(); // переменная не где не использеться и тут пустой масив
        list.add(new Object[]{"Pavel", "Kelbas", "Best A"});// внизу написано в @TEST
        list.add(new Object[]{"Nina", "Sherif", "Best AAA"});// внизу написано в @TEST
        list.add(new Object[]{"Mart", "Golof", "Best B"});// внизу написано в @TEST
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> changeLastNameAndDescription() { // Бкжнт идти, пока не проверят все записи в методе
        List<Object[]> list = new ArrayList<>(); // переменная не где не использеться и тут пустой масив
        list.add(new Object[]{ "Kelbas", "Best A"});
        list.add(new Object[]{ "Sherif", "Best AAA"});
        list.add(new Object[]{ "Golof", "Best B"});
        return list.iterator();
    }
    //Чтение с ресурсов data
    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = reader.readLine();
        while (line != null) { //зацыклит
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1], split[2]});
            line = reader.readLine();//переопредилить переменную // снова читает, то что String line = reader.readLine(); // читает все лайны пока они будут
        }
        return list.iterator();
    } @DataProvider
    public Iterator<Object[]> newContactWithCSVHomeWork() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataHW.csv")));
        String line = reader.readLine();
        while (line != null) { //зацыклит
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1]});
            line = reader.readLine();//переопредилить переменную // снова читает, то что String line = reader.readLine(); // читает все лайны пока они будут
        }
        return list.iterator();
    }

}
