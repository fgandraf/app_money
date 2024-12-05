package infra.dao

import core.contracts.ITransactionDao
import core.entity.Transaction
import core.entity.account.BankAccount
import infra.config.HibernateUtil
import org.hibernate.Hibernate
import java.time.LocalDateTime

class TransactionDao : ITransactionDao {

    private val sessionFactory = HibernateUtil.getSessionFactory()

    override fun getAll() : List<Transaction> {
        val session = sessionFactory.openSession()
        session.beginTransaction()
        val criteriaBuilder = session.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Transaction::class.java)
        val root = criteriaQuery.from(Transaction::class.java)
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get<LocalDateTime>("date")))
        val transactions = session.createQuery(criteriaQuery).resultList
        transactions.forEach { transaction ->
            Hibernate.initialize(transaction.party)
            Hibernate.initialize(transaction.account)
            Hibernate.initialize(transaction.category)
            Hibernate.initialize(transaction.subcategory)
            Hibernate.initialize(transaction.tags)
        }
        session.transaction.commit()
        session.close()
        return transactions
    }

    override fun getAllByAccount(account: BankAccount) : List<Transaction> {
        val session = sessionFactory.openSession()
        session.beginTransaction()
        val criteriaBuilder = session.criteriaBuilder

        val criteriaQuery = criteriaBuilder.createQuery(Transaction::class.java)

        val root = criteriaQuery.from(Transaction::class.java)
        criteriaQuery.where(criteriaBuilder.equal(root.get<BankAccount>("account"), account))
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get<LocalDateTime>("date")))

        val transactions = session.createQuery(criteriaQuery).resultList
        transactions.forEach { transaction ->
            Hibernate.initialize(transaction.party)
            Hibernate.initialize(transaction.account)
            Hibernate.initialize(transaction.category)
            Hibernate.initialize(transaction.subcategory)
            Hibernate.initialize(transaction.tags)
        }
        session.transaction.commit()
        session.close()
        return transactions
    }



//    fun delete(group: Group) {
//        val session = sessionFactory.openSession()
//
//        session.beginTransaction()
//        session.remove(group)
//        session.transaction.commit()
//
//        session.close()
//    }
//
//
//
//    fun updateGroupPositions(accounts: List<Group>) {
//        accounts.forEach { group ->
//            val session = sessionFactory.openSession()
//            session.beginTransaction()
//            session.merge(group)
//            session.transaction.commit()
//            session.close()
//        }
//    }
//
//    fun update(group: Group) {
//        val session = sessionFactory.openSession()
//        session.beginTransaction()
//        session.merge(group)
//        session.transaction.commit()
//        session.close()
//    }
//
//    fun insert(group: Group) {
//        val session = sessionFactory.openSession()
//        session.beginTransaction()
//        session.persist(group)
//        session.transaction.commit()
//        session.close()
//    }

}