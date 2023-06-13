
package model;

public class TaskFabrica {
    public static String H2 = "H2";

    public static String Postgre = "PostgreSQL";

    private TaskDAO fabricaDAO = null;

    public TaskDAO getFabDao(String shapeType) {
        if (shapeType.equalsIgnoreCase(H2)){
            fabricaDAO = new TaskDAOImpl();
        } else if (shapeType.equalsIgnoreCase(Postgre)){
            fabricaDAO = new TaskDAOPostgre();
        }
        return fabricaDAO;
    }
}


