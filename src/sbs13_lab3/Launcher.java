package sbs13_lab3;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            BooksDAO booksDAO = (BooksDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице книг 

            booksDAO.deleteAll(); // Удаление всех записей
            
            Books books = new Books("War and peace", "Эксмо"); // Создание нового объекта таблицы книг 
            booksDAO.insert(books); // Вставить новый объект (запись) в таблицу книг

            booksDAO.insert(new Books("Book2", "Publisher2")); // Вставить новый объект (запись) в таблицу книг
            booksDAO.insert(new Books("Book3", "Publisher3")); // Вставить новый объект (запись) в таблицу книг
            booksDAO.insert(new Books("BookXX", "PublisherXX")); // Вставить новый объект (запись) в таблицу книг

            System.out.println("Начальная БД:");
            List<Books> list = booksDAO.selectAll();
            for (Books myBooks : list) {
                System.out.println(myBooks.getName() + " " + myBooks.getPublisher());
            }
            System.out.println();
            
            booksDAO.deleteByName("ook2"); // Удаление записей по фрагменту названия
            booksDAO.deleteByPublisher("lisherXX");
            booksDAO.delete("Book3", "Publisher3"); // Удаление записи по названию и издателю

            
            
            System.out.println("Поиск по фрагменту названия - and p");
            List<Books> books_list = booksDAO.findByName("and p"); // Поиск записей по фрагменту названия
            if (books_list != null) {
                for (Object element : books_list) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();
            
            

            booksDAO.append("Book4", "Publisher4"); // Добавлние записей
            booksDAO.append("Book5", "Publisher5");
            booksDAO.append("Book6", "Publisher6");
            booksDAO.append("Book7", "Publisher7");
            booksDAO.appendOnlyName("Book????");

            booksDAO.update("Publisher100", "Publisher6"); // Изменение записей в таблице
            booksDAO.updateName("Book700", "Book7"); // Изменение записей в таблице

            System.out.println("БД после изменений:");
            list = booksDAO.selectAll();
            for (Books myBooks : list) {
                System.out.println(myBooks.getName() + " " + myBooks.getPublisher());
            }
            System.out.println();
            
            System.out.println("Поиск по фрагменту названия - her100");
            books_list = booksDAO.findByPublisher("her100"); // Поиск записей по фрагменту названия
            if (books_list != null) {
                for (Object element : books_list) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();

            System.out.println("Вывод записей с названием Book6 и издателем Publisher100:");

            list = booksDAO.select("Book6", "Publisher100");
            for (Books myBooks : list) {
                System.out.println(myBooks.getName() + " " + myBooks.getPublisher());
            }
            
            System.out.println("Вывод записей с названием Book700:");

            list = booksDAO.selectByName("Book700");
            for (Books myBooks : list) {
                System.out.println(myBooks.getName() + " " + myBooks.getPublisher());
            }
            
            System.out.println("Вывод записей с издателем Publisher5:");

            list = booksDAO.selectByPublisher("Publisher5");
            for (Books myBooks : list) {
                System.out.println(myBooks.getName() + " " + myBooks.getPublisher());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
    
}
