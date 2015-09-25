package test;

import javax.persistence.Persistence;

public class SchemaBuilder {
    public static void main(String[] args) {
        Persistence.generateSchema("RestCrudPU",null);
    }
}
