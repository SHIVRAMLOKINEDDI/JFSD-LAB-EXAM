package com.klef.jfsd.exam.hiber;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDemo {

    public static void main(String[] args) {
        // Get a new session from Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Start a transaction
        Transaction transaction = null;

        try {
            // Start the transaction
            transaction = session.beginTransaction();

            // HQL update query using positional parameters
            String hql = "UPDATE Department SET deptName = ?, location = ? WHERE deptId = ?";

            // Create the query and set parameters
            int result = session.createQuery(hql)
                    .setParameter(0, "Computer Science")
                    .setParameter(1, "Building A")
                    .setParameter(2, 1) // Assuming department ID 1 is being updated
                    .executeUpdate();

            // Commit the transaction
            transaction.commit();

            // Output the result
            System.out.println("Rows affected: " + result);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Shutdown Hibernate
        HibernateUtil.shutdown();
    }
}
