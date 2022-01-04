module com.mycompany.qlnhahangtieccuoi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.qlnhahangtieccuoi to javafx.fxml;
    exports com.mycompany.qlnhahangtieccuoi;
    exports com.mycompany.pojo;
}
