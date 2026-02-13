public class Service {
    private final Database db; // Blank Final

    public Service(Database db) {
        this.db = db; // Assigned once!
    }

    public void switchDB(Database newDB) {
        // this.db = newDB; // COMPILER ERROR: Cannot reassign!
    }
}

void main() {
  DataBase db = new DataBase();
  new Service(db);
}
