package sbs13_lab3;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class BooksDAO implements IBooksDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Books customer) { // Реализация вставки новой записи

        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO BOOKS (name, publisher) VALUES(?,?)",
                new Object[]{customer.getName(), customer.getPublisher()});
    }

    @Override
    public void append(String Name, String Publisher) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO BOOKS (name, publisher) VALUES(?,?)", new Object[]{Name, Publisher});
    }

    @Override
    public void deleteByName(String Name) {  // Реализация удаления записей по издателю
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM BOOKS WHERE Name LIKE ?", new Object[]{'%' + Name + '%'});
    }

    @Override
    public void delete(final String Name, final String Publisher) {  // Реализация удаления записей с указанными названию и издателю
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from BOOKS where Name= ? AND Publisher = ?", new Object[]{Name, Publisher});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from BOOKS");
    }

    @Override
    public void update(String newPublisher, String oldPublisher) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE BOOKS SET Publisher = ? WHERE Publisher = ?", new Object[]{newPublisher, oldPublisher});
    }

    @Override
    public List<Books> findByName(String Name) {  // Реализация поиска записей по названию
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM BOOKS WHERE Name LIKE ?";
        List<Books> books = select.query(sql, new Object[]{'%' + Name + '%'}, new BooksRowMapper());
        return books;
    }

    @Override
    public List<Books> select(String Name, String Publisher) {  // Реализация получения записей с заданными названием и издателем
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from BOOKS where Name = ? AND Publisher= ?",
                new Object[]{Name, Publisher}, new BooksRowMapper());
    }

    @Override
    public List<Books> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from BOOKS", new BooksRowMapper());
    }
    
    @Override
    public void appendOnlyName(String Name) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO BOOKS (name, publisher) VALUES(?,?)", new Object[]{Name, "????"});
    }
    
    @Override
    public void deleteByPublisher(String Publisher) {  // Реализация удаления записей по издателю
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM BOOKS WHERE Publisher LIKE ?", new Object[]{'%' + Publisher + '%'});
    }
    
    @Override
    public void updateName(String newName, String oldName) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE BOOKS SET Name = ? WHERE Name = ?", new Object[]{newName, oldName});
    }
    
    @Override
    public List<Books> findByPublisher(String Publisher) {  // Реализация поиска записей по названию
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM BOOKS WHERE Publisher LIKE ?";
        List<Books> books = select.query(sql, new Object[]{'%' + Publisher + '%'}, new BooksRowMapper());
        return books;
    }
    
    @Override
    public List<Books> selectByName(String Name) {  // Реализация получения записей с заданными названием и издателем
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from BOOKS where Name = ?",
                new Object[]{Name}, new BooksRowMapper());
    }
    
    @Override
    public List<Books> selectByPublisher(String Publisher) {  // Реализация получения записей с заданными названием и издателем
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from BOOKS where Publisher= ?",
                new Object[]{Publisher}, new BooksRowMapper());
    }
}
