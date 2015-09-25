package db;

import entity.Person;
import interfaces.IPersonFacade;
import java.util.List;
import javax.persistence.EntityManager;

public class dbFacade implements IPersonFacade {

    private dbConnector connector = dbConnector.Instance();

    @Override
    public Person addPerson(Person p) {
        EntityManager em = connector.getEm();

        try {
            em.getTransaction().begin();

            em.persist(p);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return p;
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = connector.getEm();

        Person p = null;
        try {
            em.getTransaction().begin();

            p = em.find(Person.class, (long)id);

            em.remove(p);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return p;
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = connector.getEm();

        Person p = null;
        try {
            em.getTransaction().begin();

            p = em.find(Person.class, (long)id);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return p;
    }

    @Override
    public List<Person> getPersons() {
        EntityManager em = connector.getEm();

        List<Person> persons;
        try {
            em.getTransaction().begin();

            persons = em.createQuery("SELECT p FROM Person p").getResultList();

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return persons;

    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = connector.getEm();

        try {
            em.getTransaction().begin();

            em.merge(p);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return p;
    }

}
