package sbs13_lab3;

import java.util.List;
import javax.sql.DataSource;

public interface IBooksDAO {
    void setDataSource(DataSource ds);                          
    void insert(Books customer);                                
    void append(String Name, String Publisher);
    void deleteByName(String Name);                             
    void delete(String Name, String Publisher);                 
    void deleteAll();                                           
    void update(String newPublisher, String oldPublisher);
    List<Books> findByName(String Name);
    List<Books> select(String Name, String Publisher);
    List<Books> selectAll();
    
    void appendOnlyName(String Name);
    void deleteByPublisher(String Publisher);
    void updateName(String newName, String oldName);
    List<Books> findByPublisher(String Publisher);
    List<Books> selectByName(String Name);
    List<Books> selectByPublisher(String Publisher);
}
