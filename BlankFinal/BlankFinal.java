public record DataBase() {}
public class Service {
    private final DataBase db; // Blank Final

    public Service(DataBase db) {
        this.db = db; // Assigned once!
    }

    public void switchDB(DataBase newDB) {
        // this.db = newDB; // COMPILER ERROR: Cannot reassign!
    }
}


void main() {
  DataBase db = new DataBase();
  new Service(db);
}
