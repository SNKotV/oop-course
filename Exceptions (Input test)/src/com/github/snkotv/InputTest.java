package com.github.snkotv;

import java.util.*;
import java.io.*;

public class InputTest {

    public static void main(String args[]) {

        if( args.length == 0 ) {
            System.out.println("Нужен параметр вызова: имя файла");
            return;
        }
        if (args.length == 1) {
            System.out.println("Нужен параметр вызова: строка для поиска");
            return;
        }
        String thisLine;
        ArrayList list = new ArrayList();
        BufferedReader fin = null;
        String searchString = args[1];

        try {
            fin = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0])));
            while ((thisLine = fin.readLine()) != null) {
                System.out.println("==Введена строка: "+thisLine);
                list.add(thisLine);
            }
            Collections.sort(list);
            System.out.println("\nОтсортированный список строк:");
            Iterator iter = list.iterator();
            int index = 0;
            while( iter.hasNext() ) {
                String str = (String)iter.next();
                System.out.println(index + ": " + str);
                index++;
            }

            int searchStringIndex = Collections.binarySearch(list, searchString);
            if (searchStringIndex >= 0)
                System.out.println("\nНайдена строка: " + list.get(searchStringIndex) + "\nИндекс: " + searchStringIndex);
            else
            {
                System.out.println("Строка не найдена.\nНеобходимая позиция: " + ((-searchStringIndex) - 1));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + args[0]);
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода. Файл " + args[0]);
            System.out.println("Error: " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nСтрока не найдена: " + args[1]);
            System.out.println();
            System.out.println("Error: " + e);
        }  finally {
            if ( fin != null )
                try {
                    fin.close();
                } catch ( IOException ex ) {
                    System.out.println("Ошибка закрытия файла " + args[0]);
                    System.out.println("Error: " + ex);
                }
        }
    }
}



